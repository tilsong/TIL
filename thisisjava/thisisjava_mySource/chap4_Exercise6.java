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
//���� ���� ��, �Ʒ��� å�� ��  - �Ʒ� ������ �� ���������� �ٰ������ �Ѵ٤� 
//�׸��� x,y�� 0���� �����ϴϱ� ������ �����. ���ϱ�?
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
