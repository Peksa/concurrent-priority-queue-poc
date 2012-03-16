public class LockFreeQueue<V>
{
	private volatile Entry last;
	private volatile Entry first;
	private volatile long added = 0L;
	private volatile long popped = 0L;
	
	private class Entry
	{
		private volatile V value;
		private volatile boolean ready;
		private volatile Entry next;
	}
	
	public LockFreeQueue()
	{
		first = new Entry();
		last = first;
	}
	
	public void add(V value)
	{
		last.next = new Entry();
		last.value = value;
		added++;
		last.ready = true;
		last = last.next;
	}
	
	public V peek()
	{
		return first.value;
	}
	
	public V pop()
	{
		while (!first.ready)
		{
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		V tmp = first.value;
		first = first.next;
		popped++;
		return tmp;
	}
	
	@Override
	public String toString()
	{
		String ret = "";
		Entry tmp = first;
		while (tmp != null) {
			ret += tmp.value + " ";
			tmp = tmp.next;
		}
		return ret;
	}
	
	public int size()
	{
		return (int) (added-popped);
	}
	
}