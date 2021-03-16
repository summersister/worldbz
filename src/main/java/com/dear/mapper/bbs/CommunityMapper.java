package com.dear.mapper.bbs;

import com.dear.domain.bbs.Community;
import com.dear.domain.bbs.vo.CommunityDetailsVO;
import com.dear.domain.bbs.vo.CommunityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<CommunityVO> getCommunityList();

    /**
     * 获取社区信息
     *
     * @param id
     * @return
     */
    CommunityDetailsVO getCommunityById(@Param("id") Integer id);

    /**
     * 社区是否存在
     *
     * @param id
     * @return
     */
    int getCommunityCountById(@Param("id") Integer id);
}