# 0119 프로그래머스  lv1 같은 숫자는 싫어



### **문제 설명**

배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다. 예를 들면,

- arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
- arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.

배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.



### 제한사항

- 배열 arr의 크기 : 1,000,000 이하의 자연수
- 배열 arr의 원소의 크기 : 0보다 크거나 같고 9보다 작거나 같은 정수



### 내 첫 풀이

의도:

연속되는 수가 몇 개인지 먼저 구하고, 수에 맞는 배열을 생성하여 연속되지 않는 수만 넣어준다.

```java
import java.util.*;

public class Solution {
	public int[] solution(int []arr) {
		int count =0;
		//연속되는 수의 개수 구하기
		for(int i =0; i<arr.length-1; i++){
			if(!(arr[i]==arr[i+1])){
				count++;
			}
		}    
		//연속되지 않는 수 배열 생성
    int [] answer = new int [count];
    
    //연속되지 않는 수만 배열에 넣기
    int j=0;
    for(int i=0; i<arr.length; i++){
        if(!(arr[i]==arr[i+1])){
            answer[j]=arr[i];
            j++;
        }
    }
    return answer;
}
```

#### 틀렸던 이유

1. 연속되는 수를 구할 때 조건에 -1을 주지 않아도 되었음.
2. count에 1을 먼저 주고 시작해야 했음.(모두 연속되는 수일 때 1이기 때문)
3. 답안 배열을 만들 때도 첫 번째 인덱스는 바로   arr[0]을 주고  이후 반복문으로 넣어주어야 했음.(j=1로 시작해야 함.)



#### 헷갈렸던 부분

1. 두 수를 비교할 때, (a,a+1) / (a-1,a) 중 어느 것을 고를 것인가?
2. 비교한 후 i, i+1, i-1 중 어느 것을 고를 것인가?

**실행**

{1, 1, 3, 3, 0, 1, 1}

1. (i, i+1), i 일 경우 (length>i)

   → {1, 3, 0}, 앞에서는 앞의 1이 아닌 뒤의 1을 선택하게 되고, 맨 뒤의 두 1이 생략됨.

2. (i, i+1), i +1 일 경우(length>i)

→ {3, 0, 1}, 앞의 1이 아예 생략되고, 맨 뒤는 연속이 아니어도 생략되게 됨.

따라서 1.은 맨 뒤 부분이 연속일 경우 그 부분이 생략될 수 있고, 2.는 맨 뒤가 아예 생략되므로 사용할 수 없다.



3. (i-1,i),  i-1 일 경우(length>i, i-1부터 시작해야 하므로  i=1이며, 맨 앞 인덱스는 무조건 포함시킴.)

→ {1, 1, 3, 0}, 맨 앞 부분 연속일 경우 둘 다 생략됨, 맨 뒤 선택 불가

4. (i-1,i),  i 일 경우(length>i, i-1부터 시작해야 하므로  i=1이며, 맨 앞 인덱스는 무조건 포함시킴.)

→ {1, 3, 0,1}, 완전히 가능 - 맨 앞 부분 연속일 경우 가능, 맨 뒤의 비연속도 캐치할 수 있음.



**수정한 답안**

```java
import java.util.*;
public class Solution{
	public int[] solution(int []arr){
		//연속되는 수의 개수
		int count=1; //모두 연속되는 수여도 1이기 때문
		//연속되는 수의 개수 구하기
		 for(int i=1; i<arr.length; i++){
			 if(arr[i-1] != arr[i]){
					count++;
			 }
		 }
		 //답안 배열 생성
		 int [] answer = new int [count];
		 //연속되지 않는 수만 배열에 넣기
		 answer[0] = arr[0];//첫 수가 연속되는 수이면 체크 안될 수 있으므로 먼저 넣음.
		 int j=1;
		 for(int i=1; i<arr.length; i++){
			if(arr[i-1] !=arr[i]){
				answer[j] = arr[i];
				j++;
			}
		 }

		return answer;
	}
}
```





**다른 풀이**

의도:  arraylist 와 current를 이용한다. 연속되는 수만 arraylist에 저장하여 answer에 옮긴다.

```java
import java.util.*;

public class Solution{
	public int [] solution(int [] arr) {
		//arraylist를 생성한다.
		arrayList<Integer> list = new arrayList<Integer>();
		current =10; // 인덱스 내용은 모두 1-9이므로 current가 항상 큼
		for(int i =0; i<arr.length; i++){
			if(arr[i] != current){
				list.add(arr[i]); //리스트에 연속하지 않는 수들을 넣는다.
				current = arr[i];
			}
		}
		int [] answer = new int [list.size()];
		for(int i=0; i<list.size(); i++){
			answer[i] = list.get(i);
		}
		return answer;
	}
}			
```

이렇게 풀면 리스트에 바로 연속하지 않는 수를 하나씩 비교하여 넣을 수 있기 때문에 보다 직관적이다.



**정리**

- 배열에서 연속하는 수를 제외하고, 비연속하는 수들을 다른 배열에 옮기는 문제였다.
- 어려웠던 것은 for문에서 어떻게 조건을 줄지(i+1, i, i-1), count를 준다면 풀이와 문제사항을 고려하여 0으로 시작할 것인지, 1로 시작할 것인지를 판단하는 것이었다.
- 따라서 해당 조건들을 모두 시도해봄으로 어떤 조건이 적합한 답일지를 찾아보았다.
- 또한 조건에 따라 첫 내용을 배열이나 변수에 넣고 시작해야 할 때가 있다는 것을 알게 되었다.
- ArrayList를 사용하면 더 직관적이고, 깔끔하게 문제를 해결할 수 있음을 알게 되었다. 또한 current를 이용한 방식은 기억하고 있어야 할 개념으로 생각된다.



참고 페이지(https://jhnyang.tistory.com/131)