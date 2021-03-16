package com.dear.api.post.service.impl;

import com.alibaba.fastjson.JSON;
import com.dear.api.post.service.IPostService;
import com.dear.common.annotaion.MultipleTransaction;
import com.dear.common.base.BaseService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.common.cache.redis.config.RedisKey;
import com.dear.common.util.tools.TimeUtil;
import com.dear.domain.bbs.Post;
import com.dear.domain.bbs.PostReply;
import com.dear.domain.bbs.PostReplyFloor;
import com.dear.domain.bbs.User;
import com.dear.domain.bbs.dto.PostDto;
import com.dear.domain.bbs.dto.PostReplyFloorDto;
import com.dear.domain.bbs.dto.ReplyDto;
import com.dear.domain.bbs.vo.*;
import com.dear.mapper.bbs.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl extends BaseService implements IPostService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostReplyMapper postReplyMapper;

    @Autowired
    private PostReplyFloorMapper postReplyFloorMapper;

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson createPost(PostDto vo) {

        /**
         * 用户是否存在
         */
        if(this.userMapper.selectCountById(vo.getUserId()) == 0) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        /**
         * 社区是否存在
         */
        if(this.communityMapper.getCommunityCountById(vo.getComId()) == 0) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        /**
         * 创建帖子
         */
        Post post = new Post();
        post.setComId(vo.getComId());
        post.setCreateTime(new Date());
        post.setUserId(vo.getUserId());
        post.setContent(vo.getContent());
        post.setDel(false);
        post.setHide(false);
        post.setGood(false);
        post.setTop(false);
        post.setReplyCount(0);
        post.setTitle(vo.getTitle());

        int i = this.postMapper.insertSelective(post);

        this.base.isException(i, ":createPost:创建 帖子 失败");

        /**
         * 创建一楼回复
         */
        PostReply postReply = new PostReply();

        postReply.setContent(vo.getContent());
        postReply.setCreateTime(new Date());
        postReply.setDel(false);
        postReply.setNumber(1);
        postReply.setPostId(post.getId());
        postReply.setUserId(vo.getUserId());

        int count = this.postReplyMapper.insertSelective(postReply);

        this.base.isException(count, ":createReply:保存 回复 失败");

        //创建成功后清除缓存
        this.cache.hdel(RedisKey.POST_H, String.valueOf(post.getId()));

        //清除全部分页缓存
        this.cache.del(RedisKey.POST_PAGE_H);

        return new ResultJson(ResultCode.OK.getCode(), null);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson<PageInfo<List<PostVO>>> getPostList(Integer pageNo, Integer pageSize, Integer comId) {

        String key = pageNo + "_" + pageSize;

        List<PostVO> post = this.cache.hgetList(RedisKey.POST_PAGE_H, key, PostVO.class);

        if(post == null) {

            PageHelper.startPage(pageNo, pageSize);

            post = this.postMapper.getPostList(comId);

            if(post == null) {

                return new ResultJson(ResultCode.OK.getCode(), null);
            }

            //this.cache.hsetList(RedisKey.POST_PAGE_H, key, post);·
        }

        post.forEach(p -> {

            User user = this.getUser(p.getUserId());

            p.setHeadUrl(user.getHeadUrl());
            p.setNickName(user.getNickName());
        });

        return new ResultJson(ResultCode.OK.getCode(), new PageInfo(post));
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson createReply(ReplyDto vo) {

        int i = this.postMapper.getPostByIdAndDel(vo.getPostId());

        if (i == 1) {

            Integer max = this.postReplyMapper.getMaxNumber(vo.getPostId());

            if(max == null) max = 1;

            PostReply postReply = new PostReply();

            postReply.setContent(vo.getContent());
            postReply.setCreateTime(new Date());
            postReply.setDel(false);
            postReply.setNumber(++max);
            postReply.setPostId(vo.getPostId());
            postReply.setUserId(vo.getUserId());

            int count = this.postReplyMapper.insertSelective(postReply);

            this.base.isException(count, ":createReply:保存 回复 失败");

            //回复数变更队列
            this.cache.lpush(RedisKey.REPLY_NUMBER_QUEUE, String.valueOf(vo.getPostId()));

            //清除缓存 todo


            return new ResultJson(ResultCode.OK.getCode(), null);
        }

        return new ResultJson(ResultCode.ERROR.getCode(), "请求错误 i=" + i);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson<PostDataVO> getPostDataById(Integer postId) {

        //获取帖子主内容
        PostDataVO post = this.cache.hgetObject(RedisKey.POST_H, String.valueOf(postId), PostDataVO.class);

        if(post == null) {

            post = this.postMapper.getPostDataById(postId);

            if(post == null) {

                return new ResultJson(ResultCode.POST_REPLY_NOT_FOUND.getCode(), ResultCode.POST_REPLY_NOT_FOUND.getMessage());
            }

            //this.cache.hsetObject(RedisKey.POST_H, String.valueOf(post.getId()), post);
        }

        //找不到帖子
        if(post == null) {

            return new ResultJson(ResultCode.POST_REPLY_NOT_FOUND.getCode(), ResultCode.POST_REPLY_NOT_FOUND.getMessage());
        }

        return new ResultJson(ResultCode.OK.getCode(), post);
    }

    private User getUser(Integer userId) {

        User user = this.cache.hgetObject(RedisKey.USER_H, userId, User.class);

        if(user == null) {

            user = this.userMapper.selectByPrimaryKey(userId);

            //this.cache.hsetObject(RedisKey.USER_H, userId, user);
        }

        return user;
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson createFloor(PostReplyFloorDto vo) {

        PostReply postReply = this.postReplyMapper.getReplyById(vo.getReplyId());

        if(postReply != null) {

            Integer max = this.postReplyFloorMapper.getMaxNumber(vo.getReplyId());

            if(max == null) max = 0;

            PostReplyFloor postReplyFloor = new PostReplyFloor();

            postReplyFloor.setContent(vo.getContent());
            postReplyFloor.setDel(false);
            postReplyFloor.setCreateTime(new Date());
            postReplyFloor.setNumber(++max);
            postReplyFloor.setUserId(vo.getUserId());
            postReplyFloor.setPostId(postReply.getPostId());
            postReplyFloor.setReplyId(vo.getReplyId());

            int i = this.postReplyFloorMapper.insertSelective(postReplyFloor);

            this.base.isException(i, ":createFloor:创建 楼中楼回复 失败");

            this.cache.lpush(RedisKey.REPLY_NUMBER_QUEUE, String.valueOf(postReply.getPostId()));

            //清理缓存
            this.cache.hdel(RedisKey.POST_REPLY_FLOOR_PAGE_H, String.valueOf(vo.getReplyId()));

            return new ResultJson(ResultCode.OK.getCode(), null);
        }

        return new ResultJson(ResultCode.POST_REPLY_NOT_FOUND.getCode(), ResultCode.POST_REPLY_NOT_FOUND.getMessage());
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson<PostDataVO> getPostReplyById(Integer pageNo, Integer pageSize, Integer postId) {

        //获取帖子回复内容 (分页)
        String replyKey = pageNo + "_" + pageSize;

        PageInfo pageInfo = this.cache.hgetObject(RedisKey.POST_REPLY_PAGE_H, replyKey, PageInfo.class);

        if(pageInfo == null) {

            PageHelper.startPage(pageNo, pageSize);

            List<PostReplyDataVO> list = this.postReplyMapper.getListByPostId(postId);

            pageInfo = new PageInfo(list);

            pageInfo.setList(list);

            //this.cache.hsetObject(RedisKey.POST_REPLY_PAGE_H, replyKey, pageInfo);
        }

        //获取帖子回复下的楼中楼
        List list = pageInfo.getList();

        List listVO = new ArrayList();

        list.forEach(p -> {

            PostReplyDataVO p1 = JSON.parseObject(JSON.toJSONString(p), PostReplyDataVO.class);

            List<FloorVO> floorList = this.cache.hgetList(RedisKey.POST_REPLY_FLOOR_PAGE_H,
                    String.valueOf(p1.getId()), FloorVO.class);

            if(floorList == null) {

                floorList = this.postReplyFloorMapper.getListByReplyId(p1.getId());

                //this.cache.hsetList(RedisKey.POST_REPLY_FLOOR_PAGE_H, String.valueOf(p1.getId()), floorList);
            }

            floorList.forEach(b -> {

                User userF = this.getUser(b.getUserId());

                b.setHeadUrl(userF.getHeadUrl());
                b.setNickName(userF.getNickName());
            });

            User p1User = this.getUser(p1.getUserId());

            p1.setNickName(p1User.getNickName());
            p1.setHeadUrl(p1User.getHeadUrl());

            p1.setFloorList(floorList);

            listVO.add(p1);
        });

        pageInfo.setList(listVO);

        return new ResultJson(ResultCode.OK.getCode(), pageInfo);
    }
}
