package com.acg12;

import com.acg12.dao.BangumiDao;
import com.acg12.dao.SubjectDao;
import com.acg12.entity.po.Bangumi;
import com.acg12.factory.ConnectionFactory;
import com.acg12.utils.search.SearchUtli;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SearchTest {

    public static void main(String[] args) {
//        SearchUtli.getSubjectSearchList("夏娜" , 2 , 0);
//        SearchUtli.getSubjectSearch11();


        SearchUtli.getSubjectInfo(490);
//        SearchUtli.getPersonInfo(629);
    }

    private static void initDB() {
//        SubjectDao bangumiDao = ConnectionFactory.getMapper(SubjectDao.class);
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
