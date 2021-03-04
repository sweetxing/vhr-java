package glue.pudding.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by GUIXu on 2020/4/23.
 */
@Configuration
public class JMSConfig {

    @Bean(name = "activeMQConnectionFactory")
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return activeMQConnectionFactory();
    }

    @Bean(name = "jmsTemplate")
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory factory) {
        JmsTemplate template =  new JmsTemplate(factory);
        template.setDefaultDestinationName("hr.alert.queue");
        return template;
    }


    /*@Bean(name = "queue")
    public ActiveMQQueue activeMQQueue(){
        return new ActiveMQQueue("activeMQQueue");
    }*/
}
