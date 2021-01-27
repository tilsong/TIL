package _0125;

public class ContaninerEx {

	public static void main(String[] args) {
		Container<String> container1 = new Container<String>();
		container1.setField("홍길동");
		String str = container1.getField();
		
		Container<Integer> container2 = new Container<Integer>();
		container2.setField(1);
		int int1 = container2.getField();
		System.out.println(str + int1);

	}

}
