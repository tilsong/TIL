public class chap3_InfinityAndNaNCheckExam {
	public static void main(String []args) {
		String userInput = "NaN";
		//����ڷκ��� ���ڿ��� �Է� ���� ��, �ݵ�� NaN �˻縦 �ϰ� ���� ��������� �ؾ��Ѵ�.
		//NaN�� ��������� �����ϱ� �����̴�.
		double val = Double.valueOf(userInput);//���ڿ��� �Ǽ��� �ٲ�.
		
		double currentBalance = 10000.0;
		
		if(Double.isNaN(val)) {
			System.out.println("NaN�� �ԷµǾ� ó���� �� ����");
			val = 0.0;
		}
		
		currentBalance += val;
		System.out.println(currentBalance);
	}
}
	