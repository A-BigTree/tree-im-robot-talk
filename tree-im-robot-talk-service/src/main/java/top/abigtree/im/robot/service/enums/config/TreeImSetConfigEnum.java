package top.abigtree.im.robot.service.enums.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.abigtree.im.robot.sdk.enums.TreeImRoleConfigEnum;
import top.abigtree.im.robot.service.enums.TreeImConfigInterface;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static top.abigtree.im.robot.service.enums.config.TreeImConfigOprationEnum.ADD;
import static top.abigtree.im.robot.service.enums.config.TreeImConfigOprationEnum.DELETE;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/20
 */
@Getter
@AllArgsConstructor
public enum TreeImSetConfigEnum implements TreeImConfigInterface {
    // 管理员配置
    adminConfig(Set.of("管理员"), TreeImRoleConfigEnum.ADMIN, Collections.singleton("oOt0g5rD2tourOQ-EQFspyfCFEjk")),
    // 用户配置
    userConfig(Set.of("用户"), TreeImRoleConfigEnum.ADMIN, Collections.emptySet()),

    ;
    final Set<String> keyWords;
    final TreeImRoleConfigEnum role;
    final Set<String> defaultConfig;

    @Override
    public Set<TreeImConfigOprationEnum> containsOperation() {
        return Set.of(ADD, DELETE);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<String> getConfig(Map<String, Object> map) {
        return (Set<String>) map.getOrDefault(this.name(), this.defaultConfig);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void operateConfig(TreeImConfigOprationEnum operation, Map<String, Object> map, String value) {
        Set<String> config = (Set<String>) map.getOrDefault(this.name(), new HashSet<>());
        switch (operation) {
            case ADD:
                config.add(value);
                break;
            case DELETE:
                config.remove(value);
                break;
            default:
                break;
        }
        map.put(this.name(), config);
    }
}
