package com.acg12.controller.app;

import com.acg12.constant.AppConstants;
import com.acg12.controller.AppBaseController;
import com.acg12.entity.po.Acg12UserEntity;
import com.acg12.service.Acg12UserService;
import com.acg12.utils.StringUtil;
import com.acg12.utils.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 15:43
 * Description:
 */
@Controller
@RequestMapping("api/app/user/*")
public class AppUserController extends AppBaseController {

    @Resource
    private Acg12UserService acg12UserService;

    @ResponseBody
    @RequestMapping(value = "/info", method = {RequestMethod.POST})
    public Result userInfo(Integer uid) {
        if (uid == null || uid == 0) {
            return Result.error("参数有误", AppConstants.AppError5000020);
        }
        Acg12UserEntity user = acg12UserService.find("id", uid);
        if (user == null) {
            return Result.error("不存在用户", AppConstants.AppError5000020);
        }

        user.setPassword(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        return Result.ok(user);
    }

    @ResponseBody
    @RequestMapping(value = "/restPwd", method = {RequestMethod.POST})
    public void restPwd(String username, String password, String verify) {

    }

    /**
     * @param uid
     * @param alterType 修改类型 1、昵称 2、签名 4、性别 5、密码
     * @param param1
     * @param param2
     */
    @ResponseBody
    @RequestMapping(value = "/alteruser", method = {RequestMethod.POST})
    public Result alterUser(Integer uid, Integer alterType, String param1, String param2) {
        if (uid == null || uid == 0) {
            return Result.error("参数有误", AppConstants.AppError5000020);
        }
        Acg12UserEntity user = acg12UserService.find("id", uid);
        if (user == null) {
            return Result.error("不存在用户", AppConstants.AppError5000020);
        }

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
                return Result.error("参数有误", AppConstants.AppError5000020);
            }
        } else if (alterType == 5) {  // 修改密码
            if (param2 == null || param2.isEmpty() || param2.equals("null")) {
                return Result.error("参数有误", AppConstants.AppError5000020);
            }
            if (user.getPassword().equals(param1)) {
                user.setPassword(param2);
            } else {
                return Result.error("密码错误", AppConstants.AppError5000020);
            }
        }

        long i = acg12UserService.update(user);
        if (i == 0) {
            return Result.error("SQL异常", AppConstants.AppError5000021);
        }
        user.setPassword(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        return Result.ok(user);
    }

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

    @ResponseBody
    @RequestMapping(value = "/delUser", method = {RequestMethod.POST})
    public Result delUser(Long uid) {
        if (uid == null || uid == 0) {
            return Result.error("参数有误", AppConstants.AppError5000020);
        }
        long result = acg12UserService.delete(uid);
        if (result == 0) {
            return Result.error("SQL异常", AppConstants.AppError5000021);
        }
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/userList", method = {RequestMethod.POST})
    public Result userList() {
        List<Acg12UserEntity> userList = acg12UserService.findAll();
        return Result.ok(userList);
    }

    @ResponseBody
    @RequestMapping(value = "/feedback", method = {RequestMethod.POST})
    public void feedback(Integer uid, String message) {
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
    }
}
