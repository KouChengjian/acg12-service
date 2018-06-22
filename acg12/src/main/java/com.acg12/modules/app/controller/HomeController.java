package com.acg12.modules.app.controller;

import com.acg12.modules.app.entity.dto.IndexDto;
import com.acg12.modules.app.entity.dto.ListDto;
import com.acg12.common.constant.Result;
import com.acg12.modules.app.entity.dto.subject.SubjectInfoDto;
import com.acg12.modules.app.entity.po.Album;
import com.acg12.modules.app.entity.po.Palette;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import com.acg12.modules.app.entity.po.person.PersonEntity;
import com.acg12.modules.app.service.CharacterService;
import com.acg12.modules.app.service.PersonService;
import com.acg12.modules.app.service.ResService;
import com.acg12.modules.app.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "HomeController", description = "资源控制")
@Controller
@RequestMapping(value = "/api")
public class HomeController {

    @Resource(name = "resServiceImpl")
    private ResService resService;
    @Resource(name = "subjectServiceImpl")
    private SubjectService subjectService;
    @Resource(name = "personServiceImpl")
    PersonService personService;
    @Resource(name = "characterServiceImpl")
    CharacterService characterService;

    @ApiOperation(value = "首页", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/home", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public ResponseEntity<?> index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IndexDto indexDto = resService.getIndex();
        if (indexDto == null) {
            return new ResponseEntity<>(Result.create202("由于技术原因，暂时停止服务"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(indexDto), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "每日资讯", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/home/news", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void getNews(@ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page) throws Exception {
//        JSONArray content = resourceService.getNews(page);
//        Result result = new Result();
//        if(content == null || content.length() == 0){
//            result.writeFailure("由于技术原因，暂时停止服务" ,response);
//        } else {
//            result.writeSucceed(content , response);
//        }
    }


    @ApiOperation(value = "每日精选插画", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/home/albums", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public ResponseEntity<?> queryPictrueAlbum(@ApiParam(name = "max", required = true, value = "图片的id") @RequestParam("max") String max) throws Exception {
        List<Album> albumList = resService.getHuaBanImages(max);
        if (albumList == null || albumList.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new ListDto<List<Album>>(albumList)), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "获取画板", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/home/boards", method = {RequestMethod.GET})
    public ResponseEntity<?> queryPictrueBoards(@ApiParam(name = "max", required = true, value = "画板id") @RequestParam("max") String max) throws Exception {
        List<Palette> paletteList = resService.getHuaBanBoards(max);
        if (paletteList == null || paletteList.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new ListDto<List<Palette>>(paletteList)), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "获取画板内容", httpMethod = "GET", produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/home/boards/albums", method = {RequestMethod.GET})
    public ResponseEntity<?> queryPictrueBoardsAlbum(@RequestParam("max") String max , @RequestParam("boardId") String boardId) throws Exception {
        List<Album> albumList = resService.getHuaBanBoardsToImages(boardId, max);
        if (albumList == null || albumList.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new ListDto<List<Album>>(albumList)), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "获取Subject内容", httpMethod = "GET", produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/home/subject", method = {RequestMethod.GET})
    public ResponseEntity<?> queryPictrueBoardsAlbum(@RequestParam("id") int id ,
                                                     @ApiParam(name = "type", required = true, value = "0:subject 1:preson 2:cre") @RequestParam("type") int type,
                                                     @RequestParam("key") String key) throws Exception {
        if(type == 1){
            PersonEntity personEntity = personService.queryByPersonIdJoinDetail(id);

        } else if(type == 2){
            CharacterEntity characterEntity = characterService.queryByCharacterIdJoinDetail(id);
        } else {
            SubjectInfoDto subjectInfoDto = subjectService.queryBySIdJoinDetail(id);
            if(subjectInfoDto == null || subjectInfoDto.getSubjectId() == null ){
                return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Result.create200(subjectInfoDto), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
    }
}
