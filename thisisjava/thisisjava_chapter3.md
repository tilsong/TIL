> **신용권 님의 ''이것이 자바다'' 3장 공부 기록**
>
> 책을 보면서 새롭게 눈에 들어온 부분만 담았다. 이전에 배운 부분이기도 하고, 비교적 간단한 내용을 다루고 있기 때문이다.
>
>[소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)


# 3.연산자



## 3.1 연산자와 연산식

- 연산(operations)이란, 프로그램이 데이터를 처리하여 결과를 산출하는 것을 말한다.

- 연산자(operator)란, 연산에 사용되는 표시나 기호를 말한다. ex) +, -, * ...

- 피연산자(operand)란, 연산되는 데이터를 말한다.

- 연산식(expressions)이란, 피연산자와 연산자를 이용하여 연산의 과정을 기술한 것을 말한다.

- 연산식 안의 피연산자의 수에 따라

  1. 단항 연산자 : 부호연산자(+, -), 증감연산자(++, --)
  2. 이항 연산자 : 단항과 삼항 외 모두
  3. 삼항 연산자 : 조건 연산자

  로 나누어 볼 수 있다.



## 3.2 연산의 방향과 우선순위

> 1. 단항, 이항, 삼항 연산자 순으로 우선순위를 가진다.
>
> 2. 산술, 비교, 논리, 대입 연산자 순으로 우선순위를 가진다.
>
> 3. 단항과 대입 연산자를 제외한 모든 연산자의 방향은 왼쪽에서 오른쪽이다.
>
> 4. 복잡한 연산식에는 괄호를 사용해서 우선순위를 정해준다.



## 3.3 단항 연산자



### 3.3.1 부호 연산자

- (+, -) 피연산자의 부호를 유지하거나 바꾸는 역할을 한다.
- byte, short 타입은 부호연산시  int로 자동변환 된다.



### 3.3.2 증감 연산자

- (++, --), 변수의 값을 1씩 증가 혹은 감소시키는 연산자이다.
- 변수 앞에 있으면 연산 전 저장, 변수 뒤에 있으면 연산 후 저장한다.

```java
int x = 10;
int y = 10;
int z = ++x +1 //z = 12, 연산 후 저장
int k = y++ + 1 //k = 11 연산 전 저장
```



### 3.3.3 논리 부정 연산자

- (!), 조건문과 제어문에서 조건식의 값을 부정하도록 해서 실행 흐름을 제어할 때 사용된다.
- 두 가지 상태(true, false)를 번갈아가며 변경하는 toggle기능을 구현할 때 주로 사용한다. ex) 스위치, 체크박스, caps lock, num lock



### 3.3.4 비트 반전 연산자

- (~), 정수 타입의 피연산자를 2진수로 표현했을 때 0과 1을 반전한 값을 문자열로 산출한다.

- 비트 반전 연산자 사용시 산출 타입은 int 타입이다.

- 비트 반전 후 1을 더하면 반대 부호의 정수를 구할 수 있다.

- 자바는 정수값을 총 32비트의 이진 문자열로 리턴하는 Integer.toBinaryString() 메소드를 제공하는데, 앞의 비트가 모두 0이면 0은 생략되고 나머지 문자열만 리턴한다. 따라서 앞의 0을 포함한 문자열을 얻기 위해서는 다음의 메소드를 만들어 쓸 수 있다.

  ```java
  public class test_return {
  	public static void main(String []args) {
  		
  		int v = 10;
  		
  		System.out.println(toBinaryString(v));
  	}
  	// 위 코드만 보고 하다가 안돼서 당황했는데 아래 보니 이 메소드가 있었다..
      
  	public static String toBinaryString(int value) {
  		String str = Integer.toBinaryString(value);
  		while(str.length()<32) {
  			str ="0"+str;
  		}
  		return str;
  	}
  
  }
  ```



## 3.4 이항 연산자

이항 연산자는 피연산자가 두 개인 연사자를 말한다. 산술, 문자열 연결, 대입 연산자 등이 있다.



### 3.4.1 산술 연산자

- 사칙 연산자(+, -, *, /)와 나머지(%) 연산자를 말한다. 
- 산술 연산시 피연산자들의 타입이 동일하지 않을 경우, 먼저 타입 일치를 시킨 후에 연산을 수행한다.
  1. int 이하 -> int로 통일
  2. long -> long으로 통일
  3. 실수 -> 실수로 통일

- 다만 곱셈과 나눗셈을 할 때는 연산 후 실수화한다. 따라서 
  1. 1.0을 곱하여 실수 타입으로 만든 후 산술 연산을 하거나
  2. 피연산자 하나를 double타입으로 강제 타입 변환한 후 산술 연산을 하면 된다.

- 자바는 리터럴 간의 연산은 타입 변환 없이 해당 타입으로 계산한다. 따라서 다음과 같은 char 타입 연산은 문제가 없다.

``` java
char c1 = 'A' + 1;//'B'
```

​		물론 다음과 같은 경우는 문제가 될 수 있다.

``` JAVA
char c1 = 'A';
char c2 = c1 + 1;//컴파일 에러 발생
```

이 때 c1은 int 타입으로 자동 변환이 된 후에 1과 연산이 되기 때문이다.

- 산술 연산시 오버플로우가 발생할 수가 있다. 특히 피연산자를 사용자로부터 입력받거나 프로그램 도중 데이터가 생성될 경우 더욱 그렇다.  이 때는 바로 산술 연산자를 사용하기 보다 메소드를 이용하는 것이 좋다.  ~~물론 그냥 long 타입을 쓸 수도 있다.~~

  ```java
  public class chap3_checkOverflowExam2 {
  	public static void main(String []args) {
  		
  		try {
  			int result = safeAdd(2100000000,200000000);
  			System.out.println(result);
  		} catch(ArithmeticException e) {
  		  System.out.println("오버플로우가 발생하여 정확하게 계산할 수 없음");
  		} // 예외처리코드
  	}
  		
  		public static int safeAdd(int left, int right) {
  			if((right>0)) {
  				if(left>(Integer.MAX_VALUE-right)) {
  					throw new ArithmeticException("오버플로우 발생"); //예외발생코드
  				}
  			} else {//right<=0 일 경우
  				if(left<(Integer.MIN_VALUE-right)) {
  					throw new ArithmeticException("오버플로우 발생"); //예외발생코드
  				}
  			}
  			return left+right;
  		}
  }
  
  // 결과는 오버플로우가 발생하여 정확하게 계산할 수 없음
  ```

- 정확한 계산을 원할 때는 근사치를 사용하는 실수 타입보다는 정수 타입을 사용하는 것이 좋다. 

- 좌측 피연산자가 정수 타입인 경우 나누는 수인 우측 피연산자는 0을 사용할 수 없다. 컴파일은 되지만, 실행시 ArithmeticException(예외)이 발생한다.

  ```java
  5/0 // ArithmeticException 예외 발생
  5%0 // ArithmeticException 예외 발생
  ```

  자바는 프로그램 실행 도중 예외 발생시 실행이 즉시 멈추고 프로그램이 종료되기 때문에, ArithmeticException 발생시에는 예외 처리를 해주어야 한다.

  ```java
  try{
      //int z = x / y;
      int z = x % y; //0일 경우 ArithmeticException발생 -> 예외 처리로 가게 됨
      System.out.println("z: " +z);
  } catch(ArithmeticException e) { //예외 처리
      System.out.println("0으로 나누면 안됨")
  }
  ```

  

- 그러나 실수타입인 0.0 또는 0.0f로 나누면 ArithmeticException이 발생하지 않고, /연산은 Infinity(무한대)값으로, %값은 NaN(Not a Number)을 가지게 된다. 이 값들은 산술연산이 가능하나, 어떤 수와 연산하더라도 Infinity와 NaN이 산출되어 계산이 엉망으로 만든다.
- 따라서 프로그램 코드에서 이를 미리 확인하는 메소드를 사용해야 한다. -> Double.isNaN(), Double.isInfinite() 
- 확인 이후 연산의 결과가 NaN이거나 Infinity이면 다음 연산을 수행하지 못하도록 if문을 통해 실행 흐름을 변경해야 한다.

```java
if(Double.isNaN(z)||Double.isInfinite(z)){
    System.out.println("값 산출 불가");
} else{
    System.out.println(z+2); //false일 경우에만 연산
}
```



- 사용자에게 문자열로 부동소수점(실수)을 입력받을 때는 반드시 NaN검사를 해야 한다.

```java
public class chap3_InfinityAndNaNCheckExam {
	public static void main(String []args) {
		String userInput = "NaN";
		//사용자로부터 문자열을 입력 받은 후, 반드시 NaN 검사를 하고 나서 산술연산을 해야한다.
		//NaN은 산술연산이 가능하기 때문이다.
		double val = Double.valueOf(userInput);//문자열을 실수로 바꿈.
		
		double currentBalance = 10000.0;
		
		if(Double.isNaN(val)) {
			System.out.println("NaN이 입력되어 처리할 수 없음");
			val = 0.0;
		}
		
		currentBalance += val;
		System.out.println(currentBalance);
	}
}
```

<br>

### 3.4.2 문자열 연결 연산자

- (+), 피연산자 중 한쪽이 문자열이면 다른 피연산자를 문자열로 변환하고 결합시킨다.

- 문자열과 숫자가 혼합된 연산을 수행할 때는 왼쪽에서 오른쪽으로 연산이 이루어지는데, 이 때 숫자연산이 더 왼쪽에 있을 경우 숫자 연산이 우선된다.

  

### 3.4.3 비교 연산자

- (대소 비교 연산자 <, <=, >=, >), Boolean 타입 외에 모든 타입의 비교 연산에 사용
- (동등 비교 연산자 ==, !=), 모든 타입의 비교 연산에 사용
- char 타입이 피연산자이면 유니코드 값으로 비교연산을 수행하게 된다.

```java
char c1 = 'A';
char c2 = 'B';
boolean result = (c1<c2); //true
```

- 비교연산 시에도 자동변환이 사용된다.

- 실수 타입은 부동소수점을 사용한 근사치를 나타내고 있기 때문에, 정확한 계산을 필요로 하는 연산에는 적합하지 않다.

  따라서 

  1.  피연산자를 모두 float이나 double로 강제변환하거나 ex) (float) a
  2. 피연산자를 정수로 변환하여 사용해야 한다. ex) (int) (a*10)

- String 타입의 문자열을 비교 연산할 때는 객체의 번지값을 비교하는 것이므로 동등 비교 연산자를 사용해서는 안되며,

  equal() 메소드를 사용해야 한다.

  

### 3.4.4 논리 연산자

- (&&, &, |, ||, ~, !) &보다는 &&이, |보다는 ||이 더 효율적으로 동작한다. 왜냐하면 앞의 연산을 끝낸 뒤 해당 결과로 결과 값을 가져올 수 있기 때문이다. 

  -> if( (x>0) && (y<1) ) 에서 'x>0'이 틀렸을 경우 'y<1'의 연산을 수행하지 않고 바로 false를 제시할 수 있다는 것이다. 



### 3.4.5 비트 연산자

- 데이터로 비트 단위, 즉 0, 1을 피연산자로 삼아 연산을 수행한다.
- & -> 두 비트 모두 1일 경우에만 연산결과가 1
- | -> 두 비트 중 하나만 1이면 연산 결과는 1
- ^ -> 두 비트 중 하나는 1이고 다른 하나가 0일 경우 연산 결과는 1
- ~ -> 보수를 제공한다.
- 비트 연산 또한 자동변환(java는 기본적으로 4byte 연산을 하기 때문) 후에 연산이 이루어진다.
- 비트 이동 연산자라는 것이 있다. ~~음.. 솔직히 언제 사용하게 될 것인지 제일 모르겠는 부분이다.~~

```java
a<<b //정수 a의 각 비트를 b만큼 왼쪽으로 이동(빈 자리는 0으로 채워짐)
a>>b //정수 a의 각 비트를 b만큼 오른쪽으로 이동(빈 자리는 정수 a의 최상위 부호 비트MSB와 같은 값으로 채워짐)
a>>>b //정수 a의 각 비트를 b만큼 오른쪽으로 이동(빈 자리는 0으로 채워짐)
```

​    



### 3.4.6 대입 연산자

- (=, +=, -=, *=, /= 등등) 오른쪽 피연산자의 값을 좌측 피연산자인 변수에 저장하는 연산자이다.

- 연산의 모든 방향은 오른쪽에서 왼쪽이다.

<br>

## 3.5 삼항 연산자

- 조건 연산식이라고 할 수 있다. 조건식이 true일 경우 피연산자2, false일 경우 피연산자3을 값으로 가진다. 
    -> 조건식(피연산자1)  ?   값 또는 연산식(피연산자2)  :  값 또는 연산식(피연산자3)

- 삼항 연산자는 if문으로 변경해서 작성할 수도 있지만, 한 줄에 간단하게 삽입해서 사용할 경우에는 삼항 연산자를 사용하는 것이 더 효율적이다.

  ```java
  int score = 95;
  char grade = (score>90) ? 'A' : 'B'
      
  //위 조건 연산식과 아래 if문은 같은 결과를 가져온다.
  
  int score = 95;
  char grade;
  if(score>90){
      grade = 'A';
  } else{
      grade = 'B';
  }
  ```

  

  

  