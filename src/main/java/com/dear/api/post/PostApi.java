package com.dear.api.post;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dear.api.post.service.IPostService;
import com.dear.common.annotaion.Login;
import com.dear.common.base.BaseApi;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.common.util.jwt.AesUtil;
import com.dear.domain.bbs.dto.PostDto;
import com.dear.domain.bbs.dto.PostReplyFloorDto;
import com.dear.domain.bbs.dto.ReplyDto;
import com.dear.domain.bbs.vo.PostDataVO;
import com.dear.domain.bbs.vo.PostVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Api(tags = {"帖子中心-帖子主页"})
@RestController
@RequestMapping("/post/home")
public class PostApi extends BaseApi {

    @Autowired
    private IPostService service;

    @ApiOperation(value = "帖子列表",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 成功后返回业务码0" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* pageNo PageSize " +
                    "\n* ")
    @RequestMapping(value = "/v1/getPostList", method = RequestMethod.POST)
    public ResultJson<PageInfo<List<PostVO>>> getPostList(@RequestBody String params){

        JSONObject jo = JSON.parseObject(params);

        Integer pageNo = jo.getInteger("pageNo");

        Integer pageSize = jo.getInteger("pageSize");

        Integer comId = jo.getInteger("comId");

        if(pageNo == null || pageSize == null || comId == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.getPostList(pageNo, pageSize, comId);
    }

    @ApiOperation(value = "帖子详情",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 成功后返回业务码0" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* postId 帖子id (String) (必填) " +
                    "\n* pageNo PageSize " +
                    "\n* ")
    @RequestMapping(value = "/v1/getPostDataById", method = RequestMethod.POST)
    public ResultJson<PostDataVO> getPostDataById(@RequestBody String params){

        JSONObject jo = JSON.parseObject(params);

        Integer postId = jo.getInteger("postId");

        if(postId == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.getPostDataById(postId);
    }

    @ApiOperation(value = "帖子回复列表",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 成功后返回业务码0" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* postId 帖子id (String) (必填) " +
                    "\n* pageNo PageSize " +
                    "\n* ")
    @RequestMapping(value = "/v1/getPostReplyById", method = RequestMethod.POST)
    public ResultJson<PostDataVO> getPostReplyById(@RequestBody String params){

        JSONObject jo = JSON.parseObject(params);

        Integer postId = jo.getInteger("postId");

        Integer pageNo = jo.getInteger("pageNo");

        Integer pageSize = jo.getInteger("pageSize");

        if(pageNo == null || pageSize == null || postId == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.getPostReplyById(pageNo, pageSize, postId);
    }

    @ApiOperation(value = "发帖",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* {\"content\":\"123\",\"title\":\"123\"}" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 成功后返回业务码0" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* content 帖子正文 (String) (必填) " +
                    "\n* title 帖子标题 (String) (必填) " +
                    "\n* ")
    @Login
    @RequestMapping(value = "/v1/createPost", method = RequestMethod.POST)
    public ResultJson createPost(@RequestBody PostDto vo){

        if(vo == null || vo.getTitle() == null || vo.getContent() == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.createPost(vo);
    }

    @ApiOperation(value = "回帖",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* {\"content\":\"123456\",\"postId\":\"20\"}" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 成功后返回业务码0" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* content 回帖正文 (String) (必填) " +
                    "\n* postId 帖子id (Integer) (必填) " +
                    "\n* ")
    @Login
    @RequestMapping(value = "/v1/createReply", method = RequestMethod.POST)
    public ResultJson createReply(@RequestBody ReplyDto vo){

        if(vo == null || StringUtils.isBlank(vo.getContent()) || vo.getPostId() == null){

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.createReply(vo);
    }

    @ApiOperation(value = "楼中楼回复",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* {\"content\":\"123456\",\"replyId\":\"20\"}" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 成功后返回业务码0" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* content 回帖正文 (String) (必填) " +
                    "\n* replyId 回复id (Integer) (必填) " +
                    "\n* ")
    @Login
    @RequestMapping(value = "/v1/createFloor", method = RequestMethod.POST)
    public ResultJson createFloor(@RequestBody PostReplyFloorDto vo){

        if(vo == null || StringUtils.isBlank(vo.getContent()) || vo.getReplyId() == null){

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.createFloor(vo);
    }



}
