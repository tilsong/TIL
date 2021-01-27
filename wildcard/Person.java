package wildcard;

public class Person {
	String name;
	public Person(String name) {
		this.name = name;
	};
	public String getName() {return name;}
	
	//이부분이 있어야 registerCourse의 출력값이 정상적으로 나옴
	@Override
	public String toString() {
		return name;
	}
}
