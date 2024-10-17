package top.abigtree.im.robot.service.enums;

import top.abigtree.im.robot.sdk.enums.TreeImRoleConfigEnum;
import top.abigtree.im.robot.service.enums.config.TreeImConfigOprationEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static top.abigtree.im.robot.sdk.constants.ImTreeTipMessageConstants.*;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/20
 */
public interface TreeImConfigInterface {

    TreeImRoleConfigEnum getRole();

    Set<TreeImConfigOprationEnum> containsOperation();

    Object getConfig(Map<String, Object> map);

    void operateConfig(TreeImConfigOprationEnum operation, Map<String, Object> map, String value);


    default String operateConfig(TreeImRoleConfigEnum role, String op, Map<String, Object> map, String value) {
        if (getRole() != TreeImRoleConfigEnum.DEFAULT && role != TreeImRoleConfigEnum.ADMIN && getRole() != role) {
            return String.format(OPERATION_ROLE_ERROR_TIP, getRole().getDesc());
        }
        TreeImConfigOprationEnum operation = TreeImConfigOprationEnum.fromOperation(op);
        if (operation == TreeImConfigOprationEnum.DEFAULT) {
            return String.format(OPERATION_DEFAULT_ERROR_TIP,
                    Arrays.stream(TreeImConfigOprationEnum.values())
                            .filter(o -> o != TreeImConfigOprationEnum.DEFAULT)
                            .collect(groupingBy(TreeImConfigOprationEnum::getDesc)));

        }
        Set<TreeImConfigOprationEnum> operations = containsOperation();
        if (!operations.contains(operation)) {
            return String.format(OPERATION_NOT_CONTAIN_TIP,
                    operations.stream()
                            .collect(groupingBy(TreeImConfigOprationEnum::getDesc)));
        }
        operateConfig(operation, map, value);
        return String.format(OPERATION_SUCCESS_TIP, operation.getDesc(), value);
    }
}
