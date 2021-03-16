package com.dear.api.bbs.service;

import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.vo.UserDataVO;

public interface IUserService {

    ResultJson getUserByUserId(Integer userId);

    ResultJson getUserDataByUserId(Integer userId);

    ResultJson updateUserDataByUserId(UserDataVO vo);

    ResultJson updateUserHeadUrl(Integer userId, String imageUrl);
}
