import org.junit.Test;

public class TaskDemo {
	private static TaskQueueDaemonThread instance = TaskQueueDaemonThread.getInstance();
	static{
		instance.init();
	}
	@Test
	public void put(){
		instance.put(3000, new Runnable() {
			@Override
			public void run() {
				System.out.println("取消订单");
			}
		});
	}
}
