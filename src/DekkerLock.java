public class DekkerLock
{
	private volatile int threadNumber;
	private volatile int right;
	private volatile boolean[] wish;
	private volatile boolean[] claimant;

	public DekkerLock(int threadNumber)
	{
		this.threadNumber = threadNumber;
		right = threadNumber;
		wish = new boolean[threadNumber + 1];
		claimant = new boolean[threadNumber + 1];
	}

	void lock(int threadIndex)
	{
		int i;
		claimant[threadIndex] = true;
		do
		{
			while (right != threadIndex)
			{
				wish[threadIndex] = false;
				if (!claimant[right])
					right = threadIndex;
			}
			wish[threadIndex] = true;
			for (i = 0; i < threadNumber; i++)
				if ((i != threadIndex) && wish[i]) break;
		} while (i < threadNumber);
	}

	void unlock(int threadIndex)
	{
		right = threadNumber;
		wish[threadIndex] = false;
		claimant[threadIndex] = false;
	}
}
