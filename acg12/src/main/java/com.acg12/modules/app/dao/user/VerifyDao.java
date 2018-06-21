package com.acg12.modules.app.dao.user;

import com.acg12.modules.app.entity.po.user.Verify;
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