<template>
    <div>
        <div class="book-top">
            <router-link to="/" class="book-left" >
                <mt-button icon="back" height="50" width="50" type="primary">
                </mt-button>
            </router-link>
            <mt-button icon="more"  @click="loadLatest()" class="book-right" type="primary" height="50" width="50">
<!--                <img src="../static/img/refresh-white.png"  >-->
            </mt-button>
        </div>
        <div>
        <mt-cell
                v-for="item in articles"
                :key="item.id"
                :title="item.title"
                :to="'/article/'+item.id"
                is-link>
            <span v-show="item.see" style="color: green" class="mint-button-icon"><i class="mintui mintui-success"/></span>
        </mt-cell>
        </div>
    </div>
</template>

<script>
    import { Indicator } from 'mint-ui';
    export default {
        name: "Book",
        data() {
            return{
                id:this.$route.params.id,
                book:{},
                articles:[]
            }
        },
        mounted() {
            this.loadArticle()
        },
        methods:{
            loadLatest() {
                Indicator.open();
                this.$axios.get("/novel/service/book/load/"+this.id).then(res=>{
                    if (res.status == 200) {
                        this.loadArticle();
                    }
                }).finally(()=>{
                    Indicator.close()
                })
            },
            loadArticle() {
                this.$axios.get("/novel/service/article/list/"+this.id).then(res=>{
                    if (res.status == 200) {
                        this.articles = res.data;
                    }
                })
            }
        }
    }
</script>

<style scoped>
.book-top{
    position: relative;
    top:0px;
    margin-bottom: 10px;
}
    .book-left {
        position: relative;
        left: 0px;
    }
    .book-right {
        position: absolute;
        right: 0px;
    }
</style>