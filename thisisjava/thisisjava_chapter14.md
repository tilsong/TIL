> **신용권 님의 ''이것이 자바다'' 14장 공부 기록**
>
> 책을 보면서 내용을 정리했다.
>
> [소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)

<br>

# 14. 람다식



## 14.1 람다식

> 자바는 함수적 프로그래밍을 위해 자바 8부터 람다식(Lambda Expression)을 지원한다. 
>
> 람다식은 익명함수(anonymous function)을 생성하기 위한 식으로 객체지향 언어 보다는 함수지향 언어에 가까워  기존 자바 개발과 다르게 생각될 수 있다.
>
> 자바에서 람다식을 수용한 이유는 자바 코드가 매우 간결해지고, 컬렉션의 요소를 필터링하거나 매핑해서 원하는 결과를 쉽게 집계할 수 있기 때문이다.
>
> 람다식의 형태는 매개 변수를 가진 코드 블록이지만, 런타임시에는 익명 구현 객체를 생성한다.
>
> (람다식 -> 매개 변수를 가진 코드 블록 -> 익명 구현객체)

```java
//Runnable 인터페이스의 익명 구현 객체 생성하는 전형적인 코드
Runnable runnable = new Runnable() {
    public void run();
};
//Runnable 인터페이스의 익명 구현 객체 생성하는 람다식
Runnable runnable = () -> {...};
```

<br>



## 14.2 람다식의 기본 문법

- 함수적 스타일의 람다식을 작성하는 방법은 다음과 같다.

```java
(타입 매개변수, ...) -> {실행문; ...} // 매개 변수의 이름은 개발자가 마음대로 줄 수 있다.
```

- 람다식 기본 문법

```java
//기본
(int a) -> {System.out.println(a);}

//매개 변수가 1개 라면 ()와 타입을 생략할 수 있다. 매개 변수 타입은 대입되는 값에 따라 자동으로 인식될 수 있기 때문에 람다식에서는 매개 변수의 타입을 일반적으로 언급하지 않는다.
a -> {System.out.prinln(a);}

//실행문이 하나일 때 {}를 생략할 수 있다.
a -> System.out.println(a)

//만약 매개 변수가 없다면 빈 괄호를 사용해야 한다.    
() ->{실행문; ..}

//리턴하는 실행문은 다음과 같이 작성할 수 있다.
(x,y) -> {return x+y;}

//중괄호에 return문만 있을 경우 다음과 같이 작성할 수 있다.
(x,y) -> x+y
```

<br>



## 14.3 타겟 타입과 함수적 인터페이스

> 람다식의 형태는 매개 변수를 가진 코드 블록이기 때문에 마치 자바의 메소드를 선언하는 것처럼 보이나, 자바는 메소드를 단독으로 선언할 수 없고 항상 클래스의 구성 멤버로 선언하기 때문에 람다식은 단순히 메소드를 선언하는 것이 아니라 이 메소드를 가지고 있는 객체를 생성해 낸다.

```java
  인터페이스 변수 = 람다식; //람다식으로 인터페이스의 익명 구현 객체를 생성
//(타겟 타입)
```

- 람다식은 대입될 인터페이스의 종류에 따라 작성 방법이 달라지기 때문에 람다식이 대입될 인터페이스를 람다식의 **타겟 타입(target type)**이라고 한다.



### 14.3.1 함수적 인터페이스(@FunctionalInterface)

- 람다식이 하나의 메소드를 정의하므로, 두 개 이상의 추상 메소드가 선언된 인터페이스는 람다식을 이요해서 구현 객체를 생성할 수 없다. 이렇듯 하나의 추상메소드 만이 선언된 인터페이스를 함수적 인터페이스(functional interface)라고 한다. 
- 함수적 인터페이스를 작성할 때 두 개 이상의 추상 메소드가 선언되지 않도록 컴파일러가 체킹하도록 하기 위하여 인터페이스 선언 시 @FunctionalInterface 어노테이션을 붙일 수 있다. 추상 메소드가 두 개 이상이면, 컴파일 오류가 발생된다.

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    public void method();
    public void otherMethod(); // 컴파일 오류 발생
}
```





### 13.4.2 매개 변수와 리턴값이 없는 람다식

- 매개 변수와 리턴값이 없는 추상 메소드를 가진 함수적 인터페이스는 다음과 같이 사용될 수 있다.

```java
//함수적 인터페이스
@FunctionalInterface
public interface MyFunctionalInterface {
    public void method();
}
```

```java
//위 인터페이스를 타겟 타입으로 갖는 람다식
MyFunctionalInterface fi = () -> {...}

//참조 변수로 메소드를 호출하여 람다식의 중괄호를 실행시킴
fi.method();
```

```java
public class MyFunctionalInterfaceEx{
    public static void main(String[]args){
        MyFunctionalInterface fi;
        
        fi= () -> {
            String str = "method call1";
            System.out.println(str);
        };
        fi.method();
        
        fi = () -> System.out.println("mehod call2");
        fi.method();
    }
}
```





### 13.4.3 매개 변수가 있는 람다식

- 매개 변수가 있고 리턴값이 없는 추상 메소드를 가진 함수적 인터페이스는 다음과 같이 사용될 수 있다.

```java
@FunctionalInterface
public interface MyFunctionalInterface{
    public void method(int x);
}
```

```java
//람다식
MyFunctionalInterface fi = x -> {...};

//메소드 호출
fi.method(5);
```



### 14.3.4 리턴값이 있는 람다식

- 매개 변수가 있고 리턴값이 있는 추상 메소드를 가진 함수적 인터페이스는 다음과 같이 사용될 수 있다.

```java
@FunctionalInterface
public interface MyFunctionalInterface{
    public int method(int x, int y);
}
```

```java
//람다식
MyFunctionalInterface fi = (x, y) -> {...; return 값; };

//메소드 호출
fi.method(2,4);
```

<br>



## 14.4 클래스 멤버와 로컬 변수 사용

> 람다식의 실행 블록에는 클래스의 멤버(필드와 메소드) 및 로컬 변수를 사용할 수 있다.
>
> 클래스의 멤버는 제약 사항 없이 사용 가능하지만, 로컬 변수는 제약이 따른다.



### 14.4.1 클래스의 멤버 사용

- 람다식 실행 블록에는 클래스의 멤버인 필드와 메소드를 제약 없이 사용할 수 있으나, **this** 키워드의 사용에는 주의가 필요하다.
- 일반적으로 익명 객체 내부에서 this는 익명 객체의 참조이지만, 람다식에서 this는 내부적으로 생성되는 익명 객체의 참조가 아니라 람다식을 실행한 객체의 참조이다.

```java
//함수적 인터페이스
@FunctionalInterface
public interface MyFunctionalInterface{
    public void method();
}
```

```java
//this 사용
public class UsingThis{
        public int outter =10;
        
        class Inner{
            int inner =20;
            
            void method(){
                //람다식
                MyFunctionalInterface fi = () -> {
                    System.out.println("outter: "+ outter);
                    System.out.println("outter: "+ UsingThis.this.outter+"\n");
         			//바깥 객체의 참조 얻기 위해서는 클래스명.this 사용           
                    System.out.println("inner: "+ inner);
                    System.out.println("inner: "+ this.inner +"\n");//내부 객체는 this 사용 가능
                };
                fi.method;
            }
        }
}
```

```java
//실행 클래스
public class UsingThisEx{
    public static void main(String[]args){
        UsingThis usingThis = new UsingThis();
        UsingThis.Inner inner = new usingTHis.new Inner();
        inner.method();
    }
}
```





### 14.4.2 로컬 변수 사용

- 람다식은 메소드 내부에서 주로 작성되기 때문에 로컬 익명 구현 객체를 생성시킨다고 봐야 한다. 이 때 람다식에서 바깥 클래스의 필드나 메소드는 제한 없이 사용할 수 있으나, **메소드의 매개 변수 또는 로컬 변수**를 사용하면 이 두 변수는 **final 특성**을 가져야 한다. 
- 따라서 매개 변수 또는 로컬 변수를 람다식에서 읽는 것은 허용되지만, 람다신 내부 또는 외부에서 변경할 수 없다.
- (9.5.3 익명 객체의 로컬 변수 사용 참고, 람다식 내부 변수는 변경 가능)

<br>



## 14.5 표준 API의 함수적 인터페이스

> 자바 8부터는 빈번하게 사용되는 함수적 인터페이스(functional interface)는 java.util.function 표준  API로 제공한다.
>
> 이 패키지에서 제공하는 함수적 인터페이스의 목적은 **메소드 또는 생성자의 매개 타입으로 사용되어 람다식을 대입**할 수 있도록 하기 위해서이다.  
>
> java.util.function 패키지의 함수적 인터페이스는 크게 Consumer, Supplier, Function, Operator, Predicate로 구분 되며, 구분 기준은 인터페이스에 선언된 추상 메소드의 매개값과 리턴값의 유무이다.



### 14.5.1 Consumer 함수적 인터페이스

- Consumer 함수적 인터페이스는 **단지 매개값을 소비하며** **리턴값이 없는 accept() 메소드**를 가진다. 
- 매개 변수의 타입과 수에 따른 Consumer들

| 인터페이스명      | 추상 메소드                    | 설명                           |
| ----------------- | ------------------------------ | ------------------------------ |
| Consumer<T>       | void accept(T t)               | 객체를 T를 받아 소비           |
| BiConsumer<T,U>   | void accept(T t, U u)          | 객체 T, U를 받아 소비          |
| DoubleConsumer    | void accept(double value)      | double 값을 받아 소비          |
| intConsumer       | void accept(int value)         | int 값을 받아 소비             |
| LongConsumer      | void accept(long value)        | long 값을 받아 소비            |
| ObjDoubleConsumer | void accept(T t, double value) | 객체 T와 double 값을 받아 소비 |
| ObjIntConsumer    | void accept(T t, int value)    | 객체 T와 int 값을 받아 소비    |
| ObjLongConsumer   | void accept(T t, long value)   | 객체 T와 long 값을 받아 소비   |

- 예를 들어, 다음과 같이 작성할 수 있다.

```java
//Consumer<T> 인터페이스를 타겟 타입으로 하는 람다식
Consumer<String> consumer = t -> {t를 소비하는 실행문;};

//ObjIntConsumer<T> 인터페이스를 타겟 타입으로 하는 람다식
ObjIntConsumer consumer = <t,i> -> {t와 i를 소비하는 실행문}
```

```java
//Consumer 함수적 인터페이스
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjIntConsumer;

public class ConsumerEx {

	public static void main(String[] args) {
		Consumer<String> consumer = t-> System.out.println(t+"8");
		consumer.accept("java");
		
		BiConsumer<String, String> biConsumer = (t, u) -> System.out.println(t+u);
		biConsumer.accept("java","8");
		
		DoubleConsumer doubleConsumer = d -> System.out.println("java"+d);
		doubleConsumer.accept(8.0);
		
		ObjIntConsumer oiConsumer = (t, u) -> System.out.println(t+u);
		oiConsumer.accept("java", 8);
	}
}
```



### 14.5.2 Supplier 함수적 인터페이스

- Supplier 함수적 인터페이스는 **매개 변수가 없고 리턴값이 있는 getXXX() 메소드**를 가진다. 이 메소드들은 실행 후 호출한 곳으로 데이터를 리턴(공급)하는 역할을 한다.

- 리턴 타입에 따른 Supplier 함수적 인터페이스들

  | 인터페이스명    | 추상 메소드            | 설명              |
  | --------------- | ---------------------- | ----------------- |
  | Supplier<T>     | T get()                | T 객체를 리턴     |
  | BooleanSupplier | boolean getAsBoolean() | boolean 값을 리턴 |
  | DoubleSupplier  | double getAsDouble()   | double 값을 리턴  |
  | IntSupplier     | int getAsInt()         | int 값을 리턴     |
  | LongSupplier    | long getAsLong()       | long 값을 리턴    |

- 예제 : 주사위 수 랜덤 공급

```java
import java.util.function.IntSupplier;

public class SupplierEx {

	public static void main(String[] args) {
		IntSupplier intSupplier = () -> {
			int num = (int) (Math.random()* 6) + 1;
			return num;
		};	// 람다식
		
		int num = intSupplier.getAsInt();
		System.out.println("눈의 수: " +num);
	}

}
```





### 14.5.3 Function 함수적 인터페이스

- Function 함수적 인터페이스는 **매개값과 리턴값이 있는** **appyXXX()** 메소드를 가지며, 이 메소드들은 매개값을 리턴값으로 매핑(타입 변환)하는 역할을 한다.
- 매개 변수와 리턴 타입에 따른 Function 함수적 인터페이스들

| 인터페이스명            | 추상메서드                     | 설명                        |
| ----------------------- | ------------------------------ | --------------------------- |
| Function<T,R>           | R apply(T t)                   | 객체 T를 객체 R로 매핑      |
| BiFunction<T,U,R>       | R apply(T t, U u)              | 객체 T와 U를 객체 R로 매핑  |
| DoubleFunction<R>       | R apply(double val)            | double 를 객체 R로 매핑     |
| IntFunction<R>          | R apply(int val)               | int 를 객체 R로 매핑        |
| IntToDoubleFunction     | double applyAsdouble(int val)  | int를 double로 매핑         |
| IntToLongFunction       | long applyAsLong(int val)      | int를 long로 매핑           |
| LongToDoubleFunction    | double applyAsdouble(long val) | long을 double로 매핑        |
| LongToIntFunction       | int applyAsInt(long val)       | long을 int로 매핑           |
| ToDoubleBiFunction<T,U> | double applyAsDouble(T t, U u) | 객체 T와 U를 double 로 매핑 |
| ToDoubleFunction<T>     | double applyAsdouble(T t)      | 객체 T를 double로 매핑      |
| ToIntBiFunction<T,U>    | int applyAsInt(T t, U u)       | 객체 T와 U를 int로 매핑     |
| ToIntFunction<T>        | int applyAsInt(T t)            | 객체 T를 int로 매핑         |
| ToLongBiFunction<T,U>   | long applyAsLong(T t, U u)     | 객체 T와 U를 long으로 매핑  |
| ToLongFunction<T>       | long applyAsLong(T t)          | 객체 T를 long으로 매핑      |

- 예제 : Student 이름과 점수 출력

```java
//Student 클래스
public class Student {
	private String name;
	private int engScore;
	private int korScore;
	
	public Student(String name, int engScore, int korScore) {
		this.name=name;
		this.engScore=engScore;
		this.korScore=korScore;
	}
	
	public String getName() {
		return this.name;
	}
	public int getEngScore() {
		return this.engScore;
	}
	public int getKorScore() {
		return this.korScore;
	}
}

```

```java
//FunctionEx
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class FunctionEx {
	private static List <Student> list = Arrays.asList(
			new Student("홍길동", 90,58),
			new Student("신용권", 10,30)
			);
	
	public static void printString(Function<Student, String> function) {
		for(Student student : list) {
			System.out.print(function.apply(student)+" ");
		}
		System.out.println();
	}
	
	public static void printInt(ToIntFunction<Student> function) {
		for(Student student : list) {
			System.out.print(function.applyAsInt(student)+ " ");
		}
		System.out.println();
	}
	
	public static void main(String[]args) {
		System.out.println("[학생 이름]");
		printString(t-> t.getName());
		
		System.out.println("[영어 점수]");
		printInt(t-> t.getEngScore());
		
		System.out.println("[국어 점수]");
		printInt(t-> t.getKorScore());
	}
}
```





### 14.5.4 Operator 함수적 인터페이스

- Operator 함수적 인터페이스는 Function과 동일하게 매개 변수와 리턴값이 있는 applyXXX()메소드를 가지고 있으나, Function 처럼 매핑하는 것이 아닌 매개값을 이용해 연산을 수행한 후 동일한 타입으로 리턴값을 제공하는 역할을 한다.
- Operator 함수적 인터페이스들

| 인터페이스명         | 추상메서드                           | 설명                     |
| -------------------- | ------------------------------------ | ------------------------ |
| BinaryOperator<T>    | BiFunction<T,U,R>의 하위 인터페이스  | T와 U를 연산한 후 R 리턴 |
| UnaryOperator<T>     | Function<T,R>의 하위 인터페이스      | T를 연산한 후 R 리턴     |
| DoubleBinaryOperator | double applyAsDouble(double, double) | 두 개의 double 연산      |
| DoubleUnaryOperator  | double applyAsDouble(double)         | 한 개의 double 연산      |
| IntBinaryOperator    | int applyAsInt(int,int)              | 두 개의 int 연산         |
| IntUnaryOperator     | int applyAsInt(int)                  | 한 개의 int 연산         |
| LongBinaryOperator   | long applyAsLong(long, long)         | 두 개의 long 연산        |
| LongUnaryOperator    | long applyAsLong(long)               | 한 개의 long 연산        |



- 예제 : 최소값과 최대값 구하기

```java
import java.util.function.IntBinaryOperator;

public class OperatorEx {
	private static int[] scores = {92, 30,90};
	
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for(int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;
	}
	
	public static void main(String[]args) {
		//최대값 얻기
		int max = maxOrMin(
				(a,b) -> {
					if(a>=b) return a;
					else return b;
				}
		);
		System.out.println("최대값: " + max);
		//최소값 얻기
		int min = maxOrMin(
				(a,b) -> {
					if(a<=b) return a;
					else return b;
				}
		);
		System.out.println("최소값: " + min);
				
	}
}
```





### 14.5.5 Predicate 함수적 인터페이스

- Predicate 함수적 인터페이스는 매개 변수와 boolean 리턴값이 있는 testXXX() 메소드를 가지고 있다. 이 메소드들은 매개값을 조사해서 true 또는 false를 리턴하는 역할을 한다.
- Predicate 함수적 인터페이스들

| 인터페이스명      | 추상 메소드                | 설명                   |
| ----------------- | -------------------------- | ---------------------- |
| Predicate         | boolean test(T t)          | 객체 T를 조사          |
| BiPredicate<T, U> | boolean test(T t, U u)     | 객체 T와 U를 비교 조사 |
| DoublePredicate   | boolean test(double value) | double 값을 조사       |
| IntPredicate      | boolean test(int value)    | int 값을 조사          |
| LongPredicate     | boolean test(long value)   | long 값을 조사         |

- 예제: 남자 여자 학생들의 평균 점수 출력

```java
//StudentP 클래스
public class StudentP {
	private String name;
	private String sex;
	private int score;
	
	public StudentP(String name, String sex, int score) {
		this.name=name;
		this.sex=sex;
		this.score=score;
	}
	
	public String getName() {
		return this.name;
	}
	public String getSex() {
		return this.sex;
	}
	public int getScore() {
		return this.score;
	}
}

```

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateEx {


		private static List<StudentP> list = Arrays.asList(
				new StudentP("홍길동", "남자", 90),
				new StudentP("김길동", "남자", 80),
				new StudentP("김자바", "여자", 97),
				new StudentP("박자바", "여자", 70)
				);

		public static double avg(Predicate<StudentP> predicate) {
			int count=0, sum=0;
			for(StudentP student : list) {
				if(predicate.test(student)) {
				count++;
				sum += student.getScore();
				}
			}
			return (double) sum/count;
		}

		public static void main(String[] args) {
			double maleAvg = avg(t-> t.getSex().equals("남자"));
			System.out.println("남자 평균 점수: "+ maleAvg);
			double femaleAvg = avg(t-> t.getSex().equals("여자"));
			System.out.println("여자 평균 점수: "+ femaleAvg);
			
	}

}
```



### 14.5.6 andThen()과 compose() 디폴트 메소드

- 디폴트 및 정적 메소드는 추상 메소드가 아니기 때문에 함수적 인터페이스에 선언되어도 여전히 함수적 인터페이스의 성질을 잃지 않는다.

- Consumer, Function, Operator  종류의 함수적 인터페이스는 andThen()과 compose() 디폴트 메소드를 가지고 있다. 둘 다  두 개의 함수적인 인터페이스를 순차적으로 연결하고 , 첫 처리 결과를 두번째 매개값으로 제공해서 최종 결과값을 얻는다. 

  ```
  인터페이스AB = 인터페이스A.andThen(인터페이스B);
  최종결과 = 인터페이스AB.method(); // andThen()은 A처리 ->A결과로 B처리
  
  인터페이스AB = 인터페이스A.compose(인터페이스B);
  최종결과 = 인터페이스AB.method(); // andThen()은 B처리 ->B결과로 A처리
  ```

- andThen()과 compose() 디폴트 메소드들

|   종류   |  함수적 인터페이스  | andThen() | compose() |
| :------: | :-----------------: | :-------: | :-------: |
| Consumer |      Consumer       |     O     |           |
|          |  BiConsumer<T, U>   |     O     |           |
|          |   DoubleConsumer    |     O     |           |
|          |     IntConsumer     |     O     |           |
|          |    LongConsumer     |     O     |           |
| Function |   Function<T, R>    |     O     |     O     |
|          | BiFunction<T, U, R> |     O     |           |
| Operator |   BinaryOperator    |     O     |           |
|          | DoubleUnaryOperator |     O     |     O     |
|          |  IntUnaryOperator   |     O     |     O     |
|          |  LongUnaryOperator  |     O     |     O     |

- Consumer()의 andThen은 처리 결과를 리턴하지 않으므로 호출 순서만 정한다.
- 예제 :andThen

```java
import java.util.function.Consumer;

public class ConsumerAndThenEx {

	public static void main(String[] args) {
		Consumer<Member> consumerA = (m) -> {
			System.out.println("consumerA : "+m.getName());
		
		};
		
		Consumer<Member> consumerB = (m) -> {
			System.out.println("consumerB : "+m.getId());
		};
		
		Consumer<Member> consumerAB = consumerA.andThen(consumerB);
		consumerAB.accept(new Member("홍길동", "hong", null) );

	}

}
```

``` java
//Member 클래스
public class Member {
	private String name;
	private String id;
	private Address address;
	
	public Member(String name, String id, Address address) {
		this.name=name;
		this.id=id;
		this.address=address;
	}
	
	public String getName() {
		 return name;
	}
	public String getId() {
		return id;
	}
	public Address getAddress() {
		return address;
	}
}
```

```java
//Address클래스
public class Address {
	private String country;
	private String city;
	
	public Address(String country, String city) {
		this.country=country;
		this.city=city;
	}
	
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
}
```

- Function과 Operator는 매개값을 통한 첫 처리결과를 다음 인터페이스의 매개 값으로 넘겨주고, 최종 처리결과를 리턴한다.

```java
import java.util.function.Function;

public class FuntionAndThenComposeEx {
	public static void main(String[]args) {
		Function<Member, Address> functionA;
		Function<Address, String> functionB;
		Function<Member, String> functionAB;
		String city;
		
		functionA = (m)->m.getAddress();
		functionB = (a)->a.getCity();
		
		functionAB = functionA.andThen(functionB);
		
		city = functionAB.apply(
			new Member("홍홍", "hong", new Address("한국", "서울"))
		);
		System.out.println("거주 도시 : "+city);
		
		functionAB = functionB.compose(functionA);
		
		city = functionAB.apply(
				new Member("공공", "gong", new Address("미국", "뉴욕"))
				);
		System.out.println("거주 도시: " +city);
	}
				
}

```





### 14.5.7 and(), or(), negate() 디폴트 메소드와 isEqual() 정적 메소드

- Predicate 종류의 함수적 인터페이스는 **and(), or(), negate()** 디폴트 메소드를 가지고 있으며, 이들은 각각 &&,||,!와 대응된다고 볼 수 있다.
- 예제 : 2의 배수와 3의 배수 조사하기

``` java
import java.util.function.IntPredicate;

public class PredicteAndOrNegateEx {

	public static void main(String[] args) {
		// 2배수 검사
		IntPredicate predicateA = a-> a%2 ==0;
		
		// 3배수 검사
		IntPredicate predicateB = b-> b%3 ==0;
		
		IntPredicate predicateAB;
		boolean result;
		
		//and()
		predicateAB = predicateA.and(predicateB);
		result = predicateAB.test(9);
		System.out.println("9는 2와 3의 배수입니까?" +result);
		
		//or()
		predicateAB = predicateA.or(predicateB);
		result = predicateAB.test(9);
		System.out.println("9는 2 또는 3의 배수입니까?" +result);		
		
		//negate()
		predicateAB = predicateA.negate();// 원래 결과 true이면 false로
		result = predicateAB.test(9);
		System.out.println("9는 홀수입니까?" +result);
		
	}

}
```



- Predicate<T>함수적 인터페이스는 isEqual() 정적 메소드를 추가로 제공한다.

```java
Predicate<Object> predicate = Predicate.isEqual(targetObeject);
boolean result = predicate.test(sourceObject);//Objects.equals(sourceObject, targetObject) 실행
```

| sourceObject | targetObject | 리턴값                                              |
| ------------ | ------------ | --------------------------------------------------- |
| null         | null         | true                                                |
| not null     | null         | false                                               |
| null         | not null     | false                                               |
| not null     | not null     | Objects.equals(sourceObject, targetObject)의 리턴값 |

```java
//예제
import java.util.function.Predicate;

public class PredicateIsEqualEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Predicate<String> predicate;
		
		predicate = Predicate.isEqual("java8");
		System.out.println("java8, null : " +predicate.test(null));
		
		predicate = Predicate.isEqual(null);
		System.out.println("null, null : " +predicate.test(null));
		
	}

}

```





### 14.5.8 minBy(), maxBy() 정적 메소드

- BinaryOperator<T> 함수적 인터페이스는 minBy()와 maxBy() 정적 메소드를 제공한다. 이 두 메소드는 매개값으로 제공되는 Comparator를 이용해서 최대 T와 최소 T를 얻는 BinaryOperator<T>를 리턴한다.

| 리턴 타입         | 정적 메소드                             |
| ----------------- | --------------------------------------- |
| BinaryOperator<T> | minBy(Comparator<? super T> comparator) |
| BinaryOperator<T> | maxBy(Comparator<? super T> comparator) |

```java
@FunctionalInterface
public interface Comparator<T>{
    public int compare(T o1, T o2);
}
```

```java
(o1, o2) -> {...; return int값;} //람다식
```

- 예제: 과일 가격 비교

```java
import java.util.function.BinaryOperator;

public class OperatorMinByMaxByEx {

	public static void main(String[] args) {
		BinaryOperator<Fruit> binaryOperator;
		Fruit fruit;
		
		binaryOperator = BinaryOperator.minBy((f1,f2)-> Integer.compare(f1.price,f2.price)); 
		fruit= binaryOperator.apply(new Fruit("딸기", 1000), new Fruit("사과",2000));
		System.out.println(fruit.name);
    }
}

```



<br>



## 14.6 메소드 참조

> 메소드 참조(Method Reference)는 메소드를 참조해서 매개 변수의 정보 및 리턴 타입을 알아내어, 람다식에서 불필요한 매개 변수를 제거하는 것이 목적이다.
>
> 메소드 참조도 람다식과 마찬가지로 인터페이스의 익명 구현 객체로 생성되므로 타겟 타입인 인터페이스의 추상 메소드가 어던 매개 변수를 가지고, 리턴 타입이 무엇인가에 따라 달라진다.



### 14.6.1 정적 메소드와 인스턴스 메소드 참조

- 정적(static) 메소드를 참조할 경우에는 클래스 이름 뒤에 ::기호를 붙이고 정적 메소드 이름을 기술하면 된다.

```java
클래스 :: 메소드
```

- 인스턴스 메소드일 경우에는 먼저 객체를 생성한 다음 참조 변수 뒤에 ::기호를 붙이고 인스턴스 메소드를 기술하면 된다.

```java
참조변수 :: 메소드
```

- 예제 : 정적 및 인스턴스 메소드 참조

```java
public class MehodReferencesEx {

	public static void main(String[] args) {
		IntBinaryOperator operator;
		
		//정적 메소드 참조
		operator = Calculator :: staticMethod;
		System.out.println("결과 : " + operator.applyAsInt(1, 2));
		
		Calculator obj = new Calculator();
		operator = obj :: instanceMethod;
		System.out.println("결과 : " +operator.applyAsInt(2, 4));

	}

}
```





### 14.6.2 매개 변수의 메소드 참조

- 메소드는 람다식 외부의 클래스 멤버일 수도 있고, 람다식에서 제공되는 매개 변수의 멤버일 수도 있다. 다음과 같이 람다식에서 제공되는 a 매개 변수의 메소드를 호출해서 b 매개 변수를 매개값으로 사용하는 경우도 있다.

```java
(a,b) -> {a.instanceMethod(b);}
//클래스 :: instanceMethod
```

- 예제 

```java
import java.util.function.ToIntBiFunction;

public class ArgumentMethodReferencesEx {

	public static void main(String[] args) {
		ToIntBiFunction<String,String> function;
		
		function = (a,b) -> a.compareToIgnoreCase(b);
		print(function.applyAsInt("java8", "JAVA8"));
		
		function = String :: compareToIgnoreCase;
		print(function.applyAsInt("java8", "JAVA8"));

	}
	
	public static void print(int order) {
		if(order<0) {
			System.out.println("사전순으로 먼저 옵니다.");
		} else if(order ==0) {
			System.out.println("동일한 문자열입니다.");
		} else {
			System.out.println("사전순으로 나중에 옵니다.");
		}
	}

}

```



### 14.6.3 생성자 참조

- 메소드 참조는 생성자 참조도 포함한다. 생성자를 참조한다는 것은 객체 생성을 의미한다. 
- 단순히 객체를 생성하고 리턴하도록 구성된 람다식은 생성자 참조로 대치할 수 있다.

```java
(a,b) -> {return new 클래스(a,b);}
//클래스 :: new 로 대치 가능
```

- 생성자가 오버로딩되어 여러 개 있을 경우, 컴파일러는 함수적 인터페이스의 추상 메소드와 동일한 매개 변수 타입과 개수를 가지고 있는 생성자를 찾아 실행한다. 만약 해당 생성자가 존재하지 않으면 컴파일 오류가 발생한다.

- 예제 

```java
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConstructorReferenceEx {

	public static void main(String[] args) {
		Function<String, MemberC> function1 = MemberC :: new;
		MemberC member1 = function1.apply("angel");
		
		BiFunction<String,String, MemberC> function2 = MemberC :: new;
		MemberC member2 = function2.apply("강강", "angel");
	}
}
```



