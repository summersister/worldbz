package com.dear.mapper.bbs;

import com.dear.domain.bbs.Post;
import com.dear.domain.bbs.vo.PostDataVO;
import com.dear.domain.bbs.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    int insertSelective(Post record);

    /**
     * 获取帖子列表
     *
      * @return
     */
    List<PostVO> getPostList(@Param("comId") Integer comId);

    /**
     * 判断帖子是否存在
     *
     * @param id
     * @return
     */
    int getPostByIdAndDel(@Param("id") Integer id);

    /**
     * 给帖子增加一个回复数
     *
     * @param postId
     * @return
     */
    int addNumberCount(@Param("postId") Integer postId);

    /**
     * 根据id获取帖子内容
     *
     * @param postId
     * @return
     */
    PostDataVO getPostDataById(@Param("postId") Integer postId);











    int deleteByPrimaryKey(Integer id);

    int insert(Post record);


    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

}