package genericE;

public class UtilEx {

	public static void main(String[] args) {
		Pair<String, Integer> pair = new Pair<>("홍길동", 35);
		int age = Util.getValue(pair, "홍길동");
		System.out.println();
		
		ChildPair<String, Integer> childPair = new ChildPair<>("홍삼원", 20);
		Integer childAge = Util.getValue(childPair, "홍삼순");
		System.out.println(childAge);
		
		
	}

}
