package com.acg12.common.utils;

import com.acg12.common.utils.stack.IndexStack;
import com.acg12.common.utils.stack.MyStack;
import com.acg12.common.constant.Result;
import com.acg12.modules.app.entity.dto.subject.SubjectStaffDto;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import com.acg12.modules.app.entity.po.person.PersonEntity;
import com.acg12.modules.app.entity.po.subject.SubjectStaffEntity;
import com.acg12.modules.app.entity.view.MenuItem;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


public class StringUtil {


    public static void outputStream(HttpServletResponse response, String content) {
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("content-type", "application/json;charset=UTF-8");
            byte[] dataByteArr = content.getBytes("UTF-8");
            outputStream.write(dataByteArr);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String result(JSONObject content) {
        JSONObject json = new JSONObject();
        try {
            if (content == null) {
                json.put("result", 201);
                json.put("desc", "获取失败");
                json.put("data", new JSONObject());
            } else {
                json.put("result", 200);
                json.put("desc", "获取成功");
                json.put("data", content);
            }
        } catch (JSONException e) {
        }
        return json.toString();
    }

    public static synchronized String result(Result result) {
        JSONObject json = new JSONObject();
        try {
            json.put("result", result.getResult());
            json.put("desc", result.getDesc());
            json.put("data", result.getData());
        } catch (JSONException e) {
        }
        return json.toString();
    }



    /**
     * 压缩获取数据
     *
     * @param inStream
     * @param charsetName
     * @return
     * @throws Exception
     */
    public static String readDataForZgip(InputStream inStream,
                                         String charsetName) throws Exception {
        GZIPInputStream gzipStream = new GZIPInputStream(inStream);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = gzipStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        gzipStream.close();
        inStream.close();
        return new String(data, charsetName);
    }

    public static String readDataForZgip(InputStream inStream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        while ((tempLine = br.readLine()) != null) {
            resultBuffer.append(tempLine);
        }
        return resultBuffer.toString();
    }


    // 判断字符串是否为数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String string2Json(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String stringToJson(String s) {
        MyStack<IndexStack> stack = new MyStack<IndexStack>();
        List<Integer> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
//            System.out.println("i = " + i);
            char c = s.charAt(i);
//            System.out.println(c);
            switch (c) {
                case '\"':
//                    System.out.println("入栈");
                    stack.push(new IndexStack(i , c));
                    sb.append(c);
                    break;
                case ':':
                    if(String.valueOf(s.charAt(i-1)).equals("\"")){
//                        System.out.println("出栈 = stack.size() = "+stack.size());
                        if (stack.size() != 0) {
                            if (stack.size() == 2) {
                                stack.pop();
                                stack.pop();
                            } else if (stack.size() > 2) {
//                                System.out.println("数据异常大于2");
                            } else if (stack.size() < 2) {
//                                System.out.println("数据异常小于2");
                            }
                        }
//                        System.out.println("出栈 = stack.size() = "+stack.size());
                    }

                    sb.append(c);
                    break;
                case ',': // 目前不能识别段落里面的英文逗号
                    int num = stack.size();
                    for (int k = 0  ; k < num ; k++){
                        IndexStack indexStack = (IndexStack)stack.pop();
//                        System.out.println("indexStack.getPosition() = "+indexStack.getPosition());
//                        System.out.println("k = "+ k);
                        if(k == 0 || k == num - 1){

                        } else {
//                            System.out.println("执行");
//                            sb.replace(indexStack.getPosition() , indexStack.getPosition() +1 ,"&quot;");
                            list.add(indexStack.getPosition());
//                            System.out.println(sb.toString());
                        }
                    }
                    sb.append(c);
                    break;
                default:
                    sb.append(c);
            }
//            System.out.println("lenght = " +sb.length());
        }
//        System.out.println("list = " +list.size());
        Collections.sort(list);
        for (int i = list.size() -1 ; i >=  0 ; i--){
//            System.out.println(list.get(i));
            sb.replace(list.get(i) , list.get(i) +1 ,"&quot;");
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String stringToJson2(String s) {
        MyStack<IndexStack> stack = new MyStack<IndexStack>();
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < s.length(); i++) {
            System.out.println("i = " + i);
            char c = s.charAt(i);
            System.out.println(c);
            switch (c) {
                case '\"':
                    System.out.println("入栈");
                    stack.push(new IndexStack(i , c));
                    sb.append(c);
                    break;
                case ':':
                    if(String.valueOf(s.charAt(i-1)).equals("\"")){
                        System.out.println("出栈 = stack.size() = "+stack.size());
                        if (stack.size() != 0) {
                            if (stack.size() == 2) {
                                stack.pop();
                                stack.pop();
                            } else if (stack.size() > 2) {
                                System.out.println("数据异常大于2");
                            } else if (stack.size() < 2) {
                                System.out.println("数据异常小于2");
                            }
                        }
                        System.out.println("出栈 = stack.size() = "+stack.size());
                    }

                    sb.append(c);
                    break;
                case ',':
                    if(stack.size() % 2 == 0) {
                        System.out.println("一组数据");
                        if(stack.size() > 2){
                            System.out.println("stack.size() = " +stack.size());
                            int num = stack.size();
                            for (int k = 0  ; k < num ; k++){
                                IndexStack indexStack = (IndexStack)stack.pop();
                                System.out.println("indexStack.getPosition() = "+indexStack.getPosition());
                                System.out.println("k = "+ k);
                                if(k == 0 || k == num - 1){

                                } else {
                                    System.out.println("执行");
                                    sb.replace(indexStack.getPosition() , indexStack.getPosition() +1 ,"&quot;");
                                    System.out.println(sb.toString());
                                }
                            }
                        } else {
                            stack.pop();
                            stack.pop();
                        }
                    } else {
                        int num = stack.size();
                        for (int k = 0  ; k < num ; k++){
                            IndexStack indexStack = (IndexStack)stack.pop();
                            System.out.println("indexStack.getPosition() = "+indexStack.getPosition());
                            System.out.println("k = "+ k);
                            if(k == 0 || k == num - 1){

                            } else {
                                System.out.println("执行");
                                sb.replace(indexStack.getPosition() , indexStack.getPosition() +1 ,"&quot;");
                                System.out.println(sb.toString());
                            }
                        }
                    }
                    sb.append(c);
                    break;
                default:
                    sb.append(c);
            }
            System.out.println("lenght = " +sb.length());
        }
        return sb.toString();
    }

    public static int randomNum() {
        return (int) ((Math.random() * 9 + 1) * 1000);
    }

    public static String getTypeName1(String typeName){
        String typename = "";
        if(typeName.equals("0")){
            typename = "";
        } else if(typeName.equals("1")){
            typename = "tv";
        }else if(typeName.equals("2")){
            typename = "web";
        } else if(typeName.equals("3")){
            typename = "ova";
        } else if(typeName.equals("4")){
            typename = "剧场版";
        } else if(typeName.equals("5")){
            typename = "其他";
        }
        return typename;
    }

    public static String getTypeName2(String typeName){
        String typename = "";
        if(typeName.equals("0")){
            typename = "";
        } else if(typeName.equals("1")){
            typename = "act";
        }else if(typeName.equals("2")){
            typename = "avg";
        } else if(typeName.equals("3")){
            typename = "fps";
        } else if(typeName.equals("4")){
            typename = "ftg";
        } else if(typeName.equals("5")){
            typename = "mug";
        } else if(typeName.equals("6")){
            typename = "puz";
        } else if(typeName.equals("7")){
            typename = "rac";
        } else if(typeName.equals("8")){
            typename = "rpg";
        } else if(typeName.equals("9")){
            typename = "rts";
        } else if(typeName.equals("10")){
            typename = "slg";
        } else if(typeName.equals("11")){
            typename = "spg";
        } else if(typeName.equals("12")){
            typename = "stg";
        } else if(typeName.equals("13")){
            typename = "tab";
        } else if(typeName.equals("14")){
            typename = "avg";
        } else if(typeName.equals("15")){
            typename = "adv";
        }
        return typename;
    }

    public static String getPlatform(String platform){
        String platformName = "";
        switch (platform){
            case "0":
                platformName="";
                break;
            case "1":
                platformName="PC";
                break;
            case "2":
                platformName="Android";
                break;
            case "3":
                platformName="IOS";
                break;
            case "4":
                platformName="Mac OS";
                break;
            case "5":
                platformName="PSP";
                break;
            case "6":
                platformName="PS4";
                break;
            case "7":
                platformName="PS3";
                break;
            case "8":
                platformName="PS2";
                break;
            case "9":
                platformName="Xbox";
                break;
            case "10":
                platformName="Xbox One";
                break;
            case "11":
                platformName="Xbox360";
                break;
            case "12":
                platformName="街机";
                break;
            case "13":
                platformName="Nintendo Switch";
                break;
            case "14":
                platformName="Wii";
                break;
            case "15":
                platformName="Wii U";
                break;
            case "16":
                platformName="nds";
                break;
            case "17":
                platformName="GameCube";
                break;
            case "18":
                platformName="Dreamcast";
                break;
            case "19":
                platformName="Nintendo 64";
                break;
            case "20":
                platformName="PlayStation";
                break;
            case "21":
                platformName="SFC";
                break;
            case "22":
                platformName="FC";
                break;
            case "23":
                platformName="WonderSwan";
                break;
            case "24":
                platformName="WonderSwan Color";
                break;
            case "25":
                platformName="NEOGEO Pocket Color";
                break;
            case "26":
                platformName="GBA";
                break;
            case "27":
                platformName="GB";
                break;
            case "28":
                platformName="Virtual Boy";
                break;
        }
        return platformName;
    }

    /** -----------------------------------------------jsp-------------------------------------------------------------*/
    public static String getJob(String job) {
        String typeName = "";
        String[] type = job.split("、");
        for (int i = 0; i < type.length; i++) {
            if (i != 0) {
                typeName += "、";
            }
            if (type[i].equals("1")) {
                typeName += "声优";
            } else if (type[i].equals("2")) {
                typeName += "漫画家";
            } else if (type[i].equals("3")) {
                typeName += "制作人";
            } else if (type[i].equals("4")) {
                typeName += "音乐人";
            } else if (type[i].equals("6")) {
                typeName += "演员";
            } else if (type[i].equals("7")) {
                typeName += "绘师";
            } else if (type[i].equals("8")) {
                typeName += "作家";
            }
        }
        return typeName;
    }

    public static String getPersonInfo(PersonEntity personEntity) {
        String s1 = "";
        if (personEntity.getGender() != 0) {
            if (personEntity.getGender() == 1) {
                s1 += "性别 男";
            } else {
                s1 += "性别 女";
            }
        }
        if (personEntity.getBirthday() != null && !personEntity.getBirthday().isEmpty()) {
            s1 += " / 生日 " + personEntity.getBirthday();
        }
        if (personEntity.getBloodtype() == 1) {
            s1 += " / 血型 A";
        } else if (personEntity.getBloodtype() == 2) {
            s1 += " / 血型 B";
        } else if (personEntity.getBloodtype() == 3) {
            s1 += " / 血型 AB";
        } else if (personEntity.getBloodtype() == 4) {
            s1 += " / 血型 O";
        }
        if (personEntity.getHeight() != null && !personEntity.getHeight().isEmpty()) {
            s1 += " / 身高 " + personEntity.getHeight();
        }
        if (personEntity.getWeight() != null && !personEntity.getWeight().isEmpty()) {
            s1 += " / 体重 " + personEntity.getWeight();
        }
        return s1;
    }

    public static String getCharacterInfo(CharacterEntity characterEntity) {
        String s1 = "";
        if (characterEntity.getGender() != 0) {
            if (characterEntity.getGender() == 1) {
                s1 += "性别 男";
            } else {
                s1 += "性别 女";
            }
        }
        if (characterEntity.getBirthday() != null && !characterEntity.getBirthday().isEmpty()) {
            s1 += " / 生日 " + characterEntity.getBirthday();
        }
        if (characterEntity.getBloodtype() == 1) {
            s1 += " / 血型 A";
        } else if (characterEntity.getBloodtype() == 2) {
            s1 += " / 血型 B";
        } else if (characterEntity.getBloodtype() == 3) {
            s1 += " / 血型 AB";
        } else if (characterEntity.getBloodtype() == 4) {
            s1 += " / 血型 O";
        }
        if (characterEntity.getHeight() != null && !characterEntity.getHeight().isEmpty()) {
            s1 += " / 身高 " + characterEntity.getHeight();
        }
        if (characterEntity.getWeight() != null && !characterEntity.getWeight().isEmpty()) {
            s1 += " / 体重 " + characterEntity.getWeight();
        }
        return s1;
    }

    public static List<ArrayList<SubjectStaffDto>> getSubjectStaffDaoList(List<SubjectStaffEntity> subjectStaffEntityList){
        List<ArrayList<SubjectStaffDto>> subjectStaffDaos = new ArrayList<>();
        ArrayList<SubjectStaffDto> groups = new ArrayList<>();
        for (int i = 0 , num = subjectStaffEntityList.size() ; i < num ; i++){
            SubjectStaffEntity curEntity = subjectStaffEntityList.get(i);
            SubjectStaffDto subjectStaffDao = new SubjectStaffDto();
            subjectStaffDao.copy(curEntity);

            SubjectStaffEntity lastEntity = null;
            if(i - 1 >= 0){
                lastEntity = subjectStaffEntityList.get(i-1);
            }

            if(i == 0 ){
                groups.add(subjectStaffDao);
            } else {
                System.out.println("i = "+i);
                System.out.println("lastEntity.getJob() = "+lastEntity.getJob());
                System.out.println("curEntity.getJob() = "+curEntity.getJob());
                if(lastEntity.getJob().equals(curEntity.getJob())){
                    groups.add(subjectStaffDao);
                } else {
                    subjectStaffDaos.add(groups);
                    groups = new ArrayList<>();
                    groups.add(subjectStaffDao);
                }
            }

            if(i == num -1){
                subjectStaffDaos.add(groups);
            }
        }
        return subjectStaffDaos;
    }

    public static List<MenuItem> getSubjectTypeMenuList(String type){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("资源",""));
        if(type.equals("1")){
            menuItemList.add(new MenuItem("图片",""));
            menuItemList.add(new MenuItem("画集",""));
        } else if(type.equals("2")){
            menuItemList.add(new MenuItem("图片",""));
            menuItemList.add(new MenuItem("画集",""));
        } else if(type.equals("3")){
            menuItemList.add(new MenuItem("图片",""));
            menuItemList.add(new MenuItem("画集",""));
        } else if(type.equals("4")){
            menuItemList.add(new MenuItem("图片",""));
            menuItemList.add(new MenuItem("画集",""));
        } else if(type.equals("6")){
        }
        return menuItemList;
    }
}
