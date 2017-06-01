package com.acg12.mapper;

import com.acg12.beans.Verify;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface VerifyMapper {

    int insert(Verify verify);

    Verify queryVerify(int id);

    Verify queryVerifyCode(int code);

    int deleteUser(int id);

    int updateVerify(Verify user);

}
