//package com.slcp.devops.controller;
//
//import com.slcp.devops.pojo.QqUser;
//import com.slcp.devops.service.QqUserService;
//import com.qq.connect.QQConnectException;
//import com.qq.connect.api.OpenID;
//import com.qq.connect.api.qzone.UserInfo;
//import com.qq.connect.javabeans.AccessToken;
//import com.qq.connect.javabeans.qzone.UserInfoBean;
//import com.qq.connect.oauth.Oauth;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.util.Date;
//
//
///* * @author: Slcp
// * @date: 2020/11/28 22:43
// * @code: 一生的挚爱
// * @description: qq登录控制器
// */
//
//@Controller
//public class QQController {
//
//    @Autowired
//    private QqUserService QqUserService;
//
//    /**
//     * 根目录请求
//     *
//     * @return
//     *//*
//    @RequestMapping("/")
//    public ModelAndView root() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("index");
//        mav.addObject("title", "QQ登录测试");
//        return mav;
//    }*/
//
//    //qq注销
//    @RequestMapping("/QQLoginOut")
//    public String QQLoginOut(HttpSession session) {
//        session.removeAttribute("QQNickname");
//        session.removeAttribute("QQavatar");
//     /*   session.removeAttribute("demo_access_token");
//        session.removeAttribute("demo_token_expirein");
//        session.removeAttribute("demo_openid");*/
//        return "redirect:/index";
//    }
//
//
//    /**
//     * qq登录 带参数发送服务器
//     *
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/qqLogin")
//    public void qqLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setContentType("text/html;charset=utf-8");
//        try {
//            //底层是重定向到qq的第三方登录界面 其实重定向请求的是
//            //https://graph.qq.com/oauth2.0/authorize 接口；以及封装一堆参数；
//            response.sendRedirect(new Oauth().getAuthorizeURL(request));
//        } catch (QQConnectException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 回调
//     *
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/connect")
//    public String connect(HttpServletRequest request,
//                          HttpServletResponse response,
//                          RedirectAttributes attributes) throws Exception {
//        response.setContentType("text/html; charset=utf-8");
//        try {
//            //根据登录的状态获取AccessToken
//            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
//            String accessToken = null,
//                    openID = null;
//            long tokenExpireIn = 0L;
//
//            if (accessTokenObj.getAccessToken().equals("")) {
////                我们的网站被CSRF攻击了或者用户取消了授权
////                做一些数据统计工作
//                System.out.print("没有获取到响应参数");
//            } else {
//                accessToken = accessTokenObj.getAccessToken();  //授权令牌
//                tokenExpireIn = accessTokenObj.getExpireIn();   //过期时间
//
//
//                // 利用获取到的accessToken 去获取当前用的openid -------- start
//                //通过accessToken获取openId；（底层分析，这步请求的是https://graph.qq.com/oauth2.0/me 接口 ）；
//                //然后得到数据后 放session，放数据库 以及判断啥的 根据自己的业务来搞即可；
//                //这里的openId是每个用户的唯一标识，所以肯定得处理好；
//                OpenID openIDObj = new OpenID(accessToken);
//                openID = openIDObj.getUserOpenID();
//
//
//                //过accessToken和openId得到用户信息；（底层分析，这步请求是https://graph.qq.com/user/get_user_info接口）
//                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
//                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//
//                System.out.println("用户信息: " + userInfoBean);
//
//                if (userInfoBean.getRet() == 0) {   //getRet() 返回状态码 0成功 0<失败
//                    /*用户昵称可能带有表情解决方法参考：
//                    *   修改数据库编码格式 ：utf8改成了“utf8mb4”
//                        将昵称进行Base64加密存储，解密输出
//                        存储昵称时进行昵称判断编写过滤emoji工具
//                        引入第三方依赖包：emoji-java
//                    * */
//                    String nickname = userInfoBean.getNickname();   //用户昵称可能带有表情，存不进数据库，对其进行转码
//                    String gender = userInfoBean.getGender();
//                    String avatar = userInfoBean.getAvatar().getAvatarURL30();
//
//                    String name = URLEncoder.encode(nickname, "utf-8");//对昵称进行url编码
//
//                    QqUser QqUser = new QqUser(name, gender, avatar, accessToken, new Date(tokenExpireIn), openID,new Date());
//                    System.out.println(QqUser);
//                    //将用户信息保存在数据库中
//                    int i = QqUserService.AddQqUser(QqUser);
//                    if (i == 1) {
//                        request.getSession().setAttribute("QQNickname", URLDecoder.decode(name, "utf-8"));//解码
//                        request.getSession().setAttribute("QQavatar", avatar);
//
//                       /* request.getSession().setAttribute("demo_access_token", accessToken);
//                        request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
//                        request.getSession().setAttribute("demo_openid", openID);*/
//                    } else {
//                        attributes.addAttribute("loginMsg", "登录失败，请重新登录");
//                        return "redirect:/userLoginAndRegister/userLogin";
//                    }
//                } else {
//                    System.out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
//                }
//            }
//        } catch (QQConnectException e) {
//        }
//        return "redirect:/index";
//    }
//}
//
///*
//@Controller
//public class QQController {
//*/
///**
// * QQ互联中提供的 appid 和 appkey
// * <p>
// * 请求授权页面
// * 登录按钮点击后的接口
// * <p>
// * 获取到用户信息之后，就该写你自己的业务逻辑了
// *//*
//
//
//    @Value("${qq.oauth.appid}")
//    public String APPID;
//    @Value("${qq.oauth.appkey}")
//    public String APPKEY;
//    @Value("${qq.oauth.url}")
//    public String URL;
//
//*/
///**
// * 请求授权页面
// * 登录按钮点击后的接口
// *//*
//
//
//    @GetMapping(value = "/auth")
//    @ResponseBody
//    public String qqAuth(HttpSession session) {
//        // 用于第三方应用防止CSRF攻击
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        session.setAttribute("state", uuid);
//
//        // Step1：获取Authorization Code
//        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
//                "&client_id=" + APPID +
//                "&redirect_uri=" + URLEncoder.encode(URL) +
//                "&state=" + uuid;
//        System.out.println(url);
//
//
////        return PasswordUtils.redirectTo(url);
//        return url;
//    }
//
//*/
///*
// *
// * 授权回调
//
// *//*
//
//
//    @GetMapping(value = "/callback")
//    public String qqCallback(HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
//        // 得到Authorization Code
//        String code = request.getParameter("code");
//        // 我们放在地址中的状态码
//        String state = request.getParameter("state");
//        // 验证信息
//        String uuid = (String) session.getAttribute("state");
//
//        // 验证信息我们发送的状态码
//        if (null != uuid) {
//            // 状态码不正确，直接返回登录页面
//            if (!uuid.equals(state)) {
////                return PasswordUtils.redirectTo("/login");
//                return "userLogin";
//            }
//        }
//
//        // Step2：通过Authorization Code获取Access Token
//        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
//                "&client_id=" + APPID +
//                "&client_secret=" + APPKEY +
//                "&code=" + code +
//                "&redirect_uri=" + URL;
//        String access_token = QqHttpClient.getAccessToken(url);
//
//        // Step3: 获取回调后的openID
//        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
//        String openId = QqHttpClient.getOpenID(url);
//
//        // Step4：获取QQ用户信息
//        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
//                "&oauth_consumer_key=" + APPID +
//                "&openid=" + openId;
//
//        // 得到用户信息
//        JSONObject jsonObject = QqHttpClient.getUserInfo(url);
//
//        System.out.println(jsonObject);
//*/
///**
// * 获取到用户信息之后，就该写你自己的业务逻辑了
// *//*
//
//
////        return PasswordUtils.redirectTo("/success");
//        return "registerSuccess";
//    }
//
//}
//*/
