import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> Rq = new RandomizedQueue<String>();
		String s;
		do {
			s = StdIn.readString();
			Rq.enqueue(s);
		}while(!StdIn.isEmpty());
		
		while(k>0) {
			StdOut.println(Rq.dequeue());
			k--;
		}
	}

}
