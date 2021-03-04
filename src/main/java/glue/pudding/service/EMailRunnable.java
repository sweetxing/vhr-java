package glue.pudding.service;

import glue.pudding.entity.Employee;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by GUIXu on 2020/8/11.
 */
public class EMailRunnable implements Runnable {

    private JavaMailSender mailSender;

    private Employee employee;

    private TemplateEngine engine;

    public EMailRunnable(Employee employee, JavaMailSender mailSender, TemplateEngine engine) {
        this.employee = employee;
        this.mailSender = mailSender;
        this.engine = engine;
    }

    @Override
    public void run() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("workId", employee.getWorkID());
            context.setVariable("contractTerm", employee.getContractTerm());
            context.setVariable("beginContract", employee.getBeginContract());
            context.setVariable("endContract", employee.getEndContract());
            context.setVariable("departmentName", employee.getDepartment().getName());
            context.setVariable("posName", employee.getPosition().getName());
            String mail = engine.process("templates/email.html", context);

            helper.setTo(employee.getEmail());
            helper.setFrom("295601971@qq.com");
            helper.setSubject("李四集团：通知");
            helper.setText(mail, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
