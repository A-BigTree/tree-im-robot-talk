package top.abigtree.im.robot.service.service.chat;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/2
 */
@Slf4j
public abstract class BaseChatWithCacheService implements BaseChatService {
    private final static int MAX_CACHE_SIZE = 500;

    private final static long WAITING_TIME = 4000L;

    private final static int WAITING = 0;
    private final static int OK = 1;

    private final static LinkedHashMap<String, String> CHAT_ANSWER_CACHE = new LinkedHashMap<>() {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    private final static LinkedHashMap<String, Integer> CHAT_RECORD_CACHE = new LinkedHashMap<>() {
        private static final long serialVersionUID = 2L;

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    @Override
    public String chat(String question, String fromUser, String toUser, Long id) {
        String key = buildCacheKey(question, fromUser, toUser, id);
        try {
            if (CHAT_RECORD_CACHE.containsKey(key)) {
                if (CHAT_RECORD_CACHE.get(key) != OK) {
                    Thread.sleep(WAITING_TIME);
                }
            }
        } catch (InterruptedException e) {
            log.error("1: Time waiting error");
        }
        if (CHAT_ANSWER_CACHE.containsKey(key)) {
            return CHAT_ANSWER_CACHE.get(key);
        }
        try {
            if (CHAT_RECORD_CACHE.containsKey(key)) {
                Thread.sleep(WAITING_TIME);
            }
        } catch (InterruptedException e) {
            log.error("2: Time waiting error");
        }
        CHAT_RECORD_CACHE.put(key, WAITING);
        String answer = chatWithCache(question);
        CHAT_ANSWER_CACHE.put(key, answer);
        CHAT_RECORD_CACHE.put(key, OK);
        return answer;
    }

    public boolean filterChatService(String keyword) {
        return true;
    }

    protected String buildCacheKey(String question, String fromUser, String toUser, Long id) {
        return getTag() + "-" + fromUser + "-" + id;
    }

    abstract protected String chatWithCache(String question);

    abstract public String getTag();
}