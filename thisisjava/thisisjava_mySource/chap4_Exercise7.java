package thisisjava_mySource;

import java.util.Scanner;

public class chap4_Exercise7 {
	public static void main(String[]args) {
		boolean run = true;
		int balance = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(run) {
			System.out.println("--------------------------");
			System.out.println("1.���� �� 2.��� �� 3.�ܰ� �� 4.����");
			System.out.println("--------------------------");
			System.out.println("����>");
			
			String key = scanner.nextLine();
			switch(key) {
			case "1" : 
				System.out.println(">���ݾ��� �Է��ϼ���.");
				int plus = scanner.nextInt();
				//balance += scanner.nextInt(); �̷� ���� ����� �־���..
				balance += plus;
				break;
			case "2" :
				System.out.println(">��ݾ��� �Է��ϼ���.");
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
		System.out.println("���α׷� ����");
	}
}
