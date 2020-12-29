public class chap3_CompareOperatorExam {
	public static void main(String []args) {
		
		int v1 = 1;
		double v2 =1.0;
		System.out.println(v1==v2);//true
		
		double v3 =0.1;
		float v4 =0.1f;
		System.out.println(v3==v4);//false
		System.out.println((float)v3 ==v4);//true, 둘 다 float로 바꾸는 방법
		System.out.println((int)(v3*10)==(int)(v4*10));//true, 둘 다 정수로 바꾸어 비교하는 방법
		}
}

