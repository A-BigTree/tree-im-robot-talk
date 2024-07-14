package top.abigtree.im.robot.service.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.abigtree.im.robot.service.component.XfXhStreamClient;
import top.abigtree.im.robot.service.config.XfXhConfig;
import top.abigtree.im.robot.service.listener.XfXhWebSocketListener;
import top.abigtree.im.robot.service.models.weixin.InputMessageDTO;
import top.abigtree.im.robot.service.models.xfxh.MsgDTO;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/2
 */
@Service
@Slf4j
public class XfXhChatService extends BaseChatService {
    @Resource
    private XfXhStreamClient xfXhStreamClient;
    @Resource
    private XfXhConfig xfXhConfig;

    private final static String TAG = "XfXh";

    @Override
    public String chat(String question) {
        // 如果是无效字符串，则不对大模型进行请求
        if (StringUtils.isBlank(question)) {
            return "无效问题，请重新输入";
        }
        // 获取连接令牌
        if (!xfXhStreamClient.operateToken(XfXhStreamClient.GET_TOKEN_STATUS)) {
            return "当前大模型连接数过多，请稍后再试";
        }

        // 创建消息对象
        MsgDTO msgDTO = MsgDTO.createUserMsg(question);
        // 创建监听器
        XfXhWebSocketListener listener = new XfXhWebSocketListener();
        // 发送问题给大模型，生成 websocket 连接
        WebSocket webSocket = xfXhStreamClient.sendMsg(UUID.randomUUID().toString().substring(0, 10), Collections.singletonList(msgDTO), listener);
        if (webSocket == null) {
            // 归还令牌
            xfXhStreamClient.operateToken(XfXhStreamClient.BACK_TOKEN_STATUS);
            return "系统内部错误，请联系管理员";
        }
        try {
            int count = 0;
            // 为了避免死循环，设置循环次数来定义超时时长
            int maxCount = xfXhConfig.getMaxResponseTime() * 5;
            while (count <= maxCount) {
                Thread.sleep(200);
                if (listener.isWsCloseFlag()) {
                    break;
                }
                count++;
            }
            if (count > maxCount) {
                return "大模型响应超时，请联系管理员";
            }
            // 响应大模型的答案
            String answer = listener.getAnswer().toString();
            log.info("{}:{}", getTag(), answer);
            return listener.getAnswer().toString();
        } catch (InterruptedException e) {
            log.error("错误：" + e.getMessage());
            return "系统内部错误，请联系管理员";
        } finally {
            // 关闭 websocket 连接
            webSocket.close(1000, "");
            // 归还令牌
            xfXhStreamClient.operateToken(XfXhStreamClient.BACK_TOKEN_STATUS);
        }
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
