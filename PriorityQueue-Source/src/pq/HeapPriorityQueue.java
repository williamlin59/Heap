package pq;

import java.util.Comparator;

public class HeapPriorityQueue<K,V> implements PriorityQueue<K,V> {
	private Comparator<Entry<K,V>> comparator = new Comparator<Entry<K,V>>() {
		@SuppressWarnings("unchecked")
		@Override
		public int compare(Entry<K,V> arg0, Entry<K,V> arg1) {
			if (arg0.key() instanceof Comparable && arg1.key() instanceof Comparable) {
				return ((Comparable<K>) arg0.key()).compareTo(arg1.key());
			}
			
			throw new UnsupportedOperationException();
		}
	};

	private class PQEntry implements Entry<K,V> {
		K key;
		V value;
		
		public PQEntry(K k, V v) {
			this.key = k;
			this.value = v;
		}
		
		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
		
		public String toString() {
			return "{" + key + "," + value + "}";
		}
	}
	
	private Vector<Entry<K,V>> queue = new ArrayVector<Entry<K,V>>(); 
	
	@Override
	public void insert(K k, V v) {
		PQEntry temp = new PQEntry(k,v);
		queue.insertAtRank(queue.size(),temp);
		int position = queue.size()-1;
		boolean flag=true;
		while(position!=0&&flag==true){
			if(comparator.compare(queue.elemAtRank(position),queue.elemAtRank(parentIndex(position)))<0){
				PQEntry temporary= new PQEntry(k,v);
				temporary=(PQEntry) queue.elemAtRank(position);
				queue.replaceAtRank(position,queue.elemAtRank(parentIndex(position)));
				queue.replaceAtRank(parentIndex(position),temporary);
				position=parentIndex(position);
			}
			else{
				flag=false;
			}
		}

	}

	@Override
	public V min() {
		if (queue.isEmpty()) throw new EmptyPriorityQueueException(); 
		return queue.elemAtRank(0).value();
	}

	@Override
	public V remove() {
		if (queue.isEmpty()) throw new EmptyPriorityQueueException();
		if (queue.size() == 1) {
			return queue.removeAtRank(0).value();
		}
		
		queue.replaceAtRank(0,  queue.removeAtRank(queue.size()-1));
		int index = 0;
		boolean check = true;
		while (check && (hasLeft(index) || hasRight(index))) {
			int child = -1;
			
			// Get the smaller child
			if (hasLeft(index)) {
				child = leftIndex(index);
			}
			
			if (hasRight(index)) {
				int right = rightIndex(index);
				if (child == -1) {
					child = right;
				} else {
					// if the right child is smaller than the left child - choose the right child 
					if (comparator.compare(queue.elemAtRank(child), queue.elemAtRank(right)) > 0) {
						child = right;
					}
				}
			}
			
			if (child > -1) {
				// we have a child, so check if we need  to swap it...
				check = comparator.compare(queue.elemAtRank(index), queue.elemAtRank(child)) > 0;
				if (check) {
					Entry<K,V> temp = queue.elemAtRank(index);
					queue.replaceAtRank(index, queue.elemAtRank(child));
					queue.replaceAtRank(child, temp);
				}
				index = child;
			}
		}
		
		return null;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	private int parentIndex(int j) {
		return (j-1)/2;
	}

	private int leftIndex(int j) {
		return 2*j + 1;
	}

	private int rightIndex(int j) {
		return 2*j + 2;
	}
	
	private boolean hasLeft(int j) {
		return leftIndex(j) < queue.size();
	}
	
	private boolean hasRight(int j) {
		return rightIndex(j) < queue.size();
	}
	
	public String toString() {
		return toString(0, "");
	}
	
	public String toString(int index, String prefix) {
		if (index >= queue.size()) return "";
		String out = prefix + queue.elemAtRank(index).toString()+"\n";
		out += toString(leftIndex(index), prefix + "\t");
		out += toString(rightIndex(index), prefix + "\t");
		return out;
	}
	
	public static void main(String[] args) {
		PriorityQueue<Integer, String> pq = new HeapPriorityQueue<Integer, String>();
		pq.insert(25, "A");
		System.out.println("PQ:" + pq);
		pq.insert(12, "B");
		System.out.println("PQ:" + pq);
		pq.insert(7, "C");
		System.out.println("PQ:" + pq);
		pq.insert(4, "D");
		System.out.println("PQ:" + pq);
		pq.insert(2, "E");
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
	}
}

