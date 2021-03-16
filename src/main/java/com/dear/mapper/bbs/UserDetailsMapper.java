package com.dear.mapper.bbs;

import com.dear.domain.bbs.UserDetails;
import com.dear.domain.bbs.vo.UserDataVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsMapper {

    int insert(UserDetails record);

    int updateDetailsByUserId(UserDataVO userDataVO);
}