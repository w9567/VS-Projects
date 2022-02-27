//引入express
const { response } = require('express');
const  express =require('express');
const { request } = require('http');

//引入数据库
const mysql=require ("mysql");


//创建应用对象
const app=express();

//创建路由规则
app.get('/server',(request,response)=>{
    // response.send('hello express');
    //设置响应头、设置允许跨越
    response.setHeader('Access-Control-Allow-Origin','*');
    //设置响应体
    response.send('hello ajax');

});
app.post('/server',(request,response)=>{
    // response.send('hello express');
    //设置响应头、设置允许跨越
    response.setHeader('Access-Control-Allow-Origin','*');


    //要自适应响应头，这个得改，而且得将上头的POST更改为all
    //设置响应体、设置允许跨越
    response.setHeader('Access-Control-Allow-Headers','*');
    
    
    
    
    
    
    //设置响应体
    response.send('hello ajax POST');

});
app.post('/server-data',(request,response)=>{
    // response.send('hello express');
    //设置响应头、设置允许跨越
    response.setHeader('Access-Control-Allow-Origin','*');
    //要自适应响应头，这个得改，而且得将上头的POST更改为all
    //设置响应体、设置允许跨越
    response.setHeader('Access-Control-Allow-Headers','*');

    //设置响应体
    // response.send('hello ajax POST');
    const data={
        name:'username'
    };
    let str=JSON.stringify(data);
    response.send(str);
});
app.post('/delay',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.setHeader('Access-Control-Allow-Headers','*');
    setTimeout(() => {
        response.send('网络异常');
    }, 3000);
    
});
app.post('/off',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.setHeader('Access-Control-Allow-Headers','*');
    setTimeout(() => {
        response.send('取消请求');
    }, 3000);
    
});
app.all('/jquery-request',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.setHeader('Access-Control-Allow-Headers','*');
    const data={
        name:'jquery-request-1'
    }
    response.send(JSON.stringify(data));
    
    
});
app.all('/fetch-server',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.setHeader('Access-Control-Allow-Headers','*');
    const data={
        name:'fetch-server'
    }
    response.send(JSON.stringify(data));
    
    
});
app.get('/check-server',(request,response)=>{
    const data={
        exist:1,
        msg:'用户名已存在'

    }
    let str=JSON.stringify(data);
    response.send(`handle(${str})`);
});
app.get('/CORS-server',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.send('CORS跨域请求');
});
//sql-server
app.post('/sql-server',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.setHeader('Access-Control-Allow-Headers','*');
    let sql="select * from user?";
    db.query(sql,(err,result)=>{
        if(err){
            console.log(err);
        }else{
            console.log(result);
            response.send(JSON.stringify(result));
        }
    })
});
//创建数据库连接
const db =mysql.createConnection({
    host:"localhost",
    user:"root",
    password:"123456",
    database:"20210731test"
})
db.connect((err)=>{
    if(err) throw err;
    console.log("数据库连接成功--");
})

//监听窗口启动服务
app.listen(8001, ()=>{
    console.log("服务已启动，8000端口监听中-----");
})