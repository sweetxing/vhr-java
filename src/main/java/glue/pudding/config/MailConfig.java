package glue.pudding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by GUIXu on 2020/8/10.
 */
@Configuration
@PropertySource("classpath:mail-server.properties")
public class MailConfig {

    @Autowired
    Environment environment;

    @Bean
    public JavaMailSender mailSender(Environment environment) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(Integer.valueOf(environment.getProperty("mail.port")));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));
        mailSender.setDefaultEncoding(environment.getProperty("mail.default-encoding"));

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.debug", environment.getProperty("mail.debug"));
        properties.setProperty("mail.smtp.socketFactory.class", environment.getProperty("mail.smtp.socketFactory.class"));
        properties.setProperty("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
