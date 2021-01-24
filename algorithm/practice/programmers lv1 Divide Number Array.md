# 0119 프로그래머스  lv1 나누어 떨어지는 숫자 배열



## 문제 설명

array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.

##### 제한사항

- arr은 자연수를 담은 배열입니다.
- 정수 i, j에 대해 i ≠ j 이면 arr[i] ≠ arr[j] 입니다.
- divisor는 자연수입니다.
- array는 길이 1 이상인 배열입니다.

##### 입출력 예

| arr           | divisor | return        |
| ------------- | ------- | ------------- |
| [5, 9, 7, 10] | 5       | [5, 10]       |
| [2, 36, 1, 3] | 1       | [1, 2, 3, 36] |
| [3,2,6]       | 10      | [-1]          |



### 내 첫 풀이

의도: 나누어지는 수들의 count를 구하고, 만약 0이면 {-1} 반환, 아니면 새로운 배열을 count 크기로 생성해 for문으로 넣는다.

```java
import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int count=0;

        //answer 배열 크기 구하기
        for(int i=0; i<arr.length; i++){
            if((arr[i]%divisor)==0){
                count++;
            }
        }
        
        //answer 배열 멤버 없으면 {-1} 반환
        if(count==0){
            int [] answer = {-1};
            return answer;
        }
        
        //answer 배열 만들고 넣기
        int [] answer = new int [count];
        int j=0;
        for(int i =0; i<arr.length; i++){
            if((arr[i]%divisor)==0){
              answer[j] = arr[i];
              j++;
            }
        }
        Arrays.sort(answer);
        return answer;
    }
}
```

- 역시 쉬운 문제는 풀 때 재미있다. 쉬운 문제라기 보다, 내 수준에서 조금 아래인 문제라고 하는 게 낫겠다. 
- 변수 초기값을 주지 않아서 실행 시켰을 때 몇번 오류가 떴었다. 주의해야겠다. 근데 초기값 자동으로 주어지는 거 아니었나?ㅠㅠ
- 풀이를 제출하고 다른 사람들의 답을 보니깐 리스트를 이용하기도 하고, 람다식을 이용한 풀이도 있었다. 람다식을 이용한 풀이가 인상적이었다. 얼른 스레드 끝내고 람다 시작해야겠다!



**정리**

- 간단한 문제였다. 
