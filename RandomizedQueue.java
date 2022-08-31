import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int Capacity = 10;
	private int Counter;
	private int tail;
	private Item[] queue;
	RandomizedQueue<Item> temp=this;

	
	public RandomizedQueue() {
		queue = (Item[]) new Object[Capacity];
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return Counter;
	}
	
	public void enqueue(Item item) {
		if(item == null) throw new IllegalArgumentException();
		queue[tail++] = item;
		Counter++;
		if(tail == Capacity) copy();
		
	}
	
	public Item dequeue() {
		int a;
		if(size() == 0) throw new NoSuchElementException();
		do{
			a = StdRandom.uniform(tail);
		}while(queue[a] == null);
		Item ans = queue[a];
		queue[a] = null;
		Counter--;
		if(Counter <= Capacity/4) copy();
		return ans;
	}
	
	public Item sample() {
		int a;
		if(size() == 0) throw new NoSuchElementException();
		do{
			a = StdRandom.uniform(tail);
		}while(queue[a] == null);
		Item ans = queue[a];
		return ans;
	}
	
	private void copy() {
		Item[] resize;
		if(tail == Capacity) {
			Capacity = Capacity*2;
			resize = (Item[]) new Object[Capacity];
		}
		else {
			Capacity = Capacity/2;
			resize = (Item[]) new Object[Capacity];
		}
		Counter = 0;
		for(int i = 0; i<tail; i++ ) {
			Item a = queue[i];
			if(a == null) continue;
			resize[Counter] = a;
			Counter++;
		}
		tail = Counter;
		queue = resize;
	}
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
		int n = 50;
		while(n>0) {
			q.enqueue(StdRandom.uniform(100));
			n--;
		}
		Iterator<Integer> j = q.iterator();
		Iterator<Integer> k = q.iterator();
		n = 50;
		StdOut.println(q.Counter);
		while(n>0) {
			StdOut.println(j.next());
			StdOut.println(k.next());
			n--;
		}
	}
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	
	
	private class ListIterator implements Iterator<Item>{
		Item[] q = 
		
		
		public boolean hasNext() {
			return q.Counter>0;
		}
		public Item next() {
			if(!hasNext()) throw new java.util.NoSuchElementException();
			int a;
			do{
				a = StdRandom.uniform(queue.length);
			}while( q.queue[a]== null);
			return q.dequeue();
			
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	
	

}
