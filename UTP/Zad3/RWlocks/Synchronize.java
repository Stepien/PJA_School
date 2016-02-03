
public class Synchronize implements Runnable{

	private KontenerDanych kont;
	
	public Synchronize(KontenerDanych kont){
		this.kont = kont;
	}
	
	@Override
	public void run() {
		
		synchronized(this){
		int counter = 0;
		while((counter = kont.incr())< 10 ){
			System.out.println("Thread 1: " + counter);
		}
	}
	}

}