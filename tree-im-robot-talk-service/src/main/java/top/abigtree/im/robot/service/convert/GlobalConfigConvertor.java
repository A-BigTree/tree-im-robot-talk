package top.abigtree.im.robot.service.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.abigtree.im.robot.service.entity.db.config.ConfigUserRoleDO;
import top.abigtree.im.robot.service.models.config.ConfigUserRoleDTO;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/11/1
 */
@Mapper
public interface GlobalConfigConvertor {

    GlobalConfigConvertor CONFIG_CONVERTOR = Mappers.getMapper(GlobalConfigConvertor.class);

    ConfigUserRoleDO convertUserRoleDO(ConfigUserRoleDTO dto);

    ConfigUserRoleDTO convertUserRoleDTO(ConfigUserRoleDO roleDO);
}
