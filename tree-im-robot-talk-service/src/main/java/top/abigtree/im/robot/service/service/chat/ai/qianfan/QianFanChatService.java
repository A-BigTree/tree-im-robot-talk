package top.abigtree.im.robot.service.service.chat.ai.qianfan;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.abigtree.im.robot.service.service.chat.BaseChatWithCacheService;

@Service
public class QianFanChatService extends BaseChatWithCacheService {

    private static final String TAG = "QIAN_FAN";

    @Resource
    private QianFanConfig qianFanConfig;

    @Override
    protected String chatWithCache(String question) {
        Qianfan qianfan = new Qianfan(qianFanConfig.getAccessKey(), qianFanConfig.getSecretKey());
        ChatResponse response = qianfan.chatCompletion()
                .model(qianFanConfig.getModel())
                .temperature(qianFanConfig.getTemperature())
                .maxOutputTokens(qianFanConfig.getMaxTokens())
                .system("你叫大树二号机，是一个热情的程序员")
                .addMessage("user", question)
                .execute();
        return response.getResult();
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
