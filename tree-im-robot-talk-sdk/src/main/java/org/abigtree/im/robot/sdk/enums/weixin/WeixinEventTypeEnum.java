package org.abigtree.im.robot.sdk.enums.weixin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-29
 */
@Getter
@AllArgsConstructor
public enum WeixinEventTypeEnum {
    SUBSCRIBE(1, "subscribe", "订阅"),
    UNSUBSCRIBE(2, "unsubscribe", "取消订阅"),
    SCAN(3, "SCAN", "扫描二维码"),
    LOCATION(4, "LOCATION", "上报地理位置"),
    CLICK(5, "CLICK", "点击菜单"),
    ;
    final int value;
    final String event;
    final String desc;
}
