<template>
  <div class="main">
     <p v-bind:class="{ active: isActive, 'text-danger': hasError}">我是一条小青龙</p>
     <button @click="trun">切换</button>
     <comment/>
     <p>二</p>
     <child @getchild='yeschild'></child>
     <p >{{message}}</p>
  </div>
</template>

<script>
import Child from './child.vue';
import Comment from './Comment.vue';

export default {
    
  components: { Comment,Child },
    
    Childname:'Main',
    data(){
        return{
            isActive: true,
            hasError: false,
            message:''
        }
    },
    methods:{
        trun(){
            this.isActive=!this.isActive;
            this.hasError=!this.hasError;
            
        },
        yeschild(value){
            this.message='请稍后...'
            setTimeout(() => {
                this.message=value
            }, 2000);
        }
    }
}
</script>

<style scoped>
.active{
    color: red;
}
.text-danger{
    color: blue;
}

</style>