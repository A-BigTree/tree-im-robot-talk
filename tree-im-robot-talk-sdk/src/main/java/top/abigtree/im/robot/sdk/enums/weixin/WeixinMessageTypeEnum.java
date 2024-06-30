package top.abigtree.im.robot.sdk.enums.weixin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-29
 */
@Getter
@AllArgsConstructor
public enum WeixinMessageTypeEnum {
    TEXT(1, "text", "文本"),
    IMAGE(2, "image", "图片"),
    VOICE(3, "voice", "语音"),
    VIDEO(4, "video", "视频"),
    SHORT_VIDEO(5, "shortvideo", "小视频"),
    LOCATION(6, "location", "位置"),
    LINK(7, "link", "链接"),
    EVENT(8, "event", "事件"),
    ;


    final int value;
    final String messageType;
    final String desc;
}
