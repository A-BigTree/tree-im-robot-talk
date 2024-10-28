package top.abigtree.im.robot.service.service.chat.ai.qianfan;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.builder.ChatBuilder;
import com.baidubce.qianfan.model.chat.ChatResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.abigtree.im.robot.service.models.ai.MultiChatConfigDTO;
import top.abigtree.im.robot.service.service.chat.BaseChatWithCacheService;
import top.abigtree.im.robot.service.service.chat.ai.AutoMultiChatService;

import java.util.List;

@Service
public class QianFanChatService extends AutoMultiChatService {

    private static final String TAG = "QIAN_FAN";

    @Resource
    private QianFanConfig qianFanConfig;

    @Override
    protected String multiChat(String question, List<MultiChatConfigDTO.RoundChatDTO> history) {
        Qianfan qianfan = new Qianfan(qianFanConfig.getAccessKey(), qianFanConfig.getSecretKey());
        ChatBuilder builder = qianfan.chatCompletion()
                .model(qianFanConfig.getModel())
                .temperature(qianFanConfig.getTemperature())
                .maxOutputTokens(qianFanConfig.getMaxTokens())
                .system("你叫大树二号机，是一名热情的程序员");
        for (MultiChatConfigDTO.RoundChatDTO round : history) {
            builder.addMessage("user", round.getQ());
            builder.addMessage("assistant", round.getA());
        }
        ChatResponse response = builder.addMessage("user", question).execute();
        return response.getResult();
    }

    @Override
    protected String getSource() {
        return "百度千帆大模型";
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
