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
>  java.util.function 패키지의 함수적 인터페이스는 크게 Consumer, Supplier, Function, Operator, Predicate로 구분 되며, 구분 기준은 인터페이스에 선언된 추상 메소드의 매개값과 리턴값의 유무이다.



### 14.5.1 Consumer 함수적 인터페이스

- Consumer 함수적 인터페이스는 **단지 매개값을 소비하며** **리턴값이 없는 accept() 메소드**를 가진다. 
- 매개 변수의 타입과 수에 따른 Consumer들

| 인터페이스명         | 추상 메소드                    | 설명                           |
| -------------------- | ------------------------------ | ------------------------------ |
| Consumer<T>          | void accept(T t)               | 객체를 T를 받아 소비           |
| BiConsumer<T,U>      | void accept(T t, U u)          | 객체 T, U를 받아 소비          |
| DoubleConsumer       | void accept(double value)      | double 값을 받아 소비          |
| intConsumer          | void accept(int value)         | int 값을 받아 소비             |
| LongConsumer         | void accept(long value)        | long 값을 받아 소비            |
| ObjDoubleConsumer<T> | void accept(T t, double value) | 객체 T와 double 값을 받아 소비 |
| ObjIntConsumer<T>    | void accept(T t, int value)    | 객체 T와 int 값을 받아 소비    |
| ObjLongConsumer<T>   | void accept(T t, long value)   | 객체 T와 long 값을 받아 소비   |

- 예를 들어, 다음과 같이 작성할 수 있다.

```java
//Consumer<T> 인터페이스를 타겟 타입으로 하는 람다식
Consumer<String> consumer = t -> {t를 소비하는 실행문;};

//ObjIntConsumer<T> 인터페이스를 타겟 타입으로 하는 람다식
ObjIntConsumer<String> consumer = <t,i> -> {t와 i를 소비하는 실행문}
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
		
		ObjIntConsumer<String> oiConsumer = (t, u) -> System.out.println(t+u);
		oiConsumer.accept("java", 8);
	}
}
```



### 14.5.2 Supplier 함수적 인터페이스

- Supplier 함수적 인터페이스는 **매개 변수가 없고 리턴값이 있는 getXXX() 메소드**를 가진다. 이 메소드들은 실행 후 호출한 곳으로 데이터를 리턴(공급)하는 역할을 한다.
- 리턴 타입에 따른 Supplier 함수적 인터페이스들

| 인터페이스명         | 추상메서드                   | 설명                           |
| -------------------- | ---------------------------- | ------------------------------ |
| Consumer<T>          | void accept(T t)             | 객체 T를 받아 소비             |
| BiConsumer<T,U>      | void accept(T t, U u)        | 객체 T와 U를 받아 소비         |
| DoubleConsumer       | void accept(double val)      | double 값을 받아 소비          |
| IntConsumer          | void accept(int val)         | int 값을 받아 소비             |
| LongConsumer         | void accept(long val)        | long 값을 받아 소비            |
| ObjDoubleConsumer<T> | void accept(T t, double val) | 객체 T와 double 값을 받아 소비 |
| ObjIntConsumer<T>    | void accept(T t, int val)    | 객체 T와 int 값을 받아 소비    |
| ObjLongConsumer<T>   | void accept(T t, long val)   | 객체 T와 long 값을 받아 소비   |

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
| TODoubleFunction<T>     | double applyAsdouble(T t)      | 객체 T를 double로 매핑      |
| ToIntBiFunction<T,U>    | int applyAsInt(T t, U u)       | 객체 T와 U를 int로 매핑     |
| ToIntFunction<T>        | int applyAsInt(T t)            | 객체 T를 int로 매핑         |
| ToLongBiFunction<T,U>   | long applyAsLong(T t, U u)     | 객체 T와 U를 long으로 매핑  |
| ToLongFunction<T>       | long applyAsLong(T t)          | 객체 T를 long으로 매핑      |

- 

### 14.5.4 Operator 함수적 인터페이스



### 14.5.5 Predicate 함수적 인터페이스



### 14.5.6 andThen()과 compose() 디폴트 메소드



### 14.5.7 and(), or(), negate() 디폴트 메소드와 isEqual() 정적 메소드



### 14.5.8 minBy(), maxBy() 정적 메소드



## 14.6 메소드 참조



### 14.6.1 정적 메소드와 인스턴스 메소드 참조



### 14.6.2 매개 변수의 메소드 참조



### 14.6.3 생성자 참조



