package com.dear.mapper.bbs;

import com.dear.domain.bbs.UserIp;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIpMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserIp record);

    int insertSelective(UserIp record);

    UserIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserIp record);

    int updateByPrimaryKey(UserIp record);
}