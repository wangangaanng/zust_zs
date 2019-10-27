package com.zghzbckj.manage.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.MapUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * <p>
 * Title: 使用javamail发送邮件
 * </p>
 */
public class MailUtils {

    private String to = "";// 收件人
    private final static String from = "1074012885@qq.com";// 发件人
    private final static String host = "smtp.qq.com";// smtp主机
    private final static String username = "1074012885@qq.com";
    private final static String password = "mmlvjdhqwxmoghic";
    private String filename = "";// 附件文件名
    private String subject = "";// 邮件主题
    private String content = "";// 邮件正文
    private Vector file = new Vector();// 附件文件集合

    /**
     * <br>
     * 方法说明：默认构造器 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public MailUtils() {
    }

    /**
     * <br>
     * 方法说明：构造器，提供直接的参数传入 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public MailUtils(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }


    /**
     * <br>
     * 方法说明：设置邮件发送目的邮箱 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setTo(String to) {
        this.to = to;
    }


    /**
     * <br>
     * 方法说明：设置邮件主题 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * <br>
     * 方法说明：设置邮件内容 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * <br>
     * 方法说明：把主题转换为中文 <br>
     * 输入参数：String strText <br>
     * 返回类型：
     */
    public String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(),
                    "utf-8"), "utf-8", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }

    /**
     * <br>
     * 方法说明：往附件组合中添加附件 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void attachfile(String fname) {
        file.addElement(fname);
    }

    /**
     * <br>
     * 方法说明：发送邮件 <br>
     * 输入参数： <br>
     * 返回类型：boolean 成功为true，反之为false
     */
    public boolean sendMail() {

        // 构造mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        //Session session = Session.getDefaultInstance(props);
//      Session session = Session.getDefaultInstance(props, null);

        try {
            // 构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            //MimeMessage msg = new MimeMessage();
            msg.setFrom(new InternetAddress(from));


            //msg.addRecipients(Message.RecipientType.TO, address); //这个只能是给一个人发送email
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));
            subject = transferChinese(subject);
            msg.setSubject(subject);

            // 构造Multipart
            Multipart mp = new MimeMultipart();

            // 向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setContent(content, "text/html;charset=utf-8");

            // 向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            // 向Multipart添加附件
            Enumeration efile = file.elements();
            while (efile.hasMoreElements()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                filename = efile.nextElement().toString();
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));//ISO-8859-1
                String name = fds.getName();
                mbpFile.setFileName(MimeUtility.encodeText(name));
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }

            file.removeAllElements();
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();
            // 发送邮件

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * <br>
     * 方法说明：主方法，用于测试 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public static boolean sendMails(List<String> fileList, Map value) {
        MailUtils sendmail = new MailUtils();
        sendmail.setTo(MapUtils.getString(value, "to"));
        sendmail.setSubject(MapUtils.getString(value, "subject"));
        sendmail.setContent(MapUtils.getString(value, "content"));
        for (String nameFile : fileList) {
            sendmail.attachfile(nameFile);
        }

        return sendmail.sendMail();

    }

    public static void main(String[] args) {
        Map value= Maps.newHashMap();
        value.put("to","1070467567@qq.com");
        value.put("subject","测试");
        value.put("content","你大爷");
        List<String> fileList= Lists.newArrayList();
        fileList.add("D://爱车.pdf");
        sendMails(fileList,value);
    }
}