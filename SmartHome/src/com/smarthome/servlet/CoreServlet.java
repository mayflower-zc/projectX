package com.smarthome.servlet;


import com.smarthome.service.CoreService;
import com.smarthome.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhengcong on 14-4-17.
 */
public class CoreServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(CoreServlet.class);

    /**
     * 确认请求来自微信服务器
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //微信加密签名
        String singnature = req.getParameter("signature");
        //时间戳
        String timestamp = req.getParameter("timestamp");
        //随机数
        String nonce = req.getParameter("nonce");
        //随机字符串
        String echostr = req.getParameter("echostr");

        //打印参数
        log.debug("signature:{} timestamp:{} nonce:{} echostr:{}", new Object[]{singnature, timestamp, nonce, echostr});

        PrintWriter out = resp.getWriter();
        if (SignUtil.checkSignature(singnature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将请求、相应的编码均设置为UTF-8(防止中文乱码)
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //调用核心业务类接收消息、处理消息
        String respMessage = CoreService.processRequest(req);

        PrintWriter out = resp.getWriter();
        out.write(respMessage);
        out.close();
    }
}
