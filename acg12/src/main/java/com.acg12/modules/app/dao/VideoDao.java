package com.acg12.modules.app.dao;

import com.acg12.modules.app.entity.po.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
public interface VideoDao {

    int insert(Video bangumi);

    int update(Video user);

    List<Video> queryList();

    List<Video> queryByBangumiId(@Param(value = "bangumitId") int bangumitId);
}
