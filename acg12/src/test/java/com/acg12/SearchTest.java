package com.acg12;

import com.acg12.dao.character.CharacterActorsDao;
import com.acg12.dao.character.CharacterDao;
import com.acg12.dao.character.CharacterDetailDao;
import com.acg12.dao.person.PersonDao;
import com.acg12.dao.person.PersonDetailDao;
import com.acg12.dao.subject.SubjectCrtDao;
import com.acg12.dao.subject.SubjectDao;
import com.acg12.dao.subject.SubjectDetailDao;
import com.acg12.dao.subject.SubjectStaffDao;
import com.acg12.entity.po.character.CharacterActorsEntity;
import com.acg12.entity.po.character.CharacterDetailEntity;
import com.acg12.entity.po.character.CharacterEntity;
import com.acg12.entity.po.person.PersonDetailEntity;
import com.acg12.entity.po.person.PersonEntity;
import com.acg12.entity.po.subject.SubjectCrtEntity;
import com.acg12.entity.po.subject.SubjectDetailEntity;
import com.acg12.entity.po.subject.SubjectEntity;
import com.acg12.entity.po.subject.SubjectStaffEntity;
import com.acg12.factory.ConnectionFactory;
import com.acg12.utils.JsonParse;
import com.acg12.utils.TimeUtil;
import com.acg12.utils.pagination.PageInfo;
import com.acg12.utils.search.SearchUtli;
import com.google.gson.Gson;
import com.mysql.jdbc.log.LogUtils;
import jdk.nashorn.internal.parser.JSONParser;
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
//        SearchUtli.getSubjectSearchList("夏娜" , 2 , 0);
//        SearchUtli.getSubjectInfo(143694);
//        SearchUtli.getPersonInfo(45396);
//        SearchUtli.getCharacterInfo(951);

        SearchTest searchTest = new SearchTest();
//        searchTest.savaSubjectInfo(490);
//        searchTest.savaSubjectInfo();
//        searchTest.getSubjectInfo();


        searchTest.personStep();
//        searchTest.characterStep();
//        searchTest.testDb();


        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");


    }

    private void testDb(){
        PersonDao personDao = ConnectionFactory.getMapper(PersonDao.class);
        PageInfo page = new PageInfo();
        page.setShowCount(20);
        page.setCurrentResult(1);
        List<PersonEntity> list = personDao.queryByPersonListPage(page ,"3" , 0 , 0  , null);
        System.out.println(list.size()+"====");
    }

    // 查本地数据库
    private void getSubjectInfo() {
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

    private void savaSubjectInfo(int subjectId) {
        SubjectDao bangumiDao = ConnectionFactory.getMapper(SubjectDao.class);
        SubjectDetailDao subjectDetailsDao = ConnectionFactory.getMapper(SubjectDetailDao.class);
        SubjectStaffDao subjectStaffDao = ConnectionFactory.getMapper(SubjectStaffDao.class);
        SubjectCrtDao subjectCrtDao = ConnectionFactory.getMapper(SubjectCrtDao.class);

        PersonDao personDao = ConnectionFactory.getMapper(PersonDao.class);
        PersonDetailDao personDetailDao = ConnectionFactory.getMapper(PersonDetailDao.class);

        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);
        CharacterActorsDao characterActorsDao = ConnectionFactory.getMapper(CharacterActorsDao.class);

        try {
            JSONObject jsonObject = SearchUtli.getSubjectInfo(subjectId);
            int sId = JsonParse.getInt(jsonObject, "sId");
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setsId(sId);
            subjectEntity.setType(JsonParse.getInt(jsonObject, "type"));
            subjectEntity.setName(JsonParse.getString(jsonObject, "name"));
            subjectEntity.setNameCn(JsonParse.getString(jsonObject, "name_cn"));
            subjectEntity.setSummary(JsonParse.getString(jsonObject, "summary"));
            subjectEntity.setImage(JsonParse.getString(jsonObject, "image"));
            subjectEntity.setEpsCount(JsonParse.getInt(jsonObject, "eps_count"));
            subjectEntity.setAirDate(JsonParse.getString(jsonObject, "air_date"));
            subjectEntity.setAirWeekday(JsonParse.getInt(jsonObject, "air_weekday"));

            SubjectEntity querySubjectEntity = bangumiDao.queryBySId(sId);
            if (querySubjectEntity == null) {
                bangumiDao.insert(subjectEntity);
                ConnectionFactory.commit();
            } else {
                if (!querySubjectEntity.equals(subjectEntity)) {
                    subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                    bangumiDao.update(subjectEntity);
                    ConnectionFactory.commit();
                } else {
                    subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                }
            }

            List<SubjectDetailEntity> subjectDetailList = subjectDetailsDao.queryBySubjectId(subjectEntity.getSubjectId());
            JSONArray subjectDetailsJSONArray = jsonObject.getJSONArray("details");
            if (subjectDetailList.size() == 0) {
                for (int i = 0, total = subjectDetailsJSONArray.length(); i < total; i++) {
                    JSONObject item = subjectDetailsJSONArray.getJSONObject(i);
                    SubjectDetailEntity subjectDetailEntity = new SubjectDetailEntity();
                    subjectDetailEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectDetailEntity.setOtherTitle(item.getString("otherTitle"));
                    subjectDetailEntity.setOtherValue(item.getString("otherValue"));
                    subjectDetailList.add(subjectDetailEntity);
                }
                subjectDetailsDao.insertList(subjectDetailList);
                ConnectionFactory.commit();
            }


            List<SubjectStaffEntity> subjectStaffEntities = new ArrayList<>();
            // 参与人物
            JSONArray staffJSONArray = jsonObject.getJSONArray("staff");
            for (int i = 0, total = staffJSONArray.length(); i < total; i++) {
                JSONObject item = staffJSONArray.getJSONObject(i);
                PersonEntity personEntity = savaPerson(personDao, personDetailDao, item);

                // 关联
                SubjectStaffEntity subjectStaffEntity = subjectStaffDao.queryByStaff(subjectEntity.getSubjectId(), personEntity.getPersonId(), JsonParse.getString(item, "curJob"));
                if (subjectStaffEntity == null) {
                    subjectStaffEntity = new SubjectStaffEntity();
                    subjectStaffEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectStaffEntity.setPersonId(personEntity.getPersonId());
                    subjectStaffEntity.setName(personEntity.getName());
                    subjectStaffEntity.setNameCn(personEntity.getNameCn());
                    subjectStaffEntity.setImage(personEntity.getImage());
                    subjectStaffEntity.setJob(JsonParse.getString(item, "curJob"));
                    subjectStaffEntity.setGender(personEntity.getGender() + "");
                    subjectStaffEntities.add(subjectStaffEntity);
                }
            }
            if (subjectStaffEntities.size() != 0) {
                subjectStaffDao.insertList(subjectStaffEntities);
            }

            // 人物
            JSONArray crtJSONArray = jsonObject.getJSONArray("crt");
            for (int i = 0, total = crtJSONArray.length(); i < total; i++) {
                JSONObject item = crtJSONArray.getJSONObject(i);
                CharacterEntity characterEntity = savaCharacter(personDao, personDetailDao, characterDao, characterDetailDao, characterActorsDao, item);

                // 关联
                SubjectCrtEntity subjectCrtEntity = subjectCrtDao.queryByCrt(subjectEntity.getSubjectId(), characterEntity.getCharacterId());
                if (subjectCrtEntity == null) {
                    subjectCrtEntity = new SubjectCrtEntity();
                    subjectCrtEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectCrtEntity.setCharacterId(characterEntity.getCharacterId());
//                    subjectCrtEntity.setPersonId(characterEntity.getPersonId());
                    subjectCrtEntity.setName(characterEntity.getName());
                    subjectCrtEntity.setNameCn(characterEntity.getNameCn());
//                    subjectCrtEntity.setRoleName(characterEntity.getRoleName());
                    subjectCrtEntity.setImage(characterEntity.getImage());
//                    subjectCrtEntity.setGender(characterEntity.getGender());
//                    subjectCrtEntity.setPersonName(characterEntity.getPersonName());
//                    subjectCrtEntity.setPersonNameCn(characterEntity.getPersonNameCn());
//                    subjectCrtEntity.setPersonImage(characterEntity.getPersonImage());
//                    subjectCrtEntity.setPersonGender(characterEntity.getPersonGender());
                    subjectCrtDao.insert(subjectCrtEntity);
                    ConnectionFactory.commit();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionFactory.close();
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

    // 本地测试
    private void savaSubjectInfo() {
        SubjectDao bangumiDao = ConnectionFactory.getMapper(SubjectDao.class);
        SubjectDetailDao subjectDetailsDao = ConnectionFactory.getMapper(SubjectDetailDao.class);
        SubjectStaffDao subjectStaffDao = ConnectionFactory.getMapper(SubjectStaffDao.class);
        SubjectCrtDao subjectCrtDao = ConnectionFactory.getMapper(SubjectCrtDao.class);

        PersonDao personDao = ConnectionFactory.getMapper(PersonDao.class);
        PersonDetailDao personDetailDao = ConnectionFactory.getMapper(PersonDetailDao.class);

        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);
        CharacterActorsDao characterActorsDao = ConnectionFactory.getMapper(CharacterActorsDao.class);

        try {
            JSONObject jsonObject = new JSONObject(readTxtFile("C:\\Users\\Administrator\\Desktop\\loc.txt"));
            int sId = JsonParse.getInt(jsonObject, "sId");
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setsId(sId);
            subjectEntity.setType(JsonParse.getInt(jsonObject, "type"));
            subjectEntity.setName(JsonParse.getString(jsonObject, "name"));
            subjectEntity.setNameCn(JsonParse.getString(jsonObject, "name_cn"));
            subjectEntity.setSummary(JsonParse.getString(jsonObject, "summary"));
            subjectEntity.setImage(JsonParse.getString(jsonObject, "image"));
            subjectEntity.setEpsCount(JsonParse.getInt(jsonObject, "eps_count"));
            subjectEntity.setAirDate(JsonParse.getString(jsonObject, "air_date"));
            subjectEntity.setAirWeekday(JsonParse.getInt(jsonObject, "air_weekday"));

            SubjectEntity querySubjectEntity = bangumiDao.queryBySId(sId);
            if (querySubjectEntity == null) {
                bangumiDao.insert(subjectEntity);
                ConnectionFactory.commit();
            } else {
                if (!querySubjectEntity.equals(subjectEntity)) {
                    subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                    bangumiDao.update(subjectEntity);
                    ConnectionFactory.commit();
                } else {
                    subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                }
            }

            List<SubjectDetailEntity> subjectDetailList = subjectDetailsDao.queryBySubjectId(subjectEntity.getSubjectId());
            JSONArray subjectDetailsJSONArray = jsonObject.getJSONArray("details");
            if (subjectDetailList.size() == 0) {
                for (int i = 0, total = subjectDetailsJSONArray.length(); i < total; i++) {
                    JSONObject item = subjectDetailsJSONArray.getJSONObject(i);
                    SubjectDetailEntity subjectDetailEntity = new SubjectDetailEntity();
                    subjectDetailEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectDetailEntity.setOtherTitle(item.getString("otherTitle"));
                    subjectDetailEntity.setOtherValue(item.getString("otherValue"));
                    subjectDetailList.add(subjectDetailEntity);
                }
                subjectDetailsDao.insertList(subjectDetailList);
                ConnectionFactory.commit();
            }


            List<SubjectStaffEntity> subjectStaffEntities = new ArrayList<>();
            // 参与人物
            JSONArray staffJSONArray = jsonObject.getJSONArray("staff");
            for (int i = 0, total = staffJSONArray.length(); i < total; i++) {
                JSONObject item = staffJSONArray.getJSONObject(i);
                PersonEntity personEntity = savaPerson(personDao, personDetailDao, item);

                // 关联
                SubjectStaffEntity subjectStaffEntity = subjectStaffDao.queryByStaff(subjectEntity.getSubjectId(), personEntity.getPersonId(), JsonParse.getString(item, "curJob"));
                if (subjectStaffEntity == null) {
                    subjectStaffEntity = new SubjectStaffEntity();
                    subjectStaffEntity.setSubjectId(subjectEntity.getSubjectId());
                    subjectStaffEntity.setPersonId(personEntity.getPersonId());
                    subjectStaffEntity.setName(personEntity.getName());
                    subjectStaffEntity.setNameCn(personEntity.getNameCn());
                    subjectStaffEntity.setImage(personEntity.getImage());
                    subjectStaffEntity.setJob(JsonParse.getString(item, "curJob"));
                    subjectStaffEntity.setGender(personEntity.getGender() + "");
                    subjectStaffEntities.add(subjectStaffEntity);
                }
            }
            if (subjectStaffEntities.size() != 0) {
                subjectStaffDao.insertList(subjectStaffEntities);
            }

            // 人物
            JSONArray crtJSONArray = jsonObject.getJSONArray("crt");
            for (int i = 0, total = crtJSONArray.length(); i < total; i++) {
                JSONObject item = crtJSONArray.getJSONObject(i);
                CharacterEntity characterEntity = savaCharacter(personDao, personDetailDao, characterDao, characterDetailDao, characterActorsDao, item);

                // 关联
                SubjectCrtEntity subjectCrtEntity = new SubjectCrtEntity();
                subjectCrtEntity.setSubjectId(subjectEntity.getSubjectId());
                subjectCrtEntity.setCharacterId(characterEntity.getCharacterId());
//                subjectCrtEntity.setPersonId(characterEntity.getPersonId());
                subjectCrtEntity.setName(characterEntity.getName());
                subjectCrtEntity.setNameCn(characterEntity.getNameCn());
//                subjectCrtEntity.setRoleName(characterEntity.getRoleName());
                subjectCrtEntity.setImage(characterEntity.getImage());
//                subjectCrtEntity.setGender(characterEntity.getGender());
//                subjectCrtEntity.setPersonName(characterEntity.getPersonName());
//                subjectCrtEntity.setPersonNameCn(characterEntity.getPersonNameCn());
//                subjectCrtEntity.setPersonImage(characterEntity.getPersonImage());
//                subjectCrtEntity.setPersonGender(characterEntity.getPersonGender());

                SubjectCrtEntity querySubjectCrtEntity = subjectCrtDao.queryByCrt(subjectEntity.getSubjectId(), characterEntity.getCharacterId());

                if (querySubjectCrtEntity == null) {
                    subjectCrtDao.insert(subjectCrtEntity);
                    ConnectionFactory.commit();
                } else {
                    if (!querySubjectCrtEntity.equals(subjectCrtEntity)) {
                        subjectCrtEntity.setSubjectCrtId(querySubjectCrtEntity.getSubjectCrtId());
                        subjectCrtDao.update(subjectCrtEntity);
                        ConnectionFactory.commit();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionFactory.close();
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
                } else {
                    alias += "、" + s1;
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
        PersonDetailEntity personDetail = new PersonDetailEntity();
        personDetail.setPersonId(personEntity.getPersonId());
        personDetail.setSummary(JsonParse.getString(item, "summary"));
        personDetailEntityList.add(personDetail);

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

            if(engineAllId.contains(characterEntity.getcId())){
                characterEntity.setType("2");
            } else if(shipAllId.contains(characterEntity.getcId())){
                characterEntity.setType("3");
            } else if(organizationAllId.contains(characterEntity.getcId())){
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
        // 简介
        CharacterDetailEntity characterDetail = new CharacterDetailEntity();
        characterDetail.setCharacterId(characterEntity.getCharacterId());
        characterDetail.setSummary(JsonParse.getString(item, "summary"));
        characterDetailEntityList.add(characterDetail);

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
     * 1、先抓取三次元人物信息    关联词条
     * 2、在抓取二次元人物信息    关联词条
     * 3、抓取词条 关联词条
     */

    private void personStep() {
        PersonDao personDao = ConnectionFactory.getMapper(PersonDao.class);
        PersonDetailDao personDetailDao = ConnectionFactory.getMapper(PersonDetailDao.class);
        for (int i = 1111, total = 2000; i <= total; i++) {
            JSONObject item = SearchUtli.getPersonInfo(i);
            PersonEntity personEntity = savaPerson(personDao, personDetailDao, item);
        }
        ConnectionFactory.close();
    }

    List<Integer> engineAllId;
    List<Integer> shipAllId;
    List<Integer> organizationAllId;

    private void characterStep() {
        engineAllId = SearchUtli.characterType2();       // 机体 2   251
        shipAllId = SearchUtli.characterType3();         // 舰船 3   122
        organizationAllId = SearchUtli.characterType4(); // 组织 4   394

        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);
        CharacterActorsDao characterActorsDao = ConnectionFactory.getMapper(CharacterActorsDao.class);

        for (int i = 1937, total = 2000; i <= total; i++) {
            JSONObject item = SearchUtli.getCharacterInfo(i);
            CharacterEntity characterEntity = savaCharacter(null, null, characterDao, characterDetailDao, characterActorsDao, item);
        }
        ConnectionFactory.close();
    }
}
