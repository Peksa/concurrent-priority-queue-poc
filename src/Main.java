import java.util.ArrayList;

public class Main {
	public static void main(String ... args) {
		
		ArrayList<Generator> generators = new ArrayList<Generator>();
		
		// Three generators

		final Generator first = new Generator();
		final Generator second = new Generator();
		
		first.out.add(Math.random());
		second.out.add(Math.random());
		
		generators.add(first);
		generators.add(second);
		
		final Prioritizer prio = new Prioritizer(generators);
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					System.out.println(first.in.size() + "\t" + first.out.size() + "\t" + second.in.size() + "\t" + second.out.size() + "\t" + prio.size() + "\t" + (prio.first()));
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
		
		
		for (Generator g : generators)
			new Thread(g).start();
		
		new Thread(prio, "Prioritizer").start();
		
		
		new Thread(r).start();
	}
}