package com.acg12.controller.app;

import com.acg12.constant.AppConstants;
import com.acg12.controller.AppBaseController;
import com.acg12.entity.dto.*;
import com.acg12.entity.po.Acg12BannerEntity;
import com.acg12.entity.po.Acg12TagEntity;
import com.acg12.entity.po.Acg12UserEntity;
import com.acg12.service.*;
import com.acg12.utils.StringUtil;
import com.acg12.utils.res.BgmResourceUtil;
import com.acg12.utils.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 15:43
 * Description:
 */
@Controller
@RequestMapping("api/app/common/*")
public class AppCommonController extends AppBaseController {

    @Resource
    private Acg12UserService acg12UserService;
    @Resource
    private Acg12SubjectService acg12SubjectService;
    @Resource
    private Acg12PersonService acg12PersonService;
    @Resource
    private Acg12CharacterService acg12CharacterService;
    @Resource
    private Acg12ResourceService acg12ResourceService;
    @Resource
    private Acg12BannerService acg12BannerService;
    @Resource
    private Acg12TagService acg12TagService;

    @Transactional
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(String username, String password) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return Result.error("参数有误", AppConstants.AppError5000020);
        }

        Acg12UserEntity acg12UserEntity = acg12UserService.findUserByPhone(username, password);
        if (acg12UserEntity == null) {
            return Result.error("不存在用户", AppConstants.AppError5000020);
        }

        return Result.ok(acg12UserEntity);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public Result register(String username, String password, int verify) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password) || verify == 0) {
            return Result.error("参数有误", AppConstants.AppError5000020);
        }

        Acg12UserEntity userEntity = null;
        if (StringUtil.checkEmail(username)) {
            userEntity = acg12UserService.find("username", username);
        } else if (StringUtil.checkMobileNumber(username)) {
            userEntity = acg12UserService.find("email", username);
        }
        if (userEntity != null) {
            return Result.error("该账户已存在", AppConstants.AppError5000108);
        }
        Date date = new Date();
        userEntity = new Acg12UserEntity();
        if (StringUtil.checkEmail(username)) {
            userEntity.setUsername(username);
        } else if (StringUtil.checkMobileNumber(username)) {
            userEntity.setEmail(username);
        }
        userEntity.setUpdateTime(date);
        userEntity.setCreateTime(date);
        userEntity.setSex(1);
        userEntity.setSign("这个家伙很懒，什么也不说...");
        userEntity.setNick("取名字最讨厌啦");
        userEntity.setAvatar("http://acg12/res/images/defaultAvatar.png");
        return Result.ok();
    }


    @RequestMapping(value = "/verify", method = {RequestMethod.POST})
    public void verify(String username, int type) {  //1、注册2、重置
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public void updateApp(Integer versionCode) {
    }


    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public Result index() {
        Acg12BannerEntity bannerEntity = acg12BannerService.find("type", 1);
        Map<String, Object> parameter = new HashMap<String, Object>();
//        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
//        parameter.put("pageSize", pageSize);
        parameter.put("pageNumber", 0);
        parameter.put("pageSize", 10);
        parameter.put("status", 1);
        parameter.put("order", " id desc");
        List<Acg12TagEntity> tagList = acg12TagService.findListByPage(parameter);
        if (bannerEntity == null || tagList == null || tagList.size() == 0) {
            return Result.error("数据为空");
        }
        Acg12IndexDto acg12IndexDto = new Acg12IndexDto();
        BeanUtils.copyProperties(bannerEntity, acg12IndexDto);
        acg12IndexDto.setTagList(tagList);
        return Result.ok(acg12IndexDto);
    }

    @ResponseBody
    @RequestMapping(value = "/newList", method = {RequestMethod.GET})
    public Result newList(String page) {
        String content = acg12ResourceService.getDongManZhiJiaNews(page);
        if (content == null || content.isEmpty()) {
            return Result.error("数据为空");
        } else {
            return Result.ok().put("data", content);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/calendarList", method = {RequestMethod.GET})
    public Result calendarList() {
        String content = acg12ResourceService.getBgmCalendarList();
        if (content == null || content.isEmpty()) {
            return Result.error("数据为空");
        } else {
            return Result.ok().put("data", content);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/albumList", method = {RequestMethod.GET})
    public Result albumList(String max) {
        List<Acg12AlbumDto> albumList = acg12ResourceService.getHuaBanImages(max);
        if (albumList == null || albumList.size() == 0) {
            return Result.error("数据为空");
        } else {
            return Result.ok(albumList);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/boardList", method = {RequestMethod.GET})
    public Result boardList(String max) {
        List<Acg12PaletteDto> paletteList = acg12ResourceService.getHuaBanBoards(max);
        if (paletteList == null || paletteList.size() == 0) {
            return Result.error("数据为空");
        } else {
            return Result.ok(paletteList);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/boardList/albums", method = {RequestMethod.GET})
    public Result boardListInfo(@RequestParam("max") String max, @RequestParam("boardId") String boardId) {
        List<Acg12AlbumDto> albumList = acg12ResourceService.getHuaBanBoardsToImages(boardId, max);
        if (albumList == null || albumList.size() == 0) {
            return Result.error("数据为空");
        } else {
            return Result.ok(albumList);
        }
    }

    /**
     * @param id
     * @param type 0:subject 1:crt 2:preson
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject", method = {RequestMethod.GET})
    public Result subject(int id, int type, String key) {
        if (type == 0) {
            subjectInfo(id, key);
        } else if (type == 1) {
            characterInfo(id, key);
        } else if (type == 2) {
            personInfo(id, key);
        }

        return Result.error("数据为空");
    }

    public Result subjectInfo(int id, String key) {
        Acg12SubjectDto acg12SubjectDto = acg12SubjectService.findSubjectDto(id);
        if (acg12SubjectDto != null) {
            return Result.ok(acg12SubjectDto);
        } else {
            return Result.error("数据为空");
        }
//        acg12ResourceService.getDongManZhiJiaNews()
    }

    public Result characterInfo(int id, String key) {
        Acg12CharacterDto acg12CharacterDto = acg12CharacterService.findCharacterDto(id);
        if (acg12CharacterDto != null) {
            return Result.ok(acg12CharacterDto);
        } else {
            return Result.error("数据为空");
        }

    }

    public Result personInfo(int id, String key) {
        Acg12PersonDto acg12PersonDto = acg12PersonService.findPersonDto(id);
        if (acg12PersonDto != null) {
            return Result.ok(acg12PersonDto);
        } else {
            return Result.error("数据为空");
        }
    }


    /**
     * -------------------------------------------------------测试数据------------------------------------------------------------
     */

    @ResponseBody
    @RequestMapping(value = "/test/subject", method = {RequestMethod.GET})
    public Result testSubjectInfo(@RequestParam("id") int sId) {
        Acg12SubjectDto acg12SubjectDto = acg12SubjectService.findSubjectDto(sId);
        if (acg12SubjectDto != null) {
            return Result.ok(acg12SubjectDto);
        }

        // 外网获取
        acg12SubjectDto = BgmResourceUtil.getSubjectDto(sId);
        if (acg12SubjectDto != null) {
            acg12SubjectService.savaSubjectDto(acg12SubjectDto);
            return Result.ok(acg12SubjectDto);
        }
        return Result.error("无当前内容");
    }

    @ResponseBody
    @RequestMapping(value = "/test/person", method = {RequestMethod.GET})
    public Result testPersonInfo(@RequestParam("id") int pId) {
        Acg12PersonDto acg12PersonDto = acg12PersonService.findPersonDto(pId);
        if (acg12PersonDto != null) {
            return Result.ok(acg12PersonDto);
        }

        // 外网获取
        acg12PersonDto = BgmResourceUtil.getPersonDto(pId);
        if (acg12PersonDto != null) {
            acg12PersonService.savaPersonDto(acg12PersonDto);
            return Result.ok(acg12PersonDto);
        }
        return Result.error("无当前内容");
    }

    @ResponseBody
    @RequestMapping(value = "/home/character", method = {RequestMethod.GET})
    public Result homeCharacterInfo(@RequestParam("id") int cId) {
        Acg12CharacterDto acg12CharacterDto = acg12CharacterService.findCharacterDto(cId);
        if (acg12CharacterDto != null) {
            return Result.ok(acg12CharacterDto);
        }

        // 外网获取
        acg12CharacterDto = BgmResourceUtil.getCharacterDto(cId);
        if (acg12CharacterDto != null) {
            acg12CharacterService.savaCharacterDto(acg12CharacterDto);
            return Result.ok(acg12CharacterDto);
        }
        return Result.error("无当前内容");
    }
}
