package com.acg12.controller.app;

//@Api(value = "HomeController", description = "资源控制")
//@Controller
//@RequestMapping(value = "/api")
public class AppHomeController {

//    @Resource(name = "resServiceImpl")
//    private ResService resService;
//    @Resource(name = "subjectServiceImpl")
//    private SubjectService subjectService;
//    @Resource(name = "personServiceImpl")
//    private PersonService personService;
//    @Resource(name = "characterServiceImpl")
//    private CharacterService characterService;
//
//    @ApiOperation(value = "首页", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/home", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
//    @ResponseBody
//    public ResponseEntity<?> index() throws Exception {
//        IndexDto indexDto = resService.getIndex();
//        if (indexDto == null) {
//            return new ResponseEntity<>(Result.create202("由于技术原因，暂时停止服务"), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(indexDto), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "每日资讯", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/home/news", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
//    @ResponseBody
//    public ResponseEntity<?> getNews(@ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page) throws Exception {
//        String content = resService.getDMZJNews(page);
//        if (content == null || content.isEmpty()) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(content), HttpStatus.OK);
//        }
//    }
//
//
//    @ApiOperation(value = "每日精选插画", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/home/albums", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
//    @ResponseBody
//    public ResponseEntity<?> queryPictrueAlbum(@ApiParam(name = "max", required = true, value = "图片的id") @RequestParam("max") String max) throws Exception {
//        List<Album> albumList = resService.getHuaBanImages(max);
//        if (albumList == null || albumList.size() == 0) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(new ListDto<List<Album>>(albumList)), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "每日放映", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/home/calendar", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
//    @ResponseBody
//    public ResponseEntity<?> homeCalendar() throws Exception {
//        String content = resService.getBgmCalendarList();
//        if (content == null || content.isEmpty()) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(content), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "获取画板", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/home/boards", method = {RequestMethod.GET})
//    public ResponseEntity<?> queryPictrueBoards(@ApiParam(name = "max", required = true, value = "画板id") @RequestParam("max") String max) throws Exception {
//        List<Palette> paletteList = resService.getHuaBanBoards(max);
//        if (paletteList == null || paletteList.size() == 0) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(new ListDto<List<Palette>>(paletteList)), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "获取画板内容", httpMethod = "GET", produces = "application/json;charset=utf-8")
//    @RequestMapping(value = "/home/boards/albums", method = {RequestMethod.GET})
//    public ResponseEntity<?> subjectBoardsAlbums(@RequestParam("max") String max, @RequestParam("boardId") String boardId) throws Exception {
//        List<Album> albumList = resService.getHuaBanBoardsToImages(boardId, max);
//        if (albumList == null || albumList.size() == 0) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(new ListDto<List<Album>>(albumList)), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "获取Subject内容", httpMethod = "GET", produces = "application/json;charset=utf-8")
//    @RequestMapping(value = "/home/subject", method = {RequestMethod.GET})
//    public ResponseEntity<?> homeSubjectInfo(@RequestParam("id") int id,
//                                             @ApiParam(name = "type", required = true, value = "0:subject 1:cre 2:preson") @RequestParam("type") int type,
//                                             @RequestParam("key") String key) throws Exception {
//        if (type == 1) {
//            CharacterInfoDto characterInfoDto = characterService.queryByCIdJoinDetail(id);
//            if (characterInfoDto == null || characterInfoDto.getsId() == 0) {
//                characterInfoDto = resService.getBgmCharacterInfo(id);
//                if (characterInfoDto == null || characterInfoDto.getsId() == 0) {
//                    return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(Result.create200(characterInfoDto), HttpStatus.OK);
//                }
//            } else {
//                return new ResponseEntity<>(Result.create200(characterInfoDto), HttpStatus.OK);
//            }
//        } else {
//            SubjectInfoDto subjectInfoDto = subjectService.queryBySIdJoinDetail(id);
//            if (subjectInfoDto == null || subjectInfoDto.getSubjectId() == null) {
//                subjectInfoDto = resService.getBgmSubjectInfo(id);
//                if (subjectInfoDto == null || subjectInfoDto.getSubjectId() == null) {
//                    return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(Result.create200(subjectInfoDto), HttpStatus.OK);
//                }
//            } else {
//                return new ResponseEntity<>(Result.create200(subjectInfoDto), HttpStatus.OK);
//            }
//        }
//    }
}
