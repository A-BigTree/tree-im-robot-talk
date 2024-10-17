package top.abigtree.im.robot.service.enums.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.abigtree.im.robot.sdk.enums.TreeImRoleConfigEnum;
import top.abigtree.im.robot.service.enums.TreeImConfigInterface;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/20
 */
@Getter
@AllArgsConstructor
public enum TreeImStringConfigEnum implements TreeImConfigInterface {
    /**
     * 大模型配置
     */
    llmContextLength("上下文轮数", TreeImRoleConfigEnum.ADMIN, "3"),
    /**
     * User大模型配置
     */
    systemConfig("大模型配置", TreeImRoleConfigEnum.USER, "你叫大树二号机，是一名人工智能问答助手"),
    /**
     * 用户位置配置
     */
    locationConfig("位置", TreeImRoleConfigEnum.SYSTEM, "浙江杭州市"),

    ;
    final String keyWord;
    final TreeImRoleConfigEnum role;
    final String defaultConfig;

    @Override
    public Set<TreeImConfigOprationEnum> containsOperation() {
        return Collections.singleton(TreeImConfigOprationEnum.CHANGE);
    }

    @Override
    public String getConfig(Map<String, Object> map) {
        return (String) map.getOrDefault(this.name(), this.defaultConfig);
    }

    @Override
    public void operateConfig(TreeImConfigOprationEnum operation, Map<String, Object> map, String value) {
        map.put(this.name(), value);
    }
}
