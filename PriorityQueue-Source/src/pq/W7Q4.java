package pq;

public class W7Q4 {
	public static void main(String[] args) {
		PriorityQueue<Integer, String> pq = new HeapPriorityQueue<Integer, String>();
		pq.insert(25, "A");
		System.out.println("PQ:" + pq);
		pq.insert(12, "B");
		System.out.println("PQ:" + pq);
		pq.insert(7, "A");
		System.out.println("PQ:" + pq);
		pq.insert(4, "B");
		System.out.println("PQ:" + pq);
		pq.insert(2, "A");
		System.out.println("PQ:" + pq);
		pq.insert(15, "B");
		System.out.println("PQ:" + pq);
		pq.insert(19, "A");
		System.out.println("PQ:" + pq);
		pq.insert(23, "B");
		System.out.println("PQ:" + pq);
		pq.insert(45, "A");
		System.out.println("PQ:" + pq);
		pq.insert(17, "A");
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);

		pq.insert(9, "A");
		System.out.println("PQ:" + pq);
		pq.insert(6, "B");
		System.out.println("PQ:" + pq);
		pq.insert(39, "A");
		System.out.println("PQ:" + pq);
		pq.insert(42, "B");
		System.out.println("PQ:" + pq);
		pq.insert(31, "A");
		System.out.println("PQ:" + pq);
		pq.remove();
		System.out.println("PQ:" + pq);
		pq.insert(6, "A");
		System.out.println("PQ:" + pq);
		pq.insert(24, "A");
		System.out.println("PQ:" + pq);
		
	}
}
