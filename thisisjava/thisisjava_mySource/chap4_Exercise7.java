package thisisjava_mySource;

import java.util.Scanner;

public class chap4_Exercise7 {
	public static void main(String[]args) {
		boolean run = true;
		int balance = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(run) {
			System.out.println("--------------------------");
			System.out.println("1.예금 ㅣ 2.출금 ㅣ 3.잔고 ㅣ 4.종료");
			System.out.println("--------------------------");
			System.out.println("선택>");
			
			String key = scanner.nextLine();
			switch(key) {
			case "1" : 
				System.out.println(">예금액을 입력하세요.");
				int plus = scanner.nextInt();
				//balance += scanner.nextInt(); 이런 쉬운 방법이 있었다..
				balance += plus;
				break;
			case "2" :
				System.out.println(">출금액을 입력하세요.");
				int minus = scanner.nextInt();
				balance -= minus;
				break;
			case "3" :
				System.out.println(balance);
				break;
			case "4" :
				run = false;
				break;
			}
			System.out.println();
			
			}
		System.out.println("프로그램 종료");
	}
}
