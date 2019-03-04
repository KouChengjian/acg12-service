package com.acg12.controller.app;

import com.acg12.constant.AppConstants;
import com.acg12.controller.AppBaseController;
import com.acg12.entity.dto.UserDao;
import com.acg12.entity.po.Acg12CollectAlbumEntity;
import com.acg12.entity.po.Acg12CollectPaletteEntity;
import com.acg12.entity.po.Acg12CollectSubjectEntity;
import com.acg12.service.Acg12CollectAlbumService;
import com.acg12.service.Acg12CollectCaricatureService;
import com.acg12.service.Acg12CollectPaletteService;
import com.acg12.service.Acg12CollectSubjectService;
import com.acg12.utils.StringUtil;
import com.acg12.utils.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2019/1/16 18:03
 * Description:
 */
@Controller
@RequestMapping("api/app/collect/*")
public class App12CollectController extends AppBaseController {

    @Resource
    private Acg12CollectSubjectService acg12CollectSubjectService;
    @Resource
    private Acg12CollectAlbumService acg12CollectAlbumService;
    @Resource
    private Acg12CollectPaletteService acg12CollectPaletteService;
    @Resource
    private Acg12CollectCaricatureService acg12CollectCaricatureService;

    @ResponseBody
    @RequestMapping(value = "/subject/list", method = {RequestMethod.POST})
    public Result subjectList(int pageNumber, int pageSize) {
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
        parameter.put("pageSize", pageSize);
        parameter.put("order", " id desc");
        List<Acg12CollectSubjectEntity> collectSubjectEntityList = acg12CollectSubjectService.findListByPage(parameter);
        collectSubjectEntityList = collectSubjectEntityList.stream().map(e -> {
            e.setIsCollect(1);
            if (!e.getImage().contains("http")) {
                e.setImage("http:" + e.getImage());
            }
            return e;
        }).collect(Collectors.toList());
        return Result.ok(collectSubjectEntityList);
    }

    @ResponseBody
    @RequestMapping(value = "/subject/add", method = {RequestMethod.POST})
    public Result subjectAdd(Acg12CollectSubjectEntity subjectEntity) {
        if (subjectEntity.getRelevanceId() == null || subjectEntity.getRelevanceId() == 0) {
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("relevanceId", subjectEntity.getRelevanceId());
        List<Acg12CollectSubjectEntity> collectSubjectList = acg12CollectSubjectService.findListByPage(parameter);
        if (collectSubjectList.size() > 0) {
            return Result.error(AppConstants.AppError5010001, "当前已收藏");
        }

        Acg12CollectSubjectEntity acg12CollectSubjectEntity = new Acg12CollectSubjectEntity();
        BeanUtils.copyProperties(subjectEntity, acg12CollectSubjectEntity);
        acg12CollectSubjectEntity.setUserId(loginUser.getId());
        acg12CollectSubjectEntity.setCreateTime(new Date());
        acg12CollectSubjectEntity.setUpdateTime(new Date());
        acg12CollectSubjectService.save(acg12CollectSubjectEntity);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/subject/del", method = {RequestMethod.POST})
    public Result subjectDel(Integer relevanceId) {
        if (relevanceId == null || relevanceId == 0) {
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("relevanceId", relevanceId);
        acg12CollectAlbumService.deletes(parameter);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/album/list", method = {RequestMethod.POST})
    public Result albumList(int pageNumber, int pageSize) {
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
        parameter.put("pageSize", pageSize);
        parameter.put("order", " id desc");
        List<Acg12CollectAlbumEntity> collectAlbumList = acg12CollectAlbumService.findListByPage(parameter);
        collectAlbumList = collectAlbumList.stream().map(e -> {
            e.setIsCollect(1);
            return e;
        }).collect(Collectors.toList());
        return Result.ok(collectAlbumList);
    }

    @ResponseBody
    @RequestMapping(value = "/album/add", method = {RequestMethod.POST})
    public Result albumAdd(Acg12CollectAlbumEntity albumEntity) {
        if (StringUtil.isEmpty(albumEntity.getPinId()) || StringUtil.isEmpty(albumEntity.getImage())) {
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("pinId", albumEntity.getPinId());
        List<Acg12CollectAlbumEntity> collectAlbumList = acg12CollectAlbumService.findListByPage(parameter);
        if (collectAlbumList.size() > 0) {
            return Result.error(AppConstants.AppError5010001, "当前已收藏");
        }

        Acg12CollectAlbumEntity acg12CollectAlbumEntity = new Acg12CollectAlbumEntity();
        BeanUtils.copyProperties(albumEntity, acg12CollectAlbumEntity);
        acg12CollectAlbumEntity.setUserId(loginUser.getId());
        acg12CollectAlbumEntity.setCreateTime(new Date());
        acg12CollectAlbumEntity.setUpdateTime(new Date());
        acg12CollectAlbumService.save(acg12CollectAlbumEntity);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/album/del", method = {RequestMethod.POST})
    public Result albumDel(String pinId) {
        if (StringUtil.isEmpty(pinId)) {
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("pinId", pinId);
        long i = acg12CollectAlbumService.deletes(parameter);
        System.out.println(i + "==============");
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/palette/list", method = {RequestMethod.POST})
    public Result paletteList(int pageNumber, int pageSize) {
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
        parameter.put("pageSize", pageSize);
        parameter.put("order", " id desc");
        List<Acg12CollectPaletteEntity> collectPaletteList = acg12CollectPaletteService.findListByPage(parameter);
        collectPaletteList = collectPaletteList.stream().map(e -> {
            e.setIsCollect(1);
            return e;
        }).collect(Collectors.toList());
        return Result.ok(collectPaletteList);
    }

    @ResponseBody
    @RequestMapping(value = "/palette/add", method = {RequestMethod.POST})
    public Result paletteAdd(Acg12CollectPaletteEntity paletteEntity) {
        if (StringUtil.isEmpty(paletteEntity.getBoardId()) || StringUtil.isEmpty(paletteEntity.getCover())) {
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("boardId", paletteEntity.getBoardId());
        List<Acg12CollectPaletteEntity> collectAlbumList = acg12CollectPaletteService.findListByPage(parameter);
        if (collectAlbumList.size() > 0) {
            return Result.error(AppConstants.AppError5010001, "当前已收藏");
        }

        Acg12CollectPaletteEntity acg12CollectPaletteEntity = new Acg12CollectPaletteEntity();
        BeanUtils.copyProperties(paletteEntity, acg12CollectPaletteEntity);
        acg12CollectPaletteEntity.setUserId(loginUser.getId());
        acg12CollectPaletteEntity.setCreateTime(new Date());
        acg12CollectPaletteEntity.setUpdateTime(new Date());
        acg12CollectPaletteService.save(acg12CollectPaletteEntity);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/palette/del", method = {RequestMethod.POST})
    public Result paletteDel(String boardId) {
        if (StringUtil.isEmpty(boardId)) {
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", loginUser.getId());
        parameter.put("boardId", boardId);
        acg12CollectPaletteService.deletes(parameter);
        return Result.ok();
    }


}
