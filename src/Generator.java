
public class Generator implements Runnable {
	
	public LockFreeQueue<Double> in = new LockFreeQueue<>();
	public LockFreeQueue<Double> out = new LockFreeQueue<>();
	
	@Override
	public void run()
	{
		while (true)
		{
			in.pop();
			
			/*try {
				Thread.sleep(5); // Man it's hard generating random numbers.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}*/
			
			// Branching factor 10
			for (int i = 0; i < 10; i++)
				out.add(Math.random());
		}
	}
}
