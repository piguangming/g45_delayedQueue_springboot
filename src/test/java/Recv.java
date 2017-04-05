import java.util.HashMap;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {
	// 队列名称
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		// channel.queueBind(QUEUE_NAME, "amq.direct", QUEUE_NAME);

		HashMap<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("x-dead-letter-exchange", "amq.direct");
		arguments.put("x-dead-letter-routing-key", QUEUE_NAME);
		channel.queueDeclare("DELAY_QUEUE", true, false, false, arguments);

		// 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
		// channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		// 创建队列消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 指定消费队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while (true) {
			// nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'" + "'   [当前系统时间戳]" + System.currentTimeMillis());
		}
	}
}