package com.dear.mapper.bbs;

import com.dear.domain.bbs.FileUrl;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUrlMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(FileUrl record);

    int insertSelective(FileUrl record);

    FileUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileUrl record);

    int updateByPrimaryKey(FileUrl record);
}