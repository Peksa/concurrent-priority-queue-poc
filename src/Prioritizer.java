import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prioritizer implements Runnable {
	
	private ArrayList<Generator> generators;
	private PriorityQueue<Double> values = new PriorityQueue<Double>();
	
	public Prioritizer(ArrayList<Generator> generators) {
		this.generators = generators;
	}
	
	@Override
	public void run() {
		while (true) {
			for (Generator g : generators) {
				
				// If not ready, try next generator
				if (!g.out.ready)
					continue;
				values.add(g.out.value);
				g.out = g.out.next;
	
			}
			System.err.println("Went through all generators, next state is: " + values.peek());
			
			// Place all states in priority queue back into in-queues
			
			for (double d : values) {
				
				// Find a ready generator for this value
				int g = 0;
				while (true) {
					Generator gen = generators.get(g);
					if (gen.in.ready) {
						gen.in.value = d;
						gen.in.next = new LockFreeQueue();
						gen.in.ready = true;
						gen.in = gen.in.next;
						break;
					}
					g = (g + 1) % generators.size();
				}
			}
		}
	}
}