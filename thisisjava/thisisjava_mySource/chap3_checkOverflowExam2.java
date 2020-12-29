public class chap3_checkOverflowExam {
	public static void main(String []args) {
		
		try {
			int result = safeAdd(2100000000,200000000);
			System.out.println(result);
		} catch(ArithmeticException e) {
		  System.out.println("�����÷ο찡 �߻��Ͽ� ��Ȯ�ϰ� ����� �� ����");
		} // ����ó���ڵ�
	}
		
		public static int safeAdd(int left, int right) {
			if((right>0)) {
				if(left>(Integer.MAX_VALUE-right)) {
					throw new ArithmeticException("�����÷ο� �߻�"); //���ܹ߻��ڵ�
				}
			} else {//right<=0 �� ���
				if(left<(Integer.MIN_VALUE-right)) {
					throw new ArithmeticException("�����÷ο� �߻�"); //���ܹ߻��ڵ�
				}
			}
			return left+right;
		}
}

// ����� �����÷ο찡 �߻��Ͽ� ��Ȯ�ϰ� ����� �� ����

