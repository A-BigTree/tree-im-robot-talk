package top.abigtree.im.robot.service.enums.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/20
 */
@Getter
@AllArgsConstructor
public enum TreeImConfigOprationEnum {
    DEFAULT(0, "默认"),
    CHANGE(1, "修改"),
    ADD(2, "新增"),
    DELETE(3, "删除"),

    ;
    final int operation;
    final String desc;

    public static TreeImConfigOprationEnum fromOperation(String operation) {
        return Arrays.stream(values())
                .filter(op -> Objects.equals(op.desc, operation))
                .findFirst()
                .orElse(DEFAULT);
    }
}
