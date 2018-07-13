package com.acg12;

import com.acg12.modules.app.utils.crawler.BgmCrawler;
import com.acg12.modules.app.dao.character.CharacterActorsDao;
import com.acg12.modules.app.dao.character.CharacterDao;
import com.acg12.modules.app.dao.character.CharacterDetailDao;
import com.acg12.modules.app.dao.person.PersonDao;
import com.acg12.modules.app.dao.person.PersonDetailDao;
import com.acg12.modules.app.dao.subject.*;
import com.acg12.factory.ConnectionFactory;
import com.acg12.common.utils.JsonParse;
import com.acg12.common.utils.StringUtil;
import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.entity.po.character.CharacterActorsEntity;
import com.acg12.modules.app.entity.po.character.CharacterDetailEntity;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import com.acg12.modules.app.entity.po.person.PersonDetailEntity;
import com.acg12.modules.app.entity.po.person.PersonEntity;
import com.acg12.modules.app.entity.po.subject.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/3/14.
 * 搜索测试
 * 词条抓取
 */
public class SearchTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        BgmCrawler.getBgmSearchSubjectList("夏娜" , 2 , 0);
//        BgmCrawler.getSubjectInfo(143694);
//        BgmCrawler.getPersonInfo(45396);
//        BgmCrawler.getCharacterInfo(951);
//        BgmCrawler.getCalendarList();

        SearchTest searchTest = new SearchTest();
//        searchTest.savaSubjectInfo(490);
//        searchTest.savaSubjectInfo();
//        searchTest.getSubjectInfo();


        searchTest.personStep();
//        searchTest.characterStep();
//        searchTest.subjectStep();
//        searchTest.testDb();
//        searchTest.searchPreson();


        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        if (excTime < 60) {
            System.out.println("执行时间：" + excTime + "s");
        } else if (excTime < 3600) {
            System.out.println("执行时间：" + excTime / 60 + "分钟");
        } else {
            System.out.println("执行时间：" + excTime + "s");
        }
    }

    private void testDb() {
//        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
//        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);

//        System.out.println(personEntity.getSummary());
//        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
//        CharacterEntity characterEntity = characterDao.queryByCharacterIdJoinDetail(8);
//        System.out.println(characterEntity.toString());

//        SubjectDao subjectDao = ConnectionFactory.getMapper(SubjectDao.class);
//        List<SubjectEntity> subjectEntityList = subjectDao.queryBySubjectGameListPage(new PageInfo(), "4", "", StringUtil.getPlatform("16"), "");
//        System.out.println("subjectEntityList = " + subjectEntityList.size());
    }

    // 查本地数据库
    private void testSubjectInfo() {
        SubjectDao subjectDao = ConnectionFactory.getMapper(SubjectDao.class);
        SubjectDetailDao subjectDetailsDao = ConnectionFactory.getMapper(SubjectDetailDao.class);
        SubjectStaffDao subjectStaffDao = ConnectionFactory.getMapper(SubjectStaffDao.class);
        SubjectCrtDao subjectCrtDao = ConnectionFactory.getMapper(SubjectCrtDao.class);

        PersonDao personDao = ConnectionFactory.getMapper(PersonDao.class);
        PersonDetailDao personDetailDao = ConnectionFactory.getMapper(PersonDetailDao.class);

        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);
        CharacterActorsDao characterActorsDao = ConnectionFactory.getMapper(CharacterActorsDao.class);

        SubjectEntity subjectEntity = subjectDao.queryBySubjectId(2);
        List<SubjectDetailEntity> subjectDetailEntityList = subjectDetailsDao.queryBySubjectId(subjectEntity.getSubjectId());
        List<SubjectStaffEntity> subjectStaffEntityList = subjectStaffDao.queryBySubjectId(subjectEntity.getSubjectId());
        List<SubjectCrtEntity> subjectCrtEntityList = subjectCrtDao.queryBySubjectId(subjectEntity.getSubjectId());

        try {
            JSONObject subjectJsonObject = new JSONObject(new Gson().toJson(subjectEntity));
            JSONArray subjectDetailJsonObject = new JSONArray(new Gson().toJson(subjectDetailEntityList));
            JSONArray subjectStaffJsonObject = new JSONArray(new Gson().toJson(subjectStaffEntityList));
            JSONArray subjectCrtJsonObject = new JSONArray(new Gson().toJson(subjectCrtEntityList));

            subjectJsonObject.put("details", subjectDetailJsonObject);
            subjectJsonObject.put("staff", subjectStaffJsonObject);
            subjectJsonObject.put("crt", subjectCrtJsonObject);
            System.err.println(subjectJsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String readTxtFile(String filepath) {
        try {
            Scanner in = new Scanner(new File(filepath));
            while (in.hasNext()) {
                String str = in.nextLine();
                System.out.println(str);
                return str;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void searchPreson(){
        JSONArray jsonArray = BgmCrawler.getBgmSearchPresonList("夏娜");
        BgmCrawler.getBgmSearchSubjectList("夏娜", 0, 0 ,jsonArray);

    }


    /**
     * ------------------------------------------保存-----------------------------------------
     */
    private SubjectEntity savaSubject(SubjectDao subjectDao, SubjectDetailDao subjectDetailsDao, SubjectStaffDao subjectStaffDao
            , SubjectCrtDao subjectCrtDao, SubjectOffprintDao subjectOffprintDao, SubjectSongDao subjectSongDao, JSONObject item) {
        int sId = JsonParse.getInt(item, "sId");
        if (sId == 0) {
            return null;
        }
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setsId(sId);
        subjectEntity.setType(JsonParse.getInt(item, "type"));
        subjectEntity.setTypeName(JsonParse.getString(item, "type_name"));
        subjectEntity.setName(JsonParse.getString(item, "name"));
        subjectEntity.setNameCn(JsonParse.getString(item, "name_cn"));
        subjectEntity.setSummary(JsonParse.getString(item, "summary"));
        subjectEntity.setImage(JsonParse.getString(item, "image"));
        subjectEntity.setEpsCount(JsonParse.getInt(item, "eps_count"));
        subjectEntity.setAirDate(JsonParse.getString(item, "air_date"));
        subjectEntity.setAirWeekday(JsonParse.getInt(item, "air_weekday"));
        subjectEntity.setEndDate(JsonParse.getString(item, "end_date"));

        SubjectEntity querySubjectEntity = subjectDao.queryBySId(sId);
        if (querySubjectEntity == null) {
            subjectDao.insert(subjectEntity);
            ConnectionFactory.commit();
        } else {
            if (!querySubjectEntity.equals(subjectEntity)) {
                subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                subjectDao.update(subjectEntity);
                ConnectionFactory.commit();
            } else {
                subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
            }
        }

        List<SubjectDetailEntity> subjectDetailList = subjectDetailsDao.queryBySubjectId(subjectEntity.getSubjectId());
        JSONArray subjectDetailsJSONArray = JsonParse.getJSONArray(item, "details");
        if (subjectDetailList.size() == 0) {
            for (int i = 0, total = subjectDetailsJSONArray.length(); i < total; i++) {
                JSONObject item1 = JsonParse.getJSONObject(subjectDetailsJSONArray, i);
                SubjectDetailEntity subjectDetailEntity = new SubjectDetailEntity();
                subjectDetailEntity.setSubjectId(subjectEntity.getSubjectId());
                subjectDetailEntity.setsId(subjectEntity.getsId());
                subjectDetailEntity.setOtherTitle(JsonParse.getString(item1, "otherTitle"));
                subjectDetailEntity.setOtherValue(JsonParse.getString(item1, "otherValue"));
                subjectDetailList.add(subjectDetailEntity);
            }
            if (subjectDetailList.size() > 0) {
                subjectDetailsDao.insertList(subjectDetailList);
                ConnectionFactory.commit();
            }
        }

        // 参与人物
//        List<SubjectStaffEntity> subjectStaffEntities = new ArrayList<>();
        JSONArray staffJSONArray = JsonParse.getJSONArray(item, "staff");
        for (int i = 0, total = staffJSONArray.length(); i < total; i++) {
            JSONObject item1 = JsonParse.getJSONObject(staffJSONArray, i);
            SubjectStaffEntity subjectStaffEntity = subjectStaffDao.queryByStaffByPId(subjectEntity.getSubjectId(), JsonParse.getInt(item1, "pId"), JsonParse.getString(item1, "curJob"));
            if (subjectStaffEntity == null) {
                subjectStaffEntity = new SubjectStaffEntity();
                subjectStaffEntity.setSubjectId(subjectEntity.getSubjectId());
                subjectStaffEntity.setsId(subjectEntity.getsId());
                // subjectStaffEntity.setPersonId(personEntity.getPersonId());
                subjectStaffEntity.setpId(JsonParse.getInt(item1, "pId"));
                subjectStaffEntity.setName(JsonParse.getString(item1, "name"));
                subjectStaffEntity.setJob(JsonParse.getString(item1, "curJob"));
                subjectStaffDao.insert(subjectStaffEntity);
                ConnectionFactory.commit();
            }
        }

        // 人物
        JSONArray crtJSONArray = JsonParse.getJSONArray(item, "crt");
        for (int i = 0, total = crtJSONArray.length(); i < total; i++) {
            JSONObject item1 = JsonParse.getJSONObject(crtJSONArray, i);
            SubjectCrtEntity subjectCrtEntity = subjectCrtDao.queryByCrt(subjectEntity.getSubjectId(), JsonParse.getInt(item1, "cId"));
            if (subjectCrtEntity == null) {
                subjectCrtEntity = new SubjectCrtEntity();
                subjectCrtEntity.setSubjectId(subjectEntity.getSubjectId());
                subjectCrtEntity.setsId(subjectEntity.getsId());
                subjectCrtEntity.setcId(JsonParse.getInt(item1, "cId"));
                subjectCrtEntity.setName(JsonParse.getString(item1, "name"));
                subjectCrtEntity.setNameCn(JsonParse.getString(item1, "nameCn"));
                subjectCrtEntity.setRoleName(JsonParse.getString(item1, "role_name"));
                subjectCrtEntity.setImage(JsonParse.getString(item1, "image"));
                JSONObject actors = JsonParse.getJSONObject(item1, "actors");
                subjectCrtEntity.setpId(JsonParse.getInt(actors, "pId"));
                subjectCrtEntity.setpName(JsonParse.getString(actors, "name"));
                subjectCrtEntity.setpNameCn(JsonParse.getString(actors, "nameCn"));
                subjectCrtEntity.setpImage(JsonParse.getString(actors, "avatar"));
                subjectCrtDao.insert(subjectCrtEntity);
                ConnectionFactory.commit();
            }
        }

        // 单行本
        if (subjectEntity.getType() == 1) {
            JSONArray offprintJSONArray = JsonParse.getJSONArray(item, "offprint");
            for (int i = 0, total = offprintJSONArray.length(); i < total; i++) {
                JSONObject item1 = JsonParse.getJSONObject(offprintJSONArray, i);
                SubjectOffprintEntity subjectOffprintEntity = subjectOffprintDao.queryByOffprint(subjectEntity.getsId() , JsonParse.getInt(item1, "sId"));
                if (subjectOffprintEntity == null) {
                    subjectOffprintEntity = new SubjectOffprintEntity();
                    subjectOffprintEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectOffprintEntity.setsId(subjectEntity.getsId());
                    subjectOffprintEntity.setId(JsonParse.getInt(item1, "sId"));
                    subjectOffprintEntity.setImage(JsonParse.getString(item1, "pic"));
                    subjectOffprintEntity.setName(JsonParse.getString(item1, "name"));
                    subjectOffprintDao.insert(subjectOffprintEntity);
                    ConnectionFactory.commit();
                }
            }
        }
        // 歌曲
        else if (subjectEntity.getType() == 3) {
            JSONArray songJSONArray = JsonParse.getJSONArray(item, "song");
            for (int k = 0, total1 = songJSONArray.length(); k < total1; k++) {
                String title = JsonParse.getString(songJSONArray, k);
                SubjectSongEntity subjectSongEntity = subjectSongDao.queryBySong(subjectEntity.getsId() ,title);
                if (subjectSongEntity == null) {
                    subjectSongEntity = new SubjectSongEntity();
                    subjectSongEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectSongEntity.setsId(subjectEntity.getsId());
                    subjectSongEntity.setTitle(title);
                    subjectSongDao.insert(subjectSongEntity);
                    ConnectionFactory.commit();
                }
            }
        }

        return subjectEntity;
    }

    private PersonEntity savaPerson(PersonDao personDao, PersonDetailDao personDetailDao, JSONObject item) {
        int pId = JsonParse.getInt(item, "pId");
        if (pId == 0) {
            return null;
        }
        PersonEntity personEntity = new PersonEntity();
        {
            personEntity.setpId(JsonParse.getInt(item, "pId"));
            personEntity.setName(JsonParse.getString(item, "name"));
            personEntity.setNameCn(JsonParse.getString(item, "name_cn"));
            personEntity.setImage(JsonParse.getString(item, "image"));
            personEntity.setHeight(JsonParse.getString(item, "height"));
            personEntity.setWeight(JsonParse.getString(item, "weight"));
            personEntity.setBirthday(JsonParse.getString(item, "birth"));
            personEntity.setSummary(JsonParse.getString(item, "summary"));

            String gender = JsonParse.getString(item, "gender");
            if (gender.isEmpty()) {
                personEntity.setGender(0);
            } else {
                personEntity.setGender(gender.equals("男") ? 1 : 2);
            }

            String bloodtype = JsonParse.getString(item, "blood_type");
            if (bloodtype.isEmpty()) {
                personEntity.setBloodtype(0);
            } else if (bloodtype.contains("A")) {
                personEntity.setBloodtype(1);
            } else if (bloodtype.contains("B")) {
                personEntity.setBloodtype(2);
            } else if (bloodtype.contains("AB")) {
                personEntity.setBloodtype(3);
            } else if (bloodtype.contains("O")) {
                personEntity.setBloodtype(4);
            }

            String jobs = "";
            JSONArray jobsJSONArray = JsonParse.getJSONArray(item, "jobs");
            for (int i = 0, total = jobsJSONArray.length(); i < total; i++) {
                String job = JsonParse.getString(jobsJSONArray, i);
                int type;
                if (job.contains("声优")) {
                    type = 1;
                } else if (job.contains("漫画家")) {
                    type = 2;
                } else if (job.contains("制作人")) {
                    type = 3;
                } else if (job.contains("音乐人")) {
                    type = 4;
                } else if (job.contains("演员")) {
                    type = 6;
                } else if (job.contains("绘师")) {
                    type = 7;
                } else if (job.contains("作家")) {
                    type = 8;
                } else {
                    type = 0;
                }
                if (type != 0) {
                    if (i == 0) {
                        jobs += "" + type;
                    } else {
                        jobs += "、" + type;
                    }
                }
            }
            personEntity.setType(jobs);

            String alias = "";
            JSONArray aliasJSONArray = JsonParse.getJSONArray(item, "alias");
            for (int i = 0, total = aliasJSONArray.length(); i < total; i++) {
                String s1 = JsonParse.getString(aliasJSONArray, i);
                if (i == 0) {
                    alias += "" + s1;
                    if (s1.length() < 400) {

                    } else {

                    }
                } else {
                    if (s1.length() < 400) {
                        alias += "、" + s1;
                    }
                }
            }
            personEntity.setAlias(alias);
        }

        PersonEntity queryPersonEntity = personDao.queryByPId(pId);
        if (queryPersonEntity == null) {
            personDao.insert(personEntity);
            ConnectionFactory.commit();
        } else {
            personEntity.setPersonId(queryPersonEntity.getPersonId());
            if (!queryPersonEntity.equals(personEntity)) {
                personDao.update(personEntity);
                ConnectionFactory.commit();
            }
        }

        List<PersonDetailEntity> personDetailEntityList = new ArrayList<>();
//        PersonDetailEntity personDetail = new PersonDetailEntity();
//        personDetail.setPersonId(personEntity.getPersonId());
//        personDetail.setSummary(JsonParse.getString(item, "summary"));
//        personDetailEntityList.add(personDetail);

        JSONArray otherJSONArray = JsonParse.getJSONArray(item, "other");
        for (int j = 0, num = otherJSONArray.length(); j < num; j++) {
            JSONObject otherItem = JsonParse.getJSONObject(otherJSONArray, j);
            PersonDetailEntity personDetailEntity = new PersonDetailEntity();
            personDetailEntity.setPersonId(personEntity.getPersonId());
            personDetailEntity.setOtherTitle(JsonParse.getString(otherItem, "otherTitle"));
            personDetailEntity.setOtherValue(JsonParse.getString(otherItem, "otherValue"));
            personDetailEntityList.add(personDetailEntity);
        }

        if (personDetailEntityList.size() != 0) {
            List<PersonDetailEntity> personDetailEntities = personDetailDao.queryByPersonId(personEntity.getPersonId());
            if (personDetailEntities.size() == 0) {
                personDetailDao.insertList(personDetailEntityList);
                ConnectionFactory.commit();
            } else {
                if (personDetailEntities.size() != personDetailEntityList.size()) {
                    // 执行更新
                }
            }
        }
        return personEntity;
    }

    private CharacterEntity savaCharacter(PersonDao personDao, PersonDetailDao personDetailDao, CharacterDao characterDao, CharacterDetailDao characterDetailDao, CharacterActorsDao characterActorsDao, JSONObject item) {
        int cId = JsonParse.getInt(item, "cId");
        if (cId == 0) {
            return null;
        }

        CharacterEntity characterEntity = new CharacterEntity();
        {
            characterEntity.setcId(JsonParse.getInt(item, "cId"));
            characterEntity.setName(JsonParse.getString(item, "name"));
            characterEntity.setNameCn(JsonParse.getString(item, "name_cn"));
            characterEntity.setImage(JsonParse.getString(item, "image"));
            characterEntity.setHeight(JsonParse.getString(item, "height"));
            characterEntity.setWeight(JsonParse.getString(item, "weight"));
            characterEntity.setBirthday(JsonParse.getString(item, "birth"));
            characterEntity.setSummary(JsonParse.getString(item, "summary"));
            String gender = JsonParse.getString(item, "gender");
            if (gender.isEmpty()) {
                characterEntity.setGender(0);
            } else {
                characterEntity.setGender(gender.equals("男") ? 1 : 2);
            }

            String bloodtype = JsonParse.getString(item, "blood_type");
            if (bloodtype.isEmpty()) {
                characterEntity.setBloodtype(0);
            } else if (bloodtype.contains("A")) {
                characterEntity.setBloodtype(1);
            } else if (bloodtype.contains("B")) {
                characterEntity.setBloodtype(2);
            } else if (bloodtype.contains("AB")) {
                characterEntity.setBloodtype(3);
            } else if (bloodtype.contains("O")) {
                characterEntity.setBloodtype(4);
            }

            if (engineAllId.contains(characterEntity.getcId())) {
                characterEntity.setType("2");
            } else if (shipAllId.contains(characterEntity.getcId())) {
                characterEntity.setType("3");
            } else if (organizationAllId.contains(characterEntity.getcId())) {
                characterEntity.setType("4");
            } else {
                characterEntity.setType("1");
            }

            String alias = "";
            JSONArray aliasJSONArray = JsonParse.getJSONArray(item, "alias");
            for (int i = 0, total = aliasJSONArray.length(); i < total; i++) {
                String s1 = JsonParse.getString(aliasJSONArray, i);
                if (i == 0) {
                    alias += "" + s1;
                } else {
                    alias += "、" + s1;
                }
            }
            characterEntity.setAlias(alias);
        }

        CharacterEntity queryCharacterEntity = characterDao.queryByCId(cId);
        if (queryCharacterEntity == null) {
            characterDao.insert(characterEntity);
            ConnectionFactory.commit();
        } else {
            characterEntity.setCharacterId(queryCharacterEntity.getCharacterId());
            if (!queryCharacterEntity.equals(characterEntity)) {
                characterDao.update(characterEntity);
                ConnectionFactory.commit();
            }
        }

        List<CharacterDetailEntity> characterDetailEntityList = new ArrayList<>();
//        // 简介
//        CharacterDetailEntity characterDetail = new CharacterDetailEntity();
//        characterDetail.setCharacterId(characterEntity.getCharacterId());
//        characterDetail.setSummary(JsonParse.getString(item, "summary"));
//        characterDetailEntityList.add(characterDetail);

        // 其他
        JSONArray otherJSONArray = JsonParse.getJSONArray(item, "other");
        for (int j = 0, num = otherJSONArray.length(); j < num; j++) {
            JSONObject otherItem = JsonParse.getJSONObject(otherJSONArray, j);
            CharacterDetailEntity characterDetailEntity = new CharacterDetailEntity();
            characterDetailEntity.setCharacterId(characterEntity.getCharacterId());
            characterDetailEntity.setOtherTitle(JsonParse.getString(otherItem, "otherTitle"));
            characterDetailEntity.setOtherValue(JsonParse.getString(otherItem, "otherValue"));
            characterDetailEntityList.add(characterDetailEntity);
        }

        if (characterDetailEntityList.size() != 0) {
            List<CharacterDetailEntity> characterDetailEntitys = characterDetailDao.queryByCharacterId(characterEntity.getCharacterId());
            if (characterDetailEntitys.size() == 0) {
                characterDetailDao.insertList(characterDetailEntityList);
                ConnectionFactory.commit();
            } else {
                if (characterDetailEntitys.size() != characterDetailEntityList.size()) {
                    // 执行更新
                }
            }
        }

        // 声优
        if (personDao != null && personDetailDao != null) {
            JSONObject actorsJSONObject = JsonParse.getJSONObject(item, "actors");
            PersonEntity personEntity = savaPerson(personDao, personDetailDao, actorsJSONObject);
            // 如果为空说明没有声优
            if (personEntity != null) {
                CharacterActorsEntity characterActorsEntity = characterActorsDao.queryByActors(characterEntity.getCharacterId(), personEntity.getPersonId());
                if (characterActorsEntity == null) {
                    characterActorsEntity = new CharacterActorsEntity();
                    characterActorsEntity.setCharacterId(characterEntity.getCharacterId());
                    characterActorsEntity.setPersonId(personEntity.getPersonId());
                    characterActorsEntity.setName(personEntity.getName());
                    characterActorsEntity.setNameCn(personEntity.getNameCn());
                    characterActorsEntity.setImage(personEntity.getImage());
//                characterActorsEntity.setGender(personEntity.getGender());
                    characterActorsDao.insert(characterActorsEntity);
                    ConnectionFactory.commit();
                }

                // 补充数据
//                characterEntity.setPersonId(personEntity.getPersonId());
//                characterEntity.setPersonName(personEntity.getName());
//                characterEntity.setPersonNameCn(personEntity.getNameCn());
//                characterEntity.setPersonImage(personEntity.getImage());
//            characterEntity.setPersonGender(personEntity.getGender());
                if (!queryCharacterEntity.equals(characterEntity)) {
                    characterDao.update(characterEntity);
                    ConnectionFactory.commit();
                }
            }
        }
        return characterEntity;
    }


    /**------------------------------------------爬虫实例-----------------------------------------------------*/
    /**
     * 32537条
     * 1、先抓取三次元人物信息    关联词条
     * 2、在抓取二次元人物信息    关联词条
     * 3、抓取词条 关联词条
     */
    private void personStep() {
        PersonDao personDao = ConnectionFactory.getMapper(PersonDao.class);
        PersonDetailDao personDetailDao = ConnectionFactory.getMapper(PersonDetailDao.class);
        for (int i = 5000, total = 5010; i <= total; i++) {
            JSONObject item = BgmCrawler.getPersonInfo(i);
            PersonEntity personEntity = savaPerson(personDao, personDetailDao, item);
        }
        ConnectionFactory.close();
    }

    List<Integer> engineAllId;
    List<Integer> shipAllId;
    List<Integer> organizationAllId;

    private void characterStep() {
        engineAllId = BgmCrawler.characterType2();       // 机体 2   251
        shipAllId = BgmCrawler.characterType3();         // 舰船 3   122
        organizationAllId = BgmCrawler.characterType4(); // 组织 4   394

        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);
        CharacterActorsDao characterActorsDao = ConnectionFactory.getMapper(CharacterActorsDao.class);

        for (int i = 1937, total = 2000; i <= total; i++) {
            JSONObject item = BgmCrawler.getCharacterInfo(i);
            CharacterEntity characterEntity = savaCharacter(null, null, characterDao, characterDetailDao, characterActorsDao, item);
        }
        ConnectionFactory.close();
    }

    private void subjectStep() {
        SubjectDao subjectDao = ConnectionFactory.getMapper(SubjectDao.class);
        SubjectDetailDao subjectDetailsDao = ConnectionFactory.getMapper(SubjectDetailDao.class);
        SubjectStaffDao subjectStaffDao = ConnectionFactory.getMapper(SubjectStaffDao.class);
        SubjectCrtDao subjectCrtDao = ConnectionFactory.getMapper(SubjectCrtDao.class);
        SubjectOffprintDao subjectOffprintDao = ConnectionFactory.getMapper(SubjectOffprintDao.class);
        SubjectSongDao subjectSongDao = ConnectionFactory.getMapper(SubjectSongDao.class);

        for (int i = 1001, total = 1002; i <= total; i++) {
            JSONObject item = BgmCrawler.getSubjectInfoSimple(i);
            if (item == null) {
                continue;
            }
            System.out.println(item.toString());
            SubjectEntity subjectEntity = savaSubject(subjectDao, subjectDetailsDao, subjectStaffDao , subjectCrtDao , subjectOffprintDao , subjectSongDao, item);
        }
        ConnectionFactory.close();
    }
}
