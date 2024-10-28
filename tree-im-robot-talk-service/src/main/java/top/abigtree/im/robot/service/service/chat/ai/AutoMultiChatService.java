package top.abigtree.im.robot.service.service.chat.ai;

import com.github.benmanes.caffeine.cache.Cache;
import org.apache.commons.lang3.tuple.Pair;
import top.abigtree.im.robot.service.models.BaseMsgDTO;
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
    protected String chatWithCache(BaseMsgDTO msg) {
        if (MULTI_CHAT_CACHE.getIfPresent(msg.getFrom()) != null && CLOSE_MESSAGE.equals(msg.getContent().trim())) {
            MULTI_CHAT_CACHE.invalidate(msg.getFrom());
            return "此次对话已结束～";
        }
        MultiChatConfigDTO config = MULTI_CHAT_CACHE.get(msg.getFrom(), key -> MultiChatConfigDTO.defaultInstance());
        int round = config.getRound() + 1;
        long tokens = config.getTokens() + msg.getContent().length();
        String answer = multiChat(msg.getContent(), config.getHistory());
        String tips = "<a href=\"weixin://bizmsgmenu?msgmenucontent=结束对话&msgmenuid=0\">结束对话</a>";
        MultiChatConfigDTO.RoundChatDTO roundChat = new MultiChatConfigDTO.RoundChatDTO(msg.getContent(), answer);
        config.getHistory().add(roundChat);
        if (config.overMaxRound(MAX_CHAT_ROUND)) {
            tips = "已到达最大对话轮数，自动关闭本次会话～";
            MULTI_CHAT_CACHE.invalidate(msg.getFrom());
        } else {
            config.setTokens(tokens + answer.length());
            MULTI_CHAT_CACHE.put(msg.getFrom(), config);
        }
        return String.format(RESULT_FORMAT, answer, round, tokens, getSource(), tips);
    }


}
