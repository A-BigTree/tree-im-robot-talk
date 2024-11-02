package top.abigtree.im.robot.service.models.config;

import lombok.Data;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/11/1
 */
@Data
public class ConfigUserRoleDTO {
    private long id;
    private String userName;
    private int status;
    private String email;
    private int roleType;
    private int source;
}
