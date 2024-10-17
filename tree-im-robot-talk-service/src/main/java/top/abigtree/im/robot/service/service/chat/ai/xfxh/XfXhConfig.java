package top.abigtree.im.robot.service.service.chat.ai.xfxh;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/2
 */
@Configuration
@ConfigurationProperties(prefix = "chat-ai.xfxh")
@Data
public class XfXhConfig {
    /**
     * 服务引擎使用 讯飞星火认知大模型V3.1
     */
    private String hostUrl;
    /**
     * 发送请求时指定的访问领域
     */
    private String domain;
    /**
     * 核采样阈值。用于决定结果随机性，取值越高随机性越强即相同的问题得到的不同答案的可能性越高。取值 [0,1]
     */
    private Float temperature;
    /**
     * 模型回答的tokens的最大长度，V1.5取值为[1,4096]，V2.0取值为[1,8192]。
     */
    private Integer maxTokens;
    /**
     * 大模型回复问题的最大响应时长，单位 s
     */
    private Integer maxResponseTime;
    /**
     * 用于权限验证，从服务接口认证信息中获取
     */
    private String appId;
    /**
     * 用于权限验证，从服务接口认证信息中获取
     */
    private String apiKey;
    /**
     * 用于权限验证，从服务接口认证信息中获取
     */
    private String apiSecret;
}