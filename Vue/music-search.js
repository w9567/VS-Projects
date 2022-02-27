var app =new Vue({
    el:'#app',
    data:{
        query:'',
        musicList:[],
        musicUrl:"",
        picUrl:"",
        hotComments:""
    },
    methods:{
        searchmusic:function(){
            var that=this;
            axios.get("https://autumnfish.cn/search?keywords="+this.query)
            .then(function(response){
                // console.log(response);
                that.musicList=response.data.result.songs;
            },function(err){
                
            })

        },
        playMusic:function (musicId) {
           var _that=this;
        //    console.log(musicId);
        //歌曲播放
           axios.get("https://autumnfish.cn/song/url?id="+musicId)
           .then(function(response){
            // console.log(response);
            _that.musicUrl=response.data.data.url;
            // console.log(response.data.data[0].url);
        },function(err){
            
        }) 
        //g歌曲详情获取
            axios.get("https://autumnfish.cn/song/detail?ids="+musicId)
            .then(function(response){
                _that.picUrl=response.data.songs[0].al.picUrl;
                console.log(response.data.songs[0].al.picUrl);
            },function(err){

            })

        //歌曲评论获取
            axios.get("https://autumnfish.cn/comment/hot?type=0&id="+musicId)
            .then(function(response){
                console.log(response.data.hotComments);
                _that.hotComments=response.data.hotComments;
            },function(err){

            })
        }

    }

})