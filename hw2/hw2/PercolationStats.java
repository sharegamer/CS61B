package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.Random;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double low;
    private double high;
    public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
    {
        if(N<=0 || T<=0)
            throw new IllegalArgumentException("N and T must greater than 0");
        double[] array=new double[T];
        int i,j;
        for(int k=0;k<T;k++)
        {
            double num=0;
            Percolation p=pf.make(N);
            while(!p.percolates())
            {
                i=StdRandom.uniform(N);
                j=StdRandom.uniform(N);
                if(p.isOpen(i,j))
                    continue;
                p.open(i,j);
                num++;
                if(p.percolates())
                    break;
            }
            array[k]=num/N/N;
        }
        mean=StdStats.mean(array);
        stddev=StdStats.stddev(array);
        low=mean-1.96*stddev/Math.sqrt(T);
        high=mean+1.96*stddev/Math.sqrt(T);
    }
    public double mean()                                           // sample mean of percolation threshold
    {
        return mean;
    }
    public double stddev()                                         // sample standard deviation of percolation threshold
    {
        return stddev;
    }
    public double confidenceLow()                                  // low endpoint of 95% confidence interval
    {
        return low;
    }
    public double confidenceHigh()                                 // high endpoint of 95% confidence interval
    {
        return high;
    }
}
