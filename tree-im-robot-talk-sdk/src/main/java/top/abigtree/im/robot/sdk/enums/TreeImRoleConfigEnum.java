package top.abigtree.im.robot.sdk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/20
 */
@Getter
@AllArgsConstructor
public enum TreeImRoleConfigEnum {
    DEFAULT(-1, "默认"),
    SYSTEM(0, "系统"),
    ADMIN(1, "管理员"),
    USER(2, "用户"),

    ;
    final int type;
    final String desc;
}
