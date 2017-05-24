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
@RequestMapping(value = "/u")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/savaUser" , method = {RequestMethod.POST , RequestMethod.GET})
    public void saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User u = new User();
        u.setUsername("18710627436");
        u.setPassword("123456");
        long time = System.currentTimeMillis();
        System.err.printf(time+"===");
        time = time / 1000;
        System.err.printf(time+"===");
        u.setCreatedAt(new Long(time).intValue());
        u.setUpdatedAt(new Long(time).intValue());
        long id = userService.saveUser(u);

        Result result = new Result();
        if(id > 0){
            result.setResult(Constant.HTTP_RESULT_SUCCEED);
            result.setDesc("成功");
            result.putDataObject("user" , u);
            StringUtil.outputStream(response , StringUtil.result(result));
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_SQL_SAVE);
            result.setDesc("数据存储异常");
            StringUtil.outputStream(response , StringUtil.result(result));
        }
    }

//    @ResponseBody
    @RequestMapping(value = "/queryUser" , method = {RequestMethod.POST})
    public void queryUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String uid = request.getParameter("uid");
        User user = null;
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
                result.setResult(Constant.HTTP_RESULT_SUCCEED);
                result.setDesc("成功");
                result.putDataObject("user" , user);
                StringUtil.outputStream(response , StringUtil.result(result));
            }
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("传参异常");
            StringUtil.outputStream(response , StringUtil.result(result));
        }


    }

    @ResponseBody
    @RequestMapping(value = "/sava" , method = {RequestMethod.POST})
    public String userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User u = new User();
//        u.setId(1);
        u.setUsername("1111");
        u.setPassword("123456");
        try {
            userService.saveUser(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u.getId()+"===";
    }

//    @ResponseBody
//    @RequestMapping(value="/get/{userNo}", method=RequestMethod.GET)
//    public String get(@PathVariable String userNo, Model model){
//        String username = userService.get(userNo);
//        return username;
//    }

}
