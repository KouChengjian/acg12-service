package com.acg12.service.user;

import com.acg12.entity.po.user.Verify;
import com.acg12.dao.user.VerifyDao;
import com.acg12.service.base.VerifyService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/1.
 */
public class VerifyServiceImpl implements VerifyService {

    @Resource
    private VerifyDao verifyMapper;

    @Override
    public int saveVerify(Verify verify) throws Exception {
        return verifyMapper.insert(verify);
    }

    @Override
    public Verify queryVerify(int id) throws Exception {
        return verifyMapper.queryVerify(id);
    }

    @Override
    public Verify queryVerifyCode(int code) throws Exception {
        return verifyMapper.queryVerifyCode(code);
    }

    @Override
    public int deleteVerify(int id) throws Exception {
        return verifyMapper.deleteVerify(id);
    }

    @Override
    public int updateVerify(Verify verify) throws Exception {
        return verifyMapper.updateVerify(verify);
    }

    @Override
    public Verify query(String phone,int verify, int status, int type) {
        return verifyMapper.query(phone,verify,status ,type);
    }
}
