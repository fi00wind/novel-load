<template>
    <div>
            <mt-button @click="back()" icon="back"  style="width: 30%">
            </mt-button>
        <h1> {{ article.title }}</h1>
        <div class="content" v-html="article.content" >
        </div>
        <div class="operate">
            <mt-button icon="back" class="operate-back" @click="back()"></mt-button>
<!--            <mt-button icon="success" class="operate-success" @click="hasRead()"></mt-button>-->
            <mt-button  class="operate-next" @click="next()">&gt;</mt-button>
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
                this.$axios.get("/novel/service/article/read/"+this.id)
            },
            back() {
                this.$router.push('/book/'+this.article.bookId)
            },
            next() {
                this.hasRead()
                this.$axios.get("/novel/service/article/next/",{
                    params:{
                        id:this.article.bookId,
                        mark:this.article.mark+1
                    }
                }).then(res=>{
                    if (res.status == 200) {
                        let data = res.data
                        console.log(data)
                        if (data.code == '0000') {
                            this.article = data.data
                            this.backTop()
                            this.$router.push('/article/'+data.id)
                        }else {
                            this.back()
                        }
                    }
                })
            },
            backTop () {
                document.body.scrollTop = 0
                document.documentElement.scrollTop = 0
            },

        }//method
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
    .operate-next{
        width: 50%;
    }
    .content {
        font-size: 1.3em;
    }
</style>