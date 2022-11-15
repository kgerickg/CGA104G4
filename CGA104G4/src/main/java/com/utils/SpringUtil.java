package com.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SpringUtil {
        public static <T> T getBean(ServletContext sc, Class<T> clazz) {
            ApplicationContext context =
                    WebApplicationContextUtils.getWebApplicationContext(sc);
            return context.getBean(clazz);
        }

}
