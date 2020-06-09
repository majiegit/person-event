package com.mj.event.core.commen;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    /**
     * 邮箱SMTP 服务器
     */
    private static final String smtp = "smtp.163.com";
    /**
     * 编码
     */
    private static final String charset = "utf-8";
    /**
     * 发送人邮箱地址
     */
    private static final String emailHost = "15204741491@163.com";
    /**
     * 发送人名称
     */
    private static final String username = "MJ系统";
    /**
     * 授权码
     */
    private static final String password = "XXTSLXCWDWVNAPRY";

    public static boolean sendEmailMeathon(String recipients, String titel, String message) {
        HtmlEmail email = new HtmlEmail();
        //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setHostName(smtp);
        email.setCharset(charset);
        try {
            //设置收件人
            email.addTo(recipients);
            //发送人的邮箱为自己的，用户名可以随便填
            email.setFrom(emailHost, username);
            //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setAuthentication(emailHost, password);
            //设置发送主题
            email.setSubject(titel);
            //设置发送内容
            email.setMsg(message);
            //进行发送
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
