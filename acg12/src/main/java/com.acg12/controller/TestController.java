package com.acg12.controller;


import com.acg12.beans.Result;
import com.acg12.beans.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyifen on 16-9-28 2016 下午5:47
 */
//@Api(value = "TestController"  , description = "测试" )
//@Controller
//@RequestMapping("/user")
public class TestController {

//    @ApiOperation(value = "根据用户id查询用户信息", httpMethod = "POST", produces = "application/json")
//    @ApiResponse(code = 200, message = "success", response = Result.class)
//    @ResponseBody
//    @RequestMapping(value = "queryUserById", method = RequestMethod.POST, produces = "application/json")
//    public Result queryUserById(@ApiParam(name = "userId", required = true, value = "用户Id")
//                                    @RequestParam("userId") int userId, HttpServletRequest request) {
//        User user = new User();
//        Result result = new Result();
//        result.setResult(0);
//        result.setDesc("success");
//        List<User> list = new ArrayList<User>();
//        list.add(user);
////        result.putDataArray("user" , list);
//        return result;
//    }
}
