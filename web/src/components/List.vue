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

        <div class="add">
            <img src="../static/img/add.png" slot="icon">
        </div>
    </div>
</template>

<script>
    export default {
        name: "List",
        data() {
            return {
                books:[]
            }
        },
        mounted() {
            this.$axios.get("/novel/service/book/list").then(res=>{
                console.log(res)
                if (res.status == 200) {
                    this.books = res.data;
                }
            })
        }
    }
</script>

<style scoped>
.add{
    position: absolute;
    right: 0px;
    bottom: 0px;
}
</style>