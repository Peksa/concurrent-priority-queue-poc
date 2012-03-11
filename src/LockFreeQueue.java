public class LockFreeQueue<V>
{
	private Entry last;
	private Entry first;
	
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
	
	public void addLast(V value)
	{
		last.next = new Entry();
		last.value = value;
		last.ready = true;
		last = last.next;
	}
	
	public V peekNext()
	{
		return first.value;
	}
	
	public V popNext()
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