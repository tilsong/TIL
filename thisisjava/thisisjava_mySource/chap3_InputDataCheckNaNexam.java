public class chap3_InputDataCheckNaNexam {
	public static void main(String []args) {
		
		int v = 10;
		
		System.out.println(toBinaryString(v));

	}
	// 첨에 위 코드 안돼서 당황했는데 아래 보니 이 메소드가 있었네^^
	public static String toBinaryString(int value) {
		String str = Integer.toBinaryString(value);
		while(str.length()<32) {
			str ="0"+str;
		}
		return str;
	}

}
