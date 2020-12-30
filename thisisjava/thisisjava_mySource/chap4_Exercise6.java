package thisisjava_mySource;

public class chap4_Exercise6 {
	public static void main(String[]args) {
		for(int x =1; x<=5; x++) {
			int y=1;
			while(y<=x) {
				System.out.print("^");
				y++;
			}
			System.out.println();
		}
//위는 나의 답, 아래는 책의 답  - 아래 내용이 더 직관적으로 다가오기는 한다ㅠ 
//그리고 x,y를 0부터 시작하니깐 오류가 생겼다. 왜일까?
//		for(int i=1; i<=5; i++) {
//			for(int j=1; j<=i; j++) {
//				System.out.print("*");
//				if(j==i) {
//					System.out.println();
//				}
//			}
//		}
	}
}
