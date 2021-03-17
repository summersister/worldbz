package com.dear.file;

import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@Api(tags = {"文件管理"})
@RequestMapping("/base/file")
public class FileApi {

    @Value("${fileSetting.savePath}")
    private String savePath;

    @Value("${fileSetting.accessPath}")
    private String accessPath;


    @ApiOperation(value = "上传文件单")
    @RequestMapping(value = "/v1/upload", method = RequestMethod.POST)
    public ResultJson upload(@RequestParam("file") MultipartFile file) {

        return new ResultJson(ResultCode.OK.getCode(), this.createFile(file));
    }

    @ApiOperation(value = "上传文件多")
    @RequestMapping("/admin/uploadList")
    public @ResponseBody ResultJson uploadList(@RequestParam("file")MultipartFile[] files, HttpServletRequest request){
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        Map<String, String[]> parameterMap = multipartRequest.getParameterMap();    //获取前端传过来的参数
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();    //key是字符串
            String[] value = entry.getValue();  //value是字符串数组
            System.out.println(key+":"+ Arrays.toString(value));
        }
        System.out.println("文件的个数："+files.length);
        List<String> localPathList = new ArrayList();
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
            String fileLocalPath = this.createFile(file);
            if(StringUtils.isNotBlank(fileLocalPath)) {
                localPathList.add(fileLocalPath);
            }
        }

        return new ResultJson(ResultCode.OK.getCode(), localPathList);
    }

    /**
     * 校验及写入磁盘逻辑
     * @param file
     * @return
     */
    private String createFile(MultipartFile file) {

        String basePath = "v1/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
        UUID uid = UUID.randomUUID();
        String fileName = uid.toString().replaceAll("-","");
        String tmp = file.getOriginalFilename();
        tmp = tmp.substring(tmp.lastIndexOf("."));
        fileName = fileName + tmp;
        String path22 = "/" + basePath + fileName;
        String filePath = savePath + path22;
        File cFile = new File(filePath);
        if (cFile.exists()) { // 判断文件是否存在
            return null;
        }
        if (filePath.endsWith(File.separator)) { // 判断文件是否为目录
            return null;
        }
        // 判断目标文件所在的目录是否存在
        if (!cFile.getParentFile().exists()) {
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            if (!cFile.getParentFile().mkdirs()) { // 判断创建目录是否成功
                return null;
            }
        }
        try {
            file.transferTo(cFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("写入文件失败");
        }

        return "/" + accessPath + path22;
    }
}
