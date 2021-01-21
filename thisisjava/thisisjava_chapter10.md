> **신용권 님의 ''이것이 자바다'' 10장 공부 기록**
>
> 책을 보면서 내용을 정리했다.
>
> [소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)

<br>

# 10. 예외 처리

## 10.1 예외와 예외 클래스

> 컴퓨터 하드웨어의 오동작 도는 고장으로 인해 응용프로그램 실행 오류가 발생하는 것을 자바에서는 error라고 한다. 개발자는 이런 에러에 대처할 방법이 전혀 없다.
>
> 자바에서는 에러 이외에 예외( exception)이라고 부르는 오류가 있다. 이는 사용자의 잘못된 조작 도는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램 오류를 말한다. 예외 발생 시 프로그램은 곧바로 종료된다는 점에서 에러와 동일하다. 그러나 예외는 예외처리를 통해 프로그램을 종료하지 않고 정상 실행 상태가 유지되도록 할 수 있다.

- 예외의 종류

  - 일반 예외(Exception)

    컴파일러 체크 예외라고도 하며, 자바 소스를 컴파일하는 과정에서 예외 처리 코드가 필요한지 검사한다. 예외 처리 코드가 없으면 컴파일 오류가 발생한다.

  - 실행 예외(RuntimeException)

    컴파일하는 과정에서 예외 처리 코드를 검사하지 않는 예외를 말한다.

- JVM은 프로그램을 실행하는 도중에 예외가 발생하면 해당 예외 클래스로 객체를 생성한다. 그리고 예외 처리 코드에서 예외 객체를 이용하도록 한다.

- 모든 예외 클래스는 java.lang.Exception을 상속 받는다.

- 일반 예외는  Exception은 상속 받지만 RuntimeException을 상속 받지 않고, 실행 예외는 RuntimeException을 상속 받음으로 둘 다 받게 된다.

<br>



## 10.2 실행 예외

> 실행 예외는 자바 컴파일러가 체크를 하지 않기 때문에 오로지 개발자의 경험에 의해서 예외 처리 코드를 삽입해야 한다.



### 10.2.1 NullPointException

- 객체 참조가 없는 상태, 즉 null값을 갖는 참조 변수로 객체 접근 연산자인 도트(.)를 사용했을 때 발생한다.

```java
public class NullPointExceptionEx{
	public static void main(String[]args){
		String data = null;
		System.out.println(data.toString()); //toString 함수가 참조할 객체가 없으므로 예외 발생
	}
}
```



### 10.2.2 ArrayIndexOutOfBoundsException

- 배열에서 인덱스 범위를 초과하여 사용할 경우 발생한다.

```java
public class ArrayIndexOutOfBoundsException{
	public static void main(String[]args){
		String data1 = args[0]; //args[0], args[1]의 실행 매개값 주기 않았기 때문에 예외 발생
		String data2 = args[1];
		//if(args.lenghth ==2) 으로 먼저 조건을 걸어 예외를 처리해야 함.
		System.out.println("args[0]:" + data1);
		System.out.println("args[1]:" + data2);
	}
}
```



### 10.2.3 NumberFormatException

- 문자열로 되어 있는 데이터를 숫자로 변경할 때, 해당 데이터에 숫자로 변환될 수 없는 문자가 포함되어 있으면 발생한다.

```java
public class NumberFormatException{
	public static void main(String[]args){
		String data1 = "100";
		String data2 = "a100";

		int value1= Integer.parseInt(data1);
		int value2= Integer.parseInt(data2); //숫자 변환 불가 문자 포함되어 있어 예외 발생

		System.out.println(data1 + data2);
	}
}
```

### 10.2.4 ClassCastException

- 타입 변환될 수 없는 클래스나 인터페이스를 강제로 타입 변환하고자 할 때 발생한다.

  ```java
  Animal animal = new Dog();
  // Cat cat = (Cat) animal; 참조한 자식 객체가 아니므로 예외 발생
   
  RemoteControl rc = new TV;
  //Audio audio = (Audio) rc; 대입한 구현 클래스가 아니므로 예외 발생
  ```

- 해당 예외를 발생시키지 않기 위해서 타입 변환 전에 타입 변환이 가능한지 instanceof 연산자로 확인하는 것이 좋다. instanceof

  ```java
  Animal animal = new Cat();
  if(animal instanceof Cat){
  	Cat cat = (Cat) animal;
  } else if(animal instanceof Dog){
  	Dog dog = (Dog) animal;
  }
  ```

<br>



## 10.3  예외 처리 코드

> 프로그램에서 예외가 발생했을 경우 프로그램의 갑작스러운 종료를 막고, 정상 실행을 유지할 수 있도록 처리하는 코드를 예외 처리 코드라고 한다.

- 예외 처리 코드는 try-catch-finally 블록을 사용할 수 있다.
- try-catch-finally 블록은 생성자 내부와 메소드 내부에서 작성되어 일반 예외와 실행 예외가 발생할 수 있도록 예외 처리를 해준다.

```java
public class Try{
	public static void main(String[]args){
		String data1 = null;
		
		try{
			data1 = args[0]; //예외 발생시 예외 객체를 catch의 매개값으로 보낸다.
		} catch(ArrayIndexOutOfBoudsException e) {
				System.out.println("실행 매개값의 수가 부족합니다");
				System.out.println("실행방법");
			  System.out.println("java Try num1");
		} finally { //예외 발생 여부와 상관 없이 실행, try나 catch블록에서 return문을 사용하더라도 항상 실행됨
				System.out.println("다시 실행하세요");
		}
	}
}
```

<br>



## 10.4 예외 종류에 따른 처리 코드

### 10.4.1 다중 catch

- try 블록 내부에서 다양한 종류의 예외가 발생할 수 있다. 이 때 발생하는 예외별로 예외 처리 코드를 다양하게 하기 위해 다중 catch블록을 사용할 수 있다.
- catch 블록이 여러 개 있더라도 try 블록에서 하나의 예외가 발생하면 즉시 해당의 catch 블록으로 이동하기 때문에 실행되는 catch 블록은 단 하나이다.



### 10.4.2 catch 순서

- 다중 catch 블록을 작성할 때는 상위 예외 클래스가 하위 예외 클래스보다 아래쪽에 위치하도록 해야 한다. try블록에서 예외 발생 시 catch 블록을 위부터 차례 대로 검색하기 때문에 하위 예외가 상속한 상위 예외가 실행될 수 있기 때문이다.

```java
public class Catch{
	public static void main(String[]args){
		try{
			String data1 = args[0];
			System.out.println(data1);
		} catch(ArrayIndexOfBoundsException e){ //하위 예외 클래스가 먼저 있음
				System.out.println("실행 매개값의 수가 부족합니다.");
			} catch(Exception e) { //상위 예외 클래스 일수록 아래에 있어야 함
				System.out.println("실행에 문제가 있습니다.");
			} finally {
				System.out.println("다시 실행하세요");
			}
	}
}
```



### 10.4.3 멀티 catch

- 자바7부터 하나의  catch 블록에서 여러 개의 예외를 처리할 수 있도록 멀티(multi) catch 기능을 추가했다.

```java
try{
  //실행 내용
} catch (ArrayIndexOfBoundsException e || NumberFormatException e) {// ||를 통해 멀티 가능
	System.out.println("실행 매개값의 수가 부족하거나 숫자로 변환할 수 없습니다.");
} catch (Exception e) {
	System.out.println("알 수 없는 예외 발생");
}
```



<br>



## 10.5 자동 리소스 닫기

- try-with-resources를 사용하면 예외 발생 여부와 상관없이 사용했던 리소스 객체의 close() 메소드를 호출해서 안전하게 리소스를 닫아준다.

  (리소스 : 지금은 데이터를 읽고 쓰는 객체 정도로 생각)

  ```java
  try(FileInputStream fis = new FileInputStream("file.txt");{
  	...
  } catch(IOException e){
  	...
  }
  ```

- try 블록이 정상적으로 **실행을 완료했거나 도중에 예외가 발생하게 되면** 자동으로 FileInputStream의 close()메소드가 호출된다. try{}에서 예외가 발생하면 우선 close()로 리소스를 닫고 catch 블록을 실행한다.

  ```java
  //복수 개의 리소스를 사용해야 한다면 다음과 같이 작성할 수 있다.
  try(
  		FileInputStream fis = new FileInputStream("file1.txt");
  		FileOutputStream fos = new FileOutputStream("file2.txt")
  ) {
  	...
  } catch(IOException e){
  	...
  }
  ```



> try-with-resources를 사용하기 위해서 리소스 객체는 java.lang.AutoCloseable 인터페이스를 구현하고 있어야 한다. AutoCloseable에는 close() 메소드가 정의되어 있는데 try-with-resources는 이 메소드를 자동 호출한다.

```java
//AutoCloseable 구현 클래스
public class FileInputStream implements AutoCloseable {
	private String file;

	public FileInputStream(String file){
		this.file = file;
	}
	
	public void read(){
		System.out.println(file +"을 읽습니다.");
	}
	
	@Override
	public void close() throws Exception {
		System.out.println(file +"을 닫습니다.");
	}
}
public class Try {
	public static void main(String[]args){
		try (FileInputStream fis =  new FileInputStream("file.txt")){
			fis.read();
			throw new Exception();//강제 예외 발생 ->  close() 호출됨
		} catch(Exception e){
			System.out.println("예외 처리 코드가 실행되었습니다.");
		}
	}
}
```

<br>



## 10.6 예외 떠넘기기

> 메소드 내부에서 예외가 발생할 수 있는 코드를 작성할 때 try-catch 블록으로 예외를 처리하는 것이 기본이지만. 경우에 따라서는 메소드를 호출한 곳으로 예외를 떠넘길 수도 있다. 이 때 throws를 사용한다.

```java
리턴타입 메소드명(매개 변수, ...) throws 예외클래스1, 예외클래스2, ...{ //Exception만 적을 수도 있다
}
public void method1(){
		try{
			method2(); //호출한 곳
		} catch(ClassNotFoundException e) {
			System.out.println("클래스가 존재하지 않습니다");
		}
	}

	public void method2() throws ClassNotFoundException {//호출한 곳에서 예외처리하게 됨
		Class clazz = Class.forName("java.lang.String2");
	}
```

- 자바 API 도큐먼트에서 클래스 생성자와 메소드 선언부에 throws 키워드가 있는 것의 생성자와 메소드를 사용하고 싶다면, 반드시 try-catch 블록으로 예외 처리를 하거나, throws를 다시 사용해서 예외를 호출한 곳으로 떠넘겨야 한다. 그렇지 않으면 컴파일 오류가 발생한다.



- 예제 : forName () 메소드 try-catch 블록 처리

```java
public class Ex {
	public static void main(String[]args){
		try{
			findClass(); //호출한 곳
		} catch(ClassNotFoundException e) {
			System.out.println("클래스가 존재하지 않습니다");
		}
	}

	public static void findClass() throws ClassNotFoundException {//호출한 곳에서 예외처리하게 됨
		Class clazz = Class.forName("java.lang.String2");
	}
}
```

- main 메소드에서도 throws키워드를 통해 예외처리 떠넘겨서 JVM이 최종적으로 예외처리를 하게 할 수 있다. 이는 콘솔에 출력된다. 권장되는 방법은 아니다.

<br>



## 10.7 사용자 정의 예외와 예외 발생

- 자바 표준 API에서 제공하는 예외 클래스 외에 개발자가 직접 정의하여 예외를 만들 수 있다. 이를 **사용자 정의 예외** 라고 하며, 애플리케이션 서비스와 관련된 예외이기도 하기에 **애플리케이션 예외(Application Exception)** 라고도 한다.



### 10.7.1 사용자 정의 예외 클래스 선언

- 일반 예외로 선언할 경우 Exception을 상속하고, 실행 예외로 선언할 경우 RuntimeException을 상속한다.

- 이름은 Exception으로 끝나는 것이 좋다.

- 필드, 생성자, 메소드 선언들을 포함할 수 있지만 대부분 생성자 선언만을 포함한다.

- 생성자는 일반적으로 두 개를 선언한다.

  1. 매개 변수가 없는 기본 생성자
  2. 예외 발생 원인(예외 메시지)을 전달하기 위해  String 타입의 매개 변수를 갖는 생성자

  - 상위 클래스의 생성자를 호출하여 예외 메시지를 넘겨준다. 예외메시지는 catch() 블록의 예외 처리 코드에서 이용하기 위함이다.

  ```java
  //사용자 정의 예외 클래스
  public class BalanceInsufficientException extends Exception {
  	public BalanceInsufficientException(){}
  	public BalanceInsufficientException(String message){
  		super(message);
  	}
  }
  ```

  - Exception을 상속하므로 컴파일러에 의해 체크 되는 예외가 되므로 소스 작성 시 try-catch 블록으로 예외 처리가 필요하다.

  

### 10.7.2 예외 발생시키기

- 다음과 같이 예외를 발생 시킬 수 있다.

```java
throw new XXXException();
throw new XXXException("메시지");//catch블록에서 예외 메시지가 필요하다면 이 생성자를 사용
```

- 예외 발생 코드를 가지고 있는 메소드는 내부에서  try-catch 블록으로 예외를 처리할 수 있지만, 대부분은 자신을 호출한 곳에서 예외를 처리하도록 throws 키워드로 예외를 떠넘긴다.

```java
public void method() throws XXXException{
	throw new XXXException("메시지");
}
```

<br>



## 10.8 예외 정보 얻기

> try 블록에서 예외가 발생되면 예외 객체는 catch 블록의 매개 변수에서 참조하게 되므로 매개 변수를 이용하면 예외 객체의 정보를 알 수 있다. 모든 객체는 Exception을 상속하므로 Exception이 가진 모든 메소드들을 모든 예외 객체에서 호출할 수 있다.

- 예외를 발생시켰을 때 String 타입의 메시지를 갖는 생성자를 이용했다면, 메시지는 자동적으로 예외 객체 내부에 저장된다. ex) throw new XException("예외 메시지");
- 예외 메시지의 내용에는 왜 예외가 발생했는지에 대한 간단한 설명이 포함되며, 좀 더 상세한 원인을 알기 위해 예외 코드를 포함하기도 한다.

```java
try{
	// 실행내용 및 예외 객체 생성
} catch (예외 클래스 e){
	//예외가 가지고 있는 메시지 얻기
	String message = e.getMessage();

	//예외의 발생 경로를 추적
	e.printStackTrace(); //콘솔에 추적 내용이 출력됨
}
```