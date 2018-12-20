package com.acg12.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 15:43
 * Description:
 */
@Controller
@RequestMapping("api/app/user/*")
public class AppUserController {

    //    @ApiOperation(value = "获取用户信息", httpMethod = "POST", produces = "application/json")
//    @RequestMapping(value = "/userInfo", method = {RequestMethod.POST})
//    public void userInfo(@ApiParam(name = "uid", required = true, value = "用户id") @RequestHeader("uid") Integer uid,
//                         HttpServletRequest request, HttpServletResponse response) throws Exception {
//        User user;
//        Result result = new Result();
//        if (uid == 0) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        user = userService.queryUser(uid);
//        if (user == null) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
//            result.setDesc("不存在该数据");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        user.setPassword(null);
//        user.setCreatedAt(null);
//        user.setUpdatedAt(null);
//        result.setResult(Constant.HTTP_RESULT_SUCCEED);
//        result.setDesc("成功");
//        result.putDataObject("user", user);
//        StringUtil.outputStream(response, StringUtil.result(result));
//
//    }

//    @ApiOperation(value = "重置密码", httpMethod = "POST", produces = "application/json")
//    @RequestMapping(value = "/restPwd", method = {RequestMethod.POST})
//    public void restPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String verify = request.getParameter("verify");
//        User user;
//        Result result = new Result();
//        if (username.isEmpty() || password.isEmpty() || verify.isEmpty()) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//
//    }
//
//    @ApiOperation(value = "修改用户信息", httpMethod = "POST", produces = "application/json")
//    @RequestMapping(value = "/alteruser", method = {RequestMethod.POST})
//    public void alterUser(@ApiParam(name = "uid", required = true, value = "用户id") @RequestHeader("uid") Integer uid,
//                          @ApiParam(name = "alterType", required = true, value = "修改类型 1、昵称 2、签名 4、性别 5、密码") @RequestParam("alterType") Integer alterType,
//                          @ApiParam(name = "param1", required = true, value = "参数1") @RequestParam("param1") String param1,
//                          @ApiParam(name = "param2", required = true, value = "参数2") @RequestParam("param2") String param2,
//                          HttpServletRequest request, HttpServletResponse response) throws Exception {
//        User user;
//        Result result = new Result();
//
//        if (alterType == 0 || alterType > 5) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求修改类型错误");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        user = userService.queryUser(uid);
//        if (user == null) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
//            result.setDesc("不存在该用户");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        if (param1 == null || param1.isEmpty()) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        if (alterType == 5) {
//            if (param2 == null || param2.isEmpty() || param2.equals("null")) {
//                result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//                result.setDesc("请求参数为空");
//                StringUtil.outputStream(response, StringUtil.result(result));
//                return;
//            }
//        }
//
//        long time = System.currentTimeMillis();
//        time = time / 1000;
//        user.setUpdatedAt(new Long(time).intValue());
//
//        if (alterType == 1) {         // 修改昵称
//            user.setNick(param1);
//        } else if (alterType == 2) {  // 修改签名
//            user.setSign(param1);
//        } else if (alterType == 3) {  // 修改头像
//
//        } else if (alterType == 4) {  // 修改性别
//            if (StringUtil.isNumeric(param1)) {
//                int s = Integer.valueOf(param1).intValue();
//                user.setSex(s);
//            } else {
//                result.setResult(Constant.HTTP_RESULT_ERROR);
//                result.setDesc("失败");
//                StringUtil.outputStream(response, StringUtil.result(result));
//                return;
//            }
//        } else if (alterType == 5) {  // 修改密码
//            if (user.getPassword().equals(param1)) {
//                user.setPassword(param2);
//            } else {
//                result.setResult(Constant.HTTP_RESULT_ERROR);
//                result.setDesc("密码错误");
//                StringUtil.outputStream(response, StringUtil.result(result));
//                return;
//            }
//        }
//
//        long i = userService.updateUser(user);
//        if (i > 0) {
//            user.setPassword(null);
//            user.setCreatedAt(null);
//            user.setUpdatedAt(null);
//            result.setResult(Constant.HTTP_RESULT_SUCCEED);
//            result.setDesc("成功");
//            result.putDataObject("user", user);
//            StringUtil.outputStream(response, StringUtil.result(result));
//        } else {
//            result.setResult(Constant.HTTP_RESULT_ERROR);
//            result.setDesc("失败");
//            StringUtil.outputStream(response, StringUtil.result(result));
//        }
//    }
//
//    @RequestMapping(value = "/alteruser/file", method = {RequestMethod.POST})
//    public void alterFileUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String uid = request.getHeader("uid");
//        String alterType = request.getParameter("alterType");
////        File param1 = request.getParameter("param1");
//        String param2 = request.getParameter("param2");
//        User user;
//        Result result = new Result();
//        if (alterType == null || alterType.isEmpty()) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        if (StringUtil.isNumeric(uid)) {
//            int id = Integer.valueOf(uid).intValue();
//            user = userService.queryUser(id);
//            if (user == null) {
//                result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
//                result.setDesc("不存在该用户");
//                StringUtil.outputStream(response, StringUtil.result(result));
//                return;
//            }
//        } else {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        //创建一个通用的多部分解析器
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        //判断 request 是否有文件上传,即多部分请求
//        if (!multipartResolver.isMultipart(request)) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        //转换成多部分request
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//        //取得request中的所有文件名
//        Iterator<String> iter = multiRequest.getFileNames();
//        List<String> myList = ListUtil.copyIterator(iter);
//        if (myList == null || myList.isEmpty()) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        iter = ListUtil.copyList(myList);
//        while (iter.hasNext()) {
//            //取得上传文件
////            System.err.printf("=======");
//            MultipartFile file = multiRequest.getFile(iter.next());
//            if (file != null) {
//                //取得当前上传文件的文件名称
////                String myFileName = file.getOriginalFilename();
////                System.out.println(myFileName);
//                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
//                String filePath = FileUpload.uploadFile(file, request);
//                System.out.println(filePath);
//                user.setAvatar(filePath);
//                long i = userService.updateUser(user);
//                if (i > 0) {
//                    user.setPassword(null);
//                    user.setCreatedAt(null);
//                    user.setUpdatedAt(null);
//                    result.setResult(Constant.HTTP_RESULT_SUCCEED);
//                    result.setDesc("成功");
//                    result.putDataObject("user", user);
//                    StringUtil.outputStream(response, StringUtil.result(result));
//                } else {
//                    result.setResult(Constant.HTTP_RESULT_ERROR);
//                    result.setDesc("失败");
//                    StringUtil.outputStream(response, StringUtil.result(result));
//                }
////                if (!myFileName.trim().isEmpty()) {
////                    //重命名上传后的文件名
////                    String fileName = "demoUpload" + file.getOriginalFilename();
////                    //定义上传路径
////                    String path = "D:/" + fileName;
////                    File localFile = new File(path);
////                    file.transferTo(localFile);
////                }
//            }
//        }
//    }
//
//    @RequestMapping(value = "/delUser", method = {RequestMethod.POST})
//    public void delUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String uid = request.getHeader("uid");
//        String username = request.getParameter("username");
//        Result result = new Result();
//        if (uid == null || uid.isEmpty()) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//            result.setDesc("请求参数为空");
//            StringUtil.outputStream(response, StringUtil.result(result));
//        } else {
//            if (StringUtil.isNumeric(uid)) {
//                int id = Integer.valueOf(uid).intValue();
//                long i = userService.deleteUser(id);
//                if (i > 0) {
//                    result.setResult(Constant.HTTP_RESULT_SUCCEED);
//                    result.setDesc("成功");
//                    StringUtil.outputStream(response, StringUtil.result(result));
//                } else {
//                    result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
//                    result.setDesc("不存在该数据");
//                    StringUtil.outputStream(response, StringUtil.result(result));
//                }
//            } else {
//                result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//                result.setDesc("请求参数为空");
//                StringUtil.outputStream(response, StringUtil.result(result));
//            }
//        }
//    }
//
//    @ApiOperation(value = "所有用户", httpMethod = "POST", produces = "application/json")
//    @RequestMapping(value = "/userList", method = {RequestMethod.POST})
//    public void userList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Result result = new Result();
//        List<User> userList = userService.queryUserList();
//
//        if (userList == null) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
//            result.setDesc("不存在该数据");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        result.setResult(Constant.HTTP_RESULT_SUCCEED);
//        result.setDesc("成功");
//        result.putDataArray("userList", userList);
//        StringUtil.outputStream(response, StringUtil.result(result));
//    }
//
//    @ApiOperation(value = "意见反馈", httpMethod = "POST", produces = "application/json")
//    @RequestMapping(value = "/feedback", method = {RequestMethod.POST})
//    public void feedback(@ApiParam(name = "uid", required = true, value = "用户id") @RequestHeader("uid") Integer uid,
//                         @ApiParam(name = "message", required = true, value = "信息") @RequestParam("message") String message,
//                         HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Result result = new Result();
//        Feedback feedback = new Feedback();
//        feedback.setUid(uid);
//        feedback.setMessage(message);
//        int i = feedbackServiceImpl.save(feedback);
//        System.err.printf(i + "==============");
//        if (i <= 0) {
//            result.setResult(Constant.HTTP_RESULT_ERROR_NULL_DATA);
//            result.setDesc("存储失败");
//            StringUtil.outputStream(response, StringUtil.result(result));
//            return;
//        }
//
//        result.setResult(Constant.HTTP_RESULT_SUCCEED);
//        result.setDesc("成功");
//        StringUtil.outputStream(response, StringUtil.result(result));
//    }
}
