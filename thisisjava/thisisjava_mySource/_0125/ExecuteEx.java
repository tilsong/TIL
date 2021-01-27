package _0125;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteEx {

	public static void main(String[] args) throws Exception {
		//최대 스레드 수가 2개인 스레드풀 생성
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<10; i++) {	//작업 정의
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					//스레드의 총 개수 및 작업 스레드 이름 출력
					ThreadPoolExecutor threadPoolExecutor = 
							(ThreadPoolExecutor) executorService;
					int poolSize = threadPoolExecutor.getPoolSize();
					String threadName = Thread.currentThread().getName();
					System.out.println("[총 스레드 개수: " + poolSize +
							"] 작업 스레드 이름" + threadName);
					//예외 발생시킴
					int value = Integer.parseInt("삼");
				}
			};
			executorService.execute(runnable); //작업 처리 요청
			//executorService.submit(runnable);
			
			Thread.sleep(10);
		}
	
		executorService.shutdown();
	}
}
