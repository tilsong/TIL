public class chap3_InfinityAndNaNCheckExam {
	public static void main(String []args) {
		String userInput = "NaN";
		//사용자로부터 문자열을 입력 받은 후, 반드시 NaN 검사를 하고 나서 산술연산을 해야한다.
		//NaN은 산술연산이 가능하기 때문이다.
		double val = Double.valueOf(userInput);//문자열을 실수로 바꿈.
		
		double currentBalance = 10000.0;
		
		if(Double.isNaN(val)) {
			System.out.println("NaN이 입력되어 처리할 수 없음");
			val = 0.0;
		}
		
		currentBalance += val;
		System.out.println(currentBalance);
	}
}
	