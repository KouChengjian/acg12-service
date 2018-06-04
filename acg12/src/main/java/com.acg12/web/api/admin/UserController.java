package com.acg12.web.api.admin;

import com.acg12.config.Constant;
import com.acg12.entity.dto.Result;
import com.acg12.entity.po.user.Feedback;
import com.acg12.entity.po.user.Update;
import com.acg12.entity.po.user.User;
import com.acg12.entity.po.user.Verify;
import com.acg12.service.user.FeedbackServiceImpl;
import com.acg12.service.user.UpdateAppServiceImpl;
import com.acg12.service.user.UserServiceImpl;
import com.acg12.service.user.VerifyServiceImpl;
import com.acg12.utils.FileUpload;
import com.acg12.utils.ListUtil;
import com.acg12.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Api(value = "UserController", description = "用户控制")
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;
    @Resource
    private VerifyServiceImpl verifyService;
    @Resource
    private FeedbackServiceImpl feedbackServiceImpl;
    @Resource
    private UpdateAppServiceImpl updateAppServiceImpl;

    @ApiOperation(value = "登录", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = "application/json")
    public void login(@ApiParam(name = "username", required = true, value = "用户名") @RequestParam("username") String username,
                      @ApiParam(name = "password", required = true, value = "用户密码") @RequestParam("password") String password,
                      HttpServletRequest request, HttpServletResponse response) throws Exception {

        Result result = new Result();
        if (username.isEmpty() || password.isEmpty()) {
            result.writeFailure(Constant.HTTP_RESULT_ERROR_PARAM, "请求参数为空", response);
            return;
        }

        User user = userService.queryUserName(username);
        if (user == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
            result.setDesc("不存在该用户");
            result.write(response);
            return;
        }

        if (!user.getPassword().equals(password)) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PASSWORD);
            result.setDesc("密码错误");
            result.write(response);
            return;
        }

        user.setPassword(null);
        user.setCreatedAt(null);
        user.setUpdatedAt(null);
        result.setResult(Constant.HTTP_RESULT_SUCCEED);
        result.setDesc("成功");
        result.putDataObject("user", user);
        result.write(response);
    }

    @ApiOperation(value = "注册", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public void register(@ApiParam(name = "username", required = true, value = "手机号码") @RequestParam("username") String username,
                         @ApiParam(name = "password", required = true, value = "密码") @RequestParam("password") String password,
                         @ApiParam(name = "verify", required = true, value = "验证码") @RequestParam("verify") int verify,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {

        Result result = new Result();

        if (username.isEmpty() || password.isEmpty() || verify == 0) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            result.write(response);
            return;
        }

        Verify ver = verifyService.query(username, verify, 0, 1);
        if (ver == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("验证码错误");
            result.write(response);
            return;
        }

        int duration = ver.getDuration();
        if (duration != 0) {
            int create = ver.getCreateTime();
            long time = System.currentTimeMillis();
            int present = new Long(time).intValue();
            if (present - create > duration) {
                result.setResult(Constant.HTTP_RESULT_ERROR);
                result.setDesc("验证码过期");
                result.write(response);
                return;
            }
        }

        User user = userService.queryUserName(username);
        if (user != null) {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("当前手机已经注册");
            result.write(response);
            return;
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);

        long time = System.currentTimeMillis();
        time = time / 1000;
        user.setCreatedAt(new Long(time).intValue());
        user.setUpdatedAt(new Long(time).intValue());
        user.setSex(1);
        user.setSign("这个家伙很懒，什么也不说...");
        user.setNick("取名字最讨厌啦");
        user.setAvatar("http://139.196.46.40:8080/res/images/defaultAvatar.png");
        long id = userService.saveUser(user);
        if (id > 0) {
            user.setPassword(null);
            user.setCreatedAt(null);
            user.setUpdatedAt(null);
            result.setResult(Constant.HTTP_RESULT_SUCCEED);
            result.setDesc("成功");
            result.putDataObject("user", user);
            result.write(response);
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_SQL_SAVE);
            result.setDesc("数据存储异常");
            result.write(response);
        }

        ver.setStatus(1);
        verifyService.updateVerify(ver);
    }

    @ApiOperation(value = "获取用户信息", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/userInfo", method = {RequestMethod.POST})
    public void userInfo(@ApiParam(name = "uid", required = true, value = "用户id") @RequestHeader("uid") Integer uid,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user;
        Result result = new Result();
        if (uid == 0) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        user = userService.queryUser(uid);
        if (user == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
            result.setDesc("不存在该数据");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        user.setPassword(null);
        user.setCreatedAt(null);
        user.setUpdatedAt(null);
        result.setResult(Constant.HTTP_RESULT_SUCCEED);
        result.setDesc("成功");
        result.putDataObject("user", user);
        StringUtil.outputStream(response, StringUtil.result(result));

    }

    @ApiOperation(value = "获取验证码", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/verify", method = {RequestMethod.POST})
    public void verify(@ApiParam(name = "username", required = true, value = "手机号码") @RequestParam("username") String username,
                       @ApiParam(name = "type", required = true, value = "验证码类型 1、注册2、重置") @RequestParam("type") int type,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result();
        if (username.isEmpty()) {
            result.writeFailure("请求参数为空", response);
            return;
        }
        if (type != 1 && type != 2) {
            result.writeFailure("短信类型无效", response);
            return;
        }

        Verify verify = new Verify();
        int randomNum = StringUtil.randomNum();
        verify.setPhone(username);
        verify.setVerifycode(randomNum);
        verify.setType(type);
        verify.setDuration(60 * 60 * 1000);
        verify.setStatus(0);
        int code = verifyService.saveVerify(verify);
        if (code < 0) {
            result.writeFailure("存储失败", response);
            return;
        }

        // 发送验证码
//        if (SendSMSUtli.sendSMS(username, randomNum + "")) {
//            result.writeSucceed(response);
//        } else {
//            result.writeFailure("发送验证码失败", response);
//        }
    }

    @ApiOperation(value = "重置密码", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/restPwd", method = {RequestMethod.POST})
    public void restPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verify = request.getParameter("verify");
        User user;
        Result result = new Result();
        if (username.isEmpty() || password.isEmpty() || verify.isEmpty()) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }


    }

    @ApiOperation(value = "修改用户信息", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/alteruser", method = {RequestMethod.POST})
    public void alterUser(@ApiParam(name = "uid", required = true, value = "用户id") @RequestHeader("uid") Integer uid,
                          @ApiParam(name = "alterType", required = true, value = "修改类型 1、昵称 2、签名 4、性别 5、密码") @RequestParam("alterType") Integer alterType,
                          @ApiParam(name = "param1", required = true, value = "参数1") @RequestParam("param1") String param1,
                          @ApiParam(name = "param2", required = true, value = "参数2") @RequestParam("param2") String param2,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user;
        Result result = new Result();

        if (alterType == 0 || alterType > 5) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求修改类型错误");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        user = userService.queryUser(uid);
        if (user == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
            result.setDesc("不存在该用户");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        if (param1 == null || param1.isEmpty()) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        if (alterType == 5) {
            if (param2 == null || param2.isEmpty() || param2.equals("null")) {
                result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
                result.setDesc("请求参数为空");
                StringUtil.outputStream(response, StringUtil.result(result));
                return;
            }
        }

        long time = System.currentTimeMillis();
        time = time / 1000;
        user.setUpdatedAt(new Long(time).intValue());

        if (alterType == 1) {         // 修改昵称
            user.setNick(param1);
        } else if (alterType == 2) {  // 修改签名
            user.setSign(param1);
        } else if (alterType == 3) {  // 修改头像

        } else if (alterType == 4) {  // 修改性别
            if (StringUtil.isNumeric(param1)) {
                int s = Integer.valueOf(param1).intValue();
                user.setSex(s);
            } else {
                result.setResult(Constant.HTTP_RESULT_ERROR);
                result.setDesc("失败");
                StringUtil.outputStream(response, StringUtil.result(result));
                return;
            }
        } else if (alterType == 5) {  // 修改密码
            if (user.getPassword().equals(param1)) {
                user.setPassword(param2);
            } else {
                result.setResult(Constant.HTTP_RESULT_ERROR);
                result.setDesc("密码错误");
                StringUtil.outputStream(response, StringUtil.result(result));
                return;
            }
        }

        long i = userService.updateUser(user);
        if (i > 0) {
            user.setPassword(null);
            user.setCreatedAt(null);
            user.setUpdatedAt(null);
            result.setResult(Constant.HTTP_RESULT_SUCCEED);
            result.setDesc("成功");
            result.putDataObject("user", user);
            StringUtil.outputStream(response, StringUtil.result(result));
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("失败");
            StringUtil.outputStream(response, StringUtil.result(result));
        }
    }

    @RequestMapping(value = "/alteruser/file", method = {RequestMethod.POST})
    public void alterFileUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uid = request.getHeader("uid");
        String alterType = request.getParameter("alterType");
//        File param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        User user;
        Result result = new Result();
        if (alterType == null || alterType.isEmpty()) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        if (StringUtil.isNumeric(uid)) {
            int id = Integer.valueOf(uid).intValue();
            user = userService.queryUser(id);
            if (user == null) {
                result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
                result.setDesc("不存在该用户");
                StringUtil.outputStream(response, StringUtil.result(result));
                return;
            }
        } else {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (!multipartResolver.isMultipart(request)) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        //转换成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        //取得request中的所有文件名
        Iterator<String> iter = multiRequest.getFileNames();
        List<String> myList = ListUtil.copyIterator(iter);
        if (myList == null || myList.isEmpty()) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        iter = ListUtil.copyList(myList);
        while (iter.hasNext()) {
            //取得上传文件
//            System.err.printf("=======");
            MultipartFile file = multiRequest.getFile(iter.next());
            if (file != null) {
                //取得当前上传文件的文件名称
//                String myFileName = file.getOriginalFilename();
//                System.out.println(myFileName);
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                String filePath = FileUpload.uploadFile(file, request);
                System.out.println(filePath);
                user.setAvatar(filePath);
                long i = userService.updateUser(user);
                if (i > 0) {
                    user.setPassword(null);
                    user.setCreatedAt(null);
                    user.setUpdatedAt(null);
                    result.setResult(Constant.HTTP_RESULT_SUCCEED);
                    result.setDesc("成功");
                    result.putDataObject("user", user);
                    StringUtil.outputStream(response, StringUtil.result(result));
                } else {
                    result.setResult(Constant.HTTP_RESULT_ERROR);
                    result.setDesc("失败");
                    StringUtil.outputStream(response, StringUtil.result(result));
                }
//                if (!myFileName.trim().isEmpty()) {
//                    //重命名上传后的文件名
//                    String fileName = "demoUpload" + file.getOriginalFilename();
//                    //定义上传路径
//                    String path = "D:/" + fileName;
//                    File localFile = new File(path);
//                    file.transferTo(localFile);
//                }
            }
        }
    }

    @RequestMapping(value = "/delUser", method = {RequestMethod.POST})
    public void delUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uid = request.getHeader("uid");
        String username = request.getParameter("username");
        Result result = new Result();
        if (uid == null || uid.isEmpty()) {
            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
            result.setDesc("请求参数为空");
            StringUtil.outputStream(response, StringUtil.result(result));
        } else {
            if (StringUtil.isNumeric(uid)) {
                int id = Integer.valueOf(uid).intValue();
                long i = userService.deleteUser(id);
                if (i > 0) {
                    result.setResult(Constant.HTTP_RESULT_SUCCEED);
                    result.setDesc("成功");
                    StringUtil.outputStream(response, StringUtil.result(result));
                } else {
                    result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
                    result.setDesc("不存在该数据");
                    StringUtil.outputStream(response, StringUtil.result(result));
                }
            } else {
                result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
                result.setDesc("请求参数为空");
                StringUtil.outputStream(response, StringUtil.result(result));
            }
        }
    }

    @ApiOperation(value = "所有用户", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/userList", method = {RequestMethod.POST})
    public void userList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result();
        List<User> userList = userService.queryUserList();

        if (userList == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
            result.setDesc("不存在该数据");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        result.setResult(Constant.HTTP_RESULT_SUCCEED);
        result.setDesc("成功");
        result.putDataArray("userList", userList);
        StringUtil.outputStream(response, StringUtil.result(result));
    }

    @ApiOperation(value = "意见反馈", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/feedback", method = {RequestMethod.POST})
    public void feedback(@ApiParam(name = "uid", required = true, value = "用户id") @RequestHeader("uid") Integer uid,
                         @ApiParam(name = "message", required = true, value = "信息") @RequestParam("message") String message,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result();
        Feedback feedback = new Feedback();
        feedback.setUid(uid);
        feedback.setMessage(message);
        int i = feedbackServiceImpl.save(feedback);
        System.err.printf(i + "==============");
        if (i <= 0) {
            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
            result.setDesc("存储失败");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        result.setResult(Constant.HTTP_RESULT_SUCCEED);
        result.setDesc("成功");
        StringUtil.outputStream(response, StringUtil.result(result));
    }

    @ApiOperation(value = "App更新", httpMethod = "POST", produces = "application/json")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public void update(@ApiParam(name = "versionCode", required = true, value = "版本号") @RequestParam("versionCode") Integer versionCode,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result();
        Update update = updateAppServiceImpl.queryUpdate(1);
        if (update == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("无最新信息");
            StringUtil.outputStream(response, StringUtil.result(result));
            return;
        }

        result.setResult(Constant.HTTP_RESULT_SUCCEED);
        result.setDesc("成功");
        result.putDataObject("update", update);
        StringUtil.outputStream(response, StringUtil.result(result));
    }


//    @ResponseBody
//    @RequestMapping(value = "/sava" , method = {RequestMethod.POST})
//    public String userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        User u = new User();
//        u.setId(1);
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
