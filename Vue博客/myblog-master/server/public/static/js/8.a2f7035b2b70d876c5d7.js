webpackJsonp([8],{OGqi:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=n("4ZRF"),o=n("WheD"),c={data:function(){return{tags:[]}},components:{Header:a.a,Footer:o.a},mounted:function(){var t=this;this.axios.get("/api/admin/getTagAll").then(function(e){t.tags=e.data}).catch(function(t){console.log(t)})},methods:{selectBytag:function(t){var e=this;this.axios.get("/api/admin/getArticleById",{params:{id:t}}).then(function(t){e.$router.push({path:"/",query:{articleList:t.data}})}).catch(function(t){console.log(t)}),console.log(t)}}},i={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("section",[n("Header"),t._v(" "),n("section",{staticClass:"tags"},t._l(t.tags,function(e){return n("el-tag",{key:e.id,on:{click:function(n){return t.selectBytag(e.id)}}},[t._v(t._s(e.name))])}),1),t._v(" "),n("Footer")],1)},staticRenderFns:[]};var s=n("VU/8")(c,i,!1,function(t){n("U8Nf")},"data-v-3acb2c1c",null);e.default=s.exports},U8Nf:function(t,e){}});
//# sourceMappingURL=8.a2f7035b2b70d876c5d7.js.map