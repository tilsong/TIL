# 0124 프로그래머스 lv1 문자열 다루기 기본



### 문제설명

문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수, solution을 완성하세요. 예를 들어 s가 a234이면 False를 리턴하고 1234라면 True를 리턴하면 됩니다.

**제한 사항**

- `s`는 길이 1 이상, 길이 8 이하인 문자열입니다.

##### 입출력 예

| s    | return |
| ---- | ------ |
| a234 | false  |
| 1234 | true   |



### 나의 풀이 :

앞선 길이 조건을 length() 메소드로 구하고, 문자열변환 메소드와 예외 처리(오늘 배운!!)를 통해 문제를 해결했다.

```java
class Solution {
    public boolean solution(String s) {
        Boolean answer= false;
        if(!(s.length()==4||s.length()==6)){
            return answer;
        }
        try{
          	int a = Integer.parseInt(s);
          	answer = true;
        } catch (NumberFormatException e){
          	answer = false;
        } finally{
          	return answer;
         }       
    }
}
```

- 풀고 나서 다른 사람들 풀이를 보니 나처럼 적은 사람이 가장 많았다! 솔직히 오늘 예외 처리 배워서 그냥 써먹어 본건데 이렇게 많이들 했을 줄이야..

- 다만 try-finally 내용을 if 절 안에 넣고, else절을 통해 return을 주고 있었다. 별 차이는 없다고 생각.. 한다!



### **다른 풀이** :

```java

class Solution {
  public boolean solution(String s) {
     int length = s.length();
        if (length != 4 && length != 6)
            return false;
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            if (c < '0' || c > '9')
                return false;

        }
        return true;
  }
}
```

- 가장 간단한 방법으로 풀이한 듯하다.
- if절 뒤에 실행문이 하나이면 중괄호를 많이 빼는 것 같다. 나도 한 번 해봐야겠다.
- charAt과 char을 활용한 방법이 인상적이다.



### 정리

- 이전 "모의고사" 문제에서 이런저런 생각이 많았는데, 맘 편하게 풀 수 있는 문제가 아니었나 생각한다.
- 확실히 필요한 문법을 알고 있느냐 아니냐에 대한 영향도 크다. 예외처리를 오늘 보지 않았다면 이러한 풀이법을 생각하지 못했을 것이다..
- 하지만 다른 풀이에서 보았듯이 원래 알고 있던 것들을 활용할 줄 아는 능력이 더 멋있는 것 같긴하다.