> **신용권 님의 ''이것이 자바다'' 9장 공부 기록**
>
> 책을 보면서 내용을 정리했다.
>
> [소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)



# 9. 중첩 클래스와 중첩 인터페이스

<br>



## 9.1 중첩 클래스와 중첩 인터페이스란?

> **중첩 클래스(Nested Class)**란 클래스 내부에 선언한 클래스를 말하는데, 중첩 클래스를 사용하면 두 클래스의 멤버들을 서로 쉽게 접근할 수 있고, 외부에는 불필요한 관계 클래스를 감춤으로써 코드의 복잡성을 줄일 수 있다. 

- 여러 클래스와 관계를 맺는 경우에는 독립적으로 선언하나, 특정 클래스와 관계를 맺을 경우 중첩 클래스를 사용할 수 있다.

```java
//중첩 클래스 예시
class ClassName{
    class NestedClassName{       
    }
}
```

- 인터페이스도 클래스 내부에 선언할 수 있다. 이런 인터페이스를 **중첩 인터페이스**라고 한다.
- 중첩 인터페이스를 선언하는 이유는 해당 클래스와 긴밀한 관계를 맺는 **구현 클래스**를 만들기 위해서이다.
- 중첩 인터페이스는 주로 UI 프로그래밍에서 이벤트를 처리할 목적으로 많이 활용된다.

```java
//중첩 인터페이스 예시
class ClassName{
    interface NestedInterfaceName{
    }
}
```

<br>



## 9.2 중첩 클래스

> 중첩 클래스는 클래스 내부에 **선언되는 위치**에 따라서 두 가지로 분류된다. 클래스의 멤버로 선언되는 중첩 클래스를 **멤버 클래스**라고 하고, 메소드 내부에서 선언되는 중첩 클래스를 **로컬 클래스**라고 한다. 멤버 클래스는 클래스나 객체가 사용 중이라면 언제든지 재사용이 가능하지만, 로컬 클래스는 메소드 실행 시에만 사용되고, 메소드가 실행 종료되면 없어진다.

-  멤버 클래스

  - 인스턴스 멤버 클래스

    ```java
    class A {
        class B{...}
    }				
    ```

    - static 키워드 없이 선언된 클래스이며, 인스턴트 필드와 메소드 선언이 가능하고 **정적 필드와 메소드는 선언이 불가**하다.
    - A클래스의 외부에서 B의 객체를 사용하려면 **A 객체를 먼저 생성해야** B 객체를 생성할 수 있다.

    ```java
    A a = new A();
    A.B b = a.new B(); //사용 가능
    ```

    

  - 정적 멤버 클래스

    ```java
    class A{
        static class C{...}
    }				//A 클래스로 바로 접근할 수 있는 C중첩 클래스
    ```

    - static 키워드로 선언된 클래스이며, **모든 종류의 필드와 메소드를 선언할 수 있다**.
    - A 클래스의 외부에서 정적 멤버 클래스 C의 객체를 생성하기 위해서는 **A객체를 생성할 필요없이** C 객체를 다음과 같이 생성하면 된다.

    ```JAVA
    A.C c = new A.C(); //사용 가능
    ```

    

- 로컬 클래스

  ```java
  class A{
      void method(){
          class B{...}
      }
  }					//method()가 실행될 때만 사용할 수 있는 B중첩
  ```

  - 클래스의 메소드 내에 선언한 중첩 클래스를 로컬 클래스라고 한다.
  - 로컬 클래스는 **메소드 내부에서만 사용**되므로 접근을 제한할 필요가 없어 접근제한자 및 static을 붙일 수 없다.
  - 로컬 클래스 내부에는 인스턴스 핀드와 메소드만 선언이 가능하고 **정적 필드와 메소드는 선언할 수 없다**.
  - 로컬 클래스는 메소드가 실행될 때 메소드 내에서 객체를 생성하고 사용해야 한다.

  ```java
  A a = new A();
  a.method()		// 로컬 클래스 객체 생성을 위한 메소드 호출 
  ```

  

- 컴파일 시 멤버 클래스와 로컬 클래스는 별도로 바이트 코드 파일이 생성되며, 멤버 클래스는 "바깥클래스$멤버클래스.class"로, 로컬 클래스는 "바깥클래스$1로컬클래스.class" 형식으로 생성된다.

<br>



## 9.3 중첩 클래스의 접근 제한

### 9.3.1 바깥 필드와 메소드에서 사용 제한

- 멤버 클래스가 인스턴스 또는 정적으로 선언됨에 따라 바깥 클래스의 필드와 메소드에 사용제한이 생긴다.
- 인스턴스 멤버 클래스는 정적 필드나 메소드에서 객체를 생성할 수 없으나, 정적 멤버 클래스는 가능하다.

```java
public class A{
    //인스턴스 필드
    B field1 = new B();
    C field2 = new C();
    
    //인스턴스 메소드
    void method1(){
        B var1 = new B();
        C var2 = new C();
    }
    
    //정적 필드 초기화
    //static B field3 = new B(); 인스턴스 멤버 클래스는 안됨
    static C field3 = new C()
    
    //정적 메소드
    static void method2(){
        //B var1 = new B(); 안됨
        C var2 = new C();
    }
        
    //인스턴스 멤버 클래스
    class B{}
    //정적 멤버 클래스
    static class C{}
}
```



### 9.3.2 멤버 클래스에서 사용 제한

- 멤버 클래스 내부에서 바깥 클래스의 필드와 메소드를 접근할 때도 제한이 있다.
- 인스턴스 멤버 클래스는 바깥 클래스의 모든 필드와 메소드에 접근할 수 있지만, 정적 멤버 클래스는 바깥 클래스의 정적 필드와 메소드에만 접근할 수 있고 인스턴스 필드와 메소드는 접근할 수 없다.

```java
public class A{
    //인스턴스 멤버
   	int field1;
    void method1(){};

    
    //정적 멤버
    static int field2;
    static void method2();
    
    //정적 메소드
    static void method2(){
        //B var1 = new B(); 안됨
        C var2 = new C();
    }
        
    //인스턴스 멤버 클래스
    class B{
    	field1=10;
    	method1();

    	field2=10;
		method2();	// 모든 필드와 메소드에 접근할 수 있다.
    }
    //정적 멤버 클래스
    static class C{    	
        //field1=10;
    	//method1();     인스턴스 필드와 메소드에는 접근할 수 없다.

    	field2=10;
		method2();
    }
}
```



### 9.3.3 로컬 클래스에서 사용 제한

- 로컬 클래스 내부에서는 바깥 클래스의 필드나 메소드를 제한 없이 사용할 수 있다.
- 그러나 메소드의 매개 변수나 로컬 변수를 로컬 클래스에서 사용할 때, 로컬 클래스의 객체는 메소드 실행이 끝나도 힙 메모리에 존재해서 사용될 수 있는데, 이 때 **매개 변수나 로컬 변수는 메소드 실행이 끝나 스택 메모리에서 사라지므로 로컬 객체에서 사용될 경우 문제가 발생할 수 있다**.
- 자바는 이를 위하여 로컬 클래서에서 사용하는 로컬 변수의 값을 로컬 클래스 내부에 복사에 두고 사용하며, 값이 변경되었을 때를 대비하여  이를 모두 final로 선언한다. **결국 로컬 클래스에서 사용 가능한 것은 final로 선언된 매개변수와 로컬 변수뿐**이라는 것이다.
- java8부터 final 키워드를 포함하면 로컬 클래스의 메소드 내부 지역변수로 복사되고, 포함하지 않으면 로컬 클래스의 필드로 복사된다.



### 9.3.4 중첩 클래스에서 바깥 클래스 참조 얻기

- 중첩 클래스에서 this키워드를 사용하면 중첩 클래스 자신의 객체를 참조하게 된다. 

- 중첩 클래스 내부에서 바깥 클래스 참조를 얻으려면 다음의 방법을 사용한다.

  ```java
  바깥클래스.this.필드
  바깥클래스.this.메소드();
  ```

  

<br>



## 9.4 중첩 인터페이스

- 클래스의 멤버로 선언된 인터페이스를 말한다.

> 인터페이스를 클래스 내부에 선언하는 이유는 해당 클래스와 긴밀한 관계를 맺는 구현 클래스를 만들기 위해서이다. 특히 UI 프로그래밍에서 이벤트를 처리할 목적으로 많이 활용된다.

- 예제 : Button을 클릭했을 때 이벤트를 처리하는 객체를 받고 싶을 때 Button 내부에 선언된 중첩 인터페이스를 구현한 객체만 받아야 한다면, 다음과 같이 클래스를 선언할 수 있다.

```java
publi class Button{
    onClickListener listner; // 인터페이스 필드
    
    void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }		//setter 메소드, 매개 변수의 다형성 보여줌
    
    void touch(){
        listener.onClick();
    }		//구현 객체의 onClick() 메소드 호출
    
    interface OnClickListener{
        void onClick();		//중첩 인터페이스
    }   
}
```

```java
//구현 클래스
public class CallListener implements Button.OnClickListener{
    @Override
    public void onClick(){
        System.out.println("전화를 겁니다");
    }
}
```

```java
//버튼 이벤트 처리
public class ButtonEx{
    public static void main(String[] args){
        Button btn = new Button();
        
        btn.setOnClickListener(new CallListener());
        btn.touch();
    }
}
```



<br>

### 9.5 익명 객체

> 익명 객체는 이름이 없는 객체를 말한다. 
>
> 익명 객체는 단독으로 생성할 수 없고 **클래스를 상속하거나 인터페이스를 구현해야만 생성**할 수 있다.
>
> 익명 객체는 **필드의 초기값**이나 **로컬 변수의 초기값, 매개 변수의 매개값**으로 주로 대입된다.
>
> **UI 이벤트 처리 객체**나 **스레드 객체를 간편하게 생성할 목적**으로 익명 객체가 많이 활용된다.



### 9.5.1 익명 자식 객체 생성

- 부모 타입으로 필드나 변수를 선언하고, 자식 객체를 초기값으로 대입할 경우, 다음과 같이 사용하게 된다.

```JAVA
class Child extends Parent {}

class A {
    Parent field = new Child(); // 필드에 자식 객체를 대입
    void method(){
        Parent localVar = new Child(); // 로컬 변수에 자식 객체를 대입
    }
}
```

- 그러나 **자식 클래스가 재사용되지 않고, 오로지 해당 필드와 변수의 초기값으로만 사용할 경우**라면 익명 자식 객체를 생성해서 초기값으로 대입하는 것이 좋은 방법이다. 

```java
부모클래스[필드:변수] = new 부모클래스(매개값, ...){
    //필드
    //메소드
}; //세미콜론 붙여야함
```

- 중괄호 내부에는 필드나 메소드를 선언하거나 부모 클래스의 메소드를 재정의하는 내용이 들어가는데, 일반 클래스와는 달리 생성자를 선언할 수 없다.

- 필드를 선언할 때 사용하는 법

```java
class A {
    Parent field = new Parent() {
        int childField;
        void childMethod(){}
        
        @Override //parent 메소드 재정의
        void parentMethod(){}
    };
}
```

- 메소드 로컬 변수의 초기값으로 들어가는 모습

```java
class A {
    void method(){
        Parent localVar = new Parent(){
            int childField;
            void childMethod(){}
            @Override
            void parentMethod(){}
        };
    }
}
```

- 메소드의 매개 변수가 부모 타입일 경우 익명 자식 객체를 생성해서 매개값으로 대입할 수 있다.

```java
class A {
    void method1(Parent parent){}
    
    void method2(){
        method1(			//method1을 호출하면서, 매개값으로 익명 객체를 사용했다.
        	new Parent(){
                int childField;
                void childMethod(){}
                @Override
                void parentMethod(){}
            };
        )
    }
}
```

- 익명 자식 객체에 **새롭게 정의된 필드와 메소드는** **익명 자식 객체 내부에서만 사용**되고, 외부에서는 필드와 메소드에 접근할 수 없다. 왜냐하면 **익명 자식 객체는 부모 타입 변수에 대입되므로 부모 타입에 선언된 것만 사용할 수 있기 때문**이다.
- 그러나 **재정의 한 것은 사용**할 수 있다.

```java
class A {
    Parent field = new Parent(){
        int childField;
        void childMethod(){}
        @Override
        void parentMethod(){
            childField =3;
            childMethod();
        }
    };
    
    void method(){
        //field.childField =3;
        //field.childMethod(); //부모 타입 내용이므로 사용할 수 없다.
        field.parentMethod();// 재정의했기 때문에 사용가능
        
    }
}
```

- 결론적으로 **부모 타입의 필드와 메소드의 로컬 변수, 메소드의 매개 변수**로 들어가는 것을 볼 수 있다.



### 9.5.2 익명 구현 객체 생성

- 구현 -> 인터페이스 관련!
- 인터페이스 타입으로 필드나 변수를 선언하고, 구현 객체를 초기값으로 대입하는 경우는 다음과 같다,

```java
class TV implements RemoteControl{}

class A{
    RemoteControl field = new TV();// 인터페이스에 필요한 구현 객체를 생성하여 대입
    void method(){
        RemoteControl localVar = new TV(); // 로컬 변수에 구현 객체를 대입
    }
}
```

- 그러나 **구현 클래스가 재사용되지 않고, 오로지 해당 필드와 변수의 초기값으로만 사용하는 경우라면 익명 구현 객체를 초기값으로 대입하는 것이 좋다.**

```JAVA
인터페이스 [필드:변수] = new 인터페이스() {
    //인터페이스에 선언된 추상 메소드의 실체 메소드 선언
    //필드
    //메소드
}; //중괄호에는 인터페이스에 선언된 모든 추상 메소드들의 실체 메소드를 작성해야 한다.
```

- 추가적인 필드와 메소드는 선언은 가능하나 실체 메소드에서만 사용이 가능하고 외부에서는 사용하지 못한다.
- 필드로 익명 구현 객체 생성한 모습

```java
class A{
    RemoteControl field = new RemoteControl(){
        @Override
        void turnOn{} //추상메소드에 대한 실체 메소드
    }
}
```

- 메소드 내에 로컬 변수를 선언할 때 초기값으로 익명 구현 객체를 생성해서 대입하는 모습

```java
void method(){
    RemoteControl localVar = new RemoteControl(){
        @Override
        void turnOn(){} //추상 메소드에 대한 실체 메소드
    };
}
```

- 매개 값으로 사용하는 모습

```java
class A {
    void method1(RemoteControl rc){} 
    
    void method2(){
        method1(
            new RemoteControl(){
                @Override
                void turnOn(){}
            }
        ); //메소드 안에 익명 구현 객체가 선언되면 해당 메소드의 )에 ;를 쓴다.
    }
}
```



- 예제 : UI에서 사용되는 버튼의 클릭 이벤트 처리

```java
//UI 클래스
public class Button{
    onClickListener listener; //인터페이스 타입 필드
    
    void setOnClickListener(OnClickListener listener){
        this.listener = listener;		//매개 변수의 다형성
    }
    
    void touch(){ // 구현 객체의 onclick 메소드 호출
        listener.onClick();
    }
    
    interface OnClickListener { //중첩 인터페이스
        void onClick();
    }
}
```

```java
public class Window {
    Button button1 = new Button();
    Button button2 = new Button();
    
    //필드 초기값으로 대입
    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(){
            System.out.println("전화를 겁니다.");
        }
    };
    
    Window(){
        button1.setOnClickListener(lisetener); //매개값으로 위 필드 대입
        button2.setOnClickListener(new Button.OnClickListener() {//매개값으로 익명 구현 객체 대입
            @Override
            public void onClick(){
                System.out.println("메시지를 보냅니다.");
            }
        });
    }
}
```

```java
//실행 클래스
public class Main{
    public static void main(String[]args){
        Window w = new Window();
        w.button1.touch();
        w.button2.touch();
    }
}
```

- 너무 어렵당





### 9.5.3 익명 객체의 로컬 변수 사용

- 로컬 클래스와 거의 같은 내용을 담고 있다.
- 메소드의 매개 변수나 로컬 변수를 익명 객체에서 사용할 때, 메소드가 끝나면 스택 메모리에서 매개변수와 로컬 변수가 사라지므로 익명 객체에서 사용할 수 없게 된다. 그래서 자바는 매개 변수와 로컬 변수를 익명 클래스에 final로 복사한다.
- 익명 객체가 로컬 변수로 있는 메소드 {} 안은 익명 객체 로컬 변수 {} 안이 아니더라도 final로 적용이 된다.

```java
public interface Calculatable{
    public int sum();
}
```

```java
public class Anonymous{
    private int field;
    
    public void method(final int arg1, int arg2){
        final int var1=0;
        int var2 =0;
        
        field =10; //바깥 클래스 내용은 제한 없이 사용
        
        //arg1 =10;
        //arg2 =10;
        //var1=10;
        //var2=10; final이므로 수정 불가
        
        Calculatable calc = new Calculatable(){
            @Override
            publi cint sum(){
                int result = field + arg1 +arg2 + var1 + var2;
                return result;
            }
        };
        System.out.println(calc.sum);
    }
}
```

```java
public class AnonymousEx{
    public static void main(String[]args){
        Anonymous anony = new Anonymous();
        anony.method(0,0);
    }
} //실행 결과 : 10
```



