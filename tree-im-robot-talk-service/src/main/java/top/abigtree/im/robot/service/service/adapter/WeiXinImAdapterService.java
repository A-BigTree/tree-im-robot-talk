package top.abigtree.im.robot.service.service.adapter;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.abigtree.im.robot.service.models.BaseMsgDTO;
import top.abigtree.im.robot.service.models.weixin.InputMessageDTO;
import top.abigtree.im.robot.service.models.weixin.out.TextMessageDTO;
import top.abigtree.im.robot.service.service.chat.ai.qianfan.QianFanChatService;

import static top.abigtree.im.robot.sdk.enums.TreeImMessageSourceEnum.WEI_XIN_OFFICIAL;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/29
 */
@Service
public class WeiXinImAdapterService implements BaseImAdapter<InputMessageDTO, TextMessageDTO> {
    @Resource
    private QianFanChatService qianFanChatService;

    @Override
    public BaseMsgDTO convertInput(InputMessageDTO in) {
        return BaseMsgDTO.builder()
                .content(in.getContent())
                .from(in.getFromUserName())
                .to(in.getToUserName())
                .id(in.getMsgId())
                .type(in.getMsgType())
                .source(WEI_XIN_OFFICIAL)
                .build();
    }

    @Override
    public TextMessageDTO convertOutput(BaseMsgDTO out) {
        return TextMessageDTO.builder()
                .fromUserName(out.getTo())
                .toUserName(out.getFrom())
                .createTime(System.currentTimeMillis())
                .msgType("text")
                .content(out.getContent())
                .build();
    }

    @Override
    public BaseMsgDTO handleMsg(BaseMsgDTO msg) {
        String result = qianFanChatService.chat(msg);
        msg.setContent(result);
        return msg;
    }
}
