package com.coins.cms.controller;

import com.coins.ums.controller.UmsApiRestController;
import com.coins.utils.CoinLogUtils;
import com.coins.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 文件上传类
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/30
 **/
@UmsApiRestController("/common")
public class UploadController {
    @Autowired
    private UploadFileUtil uploadFileUtil;

    @PostMapping("/upload")
    public Object uploadFile(HttpServletRequest request) {
//        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        MultipartHttpServletRequest mhsr = resolver.resolveMultipart(request);
//        MultipartFile file = mhsr.getMultiFileMap().get("file").get(0);
        return uploadFileUtil.uploadFile(request);
    }
    @PostMapping("/base64")
    public Object uploadBase64File(HttpServletRequest request) {
        return uploadFileUtil.uploadBase64File(request);
    }
}
