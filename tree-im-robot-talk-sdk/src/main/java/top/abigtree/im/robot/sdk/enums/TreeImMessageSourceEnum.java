package top.abigtree.im.robot.sdk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-29
 */
@Getter
@AllArgsConstructor
public enum TreeImMessageSourceEnum {
    WEI_XIN_OFFICIAL(1, "微信公众号"),
    ;


    final int source;
    final String desc;
}
