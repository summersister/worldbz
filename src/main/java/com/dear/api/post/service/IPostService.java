package com.dear.api.post.service;

import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.dto.PostDto;
import com.dear.domain.bbs.dto.PostReplyFloorDto;
import com.dear.domain.bbs.dto.ReplyDto;
import com.dear.domain.bbs.vo.PostBasicDataVO;
import com.dear.domain.bbs.vo.PostDataVO;
import com.dear.domain.bbs.vo.PostVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPostService {

    /**
     * 发帖
     *
     * @param vo
     * @return
     */
    ResultJson createPost(PostDto vo);

    /**
     * 帖子列表
     *
     * @return
     */
    ResultJson<PageInfo<List<PostVO>>> getPostList(Integer pageNo, Integer pageSize, Integer comId);

    /**
     * 回帖
     *
     * @param vo
     * @return
     */
    ResultJson createReply(ReplyDto vo);

    /**
     * 帖子详情
     *
     * @param postId
     * @return
     */
    ResultJson<PostDataVO> getPostDataById(Integer postId);

    /**
     * 楼中楼回复
     *
     * @param vo
     * @return
     */
    ResultJson createFloor(PostReplyFloorDto vo);

    /**
     * 帖子回复列表
     *
     * @param pageNo
     * @param pageSize
     * @param postId
     * @return
     */
    ResultJson<PostDataVO> getPostReplyById(Integer pageNo, Integer pageSize, Integer postId);
}
