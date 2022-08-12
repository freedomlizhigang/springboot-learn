package com.coins.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * @description: 文件上传类
 * @author: lzg(萤火科技 ： 854378082 @ qq.com)
 * @date: 2022/7/30
 **/
@Component
public class UploadFileUtil {

    /**
     * 项目端口
     */
    @Value("${upload.dir}")
    public String uploadDir;

    /**
     * 上传文件
     *
     * @param multipartFile 文件对象
     * @param dir           上传目录
     * @return
     */
    public CommonResult uploadFile(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multiRequest.getFile("coinFile");
            String dir = request.getParameter("dir");
            if (multipartFile == null || multipartFile.isEmpty()) {
                return ResultUtil.validator(403, "请选择文件");
            }
            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 查检文件后缀是否允许上传
            List suffixList = new ArrayList<String>(Arrays.asList(".jpg", ".jpeg", ".gif", ".png", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".rar", ".zip", ".swf", ".apk", ".mp4", ".mp3", ".avi"));
            if (suffixList.contains(fileSuffix) == false) {
                return ResultUtil.validator(403, "不允许上传的文件类型");
            }
            // uuid 生成文件名
            String fileName = String.valueOf(UUID.randomUUID()) + fileSuffix;
            String basePath = getDir(dir);
            // 获取文件对象
            File file = new File(basePath, fileName);
            multipartFile.transferTo(file);
            // 如果是图片且需要压缩
            imageCompression(request,file,fileSuffix);
            // 返回绝对路径
            return resultJson(dir,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error(500, "上传失败");
    }

    public CommonResult uploadBase64File(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            String dir = request.getParameter("dir");
            String base64 = request.getParameter("coinFile");
            if (StringUtils.isBlank(base64)) {
                return ResultUtil.validator(403, "请选择文件");
            }
            String dataPrix = "";
            String data = "";
            if (base64 == null || "".equals(base64)) {
                return ResultUtil.validator(403, "请选择文件");
            } else {
                String[] d = base64.split("base64,");
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    return ResultUtil.validator(403, "数据不合法");
                }
            }
            String fileSuffix = "";
            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
                fileSuffix = ".jpg";
            } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
                fileSuffix = ".ico";
            } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
                fileSuffix = ".gif";
            } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
                fileSuffix = ".png";
            } else {
                return ResultUtil.validator(403, "上传图片格式不合法");
            }
            // uuid 生成文件名
            String fileName = String.valueOf(UUID.randomUUID()) + fileSuffix;
            String basePath = getDir(dir);
            // 创建新的文件
            File file = base64ToFile(data, basePath, fileName);
            // 如果是图片且需要压缩
            imageCompression(request, file, fileSuffix);
            // 返回绝对路径
            return resultJson(dir,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.error(500, "上传失败");
    }
    // 返回值
    private CommonResult resultJson(String dir,String fileName){
        String url = "/upload/" + dir + "/" + LocalDateUtils.getLocalDateUnsignedStr() + "/" + fileName;
        Map<String, Object> result = new HashMap<>();
        result.put("url", url);
        result.put("filename", fileName);
        return ResultUtil.success(result);
    }
    //base64字符串转化成图片
    private File base64ToFile(String base64, String basePath, String fileName) throws Exception {
        base64 = base64.toString().replace("\r\n", "");
        //创建文件目录
        File file = new File(basePath, fileName);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(base64);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    // 本地图片压缩
    private void imageCompression(HttpServletRequest request, File file, String fileSuffix) throws Exception {
        String thumb = request.getParameter("thumb");
        if (StringUtils.isBlank(thumb) == false && thumb.equals("1")) {
            BufferedImage image = ImageIO.read(new FileInputStream(file)); // 压缩图片用的
            /*
             * size(width,height) 若图片横比1920小，高比1080小，不变
             * 若图片横比1920小，高比1080大，高缩小到1080，图片比例不变 若图片横比1920大，高比1080小，横缩小到1920，图片比例不变
             * 若图片横比1920大，高比1080大，图片按比例缩小，横为1920或高为1080
             * 图片格式转化为jpg,质量不变
             */
            //设置统一图片后缀名
            String suffixName;
            if (".png".equals(fileSuffix)) {
                suffixName = ".png";
            } else {
                suffixName = ".jpg";
            }
            if (image.getHeight() > 608 || image.getWidth() > 1080) {
                if (!".png".equals(suffixName)) {
                    Thumbnails.of(file).size(1080, 608).outputQuality(0.75f).outputFormat("jpg").toFile(file);
                } else {
                    Thumbnails.of(file).size(1080, 608).outputQuality(0.75f).toFile(file);
                }
            } else {
                if (!".png".equals(suffixName)) {
                    Thumbnails.of(file).outputQuality(0.75f).scale(1f).outputFormat("jpg").toFile(file);
                } else {
                    Thumbnails.of(file).outputQuality(0.75f).scale(1f).toFile(file);
                }
            }
        }
    }

    // 路径
    private String getDir(String dir) {
        // 根路径，在 resources/static/upload
        dir = StringUtils.isBlank(dir) ? "images/" : (dir + "/");
        dir += LocalDateUtils.getLocalDateUnsignedStr();
        String basePath = uploadDir + dir;
        // CoinLogUtils.info("文件目录：" + basePath);
        // 文件夹不存在，则新建
        File fileExist = new File(basePath);
        if (!fileExist.exists()) {
            fileExist.mkdirs();
        }
        return basePath;
    }

}
