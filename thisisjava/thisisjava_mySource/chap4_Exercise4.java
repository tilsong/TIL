package thisisjava_mySource;

public class chap4_Exercise4 {
	public static void main(String[]args) {
		while(true){
			int x = (int)(Math.random()*6 + 1);
			int y = (int)(Math.random()*6 + 1);
			if((x+y)==5) {
				break;
			} else {
				System.out.println("("+x+","+y+")");
			}
		}
	}
}
