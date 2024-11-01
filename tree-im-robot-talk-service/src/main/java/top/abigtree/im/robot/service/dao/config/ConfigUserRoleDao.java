package top.abigtree.im.robot.service.dao.config;

import org.apache.ibatis.annotations.Mapper;
import top.abigtree.im.robot.service.dao.MysqlBaseDao;
import top.abigtree.im.robot.service.entity.db.config.ConfigUserRoleDO;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/31
 */
@Mapper
public interface ConfigUserRoleDao extends MysqlBaseDao<ConfigUserRoleDO> {

}
