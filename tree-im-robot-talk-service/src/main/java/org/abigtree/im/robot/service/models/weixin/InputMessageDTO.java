package org.abigtree.im.robot.service.models.weixin;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.Setter;

/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-29
 */
@Data
@XStreamAlias("xml")
@Setter
public class InputMessageDTO implements Serializable {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private Long createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("MsgId")
    private Long msgId;
    // 文本消息
    @XStreamAlias("Content")
    private String Content;
    // 图片消息
    @XStreamAlias("PicUrl")
    private String picUrl;
    // 位置消息
    @XStreamAlias("LocationX")
    private String locationX;
    @XStreamAlias("LocationY")
    private String locationY;
    @XStreamAlias("Scale")
    private Long scale;
    @XStreamAlias("Label")
    private String label;
    // 链接消息
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("Url")
    private String url;
    // 语音信息
    @XStreamAlias("MediaId")
    private String mediaId;
    @XStreamAlias("Format")
    private String format;
    @XStreamAlias("Recognition")
    private String recognition;
    // 事件
    @XStreamAlias("Event")
    private String event;
    @XStreamAlias("EventKey")
    private String eventKey;
    @XStreamAlias("Ticket")
    private String ticket;
}
