package com.dear.mapper.bbs;

import com.dear.domain.bbs.PostReply;
import com.dear.domain.bbs.vo.PostReplyDataVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReplyMapper {

    int insertSelective(PostReply record);

    PostReply getReplyById(@Param("replyId") Integer replyId);

    /**
     * 获取最大回复号
     *
     * @param id
     */
    Integer getMaxNumber(@Param("id") Integer id);

    /**
     * 获取回帖详情
     *
     * @param postId
     * @return
     */
    List<PostReplyDataVO> getListByPostId(@Param("postId") Integer postId);










    int deleteByPrimaryKey(Integer id);

    int insert(PostReply record);

    PostReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostReply record);

    int updateByPrimaryKey(PostReply record);

}