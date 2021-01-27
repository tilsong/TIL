package ex;

public class BoundedTypeParameterEx {

	public static void main(String[] args) {
		int result = UtilN.compare(10, 20);//Number타입만 가능함
		System.out.println(result);//int -> Integer, 그리고 다시 int(자동 Boxing)
		
		
		int result1 = UtilN.compare(10.2,3.1);
		System.out.println(result1);//double => Double -> double(자동 Boxing_

	}

}
