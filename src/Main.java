import java.util.ArrayList;

public class Main {
	public static String source = "Lets produce some shit";

	public static void main(String ... args) {
		
		ArrayList<Generator> generators = new ArrayList<Generator>();
		
		// Three generators

		Generator first = new Generator();
		
		// first state
		first.out.next = new LockFreeQueue();
		first.out.value = Math.random();
		first.out.ready = true;
		
		generators.add(first);
		//generators.add(new Generator());
		//generators.add(new Generator());
		
		
		for (Generator g : generators)
			new Thread(g).start();
		
		
		new Thread(new Prioritizer(generators), "Prioritizer").start();
	}
}