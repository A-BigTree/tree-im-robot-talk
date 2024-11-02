package top.abigtree.im.robot.service.entity.db.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.abigtree.im.robot.service.entity.db.MysqlBaseDO;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigUserRoleDO extends MysqlBaseDO {
    private String userName;
    private int roleType;
    private int status;
    private String email;
    private int source;
}
