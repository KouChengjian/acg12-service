package com.acg12.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/10.
 */
public class FileUpload {

    //文件上传
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String osName = System.getProperties().getProperty("os.name");
        String fileName = file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("images/");
        int index = 0;
        if(osName.contains("Windows")){
            index = path.indexOf("acg12\\");
            path = path.substring(0 , index);
            path += "images\\acg12";
        } else {
            index = path.indexOf("acg12/");
            path = path.substring(0 , index);
            path += "images/acg12";
        }

        System.err.printf("path = " + path);
        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return "acg12/img/" + tempFile.getName();
    }
}
