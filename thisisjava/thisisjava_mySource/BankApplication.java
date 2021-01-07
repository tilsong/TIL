package thisisjava_mySource;

import java.util.Scanner;

public class BankApplication {
	private static Account[] accountArray = new Account[100];
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[]args) {
		boolean run =true;
		while(run) {
			System.out.println("------------------------------------------");
			System.out.println("1.���»���  2.���¸�� 3.���� 4.��� 5.����");
			System.out.println("------------------------------------------");
			System.out.println("����>");
			
			int selectNo = sc.nextInt();
			
			if(selectNo ==1) {
				createAccount();
			} else if(selectNo==2) {
				accountList();
			} else if(selectNo==3) {
				deposit();
			} else if(selectNo==4) {
				withdraw();
			} else if(selectNo==5) {
				run=false;
			}
		}
		System.out.println("���α׷� ����");
	}
	
	//���»����ϱ�
	private static void createAccount() {

		
		System.out.print("���¹�ȣ");
		String an = sc.next();
		System.out.print("������");
		String owner = sc.next();
		System.out.print("�ʱ��Աݾ�");
		int balance = sc.nextInt();
		
		for(int i=0; i<accountArray.length; i++) {
			if(accountArray[i]==null) {
//ó������ .ano�� �ΰ��� Ȯ���ߴ�. �׷��� ������ ��ü�� �ΰ����� Ȯ���ϴ� ���� �켱�̾���.
				accountArray[i] = new Account(an, owner, balance);
				System.out.println("���°� �����Ǿ����ϴ�.");
				break;
			}
			System.out.println("���¸� �� �̻� ������ �� �����ϴ�.");
		}
		
		
	}
	//���¸�Ϻ���
	private static void accountList() {
//		int i=0;
//		for(; i<accountArray.length; i++) {
//			if(accountArray[i]==null) {
//				break;
//			}
//		}
//		for(int j=0; j<i; j++  ) {
//			String ano = accountArray[i].getAno();
//			String owner = accountArray[i].getOwner();
//			int balance = accountArray[i].getBalance();
//			System.out.println(ano +"  " + owner+ "   "+ balance);
//		}
//���� ���� �����ߴ� ���� �ٷ� nullpointexception�̾���. null���� �̷�������
//üũ���־�� �Ѵٴ� ���� �˰ԵǾ���.
		for(int i=0; i<accountArray.length; i++) {
			Account account = accountArray[i];
			if(account != null) {
				System.out.print(account.getAno());
				System.out.print("     ");
				System.out.print(account.getOwner());
				System.out.print("     ");
				System.out.print(account.getBalance());
				System.out.println();
			}
		}
	
	}
	
	//�����ϱ�
	private static void deposit() {
		System.out.println("--------------");
		System.out.println("����");
		System.out.println("--------------");
		System.out.println("���¹�ȣ");
		String ano = sc.next();
		System.out.println("���ݾ�");
		int plus = sc.nextInt();
		Account deposit = findAccount(ano);
		if(deposit == null) {
			System.out.println("���: ���°� �����ϴ�.");
			return;
		}
		deposit.setBalance(plus+deposit.getBalance());
	}
	//����ϱ�

	private static void withdraw() {
		System.out.println("--------------");
		System.out.println("���");
		System.out.println("--------------");
		System.out.println("���¹�ȣ");
		String ano = sc.next();
		System.out.println("��ݾ�");
		int minus = sc.nextInt();
		Account withdraw = findAccount(ano);
		if(withdraw == null) {
			System.out.println("���: ���°� �����ϴ�.");
			return;
		}
		withdraw.setBalance(withdraw.getBalance()-minus);
	}
	//Account �迭���� ano�� ������ Account ��ü ã��
	private static Account findAccount(String ano) {
		for(int i =0;i<accountArray.length; i++) {
			if(accountArray[i].getAno().equals(ano)) {
				return accountArray[i];
			}
		}
		return null;
	}
}


