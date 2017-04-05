import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	// 队列名称
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {

		/**
		 * 创建连接连接到MabbitMQ
		 */
		ConnectionFactory factory = new ConnectionFactory();
		// 设置MabbitMQ所在主机ip或者主机名
		factory.setHost("localhost");
		// 创建一个连接
		Connection connection = factory.newConnection();
		// 创建一个频道
		Channel channel = connection.createChannel();

		// 指定一个队列
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		// 发送的消息
		String message = "hello world!" + System.currentTimeMillis();
		;

		AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
		AMQP.BasicProperties properties = builder.expiration("2000").deliveryMode(2).build();
		// 往队列中发出一条消息 这时候要发送的队列不应该是QUEUE_NAME，这样才能进行转发的
		channel.basicPublish("", "DELAY_QUEUE", properties, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		// 关闭频道和连接
		channel.close();
		connection.close();
	}
}
