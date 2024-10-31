package top.abigtree.im.robot.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.abigtree.im.robot.sdk.enums.TreeImMessageSourceEnum;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseMsgDTO {
    private String content;
    private String from;
    private String to;
    private long id;
    private String type;
    private TreeImMessageSourceEnum source;
}
