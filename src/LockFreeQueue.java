public class LockFreeQueue<V>
{
	private volatile Entry last;
	private volatile Entry first;
	
	private class Entry
	{
		private V value;
		private boolean ready;
		private Entry next;
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
		// TODO SLEEP
		while (!first.ready);
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
	
}