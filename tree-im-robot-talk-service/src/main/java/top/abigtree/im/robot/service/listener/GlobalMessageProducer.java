package top.abigtree.im.robot.service.listener;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.abigtree.im.robot.sdk.enums.listener.TreeRobotTopicEnum;

/**
 * 总线消息发送服务
 *
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/31
 */
@Service
public class GlobalMessageProducer {
    @Resource
    private ListenerRegistryFactory listenerRegistryFactory;

    public void sendMessage(TreeRobotTopicEnum topic, Object message) {
        listenerRegistryFactory.sendMessageAsync(topic, message);
    }
}
