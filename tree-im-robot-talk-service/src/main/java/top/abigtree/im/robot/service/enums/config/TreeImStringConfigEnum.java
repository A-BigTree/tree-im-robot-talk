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
     * 星火大模型配置
     */
    xfxhHost("星火URL", TreeImRoleConfigEnum.ADMIN, "https://spark-api.xf-yun.com/v3.1/chat"),
    xfxhDomain("星火模型", TreeImRoleConfigEnum.ADMIN, "generalv3"),
    xfxhTemperature("星火阈值", TreeImRoleConfigEnum.ADMIN, "0.5"),
    xfxhMaxTokens("星火最大生成长度", TreeImRoleConfigEnum.ADMIN, "500"),
    xfxhMaxResponseTime("星火最大响应时间", TreeImRoleConfigEnum.ADMIN, "30"),
    xfxhQPS("星火QPS", TreeImRoleConfigEnum.ADMIN, "10"),
    xfxhAppId("星火appId", TreeImRoleConfigEnum.ADMIN, "ee53fa4b"),
    xfxhApiKey("星火apiKey", TreeImRoleConfigEnum.ADMIN, "0156c50d4844fe52d1f164c526a3a007"),
    xfxhApiSecret("星火密钥", TreeImRoleConfigEnum.ADMIN, "ZmQyZTliNWFjYmEyZjRiMjc3NWFlYWM4"),
    /**
     * 大模型配置
     */
    // 上下文轮数配置
    llmContextLength("上下文轮数", TreeImRoleConfigEnum.ADMIN, "3"),

    /**
     * User大模型配置
     */
    // System Call
    systemConfig("大模型配置", TreeImRoleConfigEnum.USER, "你叫老王二号机，是一名人工智能问答助手"),
    /**
     * 用户位置配置
     */
    locationConfig("位置", TreeImRoleConfigEnum.SYSTEM, "中国"),

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
