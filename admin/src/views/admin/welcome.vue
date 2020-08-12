<template>
    <div>
        <h4>欢迎使用</h4>
    </div>
</template>

<script>
    export default {
        mounted:function(){
            this.$parent.activeSidebar("welcome-sidebar")
        },
        name: 'welcome',
        methods:{
        }
    }
</script>