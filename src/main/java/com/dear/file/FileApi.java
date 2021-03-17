package com.dear.file;

import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation(value = "上传文件", notes = "接口需要登录，文件上传的统一接口，若无特殊需求，一律走此接口进行文件上传")
    @RequestMapping(value = "/v1/upload", method = RequestMethod.POST)
    public ResultJson upload(@RequestParam("file") MultipartFile file) {

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
            return new ResultJson(ResultCode.OK.getCode(), "目标文件已存在" + filePath);
        }
        if (filePath.endsWith(File.separator)) { // 判断文件是否为目录
            return new ResultJson(ResultCode.OK.getCode(), "目标文件不能为目录！");
        }
        // 判断目标文件所在的目录是否存在
        if (!cFile.getParentFile().exists()) {
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            if (!cFile.getParentFile().mkdirs()) { // 判断创建目录是否成功
                return new ResultJson(ResultCode.OK.getCode(), "创建目标文件所在的目录失败！");
            }
        }
        try {
            file.transferTo(cFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("写入文件失败");
        }
        return new ResultJson(ResultCode.OK.getCode(), "/" + accessPath + path22);

    }

    @RequestMapping("/admin/uploadTest")
    public @ResponseBody Map<String,Object> uploadTest(@RequestParam("file")MultipartFile[] files, HttpServletRequest request){
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        Map<String, String[]> parameterMap = multipartRequest.getParameterMap();    //获取前端传过来的参数
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        System.out.println("前端传过来的参数：------------");
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();    //key是字符串
            String[] value = entry.getValue();  //value是字符串数组
            System.out.println(key+":"+ Arrays.toString(value));
        }
        System.out.println("文件的个数："+files.length);

        System.out.println("打印文件的名字：--------------");
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }

        //返回前端的json
        Map<String, Object> map = new HashMap<>();
        map.put("status",200);
        map.put("msg","上传成功");
        return map;
    }
}
