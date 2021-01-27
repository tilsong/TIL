package generic_ex4;

public class Util {
//	Pair 사용
	public static <K,V> V getValue(Pair<K,V> pair, K key) {
		if(pair.getKey()==key) {
			return pair.getValue();
		} else {
			return null;
		}
	}
	//ChildPair 사용
//	public static <P extends Pair<K,V>, K, V> V getValue(P pair, K key) {
//		if(pair.getKey()==key) {
//			return pair.getValue();
//		} else {
//			return null;
//		}
//	}
	
}
