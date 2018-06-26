package notpatternslearning.multithread.conditions;

public class ConditionExample {
	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue sharedQueue = new SharedFiFoQueue(5);
		
		//Create a producer and a consumer.
		Thread producer = new Producer(sharedQueue);
		Thread consumer = new Consumer(sharedQueue);
		
		//Start both threads.
		producer.start();
		consumer.start();
		
		//Wait for both threads to terminate.
		producer.join();
		consumer.join();
	}
}
