package thisisjava_mySource;

public class chap4_IfDiceExam {
	public static void main(String[]args) {
		//�ֻ����� ��ȣ�� �̴� �����
		int num = (int)(Math.random()*6) + 1;// Math.random()�� 0<=a<1�� ������ �������� �Լ��̴�.
	
		switch(num) {
		case 1:
			System.out.println("1���� ���Խ��ϴ�.");
			break; //break�� ���� ��� �Ʒ��� case���� ��� ����ȴ�.
		case 2:
			System.out.println("2���� ���Խ��ϴ�.");
			break;
		case 3:
			System.out.println("3���� ���Խ��ϴ�.");
			break;
		case 4:
			System.out.println("4���� ���Խ��ϴ�.");
			break;
		case 5:
			System.out.println("5���� ���Խ��ϴ�.");
			break;
		default:
			System.out.println("6���� ���Խ��ϴ�.");
			break;
		}
	}
}
