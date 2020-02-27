<template>
    <div>
        <mt-cell
                v-for="item in books"
                :key="item.id"
                :title="item.nick"
                :to="'/book/'+item.id"
                is-link
        >
        </mt-cell>

        <div  class="mint-palette-button add" @click="showAdd= true">
            <div class="mint-sub-button-container">
                <div class="my-icon-button palette-button-23-sub-0"></div>
            </div>
            <div class="mint-main-button">
                +
            </div>
        </div>

        <mt-popup
                class="add-content"
                v-model="showAdd"
                >
            <div>
                <mt-field class="add-field" label="名字" v-model="name"></mt-field>
                <mt-field class="add-field" label="简写" v-model="nick"></mt-field>
                <mt-field class="add-field" label="来源" v-model="source"></mt-field>
                <mt-field class="add-field" label="地址" v-model="url"></mt-field>
                <mt-field class="add-field" label="最新章节" v-model="lastTitle"></mt-field>
                <mt-button style="width: 100%" type="primary" @click="add()">提交</mt-button>
            </div>
        </mt-popup>
    </div>
</template>

<script>
    import { Indicator } from 'mint-ui';
    export default {
        name: "List",
        data() {
            return {
                books: [],
                showAdd: false,
                name: '',
                nick:'',
                source:'',
                url:'',
                lastTitle:''
            }
        },
        mounted() {
            this.list()
        },
        methods:{
            add() {
                Indicator.open();
                this.$axios.post("/novel/service/book/add",{
                        name:this.name,
                        nick:this.nick,
                        source:this.source,
                        url:this.url,
                        lastTitle:this.lastTitle
                }).then(res => {
                    console.log(res)
                    if (res.status == 200) {
                        this.list()
                    }
                }).finally(()=>{
                    this.showAdd=false
                    Indicator.close();
                })
            },
            list() {
                this.$axios.get("/novel/service/book/list").then(res => {
                    console.log(res)
                    if (res.status == 200) {
                        this.books = res.data;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .add {
        position: absolute;
        left: 40%;
        bottom: 0px;
        color: white;
        width: 100px;
        height: 100px;
        line-height: 90px;

    }
    .mint-main-button {
        background-color: #26a2ff !important;
        font-size: 4em !important;
    }
    .add-content{
        width: 80%;
    }
    .add-field{
        height: 50px;
    }
</style>