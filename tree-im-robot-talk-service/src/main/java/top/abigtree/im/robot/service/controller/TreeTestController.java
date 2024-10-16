package top.abigtree.im.robot.service.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import top.abigtree.im.robot.service.service.chat.XfXhChatService;


/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-30
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TreeTestController {
    @Resource
    private XfXhChatService xfXhChatService;

    @GetMapping("/send")
    public String sendQuestion(@RequestParam("question") String question) {
        return xfXhChatService.chat(question);
    }
}
