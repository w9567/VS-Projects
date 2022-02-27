<template>
  <div class="child">{{title}}</div>
</template>

<script>
export default {
    name:'Child',
    data:function(){
        return {
            title:'123'
        }
    },
    methods:{

    },
    mounted:function(){
        this.$emit('getchild',this.title)
    }
}
</script>

<style scoped>

</style>