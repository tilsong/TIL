package thisisjava_mySource;

public class chap4_SwitchExam {
	public static void main(String[]args) {
		//주사위의 번호를 뽑는 연산식
		int num = (int)(Math.random()*6) + 1;// Math.random()은 0<=a<1의 난수를 가져오는 함수이다.
		
		if(num==1) {
			System.out.println("1번이 나왔습니다.");
		} else if(num==2){
			System.out.println("2번이 나왔습니다.");
		} else if(num==3){
			System.out.println("3번이 나왔습니다.");
		} else if(num==4){
			System.out.println("4번이 나왔습니다.");
		} else if(num==5){
			System.out.println("5번이 나왔습니다.");
		} else {
			System.out.println("6번이 나왔습니다.");
		}
	}
}
