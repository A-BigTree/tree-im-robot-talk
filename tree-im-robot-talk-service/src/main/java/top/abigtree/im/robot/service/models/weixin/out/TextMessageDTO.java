package top.abigtree.im.robot.service.models.weixin.out;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.abigtree.im.robot.service.annotation.XStreamCDATA;

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
    @XStreamCDATA
    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamCDATA
    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private long createTime;

    @XStreamCDATA
    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamCDATA
    @XStreamAlias("Content")
    private String content;
}
