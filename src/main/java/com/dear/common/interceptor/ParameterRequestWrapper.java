package com.dear.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 存储请求数据
     */
    private String bodyParams;

    //private Map<String, String[]> params;

    public ParameterRequestWrapper(HttpServletRequest request, String userId) {
        super(request);


        JSONObject jsonObject = JSON.parseObject(this.renewBody(request));

        if(jsonObject == null) {

            jsonObject = new JSONObject();
        }

        if(jsonObject.containsKey("userId")) {

            //存在userId参数
            jsonObject.put("userId2", userId);

        } else {

            jsonObject.put("userId", userId);
        }

        bodyParams = jsonObject.toJSONString();
    }

    /**
     * 重写getInputStream方法
     *
     * @return
     */
    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bodyParams.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;

    }

    /**
     * 重写getReader方法
     *
     * @return
     */
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 读取body的值
     *
     * @param request
     */
    private String renewBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 取出参数放在全局变量
     *
     * @param req
     */
    /*private void renewParameterMap(HttpServletRequest req) {
        String queryString = req.getQueryString();
        if (queryString != null && queryString.trim().length() > 0) {
            String[] params = queryString.split("&");
            for (int i = 0; i < params.length; i++) {
                int splitIndex = params[i].indexOf("=");
                if (splitIndex == -1) {
                    continue;
                }
                String key = params[i].substring(0, splitIndex);
                if (!this.params.containsKey(key)) {
                    if (splitIndex < params[i].length()) {
                        String value = params[i].substring(splitIndex + 1);
                        this.params.put(key, new String[]{value});
                    }
                }
            }
        }
    }*/

}