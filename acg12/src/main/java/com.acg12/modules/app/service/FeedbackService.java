package com.acg12.modules.app.service;


import com.acg12.modules.app.entity.po.user.Feedback;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface FeedbackService {

    int save(Feedback feedback) throws Exception;

}
