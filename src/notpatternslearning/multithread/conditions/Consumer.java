package notpatternslearning.multithread.conditions;

import java.util.HashSet;
import java.util.Set;

public class Consumer extends Thread {
	private final Set<Object> seenObjects = new HashSet<>();
	private int total = 0;
	private final SharedFiFoQueue queue;

	public Consumer(SharedFiFoQueue queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			do {
				Object obj = queue.remove();
				if(obj == null)
					break;
				
				if(!seenObjects.contains(obj)) {
					++total;
					seenObjects.add(obj);
				}
				
				System.out.println("[Consumer] Read the element: " + obj.toString());
				
			} while(true);
		}
		catch (InterruptedException ex) {
			System.err.println("An InterruptedException was caught: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		System.out.println("[Consumer] " + total + " distinct words have been read...");
	}
}
