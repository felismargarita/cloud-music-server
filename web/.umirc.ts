import { defineConfig } from 'umi';

export default defineConfig({
  nodeModulesTransform: {
    type: 'none',
  },
  proxy:{
    '/api': {
      target: 'http://localhost:9001',
      pathRewrite: { '^/api': '' },
      changeOrigin: true
    }
  },
  routes: [
    { path: '/', component: '@/pages/index' },
  ],
  fastRefresh: {},
});
