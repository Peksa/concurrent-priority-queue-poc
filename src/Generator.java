
public class Generator implements Runnable {
	
	public LockFreeQueue<Double> in = new LockFreeQueue<>();
	public LockFreeQueue<Double> out = new LockFreeQueue<>();
	
	@Override
	public void run()
	{
		while (true)
		{
			Double val = in.popNext();
			System.out.println("I got " + val);
			
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			out.addLast(Math.random());
			
		}
	}
}
