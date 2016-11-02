package org.acg12.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

	private String name;
    private String sex;
    private Integer age;//以后开发，基本数据类型尽量用包装类来声明---为以后使用框架做铺垫
    private Date birthday;//出生年月

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    //使用jsp:setProperty和jsp:getProperty两个标记(处理的数组都是字符串)时，JavaBean中复杂类型的处理---进行字符串和复杂类型的格式转换
    public String getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.birthday);
    }
    
    public void setBirthday(String strBirth) {
        //进行复杂类型和字符串类型的转换
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = sdf.parse(strBirth);//解析之后返回一个Date形的值
        } catch (ParseException e) {
            System.out.println("解析时异常！");
            e.printStackTrace();
        }
        this.birthday = d;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", sex=" + sex + ", age=" + age + "]";
    }

    public User login(){
        User u = new User();
        if(this.getName().startsWith("hncu")){
            u.setSex("男");
            u.setAge(54);
        }else{
            u = null;
        }
        return u;
    }
}
