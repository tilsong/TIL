package wildcard;

public class Course <T>{
	private String name;
	private T [] students;
	
	public Course(String name, int capacity) {
		this.name = name;
		//타입 파라미터로 배열 생성하기
		students = (T[]) (new Object[capacity]);
	}
	
	public String getName() {return name;}
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
