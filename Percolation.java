import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private boolean [][] grid;
	final private WeightedQuickUnionUF uf;
	
	public Percolation(int n) {
		if(n <=0) throw new IllegalArgumentException("N Can't be less than or equal to 0");
		grid = new boolean[n][n];
		uf = new WeightedQuickUnionUF(n*n +2);
	}
		
	public void open(int row, int col) {
		if((row > grid.length || row <= 0) || (col > grid.length || col <= 0)) throw new IllegalArgumentException("row or column can't be lesss than 0 or greater than n");
		if(grid [row-1][col-1] == true) return;
        grid [row-1][col-1] = true;
		
		if(row ==1) uf.union(num(row,col), 0);
		if(row == grid.length) uf.union(num(row,col), grid.length*grid.length +1);
		if (row > 1 && isOpen(row - 1, col)) uf.union(num(row, col), num(row - 1, col));
        if (row < grid.length && isOpen(row + 1, col)) uf.union(num(row, col), num(row + 1, col));
        if (col > 1 && isOpen(row, col - 1)) uf.union(num(row, col), num(row, col - 1));
        if (col < grid.length && isOpen(row, col + 1)) uf.union(num(row, col), num(row, col + 1));
	}
	
	public boolean isOpen(int row, int col) {
		if((row > grid.length || row <= 0) || (col > grid.length || col <= 0)) throw new IllegalArgumentException("row or column can't be lesss than 0 or greater than n");
		row--;col--;
		return grid[row][col];
	}
	public boolean isFull(int row, int col) {
		if((row > grid.length || row <= 0) || (col > grid.length || col <= 0)) throw new IllegalArgumentException("row or column can't be lesss than 0 or greater than n");
		return uf.find(num(row,col)) == uf.find(0);
	}
	public int numberOfOpenSites() {
		int Counter =0;
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid.length;j++) {
				if (grid [i][j] == true) Counter++;
			}
		}
		return Counter;
	}
	public boolean percolates() {
	return uf.find(0) == uf.find(grid.length*grid.length +1);
	}
	private int num(int row, int col) {
		return (grid.length * (row-1)) + col;
	}
}
