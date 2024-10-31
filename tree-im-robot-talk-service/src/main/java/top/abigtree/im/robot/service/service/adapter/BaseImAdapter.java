package top.abigtree.im.robot.service.service.adapter;

import top.abigtree.im.robot.service.models.BaseMsgDTO;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/28
 */
public interface BaseImAdapter<IN, OUT> {
    BaseMsgDTO convertInput(IN in);

    OUT convertOutput(BaseMsgDTO out);

    BaseMsgDTO handleMsg(BaseMsgDTO msg);

    default OUT handleMsg(IN in) {
        return convertOutput(handleMsg(convertInput(in)));
    }
}
