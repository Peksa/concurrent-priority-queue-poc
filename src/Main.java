import java.util.ArrayList;

public class Main {
	public static String source = "Lets produce some shit";

	public static void main(String ... args) {
		
		ArrayList<Generator> generators = new ArrayList<Generator>();
		
		// Three generators

		Generator first = new Generator();
		Generator second = new Generator();
		
		first.out.add(Math.random());
		second.out.add(Math.random());
		
		// first state
		generators.add(first);
		generators.add(second);
		
		
		for (Generator g : generators)
			new Thread(g).start();
		
		
		new Thread(new Prioritizer(generators), "Prioritizer").start();
	}
}