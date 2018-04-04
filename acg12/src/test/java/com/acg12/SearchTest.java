package com.acg12;

import com.acg12.dao.SubjectDao;
import com.acg12.dao.SubjectDetailDao;
import com.acg12.entity.po.SubjectDetailEntity;
import com.acg12.factory.ConnectionFactory;

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
//        JSONObject jsonObject = SearchUtli.getSubjectInfo(subjectId);

        SubjectDao bangumiDao = ConnectionFactory.getMapper(SubjectDao.class);
        SubjectDetailDao subjectDetailsDao = ConnectionFactory.getMapper(SubjectDetailDao.class);

//        SubjectEntity subjectEntity = new SubjectEntity();
//        subjectEntity.setName("sdcsds");
//        bangumiDao.insert(subjectEntity);
//        ConnectionFactory.commit();
        SubjectDetailEntity subjectDetail = new SubjectDetailEntity();
        subjectDetail.setSubjectId(2121);
        subjectDetailsDao.insert(subjectDetail);
        ConnectionFactory.commit();
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
