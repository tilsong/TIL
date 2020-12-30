> **신용권 님의 ''이것이 자바다'' 4장 공부 기록**
>
> 책을 보면서 새롭게 눈에 들어온 부분만 담았다. 이전에 배운 부분이기도 하고, 비교적 간단한 내용을 다루고 있기 때문이다.
>
> [소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)



# 4. 조건문과 반복문



## 4.1 코드 실행 흐름 제어

- 코드의 실행 흐름을 제어할 수 있도록 해주는 것을 흐름제어문이라 하는데, 줄여서 제어문이라고 한다.
- 조건문에는 if, switch가, 반복문에는 for, while, do-while문이 있다.



## 4.2 조건문



### 4.2.1 if문

- 조건식의 결과에 따라 해당 if 블록의 실행 여부를 결정하는 제어문
- 조건식에는 true, false로 산술될 수 있는 연산식이나 boolean 변수가 온다.
- 조건식이 true일 때 실행할 문장이 하나이면 괄호 없이 쓸 수 있다.(그러나 그러지 않는 것이 좋다.)

- if의 조건식이 false일 때는 else if로 이어질 수 있다.

```java
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

```



### 4.2.2 switch문

- 변수값에 따라 실행문이 결정된다. 따라서 같은 기능의 if문보다 코드가 간결하다.
- default는 생략이 가능하다.
- 변수값으로 정수와 문자(char), 문자열(String)이 올 수 있다.
- break가 없을 시 다음 case가 연달아 case값에 상관없이 실행된다.
- 다음은 switch문 예시이다.

```java
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
```



## 4.3 반복문

- 어떤 작업이 반복적으로 실행되도록 할 때 사용된다.
- 반복 횟수를 알고 있었을 때는 for을, 조건에 따라 반복할 때는 while을 사용할 수 있다.



### 4.3.1 for문

- for문 기본 형태

```java
for(초기화; 조건식; 증감식){
    실행문
}
```

- 초기화식은 다음과 같을 때 생략될 수 있다.

```java
int i =1;
for(; i<=100; i++){...}
```

- 초기화식과 증감식은 둘 이상일 수 있다. 이 때는 쉼표로 구분할 수 있다.

```java
for(int i =0, int j=0; i<=50&&j<=10; i++,j--){...}
```

- 초기화식에 선언된 변수는 로컬 변수이나, for문 전에 선언했다면 외부에서도 사용가능하다.
- 루프카운트 변수를 선언할 때는 부동소수점 타입을 사용하면 안된다.



### 4.3.2 while문

- while문 기본 형태

```java
while(조건식){//조건식이 true일 경우 계속 반복한다.
    실행문
}
```

- 조건식이 true이면 무한루트에 빠지게 되므로, 언젠간 빠져나갈 수 있는 코드가 필요하다.

//참고: enter 키 입력시 윈도우는 "CR(캐리지 리턴)"과 LF(라인피드)가 같이 눌리게 된다.

> 줄바꿈을 할 때에 커서가 수평으로 이동하는 것이 캐리지리턴이고 라인피드는 커서의 수직이동을 의미합니다. 그리고 이 말을 이해하려면 타자기의 역사를 알아야 해요.
>
> 입력을 한 글자씩 받아서 그 때마다 한 자 한 글자 느리게 출력하던 구식 타자기에는 글자를 종이의 원하는 위치에 찍어낼 수 있도록 좌우로 움직이는 캐리지(Carriage)라는 장치가 있었다고 합니다. 타자기로 글자를 칠 때마다, 이 캐리지가 오른쪽으로 조금씩 이동하면서 종이에 글자를 하나씩 출력하는 것입니다.
>
> 이런 구식 타자기에서는 줄바꿈을 위해서 두 단계의 행동이 필요합니다.
>
> 첫 단계는 이 캐리지가 수평 왼쪽으로 이동하여 종이의 맨 왼쪽에서 다음 줄의 글자를 찍을 준비를 하는 것입니다. 이 동작을 캐리지가 처음 위치로 돌아온다(복귀한다)고 하여 캐리지 리턴(Carriage return)이라고 부릅니다.
>
> 두 번째 움직임은 캐리지가 행의 맨 앞쪽으로 이동한 직후에, 다음 줄로 이동하기 위해서 수직으로 종이를 조금 위로 올리는 것입니다. 이를 라인 피드(Line feed)라고 부릅니다.
>
> 그러니까 옛날 타자기에서 줄바꿈이란 캐리지리턴과 라인피드라는 두 가지 동작의 혼합입니다. 타자기가 느려서 줄바꿈을 하기 위해서 2글자를 출력할 만큼의 시간이 필요했던 옛날에는 줄바꿈이 CR과 LF라는 두 제어 문자의 조합으로 이루어졌었습니다.
>
> 하지만 오늘날과 같은 디지털 시대에는 둘 중 한 글자만 있어도 줄바꿈을 표현하기에 충분합니다. 그래서 컴퓨터 키보드의 엔터키를 누르면 CR 문자만이 입력됩니다.
>
> 여기서 컴퓨터 프로그래머가 알아야 하는 것은 한 가지뿐인데, 이 세상에는 줄바꿈 문자가 2개나 존재하기 때문에 텍스트를 파일로 저장할 때에 시스템(운영체제)별로 줄바꿈의 표현 방식이 다르다는 점입니다. 
>
> 윈도우 : "\r\n"
> MS-DOS나 Windows 같은 마이크로소프트의 운영체제에서는 구식 장치와 호환될 수 있도록 텍스트의 줄바꿈을 CR+LF로 표현합니다. 두 문자를 모두 사용해서 용량이 약간 더 커진다는 단점이 있지만 1바이트냐 2바이트냐일 뿐입니다. 가장 근본적이고 전통적인 줄바꿈의 표현 방법입니다.
>
> https://nanite.tistory.com/42 

//참고 :  System.in.read()는 키보드에서 입력된 키코드를 리턴하는 메소드인데, 사용할 때 throw Exception을 추가해 주어야 한다.



### 4.3.3 do-while문

- do-while문 기본 형태

```java
do{
    실행문
}while(조건식) // 조건식이 false일 경우 종료
```

//참고 : 사용자로부터 문자열을 입력 받을 때는 Scanner 객체를 생성하고 nextline 코드를 통해 입력된 문자열을 읽을 수 있다.

```java
Scanner scanner = new Scanner(System.in); //Scanner 객체 생성
String inputString = scanner.nextLine(); //nextLine() 메소드 호출
```



### 4.3.4 break문

- for, while, do-while, switch문을 실행 중지시킬 때 사용한다.
- 보통 if문과 같이 사용한다.
- 반복문이 중첩되어 있을 대는 가장 가까운 안쪽 반복문만 중지시킨다.
- 그러나 바깥쪽 반복문에 이름을 붙이면 바깥 반복문도 중지가 가능하다.

```java
public class Exam {
	public static void main(String[]args) {
		Outter : for(char upper='A'; upper<='Z'; upper++){
            for(char lower='a'; lower<='z'; lower++){
                System.out.println(upper+"-"+lower);
                if(lower=='g'){
                    break Outter;
                }
            }
        }
        System.out.println("프로그램 실행 종료")
    }
}
```



### 4.3.5 continue문

- for문의 증감식이나 while, do-while문의 조건식으로 이동한다.
- 반복문은 종료되지 않으며, 계속 반복을 수행한다.
- 보통 if문과 같이 사용된다.