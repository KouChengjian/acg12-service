package com.acg12.dao;

import com.acg12.entity.po.Bangumi;
import com.acg12.entity.po.Tag;
import com.acg12.entity.po.User;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
public interface BangumiDao {

    int insert(Bangumi bangumi);

    int insertList(List<Bangumi> bangumiList);

    int update(Bangumi user);

    List<Bangumi> queryList();
}
