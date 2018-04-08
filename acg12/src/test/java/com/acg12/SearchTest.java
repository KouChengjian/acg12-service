package com.acg12;

import com.acg12.dao.*;
import com.acg12.entity.po.*;
import com.acg12.factory.ConnectionFactory;
import com.acg12.utils.search.SearchUtli;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 * 搜索测试
 * 词条抓取
 */
public class SearchTest {

    public static void main(String[] args) {
//        SearchUtli.getSubjectSearchList("夏娜" , 2 , 0);

//        SearchUtli.getSubjectInfo(490);
//        SearchUtli.getPersonInfo(629);
//        SearchUtli.getCharacterInfo(951);

        SearchTest searchTest = new SearchTest();
        searchTest.savaSubjectInfo(490);
    }

    private void savaSubjectInfo(int subjectId){
        SubjectDao bangumiDao = ConnectionFactory.getMapper(SubjectDao.class);
        SubjectDetailDao subjectDetailsDao = ConnectionFactory.getMapper(SubjectDetailDao.class);
        PersonDao personDao =  ConnectionFactory.getMapper(PersonDao.class);
        PersonDetailDao personDetailDao =  ConnectionFactory.getMapper(PersonDetailDao.class);
        CharacterDao characterDao = ConnectionFactory.getMapper(CharacterDao.class);
        CharacterDetailDao characterDetailDao = ConnectionFactory.getMapper(CharacterDetailDao.class);

        try {
            JSONObject jsonObject = SearchUtli.getSubjectInfo(subjectId);
            int sId = jsonObject.getInt("sId");
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setsId(sId);
            subjectEntity.setType(jsonObject.getInt("type"));
            subjectEntity.setName(jsonObject.getString("name"));
            subjectEntity.setNameCn(jsonObject.getString("name_cn"));
            subjectEntity.setSummary(jsonObject.getString("summary"));
            subjectEntity.setImage(jsonObject.getString("image"));
            subjectEntity.setEpsCount(jsonObject.getInt("eps_count"));
            subjectEntity.setAirDate(jsonObject.getString("air_date"));
            subjectEntity.setAirWeekday(jsonObject.getInt("air_weekday"));

            SubjectEntity querySubjectEntity = bangumiDao.queryBySId(sId);
            if(querySubjectEntity == null){
                bangumiDao.insert(subjectEntity);
                ConnectionFactory.commit();
            } else {
                if(!querySubjectEntity.equals(subjectEntity)){
                    subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                    bangumiDao.update(subjectEntity);
                    ConnectionFactory.commit();
                } else {
                    subjectEntity.setSubjectId(querySubjectEntity.getSubjectId());
                }
            }

            List<SubjectDetailEntity> subjectDetailList= subjectDetailsDao.queryById(subjectEntity.getSubjectId());
            JSONArray subjectDetailsJSONArray = jsonObject.getJSONArray("details");
            if(subjectDetailList.size() == 0){
                for (int i = 0 , total = subjectDetailsJSONArray.length() ; i < total ; i++){
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

            // 参与人物
            JSONArray staffJSONArray = jsonObject.getJSONArray("staff");
            for (int i = 0 , total = staffJSONArray.length() ; i < total ; i++){
                JSONObject item = staffJSONArray.getJSONObject(i);
                int pId = item.getInt("pId");
                PersonEntity personEntity = personDao.queryByPId(pId);
                if(personEntity == null){
                    personEntity = new PersonEntity();
                    personEntity.setpId(item.getInt("pId"));
                    personEntity.setName(item.getString("name"));
                    personEntity.setNameCn(item.getString("name_cn"));
                    personEntity.setSummary(item.getString("summary"));
                    personEntity.setImage(item.getString("image"));
                    personEntity.setGender(item.getString("gender"));
                    personDao.insert(personEntity);
                    ConnectionFactory.commit();

                    List<PersonDetailEntity> personDetailEntityList = new ArrayList<>();
                    // 别名
                    JSONArray aliasJSONArray = item.getJSONArray("alias");
                    for (int j = 0 , num = aliasJSONArray.length() ; j < num ; j++){
                        JSONObject aliasItem = aliasJSONArray.getJSONObject(j);
                        PersonDetailEntity personDetailEntity = new PersonDetailEntity();
                        personDetailEntity.setPersonId(personEntity.getPersonId());
                        personDetailEntity.setAlias(aliasItem.getString("alias"));
                        personDetailEntityList.add(personDetailEntity);
                    }

                    // 工作
                    JSONArray jobJSONArray = item.getJSONArray("jobs");
                    for (int j = 0 , num = jobJSONArray.length() ; j < num ; j++){
                        String jobItem = jobJSONArray.getString(j);
                        PersonDetailEntity personDetailEntity = new PersonDetailEntity();
                        personDetailEntity.setPersonId(personEntity.getPersonId());
                        personDetailEntity.setJob(jobItem);
                        personDetailEntityList.add(personDetailEntity);
                    }

                    // 其他
                    JSONArray otherJSONArray = item.getJSONArray("other");
                    for (int j = 0 , num = otherJSONArray.length() ; j < num ; j++){
                        JSONObject otherItem = otherJSONArray.getJSONObject(j);
                        PersonDetailEntity personDetailEntity = new PersonDetailEntity();
                        personDetailEntity.setPersonId(personEntity.getPersonId());
                        personDetailEntity.setOtherTitle(otherItem.getString("otherTitle"));
                        personDetailEntity.setOtherValue(otherItem.getString("otherValue"));
                        personDetailEntityList.add(personDetailEntity);
                    }

                    personDetailDao.insertList(personDetailEntityList);
                    ConnectionFactory.commit();
                }

            }

            // 人物
            JSONArray crtJSONArray = jsonObject.getJSONArray("crt");


        } catch (Exception e) {
            e.printStackTrace();
        }


//        List<SubjectDetailEntity> subjectDetailList= subjectDetailsDao.queryById(111);
//        System.err.println(subjectDetailList.size());


//        SubjectEntity subjectEntity = new SubjectEntity();
//        subjectEntity.setName("sdcsds");
//
//        SubjectEntity subjectEntity1 = new SubjectEntity();
//        subjectEntity1.setName("sdcsds");
//
//        System.err.println(subjectEntity.toString().hashCode());
//        System.err.println(subjectEntity1.toString().hashCode());
//
//        if(subjectEntity.equals(subjectEntity1)){
//            System.err.println(true);
//        } else {
//            System.err.println(false);
//        }

//        bangumiDao.insert(subjectEntity);


//        List<SubjectDetailEntity> list = new ArrayList<>();
//        SubjectDetailEntity subjectDetail = new SubjectDetailEntity();
//        subjectDetail.setSubjectId(2121);
//        SubjectDetailEntity subjectDetail1 = new SubjectDetailEntity();
//        subjectDetail1.setSubjectId(54626);
//        list.add(subjectDetail);
//        list.add(subjectDetail1);
//        subjectDetailsDao.insertList(list);
//        ConnectionFactory.commit();

//        PersonEntity personEntity = new PersonEntity();
//        personEntity.setName("ssssssss");
//        personDao.insert(personEntity);

//        PersonDetailEntity personDetailEntity = new PersonDetailEntity();
//        personDetailEntity.setAlias("ssss");
//        personDetailDao.insert(personDetailEntity);

//        CharacterEntity characterEntity = new CharacterEntity();
//        characterEntity.setName("aaaaaaa");
//        characterDao.insert(characterEntity);

//        CharacterDetailEntity characterDetailEntity = new CharacterDetailEntity();
//        characterDetailEntity.setAlias("sss");
//        characterDetailDao.insert(characterDetailEntity);
//        ConnectionFactory.commit();
        ConnectionFactory.close();
    }

    private static void initDB() {
//
//        List<Bangumi> bangumis = new ArrayList<Bangumi>();
//        Bangumi bangumi = new Bangumi();
////        bangumi.setPlatform("sssss");
//        bangumi.setTitle("sssss");
//        bangumis.add(bangumi);
//        bangumis.add(bangumi);
//        bangumiDao.insertList(bangumis);
//        ConnectionFactory.commit();
    }
}
