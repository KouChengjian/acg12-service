package com.acg12.controller.app;

import com.acg12.controller.AppBaseController;
import com.acg12.entity.dto.UserDao;
import com.acg12.entity.po.Acg12CollectAlbumEntity;
import com.acg12.entity.po.Acg12CollectPaletteEntity;
import com.acg12.service.Acg12CollectAlbumService;
import com.acg12.service.Acg12CollectCaricatureService;
import com.acg12.service.Acg12CollectPaletteService;
import com.acg12.utils.StringUtil;
import com.acg12.utils.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Acg12CollectAlbumService acg12CollectAlbumService;
    @Resource
    private Acg12CollectPaletteService acg12CollectPaletteService;
    @Resource
    private Acg12CollectCaricatureService acg12CollectCaricatureService;

    @ResponseBody
    @RequestMapping(value = "/album/list", method = {RequestMethod.POST})
    public Result albumList(int pageNumber, int pageSize) {
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId" , loginUser.getId());
        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
        parameter.put("pageSize", pageSize);
        parameter.put("order", " id desc");
        List<Acg12CollectAlbumEntity> collectAlbumList = acg12CollectAlbumService.findListByPage(parameter);
        return Result.ok(collectAlbumList);
    }

    @ResponseBody
    @RequestMapping(value = "/album/add", method = {RequestMethod.POST})
    public Result albumAdd(Acg12CollectAlbumEntity albumEntity) {
        if(StringUtil.isEmpty(albumEntity.getPinId()) || StringUtil.isEmpty(albumEntity.getImage())){
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Acg12CollectAlbumEntity acg12CollectAlbumEntity = new Acg12CollectAlbumEntity();
        BeanUtils.copyProperties(albumEntity, acg12CollectAlbumEntity);
        acg12CollectAlbumEntity.setUserId(loginUser.getId());
        acg12CollectAlbumService.save(acg12CollectAlbumEntity);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/album/del", method = {RequestMethod.POST})
    public Result albumDel(long id) {
        long i = acg12CollectAlbumService.delete(id);
        System.out.println(i+"==============");
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/palette/list", method = {RequestMethod.POST})
    public Result paletteList(int pageNumber, int pageSize) {
        UserDao loginUser = getCurrentUser();
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId" , loginUser.getId());
        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
        parameter.put("pageSize", pageSize);
        parameter.put("order", " id desc");
        List<Acg12CollectPaletteEntity> collectAlbumList = acg12CollectPaletteService.findListByPage(parameter);
        return Result.ok(collectAlbumList);
    }

    @ResponseBody
    @RequestMapping(value = "/palette/add", method = {RequestMethod.POST})
    public Result paletteAdd(Acg12CollectPaletteEntity paletteEntity) {
        if(StringUtil.isEmpty(paletteEntity.getBoardId())){
            return Result.error("参数错误");
        }
        UserDao loginUser = getCurrentUser();
        Acg12CollectPaletteEntity acg12CollectPaletteEntity = new Acg12CollectPaletteEntity();
        BeanUtils.copyProperties(paletteEntity, acg12CollectPaletteEntity);
        acg12CollectPaletteEntity.setUserId(loginUser.getId());
        acg12CollectPaletteService.save(acg12CollectPaletteEntity);
        return Result.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/palette/del", method = {RequestMethod.POST})
    public Result paletteDel(long id) {
        long i = acg12CollectPaletteService.delete(id);
        System.out.println(i+"==============");
        return Result.ok();
    }
}
