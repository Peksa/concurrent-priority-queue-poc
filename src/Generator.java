
public class Generator implements Runnable {

	public LockFreeQueue in = new LockFreeQueue();
	public LockFreeQueue out = new LockFreeQueue();

	@Override
	public void run() {
		while (true) {
			while(!in.ready); // Wait for input
			System.out.println("I got " + in.value);
			System.out.flush();
			in = in.next;

			// Generate 2 states
			out.next = new LockFreeQueue();
			out.value = Math.random();
			out.ready = true;
			
			out = out.next;
			out.value = Math.random();
			out.ready = true;

			if(in == null) break;
		}
		System.out.println("Done");
	}
}
