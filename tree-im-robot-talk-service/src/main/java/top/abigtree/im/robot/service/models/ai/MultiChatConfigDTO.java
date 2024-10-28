package top.abigtree.im.robot.service.models.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChatConfigDTO {
    private long tokens;
    private List<RoundChatDTO> history;

    public static MultiChatConfigDTO defaultInstance() {
        return new MultiChatConfigDTO(0, new ArrayList<>());
    }

    public boolean overMaxRound(long maxRound) {
        return history.size() >= maxRound;
    }

    public int getRound() {
        return history.size();
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoundChatDTO {
        private String q;
        private String a;
    }
}
