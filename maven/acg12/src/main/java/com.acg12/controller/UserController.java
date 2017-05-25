package com.acg12.controller;

import com.acg12.beans.Result;
import com.acg12.beans.User;
import com.acg12.config.Constant;
import com.acg12.service.UserService;
import com.acg12.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Controller
@RequestMapping(value = "/api")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login" , method = {RequestMethod.POST})
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user ;
        Result result = new Result();
        if(!username.isEmpty() && !password.isEmpty()) {
            user = userService.queryUserName(username);
            if(user == null){
                result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
                result.setDesc("不存在该用户");
                StringUtil.outputStream(response , StringUtil.result(result));
            } else {
                if(!user.getPassword().equals(password)) {
                    result.setResult(Constant.HTTP_RESULT_ERROR_PASSWORD);
                    result.setDesc("密码错误");
                    StringUtil.outputStream(response , StringUtil.result(result));
                } else {
                    user.setPassword(null);
                    user.setCreatedAt(null);
                    user.setUpdatedAt(null);
                    result.setResult(Constant.HTTP_RESULT_SUCCEED);
                    result.setDesc("成功");
                    result.putDataObject("user" , user);
                    StringUtil.outputStream(response , StringUtil.result(result));
                }
            }
        }else{
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
        }
    }

    @RequestMapping(value = "/register" , method = {RequestMethod.POST })
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = new User();
        Result result = new Result();

        if(!username.isEmpty() && !password.isEmpty()) {
            u.setUsername(username);
            u.setPassword(password);
            long time = System.currentTimeMillis();
            time = time / 1000;
            u.setCreatedAt(new Long(time).intValue());
            u.setUpdatedAt(new Long(time).intValue());
            u.setSign("这个家伙很懒，什么也不说...");
            long id = userService.saveUser(u);
            if(id > 0){
                u.setPassword(null);
                u.setCreatedAt(null);
                u.setUpdatedAt(null);
                result.setResult(Constant.HTTP_RESULT_SUCCEED);
                result.setDesc("成功");
                result.putDataObject("user" , u);
                StringUtil.outputStream(response , StringUtil.result(result));
            } else {
                result.setResult(Constant.HTTP_RESULT_ERROR_SQL_SAVE);
                result.setDesc("数据存储异常");
                StringUtil.outputStream(response , StringUtil.result(result));
            }
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
        }
    }

    @RequestMapping(value = "/userInfo" , method = {RequestMethod.POST})
    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String uid = request.getParameter("uid");
        User user ;
        Result result = new Result();
        if(StringUtil.isNumeric(uid)){
            int id = Integer.valueOf(uid).intValue();
            user = userService.queryUser(id);
            if(user == null){
                result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
                result.setDesc("不存在该数据");
                StringUtil.outputStream(response , StringUtil.result(result));
            } else {
                user.setPassword(null);
                user.setCreatedAt(null);
                user.setUpdatedAt(null);
                result.setResult(Constant.HTTP_RESULT_SUCCEED);
                result.setDesc("成功");
                result.putDataObject("user" , user);
                StringUtil.outputStream(response , StringUtil.result(result));
            }
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
        }


    }

    @RequestMapping(value = "/verify" , method = {RequestMethod.POST})
    public void verify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        String verify = request.getParameter("verify");
        User user ;
        Result result = new Result();
        if(!username.isEmpty() && !verify.isEmpty()) {


        }else{
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
        }
    }

    @RequestMapping(value = "/restPwd" , method = {RequestMethod.POST})
    public void restPwd(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verify = request.getParameter("verify");
        User user ;
        Result result = new Result();
        if(!username.isEmpty() && !password.isEmpty() && !verify.isEmpty()) {


        }else{
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
        }
    }

    @RequestMapping(value = "/alteruser" , method = {RequestMethod.POST})
    public void alterUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String uid = request.getHeader("uid");
        String alterType = request.getParameter("alterType");
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        User user ;
        Result result = new Result();
        if(alterType == null){
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
            return;
        }

        if(param1 != null && !param1.isEmpty()){
            if(StringUtil.isNumeric(uid)){
                int id = Integer.valueOf(uid).intValue();
                user = userService.queryUser(id);
                if(user == null){
                    result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
                    result.setDesc("不存在该用户");
                    StringUtil.outputStream(response , StringUtil.result(result));
                } else {
                    long time = System.currentTimeMillis();
                    time = time / 1000;
                    user.setUpdatedAt(new Long(time).intValue());

                    if(alterType.equals("1")){ // 修改昵称
                        user.setNick(param1);
                    } else if(alterType.equals("2")){ // 修改签名
                        user.setSign(param1);
                    } else if(alterType.equals("3")){ // 修改头像

                    } else if(alterType.equals("4")){ // 修改性别
                        if(StringUtil.isNumeric(param1)){
                            int s = Integer.valueOf(param1).intValue();
                            user.setSex(s);
                        }else {
                            result.setResult(Constant.HTTP_RESULT_ERROR);
                            result.setDesc("失败");
                            StringUtil.outputStream(response , StringUtil.result(result));
                            return;
                        }
                    } else if(alterType.equals("5")){ // 修改密码
                        if(user.getPassword().equals(param1)){
                            if(param2  == null || param2.isEmpty()){
                                result.setResult(Constant.HTTP_RESULT_ERROR);
                                result.setDesc("失败");
                                StringUtil.outputStream(response , StringUtil.result(result));
                                return;
                            }else {
                                user.setPassword(param2);
                            }
                        }else {
                            result.setResult(Constant.HTTP_RESULT_ERROR);
                            result.setDesc("密码错误");
                            StringUtil.outputStream(response , StringUtil.result(result));
                            return;
                        }
                    }

                    long i = userService.updateUser(user);
                    if(i > 0){
                        user.setPassword(null);
                        user.setCreatedAt(null);
                        user.setUpdatedAt(null);
                        result.setResult(Constant.HTTP_RESULT_SUCCEED);
                        result.setDesc("成功");
                        result.putDataObject("user" , user);
                        StringUtil.outputStream(response , StringUtil.result(result));
                    } else {
                        result.setResult(Constant.HTTP_RESULT_ERROR);
                        result.setDesc("失败");
                        StringUtil.outputStream(response , StringUtil.result(result));
                    }
                }
            }else {
                result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
                result.setDesc("请求参数为空");
                StringUtil.outputStream(response , StringUtil.result(result));
            }

        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response , StringUtil.result(result));
        }
    }























//    @ResponseBody
//    @RequestMapping(value = "/sava" , method = {RequestMethod.POST})
//    public String userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        User u = new User();
////        u.setId(1);
//        u.setUsername("1111");
//        u.setPassword("123456");
//        try {
//            userService.saveUser(u);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return u.getId()+"===";
//    }

//    @ResponseBody
//    @RequestMapping(value="/get/{userNo}", method=RequestMethod.GET)
//    public String get(@PathVariable String userNo, Model model){
//        String username = userService.get(userNo);
//        return username;
//    }

}
