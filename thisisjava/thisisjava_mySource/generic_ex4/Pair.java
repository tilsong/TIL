package generic_ex4;

public class Pair <K,V>{
	private K key;
	private V value;
	
	//따로 setKey, setValue로 나눌 수도 있고, 일케 생성자로도 가능
	public Pair(K key, V value) {
		this.key=key;
		this.value=value;
	}
	public K getKey() { return this.key;}
	public V getValue() { return this.value;}
}
