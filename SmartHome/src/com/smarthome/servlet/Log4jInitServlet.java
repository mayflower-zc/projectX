package com.smarthome.servlet;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * Created by zhengcong on 14-4-20.
 */
public class Log4jInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        String prefix = this.getServletContext().getRealPath("/"); //获取当前路径
        String file = this.getInitParameter("log4j");
        System.setProperty("webappHome", prefix); //log4j.properties文件中的变量是在这里设置的
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
        }
    }
}
