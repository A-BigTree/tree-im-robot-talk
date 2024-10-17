package top.abigtree.im.robot.service.service.chat.ai.qianfan;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "chat-ai.qianfan")
@Data
public class QianFanConfig {
    private String model;
    private String accessKey;
    private String secretKey;
    /**
     * 核采样阈值
     */
    private Double temperature;
    /**
     * 模型回答的tokens的最大长度。
     */
    private Integer maxTokens;
    /**
     * 大模型回复问题的最大响应时长，单位 s
     */
    private Integer maxResponseTime;
}
