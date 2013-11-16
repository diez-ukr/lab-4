import java.util.LinkedList;

public class Main
{

	public static void main(String[] args) throws InterruptedException
	{
		LinkedList<Thread> owners = new LinkedList<Thread>();
		DekkerLock dekkerLock = new DekkerLock(5);
		owners.push(new Thread(new PetOwner(0, "Angus Young", dekkerLock)));
		owners.push(new Thread(new PetOwner(1, "Malcolm Young", dekkerLock)));
		owners.push(new Thread(new PetOwner(2, "Phil Rudd", dekkerLock)));
		owners.push(new Thread(new PetOwner(3, "Cliff Williams", dekkerLock)));
		owners.push(new Thread(new PetOwner(4, "Brian Johnson", dekkerLock)));

		for (Thread t : owners)
			t.start();
		for (Thread t : owners)
			t.join();
	}
}

