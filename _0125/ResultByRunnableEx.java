package _0125;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByRunnableEx {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);//스레드풀 생성
		
		System.out.println("[작업 처리 요청]");
		class Task implements Runnable {
			Result result;
			Task(Result result){	//외부 객체를 Task에 저장
				this.result = result;
			}
			@Override
			public void run() {
				int sum =0;
				for(int i=1; i<=10; i++) {
					sum+= i;
				}
				result.addValue(sum);//공유 객체의 메소드
			}
			
		}

		
		Result result = new Result();//공유 객체 생성
		Runnable task1 = new Task(result);//공유 객체 대입
		Runnable task2 = new Task(result);
		Future<Result> future1 = executorService.submit(task1, result);// 두 가지 작업 처리 요청
		Future<Result> future2 = executorService.submit(task2, result);
	
	
	try {
		result = future1.get();//두 가지 작업 결과를 취합
		result = future2.get();
		System.out.println("[처리결과] "+result.accumValue);
		System.out.println("[작업처리 완료]");
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("[실행예외 발생함] " + e.getMessage());
	}
	
	executorService.shutdown();
	
	}
}
class Result{//처리 결과 저장하는 Result클래스
	int accumValue;
	synchronized void addValue(int value){//공유 외부 객체
		accumValue += value;
	}
}

