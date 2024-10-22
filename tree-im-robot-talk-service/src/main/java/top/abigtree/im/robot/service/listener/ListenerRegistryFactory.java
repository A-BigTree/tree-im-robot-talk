package top.abigtree.im.robot.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import top.abigtree.im.robot.sdk.enums.listener.TreeRobotTopicEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/22
 */
@Slf4j
@Component
public class ListenerRegistryFactory implements ApplicationContextAware {

    private final Map<TreeRobotTopicEnum, List<IListener<?>>> listenerMapList = new HashMap<>();

    /**
     * 获取计算机有几个核
     */
    private final int processors = Runtime.getRuntime().availableProcessors();

    /**
     * 创建线程池:
     * 参数：
     * 核心线程数：计算机内核数
     * 最大线程数：计算机内核数*5
     * 空闲时间：60s，超过60s超过核心线程数的空闲线程被杀死
     * 任务队列长度：200
     * 线程池工厂：使用了jdk默认工厂
     * handler（队列满时的任务拒绝策略）：让提交任务的线程去执行
     */
    private ExecutorService threadPool = new ThreadPoolExecutor(processors,
            processors * 5,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(200),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        for (IListener<?> listener : applicationContext.getBeansOfType(IListener.class).values()) {
            List<IListener<?>> list = listenerMapList.getOrDefault(listener.getTopic(), new ArrayList<>());
            list.add(listener);
            listenerMapList.put(listener.getTopic(), list);
        }
    }

    public List<IListener<?>> getListenersByTopic(TreeRobotTopicEnum topic) {
        return listenerMapList.getOrDefault(topic, new ArrayList<>());
    }



    private void sendMessage(TreeRobotTopicEnum topic, Object message) {
        listenerMapList.get(topic).forEach(subscriber -> {
            try {
                subscriber.consumerMessage(message);
            } catch (Exception e) {
                log.error("Send message error, e:", e);
            }
        });
    }
}
