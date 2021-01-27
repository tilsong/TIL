package ex;

public class UtilN {
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		//Number 하위 Double 클래스로 변환되었으므로 다시 double 타입으로 변환함.
		return Double.compare(v1, v2);
	}
}
