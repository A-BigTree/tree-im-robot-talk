package top.abigtree.im.robot.service.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/19
 */
@Data
public class MysqlBaseDO implements Serializable {
    @TableId(type = IdType.AUTO)
    private long id;

    private long createTime;

    @TableLogic
    private int deleted;
}
