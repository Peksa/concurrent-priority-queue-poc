import java.util.ArrayList;

public class Main {
	public static String source = "Lets produce some shit";

	public static void main(String ... args) {
		
		ArrayList<Generator> generators = new ArrayList<Generator>();
		
		// Three generators

		final Generator first = new Generator();
		
		first.out.add(Math.random());
		
		// first state
		generators.add(first);
		
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					System.out.println(first.in.size() + "\t" + first.out.size());
					System.out.flush();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		new Thread(r).start();
		
		for (Generator g : generators)
			new Thread(g).start();
		
		
		new Thread(new Prioritizer(generators), "Prioritizer").start();
	}
}