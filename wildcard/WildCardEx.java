package wildcard;
import java.util.Arrays;
public class WildCardEx {

	public static void registerCourse( Course<?> course) {
		//제네릭 타입에서 가능
		System.out.println(course.getName()+" 수강생: "+Arrays.toString(course.getStudents()));
	}
	public static void registerCourseStudent(Course<? extends Student> course) {
		System.out.println(course.getName()+ "수강생: "+ Arrays.toString(course.getStudents()));
	}
	public static void registerCourseWorker(Course<? super Worker> course) {
		System.out.println(course.getName()+ "수강생: "+ Arrays.toString(course.getStudents()));
	}

	public static void main(String[] args) {
		Course<Person> personCourse = new Course<Person>("일반인 과정", 5);
			personCourse.add(new Person("일반인"));
			personCourse.add(new Worker("직장인"));
			personCourse.add(new Student("학생"));
			personCourse.add(new HighStudent("고등학생"));
		Course<Worker> workerCourse = new Course<Worker>("직장인 과정", 5);
			workerCourse.add(new Worker("직장인"));
		Course<Student> studentCourse = new Course<Student>("학생 과정", 5);
			studentCourse.add(new Student("학생"));
			studentCourse.add(new Student("고등학생"));
		Course<HighStudent> highStudentCourse = new Course<HighStudent>("고등학생 과정", 5);
			highStudentCourse.add(new HighStudent("고등학생"));
		
		//괄호 안의 타입만 출력
		registerCourse(personCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		registerCourse(workerCourse);
		System.out.println();
		
//		registerCourseStudent(personCourse);
		registerCourseStudent(studentCourse);
		registerCourseStudent(highStudentCourse);
//		registerCourseStudent(workerCourse);
		System.out.println();
		
		registerCourseWorker(personCourse);
//		registerCourseWorker(studentCourse);
//		registerCourseWorker(highStudentCourse);
		registerCourseWorker(workerCourse);
		System.out.println();
		
		
	}

}
