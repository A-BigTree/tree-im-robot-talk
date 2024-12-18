package top.abigtree.im.robot.service.service.chat;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import top.abigtree.im.robot.service.models.BaseMsgDTO;
import top.abigtree.im.robot.service.utils.CacheUtil;

import java.util.Objects;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/2
 */
@Slf4j
public abstract class BaseChatWithCacheService implements BaseChatService {
    private final static int MAX_CACHE_SIZE = 100;

    private final static long WAITING_TIME = 4000L;

    private final static Integer WAITING = 0;
    private final static Integer OK = 1;

    private final Cache<String, String> CHAT_ANSWER_CACHE = CacheUtil.buildCacheWithCapacity(MAX_CACHE_SIZE);

    private final static Cache<String, Integer> CHAT_RECORD_CACHE = CacheUtil.buildCacheWithCapacity(MAX_CACHE_SIZE);

    @Override
    public String chat(BaseMsgDTO msg) {
        String key = buildCacheKey(msg);
        try {
            if (CHAT_RECORD_CACHE.getIfPresent(key) != null) {
                if (!Objects.equals(CHAT_RECORD_CACHE.getIfPresent(key), OK)) {
                    Thread.sleep(WAITING_TIME);
                }
            }
        } catch (InterruptedException e) {
            log.error("1: Time waiting error");
        }
        if (CHAT_ANSWER_CACHE.getIfPresent(key) != null) {
            return CHAT_ANSWER_CACHE.getIfPresent(key);
        }
        try {
            if (CHAT_RECORD_CACHE.getIfPresent(key) != null) {
                Thread.sleep(WAITING_TIME);
                if (CHAT_ANSWER_CACHE.getIfPresent(key) != null) {
                    return CHAT_ANSWER_CACHE.getIfPresent(key);
                }
                Thread.sleep(WAITING_TIME);
            }
        } catch (InterruptedException e) {
            log.error("2: Time waiting error");
        }
        CHAT_RECORD_CACHE.put(key, WAITING);
        String answer = chatWithCache(msg);
        CHAT_ANSWER_CACHE.put(key, answer);
        CHAT_RECORD_CACHE.put(key, OK);
        return answer;
    }

    public boolean filterChatService(String keyword) {
        return true;
    }

    protected String buildCacheKey(BaseMsgDTO msg) {
        return getTag() + "-" + msg.getFrom() + "-" + msg.getId();
    }

    abstract protected String chatWithCache(BaseMsgDTO msg);

    abstract public String getTag();
}