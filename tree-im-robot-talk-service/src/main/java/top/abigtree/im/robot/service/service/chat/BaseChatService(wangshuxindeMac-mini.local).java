package top.abigtree.im.robot.service.service.chat;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/2
 */
@Slf4j
public abstract class BaseChatService {
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

    abstract public String chat(String question);

    abstract public String getTag();

    public String chatWithCache(String question, String fromUser, Long createTime) {
        String key = buildCacheKey(fromUser, createTime);
        try {
            if (CHAT_RECORD_CACHE.containsKey(key)) {
                if (CHAT_RECORD_CACHE.get(key) != OK) {
                    Thread.sleep(WAITING_TIME);
                }
            }
        } catch (InterruptedException e) {
            log.error("Time waiting error");
        }
        if (CHAT_ANSWER_CACHE.containsKey(key)) {
            return CHAT_ANSWER_CACHE.get(key);
        }
        try {
            if (CHAT_RECORD_CACHE.containsKey(key)) {
                Thread.sleep(WAITING_TIME);
            }
        } catch (InterruptedException e) {
            log.error("Time waiting error");
        }
        CHAT_RECORD_CACHE.put(key, WAITING);
        String answer = chat(question);
        CHAT_ANSWER_CACHE.put(key, answer);
        CHAT_RECORD_CACHE.put(key, OK);
        return answer;
    }

    private String buildCacheKey(String fromUser, Long createTime) {
        return getTag() + "-" + fromUser + "-" + createTime;
    }
}
