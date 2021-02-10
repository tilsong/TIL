# (servlet) Application, Session, Cookie

servlet(Server Application Let)은 말 그대로  서버 앱 조각이라고 할 수 있다. 그래서 서버는  클라이언트의 요청에 따라 필요한 servlet을 선택하여 사용한다. servlet은 사용이 끝난 뒤 was에 의해 사라지게 된다. 그렇다면 사이트에 로그인했을 때와 같이, 서블릿의 의미 있는 값을 유지해야 할 경우에는 어떻게 해야 할까?

대표적인 세 가지 도구를 사용해볼 수 있다. application, session, cookie 이다. (hidden input, queryString 등의 방법도 있다.) 이 도구들은 바로 상태를 저장할 수 있는 객체들이다.



## 1. Application

먼저 **application** 저장소를 살펴보겠다. 이는 서블릿 컨텍스트(Context)로, 서블릿들 간의 문맥을 저장할 수 있는 곳이자, 자원을 공유할 수 있는 저장소이다. application은 사이트를 방문한 누구나 상태값을 서버(웹 어플리케이션)에 application 객체를 저장하여 사용하도록 한다. 

아래 코드를 함께 보자.

```java
ServletContext application = request.getServletContext();// application 객체를 생성하여 클라이언트의 정보를 저장한다.

String v_ = request.getParameter("v");
String op = request.getParameter("operator");
int v=0;	

if(!v_.equals("")) v = Integer.parseInt(v_);
	//계산
	if(op.equals("=")) {
		int x = (Integer)application.getAttribute("value");	//이전에 사용자가 전달한 값
		int y =v; //지금 사용자가 전달한 값
		String operator =(String) application.getAttribute("op");
		
		int result = 0;
		
		if(operator.equals("+")) {
			result = x+y;
		out.printf("result is %d\\\\n", result);
		} else {
			result = x-y;
			
		out.printf("result is %d\\\\n", result);
		}
	} //값을 저장
	else {
		application.setAttribute("value", v_);//like map
		application.setAttribute("op", op);//값을 저장
	}
	
}
```

이처럼 getAttribute() 메소드와 setAttribute() 메소드를 사용하여 사용자가 전달한 값을 서버에 저장하고, 서블릿에서 사용하게 된다. 



## 2. Session

두번째로 Session을 살펴보도록 한다. Session은 웹 서버가 현재 사용자(Session)을 구분하는 방식이라고 할 수 있다. Session을 사용하는 방법과 내용은 Application과 거의 같은데, 쓸 수 있는 범위가 다르다. was에서 누구나 저장해놓을 수 있는 공간이 application라면, 그 중 개인별로 사물함을 제공한 것이 session이라고 할 수 있다. 간단하게 말하자면 애플리케이션은 대상에 상관없이 웹 어플리케이션, 즉 서버에 값을 저장하고, 세션은 현재 접속한 사용자마다 개별적으로 공간을 부여받도록 하여 값을 저장한다. 따라서 세션 값이 저장되면 한 브라우저에서 다른 창을 열었을 때도 같은 세션값을 공유할 수 있게 된다.

클라이언트가 사이트에 처음 방문했을 경우 그 사용자를 위한 세션은 존재하지 않는다.  사이트에서 나갈 때, 세션(SID, Session ID)을 발급 해주게 된다. 이후 다시 사용자가 사이트에 방문하여 SID를 가져오면 세션의 값을 넣어서 사용할 수 있다. 같은 브라우저라면 같은 SID, 세션값을 사용하고, 다른 브라우저는 새로운 세션값을 받는다. 동일 컴퓨터에서 같은 사이트를 사용하더라도 크롬과 익스플로러의 세션값은 다르게 나타나는 것이다.

아래 코드를 함께 보자.

```java
@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    HttpSession session = request.getSession();//세션
	    
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();

		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v=0;
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		//계산
		if(op.equals("=")) {
			int x = (Integer)session.getAttribute("value");//이전에 사용자가 전달한 값
			int y =v; //지금 사용자가 전달한 값
			String operator =(String) session.getAttribute("op");
			int result = 0;
			
			if(operator.equals("+")) {
				result = x+y;
			out.printf("result is %d\\n", result);
			} else {
				result = x-y;
			out.printf("result is %d\\n", result);
			}
		} //값을 저장
		else {
			session.setAttribute("value", v_);//like map
			session.setAttribute("op", op);//값을 저장
		}
		
	}
}
```

*저장소를 비울 때 사용되는 메소드 → void invalidate() 세션에서 사용되는 session 객체들을 무효화(invalidate)시킨다.

*로그인 세션에 대해 타임아웃이 있으며, 이를 위한 메소드들이 있다.



## 3. Cookie

세번째로 cookie를 이용하여 값을 저장하는 방법을 알아보겠다. 상태 저장을 위해 application과 sessin이 서버 측에 저장된 것과 달리, Cookie는 클라이언트 측에 저장되어 사용된다. 메소드들을 보면서 쿠키에 대해 알아보도록 한다.

- addCookie → 쿠키 클라이언트 측에 저장하기

```java
Cookie cookie = new Cookie("c", c); // 쿠키 생성. 쿠키 값은 문자열로 보내야 하는데, json을 사용하면 다양한 값을 쓸 수 있다고 한다.
response.addCookie(cookie); //was에서 클라이언트에 쿠키를 보낸다.
```



- getCookie() → 쿠키를 클라이언트로 부터 받아 사용

```java
쿠키 읽기 // 클라이언트가 쿠키 받음
Cookie[] cookies = request.getCookies();  //쿠키는 배열값으로 리퀘스트됨
String _c = "c";
	if(cookies !=null){ // was 측에서 쿠키를 받기 위한 식. 배열이므로 for문을 사용한다.
	for(Cookie cookie : cookies){
		if("c".equals(cookie.getName()))
			_c = cookie.getValue();
		}
	}
}
```



- 쿠키의 path 옵션

서블릿은 필요에 따라 여러 개 만들어진다. 여러 서블릿을 통해 브라우저는 서블릿마다 다른 쿠키를 가져가게 될 것이다. 따라서 쿠키에 url을 설정함으로써 쿠키를 해당 url과 관련된 서블릿에만 사용되도록 할 수 있다.

→ setPath(String url) // 쿠키가 어느 경우에 사용자에게 전달되어야 하는지  사용범위 설정

​	*사용범위: web Browser별 지정한 path 범주 공간



- 쿠키의 maxAge 옵션

쿠키의 장점 중 하나는 기간을 설정하면 **그 기간 동안 값을 유지**하는 것이다. 그러나 브라우저가 닫혔을 때, 따로 옵션을 설정하지 않았다면 cookie의 생존주기가 브라우저와 같으므로 쿠키가 사라지게 된다. 그러므로 지속적인 쿠키의 값을 유지하기 위하여 쿠키에 maxAge 옵션을 설정해준다.

설정을 해주면 쿠키는 브라우저 메모리에 연결된 외부 메모리에 저장된다. 그러면 브라우저를 닫고 다시 열었을 때에도 저장된 쿠키를 이용할 수 있게 되는 것이다.

→ setMaxAge(int second) // 쿠키의 생명주기를 설정

​	*생명주기: Browser에 전달한 시간부터 만료시간까지



아래 코드를 통해 메소드들을 직접 사용해본다.

```java
// 예시 코드(계산)

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies(); //쿠키 배열을 클라이언트에게 받음

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		int v = 0;

		if (!v_.equals(""))
			v = Integer.parseInt(v_);
		// 계산
		if (op.equals("=")) {

			int x = 0;
			
			String operator = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			int y = v; // 지금 사용자가 전달한 값
			
			int result = 0;
			for (Cookie c : cookies) {
				if (c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			if (operator.equals("+")) {
				result = x + y;
				out.printf("result is %d\\n", result);
			} else {
				result = x - y;
				out.printf("result is %d\\n", result);
			}
		} // 값을 저장
		else {
				Cookie cookie = new Cookie("Cookie", ck); //쿠키의 이름="Cookie", 값=ck 로 설정
				cookie.setMaxAge(24*60*60)//매개변수 → 시간 의미단위로 명확하게 표현
				cookie.SetPath("/의미있는url"); // "/"->모든 url, 뒤에 의미 있는 url 붙여줌
				response.addCookie(cookie); //쿠키를 클라이언트에게 전달해줌
		}

	}
```



## 정리











자료 출처 : 유투브, 뉴렉쳐 2020 Servlet&JSP 프로그래밍 (https://www.youtube.com/playlist?list=PLq8wAnVUcTFVOtENMsujSgtv2TOsMy8zd)