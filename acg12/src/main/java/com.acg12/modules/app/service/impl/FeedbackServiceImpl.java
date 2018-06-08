package com.acg12.modules.app.service.impl;

import com.acg12.modules.app.dao.user.FeedbackDao;
import com.acg12.modules.app.entity.po.user.Feedback;
import com.acg12.modules.app.service.FeedbackService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/21.
 */
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDao feedbackMapper;

    @Override
    public int save(Feedback feedback) throws Exception {
        return feedbackMapper.insert(feedback);
    }
}
