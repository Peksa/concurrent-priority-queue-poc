import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prioritizer implements Runnable
{
	private ArrayList<Generator> generators;
	private PriorityQueue<Double> values = new PriorityQueue<Double>();
	
	private static final int BUCKET_SIZE = 1000;
	
	public Prioritizer(ArrayList<Generator> generators)
	{
		this.generators = generators;
	}
	
	public int size()
	{
		return values.size();
	}
	
	public Double first()
	{
		return values.peek();
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			// Get all output!
			for (Generator g : generators)
			{
				while (g.out.peek() != null)
				{
					values.add(g.out.pop());
				}
			}
			
			// Does anyone need input?
			for (Generator g : generators)
			{
				// Less than 5*bucket size? add bucket size elements
				if (g.in.size() < 5*BUCKET_SIZE)
				{
					for (int i = 0; i < BUCKET_SIZE; i++) {
						Double v = values.poll();
						if (v == null)
							break;
						g.in.add(v);
					}
				}
			}
		}
	}
}