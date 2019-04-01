package topictest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 * 发布订阅模式
 * @author: juzi
 * @date: 2019/4/1
 * @time: 15:00
 */
public class TopicProducer {

    public static void main(String[] args) throws Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.107.100.133:61616");
        //获取连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //获取session  参数1：是否启动事务，参数2：消息确认模式
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Topic topic = session.createTopic("message01");
        //创建消息生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //创建消息
        TextMessage textMessage = session.createTextMessage("这是一条消息");
        //发送消息
        messageProducer.send(textMessage);
        //关闭资源
        messageProducer.close();
        session.close();
        connection.close();


    }
}
