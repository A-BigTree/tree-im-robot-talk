package top.abigtree.im.robot.service.service.chat;

public interface BaseChatService {

    String chat(String question, String fromUser, String toUser, Long id);

    String getTag();

    boolean filterChatService(String keyword);
}
