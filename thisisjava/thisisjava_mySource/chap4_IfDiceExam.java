package thisisjava_mySource;

public class chap4_IfDiceExam {
	public static void main(String[]args) {
		//주사위의 번호를 뽑는 연산식
		int num = (int)(Math.random()*6) + 1;// Math.random()은 0<=a<1의 난수를 가져오는 함수이다.
	
		switch(num) {
		case 1:
			System.out.println("1번이 나왔습니다.");
			break; //break가 없을 경우 아래의 case들이 모두 실행된다.
		case 2:
			System.out.println("2번이 나왔습니다.");
			break;
		case 3:
			System.out.println("3번이 나왔습니다.");
			break;
		case 4:
			System.out.println("4번이 나왔습니다.");
			break;
		case 5:
			System.out.println("5번이 나왔습니다.");
			break;
		default:
			System.out.println("6번이 나왔습니다.");
			break;
		}
	}
}
