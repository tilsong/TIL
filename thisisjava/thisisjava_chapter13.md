> **신용권 님의 ''이것이 자바다'' 13장 공부 기록**
>
> 책을 보면서 내용을 정리했다.
>
> [TOC]
>
> [소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)

<br>

# 13. 제네릭

## 13.1 왜 제네릭을 사용해야 하는가?

> Java 5 부터 제네릭 타입이 새로 추가되었는데, 제네릭 타입을 이용함으로써 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거할 수 있게 되었다.
>
> 제네릭은 클래스와 인터페이스, 그리고 메소드를 정의할 때 **타입(type)을 파라미터(parameter)**로 사용할 수 있도록 한다. 타입 파라미터는 코드 작성 시 구체적인 타입으로 대체되어 다양한 코드를 생성하도록 해준다.

- 제네릭의 두 가지 이점
  - **컴파일 시 강한 타입 체크**를 할 수 있다. : 실행 시 타입 에러가 나는 것보다는 컴파일을 강하게 체크해서 에러를 사전에 방지한다.
  - **타입 변환(casting)을 제거**한다. : get함수를 통해 저장된 요소를 찾아올 때 타입 변환할 필요가 없다.

<br>



## 13.2 제네릭타입(class<T>, interface<T>)

- **제네릭 타입**은 타입을 파라미터로 가지는 클래스와 인터페이스를 말한다. 

```java
//제네릭 타입 
public class 클래스명<T>{...}
public interface 인터페이스명<T>{...} //타입 T를 파라미터로 가진다. 일반적으로 대문자 알파벳 한 글자로 표현한다.
```

- 실제 코드에서 사용하려면 타입 파라미터에 구체적인 타입을 지정해야 한다.



- 왜 이런 타입 파라미터를 사용해야 할까?

```java
public class Box{
    private Object object;
    public void set(Object object) {this.object = object}
    public Object get(){return object}
} 
//Object 타입을 사용함으로써 모든 종류의 자바 객체를 저장할 수는 있지만, 저장할 때, 그리고 다시 찾아올 때 타입변환이 발생한다.
//이는 프로그램 성능에 좋지 않은 영향을 끼칠 수 있다.
//따라서 모든 종류의 객체를 저장하면서 타입 변환이 발생하지 않도록 하는 방법을 찾았는데, 그것이 제네릭이다.
```

- 위의 코드 수정

```java
public class Box<T>{
    private T t;
    public T get() {return t;}
    public void set(T t){this.t = t}
}
//타입 파라미터 T를 사용해서 Object 타입을 모두 T로 대체했다. T는 Box 클래스로 객체를 생성할 때 구체적인 타입으로 변경된다.
```

- String으로 Box 클래스 객체 생성

```java
Box<T> box = new Box<T>;
```

```JAVA
//T가 String  타입으로 변경되어 Box클래스의 내부가 변경된다.
public class Box<String> {
    Private String t;
    public String get(){return t;}
    public void set(String t){this.t =t}
}	//다른 타입을 T에 대입해도 같은 결과가 나온다.
```

- 이와 같이 제네릭은 클래스를 **설계할 때 구체적인 타입을 명시하지 않고, 타입 파라미터로 대체했다가 실제 클래스가 사용될 때 구체적인 타입을 지정함**으로써 **타입 변환을 최소화**시킨다.
- 사실상 클래스와 인터페이스가 타입으로 이용된다. 따라서 제네릭 타입이라고 불리는 듯하다. 추상, 다형성이 다 있는 듯하다.

<br>



## 13.3 멀티 타입 파라미터(class<K,V,...>, interface<K,V,...>)

> 제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있는데, 이 경우 타입 파라미터를 콤마로 구분한다.

- 예제 : Product

```java
//제네릭 클래스
public class Produc<T,M>{
    private T kind;
    private M model;
    
    public T getKind() {return this.kind}
    public M getModel() {return this.model}
    
    public void setKind(T kind) {this.kind = kind}
    public void setModel(M model) {this.model = model}
}
```

```java
//제네릭 객체 생성
public class ProductEx{
    public static void main(String[]args){
        Product<Tv, String> product1 = new Product<Tv, String>();
        //Product<Tv, String> product1 = new Product<>; 로 선언할 수도 있다.(다이아몬드 연산자)
        product1.setKind(new Tv());
        product1.setModel("스마트tv");
        Tv tv = product1.getKind();
        String tvModel = product1.getModel();
    }
}
```



<br>



## 13.4 제네릭 메소드(<T,R> R method(T t))

> 제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드를 말한다.

```java
//제네릭 메소드 양식
public<타입파라미터,...> 리턴타입 메소드명(매개변수,...){}
//public<T> Box<T> boxing(T t){...}
```

- 제네릭 메소드 호출

```java
리턴타입 변수 = <구체적 타입> 메소드명(매개값...);//명시적으로 구체적 타입 지정
//Box<Integer> box = <Integer>boxing(100);

리턴타입 변수 = 메소드명(매개값...);//매개값을 통해 구체적 타입 추측
//Box<Integer> box = boxing(100);
```

- 제네릭 메소드는 정적 메소드로 사용가능하다(예제에는 거의 정적메소드로만 적혀 있다.)

- 예제: 제네릭 메소드 호출

```java
//제네릭 메소드가 있는 클래스
public class Util {
	public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {
		boolean keyCompare = p1.getKey().equals(p1.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		return keyCompare&&valueCompare;
	}
}
```

```java
//제네릭 타입
public class Pair<K,V> {
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value= value;
	}
	
	public void setKey(K key) { this.key = key;}
	public void setValue(V value) { this.value = value;}
	
	public K getKey() { return key; }
	public V getValue() { return value;}
}

```

```java
//제네릭 메소드 호출
public class Ex {

	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair(100, "사과");
		Pair<Integer, String> p2 = new Pair(100, "사과");
		boolean answer = Util.<Integer, String>compare(p1, p2);
		//			   = Util.compare(p1, p2) 비명시적으로 가능
		if(answer) {
			System.out.println("논리 동등 객체");
		} else {
			System.out.println("논리 다른 객체");
		}
	}
}
```



<br>



## 13.5 제한된 타입 파라미터(<T extends 최상위타입>)

> 타입 파라미터에 지정되는 구체적인 타입을 제한할 필요가 있을 때가 있다. 이 때 제한된 타입 파라미터(bounded type parameter)를 사용한다. 

```java
public <T extends 상위타입> 리턴타입 메소드(매개변수, ...){...} //상위타입은 클래스뿐아니라 인터페이스도 가능하다.
```

- 타입 파라미터에 지정되는 구체적인 타입은 상위 타입이거나 상위 타입의 하위 또는 구현 클래스만 가능하다.
- 주의할 점은 메소드의 중괄호 {} 안에서 타입 파라미터 변수로 사용 가능한 것은 상위 타입의 멤버(필드, 메소드)로 제한된다. 하위 타입에만 있는 필드와 메소드는 사용할 수 없다.

- 예제: 숫자 변환 시험

```java
//수 비교하는 메소드
public class UtilN {
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		//Number 하위 Double 클래스로 변환되었으므로 다시 double 타입으로 변환함.
		return Double.compare(v1, v2);
	}
}
```

```java
public class BoundedTypeParameterEx {

	public static void main(String[] args) {
		int result = UtilN.compare(10, 20);//Number타입만 가능함
		System.out.println(result);//int -> Integer(자동 Boxing, Number의 하위 클래스로 변환)
		
		int result1 = UtilN.compare(10.2,3.1);
		System.out.println(result1);//double => Double(자동 Boxing) 
	}
}
```



<br>



## 13. 6 와일드카드 타입(<?>, <? extends ...>, <? super>)

> 코드에서 ?를 일반적으로 와일드카드(wildcard)라고 부른다. 제네릭 타입을 **매개값이나 리턴 타입**으로 사용할 때 구체적인 타입 대신에 와일드카드를 다음과 같이 세 가지 형태로 사용할 수 있다.
>
> - 제네릭타입<?> : Unbounded Wildcards(제한없음), 타입파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.
> - 제네릭타입<? extends 상위타입> : Upper Bounded Wildcards(상위 클래스 제한), 타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하위 타입만 올 수 있다.(상위타입-> 하위 타입 가능)
> - 제네릭타입<? super 하위타입> : Lower Bounded Wildcards(하위 클래스 제한), 타입 파라미터를 대치하는 구체적인 타입으로 하위 타입이나 상위타입이 올 수 있다.(하위 타입 -> 상위 타입 가능)

- 예제 : 수강신청

```java
//Person, 최상위 클래스
public class Person {
	String name;
	public Person(String name) {
		this.name = name;
	};
	public String getName() {return name;}
	
	//이부분이 있어야 registerCourse의 출력값이 정상적으로 나옴. toString이 원래는 뭐였을까?
    //검색->
    //Object"클래스가 가진 메소드 중 "toString"메소드가 있습니다.
	//물론 "Object" 클래스의 모든 메소드는 모든 클래스가 사용이 가능합니다.
	//"toString" 메서드는 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드 입니다.
    //그래서 이것을 의미 있는 값으로 재정의하여 사용한다!
	//출처: https://backback.tistory.com/68 [Back Ground]
	@Override
	public String toString() {
		return name;
	}
}
```

```java
//Person 하위 클래스
public class Student extends Person{
	String student;
	public Student(String student) {
		super(student);
	};
}
```

```java
//Student 하위 클래스
public class HighStudent extends Student{
	public HighStudent(String highStudent) {
		super(highStudent);
	}
}
```

```java
//Person 하위 클래스
public class Worker extends Person{
	public Worker(String worker) {
		super(worker);
	};
}
```

```java
//제네릭 타입
public class Course <T>{
	private String name;
	private T [] students;
	
	public Course(String name, int capacity) {
		this.name = name;
		//타입 파라미터로 배열 생성하기
		students = (T[]) (new Object[capacity]);
	}
	
	public String getName() {return name;}
    //T[] 리턴 타입 메소드
	public T[] getStudents() {return students;}
    
	public void add(T t) {//빈 배열 인덱스에 수강생 추가하는 메소드
		for(int i=0; i<students.length; i++) {
			if(students[i]==null) {
				students[i]=t;
				break;
			}
		}
	}
}
```



```java
//수강생 등록
import java.util.Arrays;
public class WildCardEx {

	//double[] values = {1.0, 1.1, 1.2};
	//System.out.println(values.toString()); // 이렇게 하면 [D@46a49e6 같은 값이 나옵니다.
	//System.out.println(Arrays.toString(values)); // 이렇게 하면 [1.0, 1.1, 1.2] 이 출력됩니다.
	//출처: https://crmn.tistory.com/61 [크롬망간이 글 쓰는 공간]
    //
    public static void registerCourse( Course<?> course) {
		System.out.println(course.getName()+" 수강생: "+Arrays.toString(course.getStudents()));
	}
	public static void registerCourseStudent(Course<? extends Student> course) {
		System.out.println(course.getName()+ "수강생: "+ Arrays.toString(course.getStudents()));
	}
	public static void registerCourseWorker(Course<? super Worker> course) {
		System.out.println(course.getName()+ "수강생: "+ Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
        //수강별 등록
		Course<Person> personCourse = new Course<Person>("일반인 과정", 5);
			personCourse.add(new Person("일반인"));
			personCourse.add(new Worker("직장인"));
			personCourse.add(new Student("학생"));
			personCourse.add(new HighStudent("고등학생"));
		Course<Worker> workerCourse = new Course<Worker>("직장인 과정", 5);
			workerCourse.add(new Worker("직장인"));
		Course<Student> studentCourse = new Course<Student>("학생 과정", 5);
			studentCourse.add(new Student("학생"));
			studentCourse.add(new Student("고등학생"));
		Course<HighStudent> highStudentCourse = new Course<HighStudent>("고등학생 과정", 5);
			highStudentCourse.add(new HighStudent("고등학생"));
		//강의별 와일드카드 범위 따라 출력, 범위 안 맞으면 출력 안됨
		registerCourse(personCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		registerCourse(workerCourse);
		System.out.println();
		
//		registerCourseStudent(personCourse); 
		registerCourseStudent(studentCourse);
		registerCourseStudent(highStudentCourse);
//		registerCourseStudent(workerCourse);
		System.out.println();
		
		registerCourseWorker(personCourse);
//		registerCourseWorker(studentCourse);
//		registerCourseWorker(highStudentCourse);
		registerCourseWorker(workerCourse);
		System.out.println();
		
		
	}

}
```





<br>



## 13.7 제네릭 타입의 상속과 구현

- 제네릭도 상속이 가능하다. 또한 자식 제네렉 타입은 추가적으로 타입 파라미터를 가질 수 있다.

```java
public class Child<T, M, C> extends Parent<T,M>{...}
```

- 예제 : 제네릭 타입의 상속과 구현

```JAVA
//부모 제네릭 클래스
public class Product <T,M>{
	private T kind;
	private M model;
	
	public T getKind() { return kind;}
	public M getModel() { return model;}
	
	public void setKind(T kind) {this.kind =kind;}
	public void setModel(M model) {this.model=model;}
}
	
class Tv{}
```

```java
//자식 제네릭 클래스
public class ChildProduct<T,M,C> extends Product<T,M>{
	private C company;
	
	public void setCompany(C company) {this.company=company;}
	public C getCompany() {return this.company;}
}
```

```java
//제네릭 인터페이스
public interface Storage<T> {
	public void add(T item, int index);
	public T get(int index);
}
```

```java
//제네릭 구현 클래스
public class StorageImpl<T> implements Storage<T>{
	private T[] array;
	
	public StorageImpl(int capacity) {
		this.array = (T[])(new Object[capacity]);
	}
	@Override
	public void add(T item, int index) {
		array[index] = item;
	}
	
	@Override
	public T get(int index) {
		return array[index];
	}
}
```

```java
//제네릭 타입 사용 클래스
public class ChildEx {

	public static void main(String[] args) {
		ChildProduct<Tv,String,String> product = new ChildProduct<>();
		product.setKind(new Tv());
		product.setModel("Smart TV");
		product.setCompany("Samsung");
		
		Storage<Tv> storage = new StorageImpl<Tv>(100);
		storage.add(new Tv(), 0);
		Tv tv = storage.get(0);

	}

}
```





### 참고사이트

-  https://crmn.tistory.com/61 [크롬망간이 글 쓰는 공간]
-  https://backback.tistory.com/68 [Back Ground]
