package hw2;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF unionset;
    private int[][] array;
    private int n;
    private int opensite;
    public Percolation(int N)                // create N-by-N grid, with all sites initially blocked
    {
        if(N<=0)
            throw new java.lang.IllegalArgumentException("N must greater than 0");
        unionset=new WeightedQuickUnionUF(N*N+2);
        n=N;
        opensite=0;
        array=new int[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                array[i][j]=0;
    }
    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        if(row<0 || row>=n || col<0 || col>=n)
            throw new IndexOutOfBoundsException("out of index");
        if(array[row][col]==1)
            return;
        array[row][col]=1;
        if(row>0){
            if(array[row-1][col]==1)
                unionset.union(row*n+col,row*n+col-n);}
        else
            unionset.union(col,n*n+1);
        if(row<n-1){
            if(array[row+1][col]==1)
                unionset.union(row*n+col,row*n+col+n);}
        if(col>0)
            if(array[row][col-1]==1)
                unionset.union(row*n+col,row*n+col-1);
        if(col<n-1)
            if(array[row][col+1]==1)
                unionset.union(row*n+col,row*n+col+1);
        opensite++;
    }
    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        if(row<0 || row>=n || col<0 || col>=n)
            throw new IndexOutOfBoundsException("out of index");
        if(array[row][col]==1)
            return true;
        return false;
    }
    public boolean isFull(int row, int col)  // is the site (row, col) full?
    {
        if(row<0 || row>=n || col<0 || col>=n)
            throw new IndexOutOfBoundsException("out of index");
        if(unionset.connected(row*n+col,n*n+1))
            return true;
        return false;
    }
    public int numberOfOpenSites()           // number of open sites
    {
        return opensite;
    }
    public boolean percolates()              // does the system percolate?
    {
        if(unionset.connected(n*n,n*n+1))
            return true;
        return false;
    }
    public static void main(String[] args)   // use for unit testing (not required)
    {
        double i,j,k;
        Stopwatch s=new Stopwatch();
        PercolationFactory pf=new PercolationFactory();
        PercolationStats p=new PercolationStats(300,2000,pf);
        i=p.mean();
        j=s.elapsedTime();
        k=p.stddev();
        System.out.println(i);

        System.out.println(j);

    }

}
