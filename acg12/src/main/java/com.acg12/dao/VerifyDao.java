package com.acg12.dao;

import com.acg12.entity.po.Verify;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface VerifyDao {

    int insert(Verify verify);

    int deleteVerify(int id);

    int updateVerify(Verify verify);

    Verify queryVerify(int id);

    Verify queryVerifyCode(int code);

    Verify query(@Param(value = "phone") String phone ,@Param(value = "verifycode") int verify , @Param(value = "status") int status,@Param(value = "type") int type);

}