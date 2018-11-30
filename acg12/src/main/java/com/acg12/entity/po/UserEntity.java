package com.acg12.entity.po;

public class UserEntity {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String avatar;

    private String nick;

    private Integer sex;

    private String sign;

    private Integer createdat;

    private Integer updatedat;

    public UserEntity(Integer id, String username, String password, String email, String avatar, String nick, Integer sex, String sign, Integer createdat, Integer updatedat) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.nick = nick;
        this.sex = sex;
        this.sign = sign;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public UserEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Integer getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Integer createdat) {
        this.createdat = createdat;
    }

    public Integer getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Integer updatedat) {
        this.updatedat = updatedat;
    }
}