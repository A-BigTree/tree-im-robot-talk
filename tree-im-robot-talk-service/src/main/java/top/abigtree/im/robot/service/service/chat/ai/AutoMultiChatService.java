package top.abigtree.im.robot.service.service.chat.ai;

import com.github.benmanes.caffeine.cache.Cache;
import org.apache.commons.lang3.tuple.Pair;
import top.abigtree.im.robot.service.models.ai.MultiChatConfigDTO;
import top.abigtree.im.robot.service.service.chat.BaseChatService;
import top.abigtree.im.robot.service.service.chat.BaseChatWithCacheService;
import top.abigtree.im.robot.service.utils.CacheUtil;

import java.util.List;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/22
 */
public abstract class AutoMultiChatService extends BaseChatWithCacheService {

    private static final int MAX_CHAT_ROUND = 10;

    private static final int MAX_CACHE_SIZE = 100;

    private static final Cache<String, MultiChatConfigDTO> MULTI_CHAT_CACHE =
            CacheUtil.buildCacheWithCapacity(MAX_CACHE_SIZE);

    private static final String RESULT_FORMAT =
            "%s\n------------\n对话轮数(%d/" + MAX_CHAT_ROUND + "), Tokens: %d\n来源:%s\n%s";

    private static final String CLOSE_MESSAGE = "结束对话";

    protected abstract String multiChat(String question, List<MultiChatConfigDTO.RoundChatDTO> history);

    protected abstract String getSource();

    @Override
    protected String chatWithCache(String question, String fromUser, String toUser, Long id) {
        if (MULTI_CHAT_CACHE.getIfPresent(fromUser) != null && CLOSE_MESSAGE.equals(question.trim())) {
            MULTI_CHAT_CACHE.invalidate(fromUser);
            return "此次对话已结束～";
        }
        MultiChatConfigDTO config = MULTI_CHAT_CACHE.get(fromUser, key -> MultiChatConfigDTO.defaultInstance());
        int round = config.getRound() + 1;
        long tokens = config.getTokens() + question.length();
        String answer = multiChat(question, config.getHistory());
        String tips = "回复\"结束对话\"可结束本次会话～";
        MultiChatConfigDTO.RoundChatDTO roundChat = new MultiChatConfigDTO.RoundChatDTO(question, answer);
        config.getHistory().add(roundChat);
        if (config.overMaxRound(MAX_CHAT_ROUND)) {
            tips = "已到达最大对话轮数，自动关闭本次会话～";
            MULTI_CHAT_CACHE.invalidate(fromUser);
        } else {
            config.setTokens(tokens + answer.length());
            MULTI_CHAT_CACHE.put(fromUser, config);
        }
        return String.format(RESULT_FORMAT, answer, round, tokens, getSource(), tips);
    }


}
