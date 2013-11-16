import java.sql.Time;
import java.util.Random;

public class PetOwner implements Runnable
{
	private int id;
	private String name;
	private DekkerLock dekkerLock;
	private int walkPetNumber;

	public PetOwner(int id, String name, DekkerLock dekkerLock)
	{
		this.id = id;
		this.name = name;
		this.dekkerLock = dekkerLock;
		walkPetNumber = (new Random(System.currentTimeMillis())).nextInt(5);
	}

	@Override
	public void run()
	{
		try
		{
			while (walkPetNumber >= 0)
			{
				System.out.println((new Time(System.currentTimeMillis())) + ": " + name + " wants to walk her/his pet.");
				dekkerLock.lock(id);
				System.out.println((new Time(System.currentTimeMillis())) + ": " + name + " starts walking her/his pet.");
				Thread.sleep((new Random(System.currentTimeMillis())).nextInt(1000));
				System.out.println((new Time(System.currentTimeMillis())) + ": " + name + " finishes walking her/his pet.");
				dekkerLock.unlock(id);
				Thread.sleep((new Random(System.currentTimeMillis())).nextInt(3000));
				walkPetNumber--;
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
