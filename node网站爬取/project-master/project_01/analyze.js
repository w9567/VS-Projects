const cheerio=require('cheerio');
const fs=require('fs');
function findImg(dom,Callback){
	
	let $=cheerio.load(dom);
	$('img').each(function(i,elem){
		
		let imgSrc=$(this).attr('src');
	       Callback(imgSrc,i);
	});
	
}
module.exports.findImg=findImg;