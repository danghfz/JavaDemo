package com.dhf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;


/**
 * @author danghf
 * @version 1.0
 * @date 2023/10/12 10:18
 */
@Service
public class MainService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private JavaMailSender mainSender;
    /**
     * template模板引擎 对模板进行渲染
     */
    @Resource
    private TemplateEngine templateEngine;
    /**
     * 获取配置中的邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 简单文本文件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMain(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        // 发送邮件
        mainSender.send(message);
    }

    /**
     * 发送html文件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMain(String to, String subject, String content) {
        MimeMessage message = mainSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件
            messageHelper.setText(content, true);
            // 发送邮件
            mainSender.send(message);
            logger.info("html邮件发送成功");
        } catch (Exception e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     */
    public void sendAttachmentMain(String to, String subject, String content, String filePath) {
        logger.info("发送带附件的邮件开始：{},{},{},{}", to, subject, content, filePath);
        MimeMessage message = mainSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            // true代表支持多组件，如附件，图片等
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件
            messageHelper.setText(content, true);
            // 添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName, file);
            // 发送邮件
            mainSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }

    /**
     * 发送带图片的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param rscPath 图片路径
     * @param rscId   图片ID
     */
    public void sendInlineResourceMain(String to, String subject, String content, String rscPath, String rscId) {
        logger.info("发送带图片的邮件开始：{},{},{},{},{}", to, subject, content, rscPath, rscId);
        MimeMessage message = mainSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            // true代表支持多组件，如附件，图片等
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件
            messageHelper.setText(content, true);
            // 添加图片
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            // 重复使用可以添加多张图片
            messageHelper.addInline(rscId, res);
            // 发送邮件
            mainSender.send(message);
            logger.info("带图片的邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送带图片的邮件时发生异常！", e);
        }
    }

    /**
     * 发送模板邮件
     * @param to 收件人
     * @param subject 主题
     * @param map 模板中的数据
     */
    public void sendTemplateMain(String to, String subject, Map map) {
        // 向模板中添加数据
        Context context = new Context();
        context.setVariable("id", map.get("id"));
        String emailTemplate = templateEngine.process("emailTemplate", context);
        sendHtmlMain(to, subject, emailTemplate);
    }
}
