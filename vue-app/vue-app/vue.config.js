/*const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '/dist/',
});*/

const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '',

  // Webpack 설정 확장
  chainWebpack: config => {
    // HTML 메타 태그에 charset=UTF-8 추가
    config.plugin('html').tap(args => {
      args[0].meta = { charset: 'UTF-8' };
      return args;
    });
  },


  // DevServer 옵션 (개발 서버 실행 시)
  devServer: {
    headers: {
      'Content-Type': 'text/html; charset=UTF-8',
    },
  },
});
