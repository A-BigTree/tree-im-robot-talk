package top.abigtree.im.robot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-30
 */
@SpringBootApplication
@EnableAsync
public class TreeImRobotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TreeImRobotApplication.class, args);
    }
}
