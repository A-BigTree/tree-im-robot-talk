package top.abigtree.im.robot.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.abigtree.im.robot.service.entity.db.MysqlBaseDO;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/20
 */
public interface MysqlBaseDao <T extends MysqlBaseDO> extends BaseMapper<T> {
    /**
     * 根据主键ID是否存在 插入或更新数据
     */
    default long saveEntityById(T entity) {
        if (entity.getId() > 0) {
            return updateById(entity);
        }
        return insert(entity);
    }
}
