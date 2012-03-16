
public class Generator implements Runnable {
	
	public LockFreeQueue<Double> in = new LockFreeQueue<>();
	public LockFreeQueue<Double> out = new LockFreeQueue<>();
	
	@Override
	public void run()
	{
		while (true)
		{
			Double val = in.pop();
			//System.out.println("Generator: I got " + val + " from my in-queue, adding two new random values to my out-queue");
			
			/*try {
				Thread.sleep(5); // Man it's hard generating random numbers.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}*/
			
			for (int i = 0; i < 10; i++)
				out.add(0.5);
		}
	}
}
