public class chap3_InputDataCheckNaNexam {
	public static void main(String []args) {
		
		int v = 10;
		
		System.out.println(toBinaryString(v));

	}
	// ÷�� �� �ڵ� �ȵż� ��Ȳ�ߴµ� �Ʒ� ���� �� �޼ҵ尡 �־���^^
	public static String toBinaryString(int value) {
		String str = Integer.toBinaryString(value);
		while(str.length()<32) {
			str ="0"+str;
		}
		return str;
	}

}
