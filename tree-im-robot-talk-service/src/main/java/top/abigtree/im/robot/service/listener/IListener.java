package top.abigtree.im.robot.service.listener;

import top.abigtree.im.robot.sdk.enums.listener.TreeRobotTopicEnum;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/22
 */
public interface IListener<T> {

    TreeRobotTopicEnum getTopic();

    void consumerMessage(T message);
}
