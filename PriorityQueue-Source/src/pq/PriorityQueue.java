package pq;

public interface PriorityQueue<K, V> {
	public void insert(K k, V v);
	public V min();
	public V remove();
	public int size();
	public boolean isEmpty();
}
