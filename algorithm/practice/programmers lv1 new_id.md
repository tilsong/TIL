# 0119 프로그래머스  lv1 신규 아이디 추천(카카오)

###### 문제 설명

카카오에 입사한 신입 개발자 `네오`는 카카오계정개발팀에 배치되어, 카카오 서비스에 가입하는 유저들의 아이디를 생성하는 업무를 담당하게 되었습니다. 네오에게 주어진 첫 업무는 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때, 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
다음은 카카오 아이디의 규칙입니다.

- 아이디의 길이는 3자 이상 15자 이하여야 합니다.
- 아이디는 알파벳 소문자, 숫자, 빼기(`-`), 밑줄(`_`), 마침표(`.`) 문자만 사용할 수 있습니다.
- 단, 마침표(`.`)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

네오는 다음과 같이 7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고 규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천해 주려고 합니다.
신규 유저가 입력한 아이디가 `new_id` 라고 한다면,

```
1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
```

------



### 내 첫 풀이

의도: 나누어지는 수들의 count를 구하고, 만약 0이면 {-1} 반환, 아니면 새로운 배열을 count 크기로 생성해 for문으로 넣는다.

```java
package TreadGroup;

public class aa {

	public static void main(String[] args) {
		String new_id ="qwuhi398.l3";
		for(int i =0; i<new_id.length(); i++){
		   char key= new_id.charAt(i);
			if( key >='A'&&key<='Z'){
		     new_id= new_id.replace(key, key+=32 );
		    }    
		}    
//		2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		for(int i=0; i<new_id.length(); i++){
			
			    new_id = new_id.replaceAll("!\"#[$]%&\\(\\)\\{\\}@`[*]:[+];<>,\\^~|'\\[\\]", ""); 
		}
		        
//		3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		for(int i=1; i<new_id.length(); i++){
		     if(new_id.charAt(i-1)=='.'&&new_id.charAt(i)=='.'){
			    StringBuffer sb = new StringBuffer(new_id);
				sb.deleteCharAt(i);
				new_id = sb.toString();
				i--;
			  }
		}
		        

		if('.'==new_id.charAt(0)){
			StringBuffer sb = new StringBuffer(new_id);
		    sb.deleteCharAt(0);
			new_id = sb.toString();
		}
	    if('.'==new_id.charAt(new_id.length()-1)){
			StringBuffer sb = new StringBuffer(new_id);
		    sb.deleteCharAt(new_id.length()-1);
			new_id = sb.toString();
		}

		if(new_id==""){
		    new_id ="a";
	     }
		        

		if(new_id.length()>=16){
			 StringBuffer sb = new StringBuffer(new_id);
			 sb.delete(15, new_id.length());
			 new_id = sb.toString();
		}
		if(new_id.charAt(new_id.length()-1)=='.'){
			 StringBuffer sb = new StringBuffer(new_id);
			 sb.deleteCharAt(new_id.length()-1);
			 new_id = sb.toString();
		}

		while(new_id.length() <3) {
		     new_id = new_id + new_id.charAt(new_id.length()-1);
		}
		
        System.out.println(new_id);
	 }
		
}



```

- 솔직히 2시간 동안 만든 게 이거였다. 눈물나네 정말..
- 1번은 내가 아직 컬렉션이나 함수들을 제대로 보지 않은 이유이고, 둘째는 너무나도 명확하게 아직 멀었기 때문인 것 같다.
- 정진하자! ㅠ 내일은 더 나아질테니깐



**다른 풀이**

```java
class Solution {
    public String solution(String new_id) {
        String answer = "";

        String temp = new_id.toLowerCase();
        for(int i = 0; i < temp.length(); i++){
            Character c = temp.charAt(i);
            if(((c - 'a')>=0 && (c-'a')<26) || c == '-' || c == '_' ||
              c == '.' || Character.isDigit(c)){
                answer += Character.toString(c);
            }
        }

        while(answer.indexOf("..") >= 0){
            answer = answer.replace("..", ".");
        }

        if(answer.charAt(0) == '.'){
            answer = answer.substring(1, answer.length());
        }
        if(answer.length() > 0 && answer.charAt(answer.length()-1) == '.'){
            answer = answer.substring(0, answer.length()-1);
        }

        if(answer.equals("")){
            answer = "a";
        }

        if(answer.length() >= 16){
            answer = answer.substring(0, 15);
            if(answer.length() > 0 && answer.charAt(14) == '.'){
                answer = answer.substring(0, 14);
            }
        }

        if(answer.length() <= 2){
            Character c = answer.charAt(answer.length()-1);

            while(answer.length() != 3){
                answer += Character.toString(c);
            }
        }



        return answer;
    }
}
```

다른 풀이2

```java
class Solution {

    static boolean Valid(char t) {
        if (t >= 'a' && t <= 'z') return true;
        if (t >= '0' && t <= '9') return true;
        if (t =='.' || t=='-' || t=='_') return true;
        return false;
    }

    static String solution (String s) {
        String res = s.toLowerCase();
        String r1 ="";
        char prev = 'a';
        for (int i=0; i<res.length(); i++) {
            if (Valid(res.charAt(i)) ==true) {
                if (prev == '.' && res.charAt(i) == '.') continue;
                r1 += res.charAt(i);
                prev = res.charAt(i);
            }
        }
        if (r1.length() >0 && r1.charAt(r1.length()-1) == '.')
            r1 = r1.substring(0, r1.length()-1);
        if (r1.length() >0 && r1.charAt(0) == '.')
            r1 = r1.substring(1, r1.length());
        if (r1.length()==0)
            r1 ="a";
        if (r1.length() >=16)
            r1 = r1.substring(0,15);
        if (r1.charAt(r1.length()-1) == '.')
            r1 = r1.substring(0, r1.length()-1);
        if (r1.length() ==2)
            r1 = r1+ r1.charAt(1);
        else if (r1.length()==1)
            r1 = r1+ r1.charAt(0) + r1.charAt(0);

        return r1;
    }

    public static void main(String[] args) {

        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(solution("z-+.^."));
        System.out.println(solution("=.="));
        System.out.println(solution("123_.def"));
        System.out.println(solution("abcdefghijklmn.p"));
    }
}
```

다른 풀이3

```java
class Solution {
    public String solution(String new_id) {
        String answer = "";

        StringBuilder sb= new StringBuilder();

        sb.append(new_id.replaceAll("[^\\w-_.]","").toLowerCase().replaceAll("[.]{2,}","."));

        if(sb.length()>0){
            if(sb.charAt(0)=='.'){
                sb.deleteCharAt(0);    
            }
            if(sb.length()>0&&sb.charAt(sb.length()-1)=='.'){
                sb.deleteCharAt(sb.length()-1);
            }
        }
        if(sb.length()==0){
            sb.append("a");
        }

        if(sb.length()>15){
            sb.delete(15,sb.length());
            if(sb.charAt(14)=='.'){
                sb.deleteCharAt(14);
            }
        }

        if(sb.length()<3){
            while(sb.length()<3){
                sb.append(sb.charAt(sb.length()-1));
            }
        }


        return sb.toString();
    }
}
```



```java
class Solution {
    public String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^-_.a-z0-9]","");
        System.out.println(temp);
        temp = temp.replaceAll("[.]{2,}",".");
        temp = temp.replaceAll("^[.]|[.]$","");
        System.out.println(temp.length());
        if(temp.equals(""))
            temp+="a";
        if(temp.length() >=16){
            temp = temp.substring(0,15);
            temp=temp.replaceAll("^[.]|[.]$","");
        }
        if(temp.length()<=2)
            while(temp.length()<3)
                temp+=temp.charAt(temp.length()-1);

        answer=temp;
        return answer;
    }
}
```



**정리**

- 카카오 들어가기 힘드네
