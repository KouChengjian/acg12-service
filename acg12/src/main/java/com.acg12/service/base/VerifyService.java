package com.acg12.service.base;


import com.acg12.entity.po.Verify;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface VerifyService {

    int saveVerify(Verify verify) throws Exception;

    Verify queryVerify(int id) throws Exception;

    Verify queryVerifyCode(int code) throws Exception;

    int deleteVerify(int id) throws Exception;

    int updateVerify(Verify verify) throws Exception;

    Verify query(String phone , int verify, int status , int type);
}
