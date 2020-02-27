<template>
    <div>
            <mt-button @click="back()" icon="back"  style="width: 30%">
            </mt-button>
        <h1> {{ article.title }}</h1>
        <div v-html="article.content" >
        </div>
        <div class="operate">
            <mt-button icon="back" class="operate-back" @click="back()"></mt-button>
            <mt-button icon="success" class="operate-success" @click="hasRead()"></mt-button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Article",
        data() {
            return{
                id:this.$route.params.id,
                article:{
                    title:'',
                    content:''
                }
            }
        },
        mounted() {
            this.$axios.get("/novel/service/article/"+this.id).then(res=>{
                if (res.status == 200) {
                    this.article = res.data;
                }
            })
        },
        methods:{
            hasRead() {
                this.$axios.get("/novel/service/article/read/"+this.id).then(res=>{
                    if (res.status == 200) {
                        this.back()
                    }
                })
            },
            back() {
                this.$router.go(-1)
            },
        }
    }
</script>

<style scoped>
.operate{
    float: left;
    width: 100%;
}
    .operate-back{
        width: 50%;
    }
    .operate-success{
        width: 50%;
        background-color: darkseagreen;
    }
</style>