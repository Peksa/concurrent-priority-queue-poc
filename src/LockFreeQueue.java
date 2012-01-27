public class LockFreeQueue
{
	public double value;
	public boolean ready;
	public LockFreeQueue next;
	public LockFreeQueue() { ready = false; }
}