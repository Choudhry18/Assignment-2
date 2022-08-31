import edu.princeton.cs.algs4.StdIn; import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats; import edu.princeton.cs.algs4.StdOut;
public class PercolationStats {
	private Percolation pn;
	final private double[] blocks; final private int T;

	public PercolationStats(int n, int trials) {
		if(n <= 0) throw new IllegalArgumentException("N has to be greater than 0");
		if(trials <= 0) throw new IllegalArgumentException("trials has to be greater than 0");
		blocks = new double[trials]; T = trials;
		int Counter = 0;
		while(Counter < trials) {
			pn = new Percolation(n);
			while(pn.percolates() == false) {
				int row = StdRandom.uniform(1, n+1); int col = StdRandom.uniform(1, n+1);
				pn.open(row, col);
			}
			blocks[Counter] = pn.numberOfOpenSites()/(n*n*1.0);
			Counter++;
		}
	}
	public static void main(String[] args) {
		int n = StdIn.readInt(); int trials = StdIn.readInt();
		PercolationStats ps = new PercolationStats(n, trials);		
		StdOut.println("mean                    =  " + ps.mean());
		StdOut.println("stddev                  =  " + ps.stddev());
		StdOut.println("95% confidence interval =  [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]" );
		
		
	}

	public double mean() {
		return StdStats.mean(blocks);
	}
	public double stddev() {
		return StdStats.stddev(blocks);
	}
	public double confidenceLo() {
		return mean() - (1.96 * stddev())/Math.sqrt(T);
	}
	public double confidenceHi() {
		return mean() + (1.96 * stddev())/Math.sqrt(T);
	}

}
