
public class Generator implements Runnable {
	
	public LockFreeQueue<Double> in = new LockFreeQueue<>();
	public LockFreeQueue<Double> out = new LockFreeQueue<>();
	
	@Override
	public void run()
	{
		while (true)
		{
			Double val = in.pop();
			System.out.println("Generator: I got " + val + " from my in-queue, adding two new random values to my out-queue");
			
			out.add(Math.random());
			out.add(Math.random());
		}
	}
}
