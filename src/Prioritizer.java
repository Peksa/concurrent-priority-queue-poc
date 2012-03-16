import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Prioritizer implements Runnable
{
	private ArrayList<Generator> generators;
	private PriorityQueue<Double> values = new PriorityQueue<Double>();
	
	public Prioritizer(ArrayList<Generator> generators)
	{
		this.generators = generators;
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			for (Generator g : generators)
			{
				
				System.out.println("Generator  in-queue: " + g.in.toString());
				System.out.println("Generator out-queue: " + g.out.toString());
				System.out.flush();
				
				Double val = g.out.peek();
				// If not ready, try next generator

				if (val == null)
					continue;
				
				val = g.out.pop();
				
				values.add(val);
			}
			
			
			System.out.println("Prioritizer: Moving " + values.size() + " elements from out to in-queue.");
			
			// Place all states in priority queue back into in-queues
			int g = 0;
			for (Iterator<Double> iter = values.iterator(); iter.hasNext();)
			{
				Generator gen = generators.get(g);
				gen.in.add(iter.next());
				iter.remove();
				g = (g + 1) % generators.size();
			}
		}
	}
}