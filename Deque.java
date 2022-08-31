import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item>{
	
	private int Counter;
	
	private class Node{
		
		Item item;
		Node next;
		Node prev;	
	}
	private Node head = new Node();
	private Node tail = new Node();

	public Deque() {
		head.next = tail;
	    tail.prev = head;
	}
	
	public boolean isEmpty() {
		return head.next == tail;
	}
	
	public int size() {
		return Counter;
	}
	
	public void addFirst(Item item) {
		if(item == null) throw new IllegalArgumentException();
		Node first = new Node();
		first.item = item;
		first.next = head.next;
		head.next = first;
		first.prev = head;
		first.next.prev = first;
		Counter++;

	}
	
	public void addLast(Item item) {
		if(item == null) throw new IllegalArgumentException();
		Node last = new Node();
		last.item = item;
		last.prev = tail.prev;
		tail.prev = last;
		last.next = tail;
		last.prev.next = last;
		Counter++;
	}
	
	public Item removeFirst() {
		if(isEmpty()) throw new NoSuchElementException();
		Item ans = head.next.item;
		head.next = head.next.next;
		head.next.prev = head;
		Counter--;
		return ans;
	}
	
	public Item removeLast() {
		if(isEmpty()) throw new NoSuchElementException();
		Item ans = tail.prev.item;
		tail.prev = tail.prev.prev;
		tail.prev.next = tail;
		Counter--;
		return ans;
	}
		

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
	    Iterator<Integer> i = deque.iterator();
	   
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = head.next;
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		public boolean hasNext() {
			return current.next != null;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
