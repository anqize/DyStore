import request from "../utils/request";

//登录
export const LoginSubmitApi = (user) => request.post('/login',user);

//注册
export const SignupSubmitApi = (user) => request.post('/signup',user);

//加载商品信息
export const PorductMsgApi = (head, size) => request.get(`/shopping?head=${head}&size=${size}`);

export const PorductCountApi = () => request.get('/products');

export const PorductApi = (id) => request.get(`/product?id=${id}`);

//退出登录
export const LogoutApi = (user) => request.post('/logout',user);

//加载购物车信息
export const CartApi = () => request.get('/cart');

//删除购物车中的商品
export const deleteApi = (id) => request.delete(`/cart?ids=${id}`);

//加入购物车
export const addProductApi = (product) => request.put('/cart',product);