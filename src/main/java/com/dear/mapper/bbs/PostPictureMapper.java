package com.dear.mapper.bbs;

import com.dear.domain.bbs.PostPicture;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPictureMapper {

    int insertSelective(PostPicture record);

    int deleteByPrimaryKey(Integer id);

    int insert(PostPicture record);

    PostPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostPicture record);

    int updateByPrimaryKey(PostPicture record);
}