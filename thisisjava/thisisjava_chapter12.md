> **신용권 님의 ''이것이 자바다'' 12장 공부 기록**
>
> 책을 보면서 내용을 정리했다.
>
> [소스코드 repo](https://github.com/tilsong/TIL/tree/main/thisisjava/thisisjava_mySource)

<br>

# 12. 멀티 스레드



## 12.1 멀티 스레드 개념

### 12.1.1 프로세스와 스레드

> 운영체제에서는 실행 중인 하나의 애플리케이션을 **프로세스(process)**라고 부른다. 사용자가 애플리케이션을 실행하면 운영체제로부터 실행에 필요한 메모리를 할당받아 애플리케이션의 코드를 실행하는데 이것이 프로세스이다.

- 두 가지 이상의 작업을 동시에 처리하는 것을 **멀티 태스킹(multi tasking)**라고 한다. 운영 체제는 이를 위해 CPU 및 메모리 자원을 프로세스마다 적절히 할당해주고, 병렬로 실행시킨다.

- 멀티 태스킹이 꼭 멀티 프로세스는 아니다. 한 프로세스 내에서 멀티 태스킹이 되도록 만들어진 애플리케이션(메신저, 미디어 플레이어 등)도 있다. 이렇듯 한 프로세스가 두 가지 이상의 작업을 처리할 수 있도록 하는 것이 **멀티 스레드(multi thread)**이다.
- **스레드**는 한 가닥 실처럼 한 가지 작업을 실행하기 위해 순차적으로 이어놓았다는 뜻인데, 멀티 스레드는 이러한 **실행 흐름이 두 개 이상**이라는 말이다. 한 프로세스에 여러 흐름이 흐르는 것이므로 스레드끼리 서로 영향을 미칠 수 있다.
- 멀티 프로세스가 애플리케이션 단위의 멀티 태스킹이라면 **멀티 스레드는 애플리케이션 내부에서의 멀티 태스킹**이라고 볼 수 있다.



### 12.1.2 메인 스레드

- 모든 자바 애플리케이션은 메인 스레드(main thread)가 main() 메소드를 실행하면서 시작된다. 그리고 마지막 코드나 return 문을 만남으로 실행이 종료된다.
- 메인 스레드는 필요에 따라 작업 스레드들을 만들어서 병렬로 코드를 실행할 수 있다. 이 때, 메인 스레드가 종료되더라도 작업 스레드가 하나라도 계속 실행 중이라면 프로세스는 종료되지 않는다.

<br>



## 12.2 작업 스레드 생성과 실행

> 멀티 스레드로 실행하는 애플리케이션을 개발하려면 먼저 몇 개의 작업을 병렬로 실행할지 결정하고 각 작업별로 스레드를 생성해야 한다. 메인 작업 이외에 추가적인 병력 작업의 수만큼 스레드를 생성하면 된다.

- 스레드를 생성하는 방법 2가지 인데, java.lang.Thread  클래스를 직접 객체화해서 생성해도 되고, Thread를 상속해서 하위클래스를 만들어 생성할 수도 있다.



### 12.2.1 Tread 클래스부터 직접 생성

- java.lang.Thread 클래스로부터 작업 스레드 객체를 직접 생성하려면  Runnable을 매개값으로 갖는 생성자를 호출해야 한다.

  ```java
  Thread thread = new Thread(Runnable target);
  ```

- Runnable은 작업 스레드가 실행할 수 있는 코드를 가지고 있는 객체라고 해서 붙여진 이름이다. Runnable은 인터페이스 타입이기 때문에 구현 객체를 만들어 대입해야 한다. 또한 run() 메소드 하나가 정의되어 있는데, 구현 클래스는 이를 재정의해서 작업 스레드가 실행할 코드를 작성해야 한다.

```java
class Task implements Runnable{
    public void run(){
        //실행 코드
    }
}

Runnable task = new Task();
Thread thread = new Thread(task); // 작업 스레드 생성됨
```

- Runnable 익명 객체를 매개 값으로 사용하면 코드가 절약될 수 있다.

```java
Thread thread = new Thread( new Runnable(){
    public void run(){
        //실행 코드
    }
});
```

- run 메소드 하나만 정의되어 있으므로 함수적 인터페이스이다. 따라서 람다식을 매개값으로 사용할 수 있다.

```java
Thread thread = new Thread( () ->{
    //실행 코드
} );
```

- 이후 start 메소드를 호출함으로 스레드가 실행될 수 있다.

```java
thread.start();
//start()메소드가 호출되면, 작업 스레드는 매개값으로 받은 Runnable의 run()메소드를 실행하면서 자신의 작업을 처리한다.
```



### 12.2.2 Tread 하위 클래스로부터 생성

- Thread의 하위 클래스로 작업스레드를 정의하면서 작업 내용을 포함시킬 수도 있다. 먼저 Thread를 상속한 후 run 메소드를 재정의 (Overriding)해서 스레드가 실행할 코드를 작성하면 된다.

```java
public class WorkerThread extends Thread {
    @Override
    public void run(){
        //스레드 실행 코드
    }
}
Thread thread = new WorkerThread();
```

- 이 역시 익명 객체로 작업 스레드 객체를 생성할 수 있다.

```java
Thread thread = new Thread(){
    public void run(){
        //스레드 실행 코드
    }
}
```

- 생성된 작업 스레드 객체에서 start() 메소드를 호출하면 작업 스레드는 자신의 run() 메소드를 실행하게 된다.



### 12.2.3 스레드의 이름

> 스레드는 자신의 이름을 가지고 있다. 이는 디버깅할 때 어떤 스레드가 어떤 작업을 하는지 조사할 목적으로 가끔 사용된다.

- 메인 스레드는 "main", 직접 생성한 스레드는 자동적으로 "Thread-n"이라는 이름으로 설정되며, n은 스레드의 번호를 말한다.

- 다른 이름으로 설정하고 싶다면  setName() 메소드를 사용할 수 있다.

  ```java
  thread.setName("스레드 이름");
  ```

- 스레드 이름을 알고 싶을 때는 getName() 메소드를 호출한다.

  ```java
  thread.getName();
  ```

- getName()과 setName()은 Thread의 인스턴스 메소드이므로 스레드 객체의 참조가 필요하다.

- 만약 스레드 객체의 참조를 가지고 있지 않다면, Thread 객체의 정적 메소드인 currentThread()로 코드를 실행하는 현재 스레드의 참조를 얻을 수 있다.

  ```java
  Thread thread = Thread.currentThread();
  ```

  

<br>



## 12.3 스레드 우선순위

- 멀티 스레드는 동시성(Concurrency)과 병렬성(Parallelism)으로 실행된다.

- **동시성**은 멀티 작업을 위해 **하나의 코어에서 멀티 스레드가 번갈아가며 실행**하는 성질을 말하고, **병렬성**은 멀티 작업을 위해 **멀티 코어에서 개별 스레드를 동시에 실행**하는 성질을 말한다.

- 스레드의 개수가 코어보다 많을 경우, 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 결정해야 하는데, 이것을 **스레드 스케줄링**이라 한다. 아주 짧은 시간에 번갈아가며 run() 메소드를 조금씩 실행한다.

- 스레드 스케줄링은 우선순위(Priority) 방식과 순환 할당(Round-Robin) 방식을 사용한다. 

- **우선순위 방식**은 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링하는 것을 말한다. 이 방식을 사용하면 개발자가 코드로 스레드 객체에 우선 순위 번호를 부여하여 제어할 수 있다.

  - 우선순위는 1에서부터 10까지 부여되는데, 1이 가장 낮고, 10이 가장 높다. 우선 순위를 부여하지 않은 모든 스레드들은 기본적으로 5의 우선순위를 할당받는다. 우선순위 변경은 Thread 클래스가 제공하는 setPriority() 메소드를 이용하여 가능하다.

    ```java
    thread.setPriority(우선순위);
    thread.setPriority(Thread.MAX_PRIORITY);//Thread 클래스의 상수를 이용할 수도 있다.MAX_PRIORITY=10, NORM_PRIORITY=5, MIN_PRIORITY=1
    ```

  - 우선순위가 높은 스레드는 비교젹 계산 작업을 빨리 끝낸다. 코어의 수보다 스레드의 개수가 더 적으면 큰 영향이 없다.

- **순환 할당 방식**은 시간 할당량(Time Slice)을 정해서 하나의 스레드를 정해진 시간만큼 실행하고 다시 다른 스레드를 실행하는 방식을 말한다. 이 방식은 자바 가상 기계에 의해 정해지므로 코드로 제어할 수 없다.

<br>



## 12.4 동기화 메소드와 동기화 블록

### 12.4.1 공유 객체를 사용할 때의 주의할 점

> 싱글 스레드 프로그램에서는 한 개의 스레드가 객체를 독차지해서 사용하면 되지만, 멀티 스레드 프로그램에서는 스레드들이 객체를 공유해서 작업해야 하는 경우가 있다. 이 경우, 스레드 A를 사용하던 객체가 스레드 B에 의해 상태가 변경될 수 있기 때문에 스레드 A가 의도했던 것과는 다른 결과를 산출할 수도 있다.

### 12.4.2 동기화 메소드 및 동기화 블록

- 따라서 공유 객체가 사용된다면, 사용 중인 객체를 작업이 끝날 때까지 다른 스레드가 변경할 수 없도록 잠금을 걸어야 한다.

- 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 **임계 영역(critical section)**이라고 한다. 자바는 임계 영역 지정을 위해 동기화(synchronized)  메소드와 동기화 블록을 제공한다. 

- 동기화 메소드를 만드는 방법은 메소드 선언에 synchronized 키워드를 붙이는 것이며, 인스턴스와 정적 메소드 어디든 붙일 수 있다.

  ```java
  public synchronized void method() {
      임계 영역; // 단 하나의 스레드만 실행
  }
  ```

- 메소드 전체가 아니라 일부 내용만 임계 영역으로 만들고 싶다면 다음과 같이 동기화 블록을 만들면 된다.

  ```java
  public void method(){
      //여러 스레드가 실행가능 영역
      synchronized(공유객체){ //공유 객체가 객체 자신이면 this를 넣을 수 있다.
          임계 영역
      }//동기화 블록
      //여러 스레드 실행가능 영역
  }
  ```

  



## 12.5 스레드 상태

- 스레드 객체를 생성하고 start() 메소드를 호출하면 곧바로 실행되는 것이 아닌, **실행 대기 상태**가 된다. 실행 대기 상태란 아직 스케줄링이 되지 않아서 실행을 기다리고 있는 상태를 말한다. 스케줄링으로 선택 되면 비로소 CPU를 점유하고 run() 메소드를 실행한다. 이 때를 **실행(Running) 상태**라고 한다.
- 실행 상태의 스레드는 스케줄링에 의해 다시 실행 대기 상태로 돌아갈 수 있다. 이 때 실행 대기 상태에 있는 다른 스레드가 선택되어 실행 상태가 된다.
- 이렇게 스레드는 실행 대기 상태와 실행 상태를 번갈아가면서 자신의 run() 메소드를 조금씩 실행한다. 
- 실행 상태에서 run 메소드가 종료되면, 더 이상 실행할 코드가 없기 때문에 스레드의 실행은 멈추가 된다. 이를 종료상태라고 한다.
- 스레드 객체 생성(NEW) -(start()메소드로 시작)->실행 대기(RUNNABLE) <-반복->실행 -> 종료(TERMINATED)
- 경우에 따라서 스레드는 실행 상태에서 실행 대기 상태로 가지 않고 일시 정지 상태로 가기도 한다. 일시 정지 상태는 스레드가 실행할 수 없는 상태이다. 일시 정지 상태는 WAITING, TIMED_WAITING, BLOCKED가 있다. 다시 실행 상태로 가기 위해서는 실행 대기 상태로 가야만 한다. (일시정지 -> 실행 대기 -> 실행 ->일시정지...)

| 상태      |   열거 상수   | 설명                                                         |
| :-------- | :-----------: | :----------------------------------------------------------- |
| 객체 생성 |      NEW      | 스레드 객체가 생성, 아직 start() 메소드가 호출되지 않은 상태 |
| 실행 대기 |   RUNNABLE    | 실행 상태로 언제든지 갈 수 있는 상태                         |
| 일시 정지 |    WAITING    | 다른 스레드가 통지할 때까지 기다리는 상태                    |
| "         | TIMED_WAITING | 주어진 시간 동안 기다리는 상태                               |
| "         |    BLOCKED    | 사용하고자 하는 객체의 락이 풀릴 때까지 기다리는 상태        |
| 종료      |  TERMINATED   | 실행을 마친 상태                                             |

- 스레드 상태를 확인하기 위해 getState() 메소드를 사용할 수 있다. 해당 메소드는 Thread.State 열거 상수를 리턴한다.

- 예제 : 타겟 스레드의 상태 출력하기

```JAVA
public class StatePrintThread extends Thread {
    private Thread targetThread;
    
    public StatePrintThread(Thread targetThread) {
    	this.targetThread = targetThread;
    }
    
    public void run() {
    	while(true) {
    		Thread.State state = targetThread.getState();
    		System.out.println("타겟 스레드 상태: " + state);
    		
    		if(state == Thread.State.NEW) {
    			targetThread.start();
    		}
    		if(state == Thread.State.TERMINATED) {
    			break;
    		}
    		try {
    			//0.5초간 일시 정지
    			Thread.sleep(500);
    		} catch(Exception e) {}
    	}
    }
}
```

```JAVA
// 실행 중 정지하는 클래스
public class TargetThread extends Thread {
	public void run() {
		for(long i=0; i<1000000000; i++) {}
		
		try {
			//1.5초간 일시 정지
			Thread.sleep(1500);
		} catch(Exception e){}
		
		for(long i=0; i<1000000000; i++) {}
	}

}
```

```JAVA
//실행
public class ThreadStateExample {
	public static void main(String[] args) {
		StatePrintThread statePrintThread =
				new StatePrintThread(new TargetThread());
		statePrintThread.start();
	}
}
//실행 결과
타겟 스레드 상태: NEW
타겟 스레드 상태: TIMED_WAITING //1.5초 정지하는 동안 상태
타겟 스레드 상태: TIMED_WAITING
타겟 스레드 상태: TIMED_WAITING
타겟 스레드 상태: RUNNABLE // 1.5초 정지 이후 상태
타겟 스레드 상태: TERMINATED // 종료 상태
```



## 12.6 스레드 상태 제어

> 실행 중인 스레드의 상태를 변경하는 것을 **스레드 상태 제어**라고 한다.

- 상태제어가 잘못되면 프로그램은 먹통이 되거나 다운되므로, 정교한 스레드 상태 제어가 필요하다.
- 다음은 스레드 상태 제어 메소드이다.

| 메소드                                                   | 설명                                                         |
| -------------------------------------------------------- | ------------------------------------------------------------ |
| interrupt()                                              | 일시 정지 상태의 스레드에서 interruptedExCEPTION 예외를 발생시켜, 예외 처리 코드(catch)에서 실행 대기 상태로 가거나 종료 상태로 갈 수 있도록 한다. |
| notity(), notifyAll()                                    | 동기화 블록 내에서 wait 메소드에 의해 일시 정지 상태에 있는 스레드를 실행 대기 상태로 만든다. |
| sleep(long mills) //sleep(long millis, int nanos)        | 주어진 시간 동안 스레드를 일시 정지 상태로 만든다. 주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다. |
| join() //join(long millis), join(long millis, int nanos) | join() 메소드를 호출한 스레드는 일시 정지 상태가 된다. 실행 대기 상태로 가려면, join() 메소드를 멤버로 가지는 스레드가 종료되거나, 매개값으로 주어진 시간이 지나야 한다. |
| wait() //wait(long millis), wait(long millis, int nanos) | 동기화된(synchronized) 블록 내에서 스레드를 일시 정지 상태로 만든다. 매개값으로 주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다. 시간이 주어지지 않으면 notify(), notifyAll() 메소드에 의해 실행 대기 상태로 갈 수 있다. |
| yield()                                                  | 실행 중에 우선순위가 동일한 다른 스레드에게 실행을 양보하고 실행 대기 상태가 된다. |

* resume() , suspend(), stop() 메소드는 스레드 안정성을 해치므로 더 이상 사용하지 않도록 권장된 Deprecated 메소드이므로 적지 않았다.
* wait(), notify, notifyAll은 Object 클래스의 메소드이고, 그 이외의 메소드는 모두 Thread 클래스의 메소드들이다.



### 12.6.1 주어진 시간동안 일시 정지(sleep())

- 실행 중인 스레드를 일정 시간 멈추게 하고 싶을 때는 Thread 클래스의 정적 메소드인 sleep()을 사용하면 된다. Thread.sleep()을 호출한 스레드는 주어진 시간 동안 일시 정지 상태가 되고, 다시 실행 대기 상태로 돌아간다.

```java
try{
    Thread.sleep(1000); //매개값은 얼마 동안 일시 정지 상태로 있을 것인지 밀리세컨드(1/1000) 단위로 시간을 주면 된다. 1000을 주면 1초 정지
} catch(InterruptedException e){ //주어진 시간이 되기 전에 interrupteException이 발생하면 예외 처리가 된다.
}
```

- 예제: 1초 주기로 3번 비프음 발생

```java
public class sleepEx {
	public static void main(String []args) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for(int i=0; i<3; i++) {
			toolkit.beep();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {
				}
		}
	}
}
```



### 12.6.2 다른 스레드에게 실행 양보(yield())

- 스레드의 반복문들이 무의미한 반복을 하는 경우가 있다. 이 때, 다른 스레드에게 실행을 양보하고 자신은 실행 대기 상태로 가는 것이 전체 프로그램 성능에 도움이 된다. 이 기능을 위해 yield() 메소드를 사용할 수 있다.

```java
//무의미한 반복흘 하는 메소드
public void run(){
    while(true){
        if(work){
            System.out.println("Thread 작업 내용") // work가 true이면 무한 반복한다.
        } else{
            Thead.yield(); //work가 false가 되면 yield()를 실행한다.
        }
    }
}
```

- yield() 메소드를 호출한 스레드는 실행 대기 상태로 돌아가고 동일한 우선순위 또는 높은 우선순위를 갖는 다른 스레드가 실행 기회를 가질 수 있도록 해준다.
- 예제 : 스레드 실행 양보

```java
public class YieldEx {
	public static void main(String[] args) {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		
		threadA.start();
		threadB.start();
		
		try {Thread.sleep(0,1);} catch(InterruptedException e) {}
		threadA.work =false;
		
		try {Thread.sleep(0,1);} catch(InterruptedException e) {}
		threadA.work =true;
		
		try {Thread.sleep(0,1);} catch(InterruptedException e) {}
		threadA.stop =true;
		threadB.stop =true;
		
	}
}
```

```java
//ThreadA
public class ThreadA extends Thread{
	public boolean stop = false; //종료플래그
	public boolean work = true;// 작업 진행 여부 플래스
	
	public void run() {
		while(!stop) {
			if(work) {
				System.out.println("ThreadA 작업 내용");
			} else {
				Thread.yield();
			}
		}
		System.out.println("ThreadA 종료");
	}
}
```

```java
//ThreadB
public class ThreadB extends Thread{
	public boolean stop = false; //종료플래그
	public boolean work = true;// 작업 진행 여부 플래스
	
	public void run() {
		while(!stop) {
			if(work) {
				System.out.println("ThreadB 작업 내용");
			} else {
				Thread.yield();
			}
		}
		System.out.println("ThreadB 종료");
	}
}
```



### 12.6.3 다른 스레드의 종료를 기다림(join())

> 스레드는 다른 스레드와 독립적으로 실행하는 것이 기본이지만 다른 스레드가 종료될 때까지 기다렸다가 실행해야 하는 경우가 발생할 수도 있다. 예를 들어 계산 작업을 하고 마친 스레드로 부터 계산 결과값을 받아 이용하는 경우가 이에 해당한다. 
>
> 이러한 경우를 위하여 Thread는 join() 메소드를 제공하고 있다.

- TreadA와 ThreadB가 있을 때, ThreadA에서 threadB.start()를 통해 ThreadB를 실행하고, threadB.join()을 실행하면, ThreadA는 ThreadB가 끝날 때까지 일시 정지 상태가 된다. 

- 예제 : 1부터 100 합 구하기

```java
public class SumThread extends Thread {
	private long sum;
	
	public long getSum() {
		return sum;
	}
	
	public void setSum(long sum) {
		this.sum=sum;
	}
	
	public void run() {
		for(int i=1; i<=100; i++) {
			sum+=i;
		}
	}
}
```

```java
public class JoinEx {

	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		
		try {
			sumThread.join(); // main스레드를 sumThread가 끝날 때까지 일시 정지시킴
		} catch(InterruptedException e) {
		}
		
		System.out.println("1~100 합: " + sumThread.getSum());

	}
}
```



### 12.6.4 스레드 간 협업(wait(), notify(), nofityAll())

> 두 개의 스레드를 교대로 번갈아가며 실행해야 할 경우가 있다. 정확한 교대 작업이 필요할 경우, 자신의 작업이 끝나면 상대방의 스레드를 일시 정지 상태에서 풀어주고, 자신은 일시 정지 상태로 만드는 것이다. 
>
> 이 방식의 핵심은 공유 객체에 있다. 한 스레드가 작업을 완료하면 notify() 메소드를 호출해서 일시 정지 상태에 있는 다른 스레드를 실행 대기 상태로 만들고, 자신은 두 번 작업을 하지 않도록 wait () 메소드를 호출하여 일시 정지 상태로 만든다. wait() 메소드에 시간을 지정하면 notify를 호출하지 않아도 자동적으로 실행 대기 상태가 된다.

- nofity()는 wait()에 의해 일시 정지된 스레드 중 한 개를 실행 대기 상태로 만들고, notifyAll() 메소드는 wait()에 의해 일시 정지된 모든 스레드들을 실행 대기 상태로 만든다.
- 위의 메소드들은 Thread 클래스가 아닌 Object 클래스에 선언된 메소드이므로 모든 공유 객체에서 호출이 가능하다. 다만 이 메소드들은 동기화 메소드 또는 동기화 블록 내에서만 사용할 수 있다.

- 예제: 데이터를 저장하는 스레드(생산자 스레드)가 데이터를 저장하면, 데이터를 소비하는 스레드(소비자 스레드)가 데이터를 읽고 처리하는 교대 작업을 구현

```java
//두 스레드의 작업 내용을 동기화 메소드로 작성한 공유 객체
public class DataBox {
	private String data;
	
	public synchronized String getData() {
		//data 필드가 null이면 소비자 스레드를 일시 정지 상태로 만듬
		if(this.data ==null) {
			try {
				wait();
			} catch(InterruptedException e) {}
			
		}
		String returnValue =data;
		System.out.println("ConsummerThread가 읽은 데이터: "+ returnValue);
		
		//data필드가 null이면 소비자 스레드를 일시 정지 상태로 만듬
		data = null;
		notify();
		return returnValue;
	}
	
	public synchronized void setData(String data) {
		//data 필드가 null이 아니면 생산자 스레드를 일시 정지 상태로 만듬
		if(this.data !=null) {
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		//data값을 저장하고 소비자 스레드를 실행 대기 상태로 만듬
		this.data = data;
		System.out.println("ProduceThread가  생성한 데이터: "+ data);
		notify();
		
	}
}
```

```java
//데이터를 생산(저장)하는 스레드
public class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = "Data-" +i;
			dataBox.setData(data);
		}
	}
}
```

```java
//데이터를 소비하는(읽는) 스레드
public class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = dataBox.getData();
		}
	}
}
```

```java
//두 스레드를 생성하고 실행하는 메인 스레드
public class waitNotifyEx {

	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread producerThread = new ProducerThread(dataBox);
		ConsumerThread consumerThread = new ConsumerThread(dataBox);
		
		producerThread.start();
		consumerThread.start();

	}
}
```



### 12.6.5 스레드의 안전한 종료(stop 플래그, interrupt() )

> 스레드는 자신의 run() 메소드가 모두 실행되면 자동적으로 종료된다. 경우에 따라서는 실행 중인 스레드를 즉시 종료할 필요가 있다. 동영상을 다 보지 않고 닫기 버튼을 눌렀을 때처럼 말이다.
>
> 즉시 종료를 위하여 Thread는 stop() 메소드를 제공하고 있는데, 이 메소드를 쓸 경우 스레드가 사용중이던 자원들이 불안전한 상태로 남겨지게 되어 사용하지 않을 것을 권고하게 되었다.(deprecated)

- 스레드의 안전한 종료를 위한 최선의 방법은 run()메소드가 정상적으로 종료되도록 유도하는 것이다. 2가지 방법이 있다.
- 첫 방법은 **stop 플래그**를 이용하는 것이다. 다음과 같다

```java
public class XXXThread extends Thread {
    private boolean stop; //stop 플래그 필드
    
    public void run(){
        while(!stop){
            스레드가 반복하는 코드;
        }
        //스레드가 필요한 자원 정리
    }
}
```

- 예제: 1초후 출력 스레드를 중지시킴

```java
//1초 후 출력 스레드를 중지시킴
public class StopFlagEx {
	public static void main(String[]args) {
		PrintThread1 printThread = new PrintThread1();
		printThread.start();
		
		try {Thread.sleep(100);} catch (InterruptedException e) {}
		
		//스레드 종료를 위해 stop 필드를 true로 변경
		printThread.setStop(true);
	}
}
```

```java
//무한 출력 스레드
public class PrintThread1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop=stop;
	}
	
	public void run() {
		while(!stop) {
			System.out.println("실행 중");
		}
		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
```



- 스레드를 run() 종료로 유도하는 두번째 방법은 **interrupt()** 메소드를 사용하는 것이다. 

- interrupt() 메소드는 스레드가 일시 정지 상태에 있을 때 InterruptedException 예외를 발생시켜 run() 메소드를 정상 종료시킨다.

  > 주목할 점은 스레드가 실행 대기 또는 실행 상태에 있을 때 interrupt() 메소드가 실행되면 즉시 InterruptedException 예외가 발생하지 않고, 스레드가 미래에 일시 정지 상태가 되면 예외가 발생한다는 것이다. 따라서 스레드가 일시정지 상태가 되지 않으면 해당 메소드를 통한 호출은 아무런 의미가 없다.
  >
  > 일시정지를 하지 않고도 interrupt() 호출 여부를 알 수 있는 방법이 있다.  interrupt() 메소드가 호출되었다면 스레드의 interrupted()와 isInterrupted() 메소드는 true를 리턴한다. 둘 다 현재 스레드가 interrupted되었는지 확인하는 메소드인데, interrupted() 메소드는 정적 메소드이고, isInterrupted() 메소드는 인스턴스 메소드라는 점이 다르다.
  >
  > ```java
  > boolean status = Thread.interrupted();
  > boolean status = Object.isInterrupted();
  > ```

- 예제 : 1초 후 출력 스레드를 중지시킴

```java
//1초 후 출력 스레드를 중지시킴
public class InterruptEx {
	public static void main(String[]args) {
		Thread thread1 = new PrintThread3();
		thread1.start();
		
		try {Thread.sleep(1000); } catch(InterruptedException e) {}
		
		//스레드를 종료시키기 위해 interruptException를 발생시킴
		thread1.interrupt();
		
	}
}
```

```java
//무한 반복 출력 스레드이며, sleep()메소드 진행 중 interrupt를 받아 정지함
public class PrintThread2 extends Thread{
	public void run() {
		try {
			while(true) {
				System.out.println("실행 중");
				Thread.sleep(1);
			}
		} catch(InterruptedException e) {}
		
		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
```

```java
//무한 반복 출력 스레드이며, interrupted()메소드로 interrupt 현재 상태를 받아 정지함
public class PrintThread3 extends Thread{
	public void run() {
		while(true) {
			System.out.println("실행 중");
			if(Thread.interrupted()) {
				break;
			}
		}
		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
```



## 12.7 데몬 스레드



## 12.8 스레드 그룹



### 12.8.1 스레드 그룹 이름 얻기



### 12.8.2 스레드 그룹 생성



### 12.8.3 스레드 그룹의 일괄 interrupt()



## 12.9 스레드풀



### 12.9.1 스레드풀 생성 및 종료



### 12.9.2 작업 생성과 처리 요청



### 12.9.3 블로킹 방식의 작업 완료 통보



### 12.9.4 콜백 방식의 작업 완료 통보



