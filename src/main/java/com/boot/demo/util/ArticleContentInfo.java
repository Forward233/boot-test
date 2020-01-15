package com.boot.demo.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: yhl
 * @DateTime: 2019/12/16 11:33
 * @Description:
 */
@Data
@ToString
public class ArticleContentInfo implements Serializable {

    private static final long serialVersionUID = -5571050878248449576L;
    @JSONField(ordinal = -2)
    private int id;
    /**
     * 关键词
     */
    private String keyword;
    private String source;
    private String sourceUrl;
    /**
     * 列表图片
     */
    private String thumbnail;
    @JSONField(name = "thumbnail_link")
    private String thumbnailLink;
    /**
     * 标题
     */
    @JSONField(ordinal = -1)
    private String title;
    /**
     * 列表总结
     */
    private String summary;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 创建时间
     */
    private String createTime;

    private String secondtitle;
    private String type;
    private String readtimes;
    private String status;
    private String sort;
    private String creator;
    @JSONField(name = "update_user")
    private String updateUser;
    @JSONField(name = "update_time")
    private String updateTime;
    @JSONField(name = "is_show")
    private String isShow;
    @JSONField(name = "video_id")
    private String videoId;
    private String issue;
    @JSONField(name = "transition_url")
    private String transitionUrl;
    @JSONField(name = "share_switch")
    private String shareSwitch;
    @JSONField(name = "share_title")
    private String shareTitle;
    @JSONField(name = "share_content")
    private String shareContent;
    @JSONField(name = "share_message_content")
    private String shareMessageContent;
    @JSONField(name = "share_icon")
    private String shareIcon;

}
