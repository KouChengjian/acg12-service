package com.acg12.service;

import com.acg12.beans.Verify;
import com.acg12.mapper.VerifyMapper;
import com.acg12.service.base.VerifyService;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/1.
 */
public class VerifyServiceImpl implements VerifyService {

    @Resource
    private VerifyMapper verifyMapper;

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
