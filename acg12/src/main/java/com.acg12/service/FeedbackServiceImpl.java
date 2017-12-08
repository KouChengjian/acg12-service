package com.acg12.service;

import com.acg12.entity.po.Feedback;
import com.acg12.dao.FeedbackDao;
import com.acg12.service.base.FeedbackService;

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
