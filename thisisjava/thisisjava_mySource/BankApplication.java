package thisisjava_mySource;

import java.util.Scanner;

public class BankApplication {
	private static Account[] accountArray = new Account[100];
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[]args) {
		boolean run =true;
		while(run) {
			System.out.println("------------------------------------------");
			System.out.println("1.계좌생성  2.계좌목록 3.예금 4.출금 5.종료");
			System.out.println("------------------------------------------");
			System.out.println("선택>");
			
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
		System.out.println("프로그램 종료");
	}
	
	//계좌생성하기
	private static void createAccount() {

		
		System.out.print("계좌번호");
		String an = sc.next();
		System.out.print("계좌주");
		String owner = sc.next();
		System.out.print("초기입금액");
		int balance = sc.nextInt();
		
		for(int i=0; i<accountArray.length; i++) {
			if(accountArray[i]==null) {
//처음에는 .ano의 널값을 확인했다. 그러나 그이전 객체가 널값인지 확인하는 것이 우선이었다.
				accountArray[i] = new Account(an, owner, balance);
				System.out.println("계좌가 생성되었습니다.");
				break;
			}
			System.out.println("계좌를 더 이상 개설할 수 없습니다.");
		}
		
		
	}
	//계좌목록보기
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
//내가 가장 간과했던 것이 바로 nullpointexception이었다. null값을 if(account !=null) 이런식으로
//체크해주어야 한다는 것을 알게되었다.
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
	
	//예금하기
	private static void deposit() {
		System.out.println("--------------");
		System.out.println("예금");
		System.out.println("--------------");
		System.out.println("계좌번호");
		String ano = sc.next();
		System.out.println("예금액");
		int plus = sc.nextInt();
		Account deposit = findAccount(ano);
		if(deposit == null) {
			System.out.println("결과: 계좌가 없습니다.");
			return;
		}
		deposit.setBalance(plus+deposit.getBalance());
	}
//nextLine과 next의 차이도 알게 되었다. 단순하게 보면 next는 띄어쓰기 단위로 문자열을 가져가고,
//nextLine은 한 라인을 단위로 하여 문자열을 입력 받는다.
//주의할 것은 nextLine으로 문자열을 받을 경우 엔터를 쳤을 때 엔터의 CR(캐리지리턴)과 LF(라인피드)가
//같이 입력되기 때문에 다음의 문자열입력을 받는 열에는 다른 하나의 엔터가 입력되게 된다.
//엔터의 이런 부분에 대하여는 "이것이자바다 4장"을 정리한 페이지에서 더 자세히 다루고 있다.
	//출금하기

	private static void withdraw() {
		System.out.println("--------------");
		System.out.println("출금");
		System.out.println("--------------");
		System.out.println("계좌번호");
		String ano = sc.next();
		System.out.println("출금액");
		int minus = sc.nextInt();
		Account withdraw = findAccount(ano);
		if(withdraw == null) {
			System.out.println("결과: 계좌가 없습니다.");
			return;
		}
		withdraw.setBalance(withdraw.getBalance()-minus);
	}
	//Account 배열에서 ano와 동일한 Account 객체 찾기
	private static Account findAccount(String ano) {
		for(int i =0;i<accountArray.length; i++) {
			if(accountArray[i].getAno().equals(ano)) {
				return accountArray[i];
			}
		}
		return null;
	}
}


