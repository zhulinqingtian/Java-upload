package com.lz.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gxq")
public class UpLoadController {
    public static final Logger logger = LoggerFactory.getLogger(UpLoadController.class);


    @PostMapping("/upload_img")
    public Map<String, Boolean> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Boolean> resultMap = new HashMap<>();
        String uploadPath = "c:/java/project/images/";
        String s = uploadFile(uploadPath, file);
        resultMap.put(s,true);
        return resultMap;
    }

    @PostMapping("/upload_imgs")
    public Map<String, Boolean> uploadImages(@RequestParam("file") MultipartFile[] files){
        String uploadPath = "c:/java/project/images/";
        Map<String, Boolean> resultMap = new HashMap<>();
        for(MultipartFile file:files){
            String fileName = uploadFile(uploadPath,file);
            resultMap.put(fileName,true);
        }

        return resultMap;

    }


    private String uploadFile(String uploadPath, MultipartFile file){
        InputStream inputStream = null;
        OutputStream os = null;
        String path = null;
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        try{
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(uploadPath);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            inputStream = file.getInputStream();
            path = tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(path);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  fileName;
    }



}
