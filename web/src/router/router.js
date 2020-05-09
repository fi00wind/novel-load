import Vue from 'vue'
import Router from 'vue-router'
// import Test from '../components/Test'
import Article from '../components/Article'
import List from '../components/List'
import Book from '../components/Book'

Vue.use(Router)


const router = new Router({
    base:'/web/',
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'List',
            component: List
        },
        {
            path: '/article/:id',
            name: 'Article',
            component: Article
        },
        {
            path: '/book/:id',
            name: 'Book',
            component: Book
        }
    ]
})

export default router;