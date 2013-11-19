import java.util.concurrent.atomic.AtomicIntegerArray;

public class DekkerLock
{
	private volatile int threadNumber;
	private volatile int right;
	private volatile AtomicIntegerArray wish;
	private volatile AtomicIntegerArray claimant;

	public DekkerLock(int threadNumber)
	{
		this.threadNumber = threadNumber;
		right = threadNumber;
		wish = new AtomicIntegerArray(threadNumber + 1);
		claimant = new AtomicIntegerArray(threadNumber + 1);
	}

	void lock(int threadIndex)
	{
		int i;
		claimant.set(threadIndex, 1);
		do
		{
			while (right != threadIndex)
			{
				wish.set(threadIndex, 0);
				if(claimant.get(right) == 0)
					right = threadIndex;
			}
			wish.set(threadIndex, 1);

			for (i = 0; i < threadNumber; i++)
				if ((i != threadIndex) && (wish.get(i) == 1)) break;
		} while (i < threadNumber);
	}

	void unlock(int threadIndex)
	{
		right = threadNumber;
		wish.set(threadIndex, 0);
		claimant.set(threadIndex, 0);
	}
}
