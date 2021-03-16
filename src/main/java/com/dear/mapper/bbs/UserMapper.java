package com.dear.mapper.bbs;

import com.dear.domain.bbs.User;
import com.dear.domain.bbs.vo.UserDataVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    int getUserCountByUserName(@Param("userName") String userName);

    /**
     * 根据用户名 密码 查找用户
     *
     * @param userName
     * @param passWord
     * @return
     */
    User getUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("userId") Integer userId);

    int selectCountById(@Param("userId") Integer userId);

    int deleteByPrimaryKey(@Param("userId") Integer userId);

    /**
     * 获取用户详细信息
     *
     * @param userId
     * @return
     */
    UserDataVO getUserDataByUserId(@Param("userId") Integer userId);

    int getUserCountByNickName(@Param("nickName") String nickName);

    int getUserCountByUserNameAndNickName(@Param("userName") String userName);

    int updateNickNameAndUserName(@Param("userId") Integer userId,
                                  @Param("userName") String userName,
                                  @Param("nickName") String nickName);

    int updateUserHeadUrl(@Param("userId") Integer userId, @Param("headUrl") String headUrl);
}