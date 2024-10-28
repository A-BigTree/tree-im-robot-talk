package top.abigtree.im.robot.service.service.chat;

import top.abigtree.im.robot.service.models.BaseMsgDTO;

public interface BaseChatService {

    String chat(BaseMsgDTO msg);

    String getTag();

    boolean filterChatService(String keyword);
}
