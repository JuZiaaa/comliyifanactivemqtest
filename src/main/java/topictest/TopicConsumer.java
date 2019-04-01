package topictest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author: juzi
 * @date: 2019/4/1
 * @time: 15:40
 */
public class TopicConsumer {

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
        //创建消费者对象
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //监听消息
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("接收到的消息："+ textMessage.getText());
                }catch (Exception e){

                }
            }
        });
        //等待键盘输入
        System.in.read();
        //关闭资源
        messageConsumer.close();
        session.close();
        connection.close();

    }
}
