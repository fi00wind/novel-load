const webpack = require("webpack")
let devServer = null
if (process.env.NODE_ENV === 'development') {
    devServer =  {
        port: 8171,
        disableHostCheck: true,
        proxy: {
            '/novel/service/': {
                target: 'http://localhost:8101/',
                changeOrigin: true // target是域名的话，需要这个参数，
            }
        }
    }
} else if (process.env.NODE_ENV === 'production') {
    devServer = {
        port: 8101,
        disableHostCheck: true
    }
}

module.exports = {
    outputDir: '../src/main/resources/static',
    publicPath: '/novel/service',
    devServer: devServer,
    configureWebpack: {

    },
}