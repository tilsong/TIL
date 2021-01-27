package genericE;

public class Util {
    // 방법 1
    public static <K,V> V getValue(Pair<K,V> p, K k) {
        if (p.getKey() == k) {
            return p.getValue();
        } else {
            return null;
        }
    }
    
    // 방법 2
    //제네릭과 와일드 카드가 합쳐졌을 때 갖는 확장성은 Overloading이다.
    //https://vvshinevv.tistory.com/57
    public static <P extends Pair<K,V>, K , V> V getValue(P p, K k) {
    if (p.getKey() == k) {
        return p.getValue();
    } else {
        return null;
    }
}
    
}



//public class Util {
//	public static <K,V> V getValue(Pair<K,V> pair, K key) {
//		if(pair.getKey()== key) {
//			return pair.getValue();
//		} else {
//			return null;
//		}
//	}
//	
//	public static <P extends Pair<K,V>, K, V> V getValue(P p,K key ) {
//		if(p.getKey()==key) {
//			return p.getValue();
//		} else{
//			return null;
//		}
//	}
//	
//	
//}
