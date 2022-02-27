// 新闻来源
let source_name = "网易新闻";
// 编码格式
let myEncoding = "utf-8";
// 主页URL
let seedURL = 'https://motorcycle.sh.cn/portal.php';
let myURL = "";
// Jquery全局对象
let seedURL_format = "$('*')";
// 正则表达式
let url_reg = /article/;

// 导入所需模块
let fs = require('fs');				// 文件库, 最后的新闻对象存入JSON文件中
let myRequest = require('request'); // 对网站发出请求
let myCheerio = require('cheerio');	// 对请求得到的html代码进行解析为Jquery对象
let https = require('https');		// 对网站发出https请求
let myIconv = require('iconv-lite');// 对网站进行uft8编码
let superagent = require('superagent');	// 根据url得到网站的html代码
require('date-utils');				// 日期操作

// 防止网站屏蔽我们的爬虫, 详细见下方headers
let headers = {
    'user-agent': "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36"
};

// request模块异步fetch url
function request(url, callback) {
    let options = {
        url: url,
        encoding: null,
        headers: headers,
        // 响应时间
        timeout: 10000
    }
    myRequest(options, callback);
}

request(seedURL, function(err, res, body) {
    // 回调函数 err为错误信息, 无错误则为null, res为回调函数得到的结果
    // 用iconv转换编码, 存入html中
    let html = myIconv.decode(body, myEncoding);
    // 对得到的html代码进行解析
    let $ = myCheerio.load(html, {decodeEntities: true});
    let seedurl_news;
    try {
        seedurl_news = eval(seedURL_format);
    } catch(e) {console.log('url列表所处的html模块识别出错' + e)};
    seedurl_news.each(function(i, e) {
        try {
            // 提取出网站的所有href链接并做判断, 无则跳过到下一次循环
            let href = "";
            href = $(e).attr("href");
            if (typeof href == "undefined") {
                return;
            }
            myURL = href;
            // 对得到的链接进行规整
            if (myURL == "https://jubao.163.com/") return;
            if (href.toLowerCase().indexOf('https://') >= 0 || href.toLowerCase().indexOf('https://') >= 0) myURL = href;
            else if (href.startsWith('//')) myURL = 'https:' + href;
            else myURL = seedURL.substr(0, seedURL.lastIndexOf('/') + 1) + href;
        }  catch (e) {console.log('识别种子页面中的新闻链接出错：' + e)}
        // 用正则表达式对链接进行筛选, 符合条件的, 即新闻页面, 则进行处理.
        if (!url_reg.test(myURL) || myURL == "https://jubao.163.com/") return;
        newsGet(myURL);
        })
    })



function newsGet(myURL) {       // 读取新闻页面
    // superagent.get方法会根据url得到网站的所有html代码, 存储在end时间的res参数中.
    superagent.get(myURL).end((err, res) => {
        // err中存储错误, res中为html代码
        if (err) {
            console.log("热点新闻抓取失败-${err}");
        } else {
            console.log("爬取新闻成功!");
            if (myURL == "https://jubao.163.com/") return;
            getHotNews(res, myURL);
        }
    })
}

// 提取html代码中所需信息存入fetch对象中并输出为json文件
function getHotNews(res, myURL) {
    let $ = myCheerio.load(res.text, { decodeEntities: true });
    // fetch为单条新闻对象. 详细见fetches解释
    let fetch = {};
    fetch.title = "";
    fetch.content = "";
    fetch.keywords = "";
    fetch.publish_date = "";
    fetch.author = "";
    fetch.desc = "";
    fetch.source_name = source_name;
    fetch.source_encoding = myEncoding;
    fetch.crawltime = new Date;
    fetch.url = myURL;

    fetch.title = $('title').eq(0).text();
    if (fetch.title == "") fetch.title = source_name;

    fetch.content = $(".post_body").eq(0).text().replace(/\s*/g,"");

    fetch.keywords = $('meta[name=\"keywords\"]').eq(0).attr("content");

    fetch.publish_date = $('.post_info').eq(0).text().replace(/\s*/g,"").substr(0, 10);

    fetch.author = $('.post_author').eq(0).text().replace(/\s*/g,"");

    fetch.desc = $('meta[name=\"description\"]').eq(0).attr("content");

    let filename = source_name + "_" + (new Date()).toFormat("YYYY-MM-DD") +
    "_" + myURL.slice(myURL.lastIndexOf('/') + 1, myURL.lastIndexOf('.')) +".json";
    // 输出为json文件
    fs.writeFileSync(filename, JSON.stringify(fetch));
};

