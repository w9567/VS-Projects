/**
 * 请求网站数据
 * 将数据保存本地文件
 */
//不同协议引用不同模块，http https
const http = require('http')
const fs = require('fs')
const cheerio = require('cheerio') //可将一段html 转换成可使用jq的写法(非node内置模块需要npm 安装)
let url = 'http://www.xiongmaoyouxuan.com' //text/html
let json = 'http://nodejs.org/dist/index.json'//application/json
http.get(url,(res)=>{
//安全判断
const {statusCode} = res //状态码
const contentType = res.headers['content-type'] //数据类型
 let err =null
  if(statusCode !==200){
    err = new Error('请求状态错误'+statusCode)
  } else if(!/^text\/html/.test(contentType)) {
    err = new Error('请求类型错误'+contentType)
  }
if (err){
  console.log(err)
  res.resume()//清空缓存
  return false
}


//数据处理

  //数据分段，只要接受到数据就会触发data事件， chunk 每次接受的数据片段
  let rawData = ''
  res.on('data',(chunk)=>{
      // console.log( chunk.toString('utf8'));
      rawData += chunk.toString('utf-8')//使用utf-8的格式转码
     
  })
  //数据流传输完毕
  res.on('end',()=>{
    //将请求的数据保存到本地
      let $ = cheerio.load(rawData)//使用jq的写法
        $('img').each((index,item)=>{
          saveImg($(item).attr('src'),index+'wilteMe.png')
        })
      //保存爬取的网页信息
      fs.writeFile('./xiongmaoyouxuan.html',rawData,(err)=>{
        if(err){
          console.log('保存失败');
        } else {
          console.log('保存成功');
        }
      })
    console.log('数据传输完毕');

  })
}).on('error',(err)=>{
  console.log('请求错误');
})
//保存图片到本地
function saveImg(url,path) {
  console.log(url,path)
    try{
      http.get(url,function (req,res) {
        var imgData = '';
        req.setEncoding('binary');
        req.on('data',function (chunk) {
            imgData += chunk;
        })
        req.on('end',function () {
            fs.writeFile(path,imgData,'binary',function (err) {
                console.log('保存图片成功'+path)
            })
        })
    })
    }
    catch(err){

    }

}
