package com.dear.mapper.bbs;

import com.dear.domain.bbs.PostReplyFloor;
import com.dear.domain.bbs.vo.FloorVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReplyFloorMapper {

    Integer getMaxNumber(@Param("replyId") Integer replyId);

    int insertSelective(PostReplyFloor record);

    List<FloorVO> getListByReplyId(@Param("replyId") Integer replyId);





    int deleteByPrimaryKey(Integer id);

    int insert(PostReplyFloor record);


    PostReplyFloor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostReplyFloor record);

    int updateByPrimaryKey(PostReplyFloor record);

}