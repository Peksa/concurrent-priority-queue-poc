public class LockFreeQueue<V>
{
	private volatile Entry last;
	private volatile Entry first;
	
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
		int count = -1;
		Entry tmp = first;
		while (tmp != null) {
			count++;
			tmp = tmp.next;
		}
		return count;
	}
	
}