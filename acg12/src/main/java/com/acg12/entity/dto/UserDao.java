package com.acg12.entity.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2019/1/18 15:54
 * Description:
 */
@Data
public class UserDao {

    /** id */
    private Long id;

    /** sessionId */
    private String sessionId;

    /** 账号 */
    private String username;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;

    /** 头像 */
    private String avatar;

    /** 昵称 */
    private String nick;

    /** 性别 */
    private Integer sex;

    /** 签名 */
    private String sign;
}
