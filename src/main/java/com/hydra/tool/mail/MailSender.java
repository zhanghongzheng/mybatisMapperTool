package com.hydra.tool.mail;

import com.google.common.collect.Lists;
import com.hydra.tool.object.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * tool Created by ZhengGong on 15/11/17.
 * PackageName com.hydra.tool.mail
 * Description 发邮件
 */
public class MailSender {
    private static final Logger LOG = LoggerFactory.getLogger(MailSender.class);

    public static void main(String[] args) {
        MailSender mail = new MailSender();
        mail.auth("6980gong@sohu.com", "******");
//        mail.addFile("C:\\Windows\\notepad.exe");


        List<String> list = Lists.newArrayList();
        list.add("chundizhang@meilishuo.com");
        list.add("boyueli@meilishuo.com");
        mail.send("test", "请查看附件", list);
        // mail.send("注册验证", "您的验证码是123456", "416657468@qq.com");
    }

    private String host;
    private String username;
    private String password;
    private Authenticator auth;
    private List<String> filePaths;

    public boolean auth(String username, String password) {
        String host = "smtp." + username.substring(username.indexOf("@") + 1);
        return auth(host, username, password);
    }

    public boolean auth(String host, final String username, final String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        return auth();
    }

    private boolean auth() {
        if (auth == null) {
            try {
                auth = new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                };
            } catch (Exception e) {
                LOG.info("[MAIL] " + username + " 获取认证失败");
                e.printStackTrace();
            }
        }
        return true;
    }

    public void addFile(String filePaths) {
        Arrays.asList(filePaths);
        this.addFile(Arrays.asList(filePaths));
    }

    public void addAttachments(String[] filePaths) {
        this.addFile(Arrays.asList(filePaths));
    }

    public void addFile(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public boolean send(String subject, String content, String mail) {
        return send(subject, content, Arrays.asList(mail));
    }

    public boolean send(String subject, String content, String[] emails) {
        return send(subject, content, Arrays.asList(emails));
    }

    public boolean send(String subject, String content, List<String> emails) {
        if (auth()) {
            String mails = toStrings(emails);
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.auth", "true");
                Session session = Session.getDefaultInstance(props, auth);
                MimeMessage message = new MimeMessage(session);
                message.setSubject(subject);
                message.setSentDate(new Date());
                Address from = new InternetAddress(username, username.substring(0, username.indexOf("@")));
                message.setFrom(from);
                if (filePaths != null && filePaths.size() > 0) {
                    Multipart mp = new MimeMultipart();
                    for (String filename : filePaths) {
                        MimeBodyPart mbp = new MimeBodyPart();
                        FileDataSource fds = new FileDataSource(filename);
                        mbp.setDataHandler(new DataHandler(fds));
                        mbp.setFileName(fds.getName());
                        mp.addBodyPart(mbp);
                    }
                    MimeBodyPart mbp = new MimeBodyPart();
                    mbp.setText(content);
                    mp.addBodyPart(mbp);
                    message.setContent(mp);
                } else {
                    message.setText(content);
                }
                InternetAddress[] address = InternetAddress.parse(mails);
                message.addRecipients(Message.RecipientType.TO, address);
                message.saveChanges();
                Transport.send(message);
                LOG.info("[MAIL] 发送成功 " + mails + " " + content + "" + toStrings(filePaths));
                return true;
            } catch (Exception e) {
                LOG.info("[MAIL] 发送失败 " + mails + " " + content + "" + toStrings(filePaths), e);
            }
        }
        return false;
    }

    private String toStrings(List<String> list) {
        if (ObjectUtil.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String string : list) {
            sb.append(string).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
