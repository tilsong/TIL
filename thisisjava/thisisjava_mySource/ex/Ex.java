package ex;

public class X {

	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair(100, "사과");
		Pair<Integer, String> p2 = new Pair(100, "사과");
		boolean answer = Util.<Integer, String>compare(p1, p2);
		//			   = Util.compare(p1, p2) 비명시적으로 가능
		if(answer) {
			System.out.println("논리 동등 객체");
		} else {
			System.out.println("논리 다른 객체");
		}
	}
}
