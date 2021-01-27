package _0125;

public class Ex {

	public static void main(String[] args) {
		Thread thread = new MovieThread();
		thread.start();
		
		try{Thread.sleep(10);} catch(InterruptedException e) {}
		
		thread.interrupt();

	}

}
