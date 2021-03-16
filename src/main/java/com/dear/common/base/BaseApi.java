package com.dear.common.base;

import com.dear.common.util.BaseUtils;
import com.dear.common.util.jwt.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseApi {

    @Autowired
    public BaseUtils base;

    /**
     * 获取用户id
     *
     * @param request
     * @return
     */
    public Integer getLoginUserId(HttpServletRequest request) {

        String auth = request.getHeader("Authorization");

        if ((auth != null) && (auth.length() > 7)) {

            Claims claims = JwtHelper.parseJWT(auth);

            if (claims != null) {

                String modifyTimesAndUserId = claims.get("userId") == null ? "" : claims.get("userId").toString();

                return Integer.parseInt(modifyTimesAndUserId);
            }
        }

        return null;
    }
}
