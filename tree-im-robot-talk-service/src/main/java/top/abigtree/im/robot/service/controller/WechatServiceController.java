package top.abigtree.im.robot.service.controller;

import static top.abigtree.im.robot.service.utils.JsonUtil.toLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


import jakarta.annotation.Resource;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.xstream.XStream;

import lombok.extern.slf4j.Slf4j;
import top.abigtree.im.robot.service.config.XStreamFactory;
import top.abigtree.im.robot.service.models.weixin.InputMessageDTO;
import top.abigtree.im.robot.service.models.weixin.out.TextMessageDTO;
import top.abigtree.im.robot.service.service.adapter.WeiXinImAdapterService;


/**
 * @author wangshuxin05 <wangshuxin05@kuaishou.com>
 * Created on 2024-06-30
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatServiceController {
    @Resource
    private WeiXinImAdapterService weiXinImAdapterService;

    @RequestMapping(value = "/chat", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void dealWithMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGet = request.getMethod().equalsIgnoreCase("get");
        if (isGet) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            log.info("signature:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce, echostr);
            response.getWriter().write(echostr);
            return;
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = XStreamFactory.createXStream();
        xs.processAnnotations(InputMessageDTO.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            xmlMsg.append(new String(b, 0, n, StandardCharsets.UTF_8));
        }
        log.info("Received xml content:\n {}", xmlMsg);
        // 将xml内容转换为InputMessage对象
        InputMessageDTO inputMsg = (InputMessageDTO) xs.fromXML(xmlMsg.toString());
        log.info("Received message：{}", toLog(inputMsg));
        TextMessageDTO message = weiXinImAdapterService.handleMsg(inputMsg);
        log.info("Send message: {}", toLog(message));
        // 回复消息
        String str = xs.toXML(message);
        response.getWriter().write(str);
    }
}
