package com.jsss.share.controller;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import com.jsss.common.Toolbox;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@CrossOrigin(origins = "${jsss.web.path}", allowedHeaders = "*", allowCredentials = "true")
public class ShareController implements  ErrorCode {

    private static final Logger logger = LoggerFactory.getLogger(ShareController.class);

    @Value("${jsss.web.path}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${wk.image.storage}")
    private String wkImageStorage;



    @PostMapping("/uploadImage")
    @ResponseBody
    public ResponseModel uploadImage(String token,String imageData) {

        // 截取 base64 编码的部分
        String base64Data = imageData.substring(imageData.indexOf(",") + 1);

        // 解码Base64字符串为字节数组
        byte[] imageBytes = Base64Utils.decodeFromString(base64Data);

        // 文件名
        String fileName = Toolbox.getRandomString();

        // 保存字节数组为图片文件
        String imagePath = wkImageStorage + "/" +  fileName+ ".png";

        try (FileOutputStream fos = new FileOutputStream(imagePath)) {
            fos.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseModel("Error saving image");
        }

        return new ResponseModel(fileName);

    }

    // 获取长图
    @RequestMapping(path = "/share/image/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void getShareImage(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        if (StringUtils.isBlank(fileName)) {
            throw new BusinessException(PARAMETER_ERROR,"文件名不能为空!");
        }
        File file = new File(wkImageStorage + "/" + fileName + ".png");

        if (!file.exists()) {
            throw new BusinessException(PARAMETER_ERROR,"文件不存在!");
        }

        response.setContentType("image/png");

        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            logger.error("获取长图失败: " + e.getMessage());
        }


    }

}
