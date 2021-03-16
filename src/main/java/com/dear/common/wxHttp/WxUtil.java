package com.dear.common.wxHttp;

import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

@Configuration
public class WxUtil {

    public String createGetData(String url, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        String s = sb.toString();
        System.out.println("字符串拼接后是：" + s);
        return s;
    }
}

