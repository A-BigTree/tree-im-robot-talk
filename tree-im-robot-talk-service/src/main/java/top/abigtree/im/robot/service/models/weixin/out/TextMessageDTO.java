package top.abigtree.im.robot.service.models.weixin.out;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XStreamAlias("xml")
public class TextMessageDTO {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private long createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("Content")
    private String content;
}
