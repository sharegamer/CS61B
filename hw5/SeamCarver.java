import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture picture;
    public SeamCarver(Picture picture)
    {
        this.picture=picture;
    }
    public Picture picture()                       // current picture
    {
        return picture;
    }
    public     int width()                         // width of current picture
    {
        return picture.width();
    }
    public     int height()                        // height of current picture
    {
        return picture.height();
    }
    public  double energy(int x, int y)            // energy of pixel at column x and row y
    {
        if(x<0 || x>=picture.width() || y<0 || y>=picture.height())
        {
            throw new IndexOutOfBoundsException("x,y out of bound");
        }
        Color xdir1=picture.get((x-1+width())%width(),y);
        Color xdir2=picture.get((x+1)%width(),y);
        double enx=Math.pow(xdir1.getBlue()-xdir2.getBlue(),2)+Math.pow(xdir1.getGreen()-xdir2.getGreen(),2)+Math.pow(xdir1.getRed()-xdir2.getRed(),2);
        Color ydir1=picture.get(x,(y-1+height())%height());
        Color ydir2=picture.get(x,(y+1)%height());
        double eny=Math.pow(ydir1.getRed()-ydir2.getRed(),2)+Math.pow(ydir1.getBlue()-ydir2.getBlue(),2)+Math.pow(ydir1.getGreen()-ydir2.getGreen(),2);
        return enx+eny;
    }
    public   int[] findHorizontalSeam()            // sequence of indices for horizontal seam
    {
        int[][] pre=new int[width()][height()];
        double[][] energy=new double[width()][height()];
        double[][] pathen=new double[width()][height()];
        int[] path=new int[width()];
        for(int i=0;i<height();i++)
            for(int j=0;j<width();j++)
            {
                energy[j][i]=energy(j,i);
            }
        for(int i=0;i<height();i++) {
            pre[0][i] = 0;
            pathen[0][i]=energy[0][i];
        }
        for(int i=1;i<width();i++)
            for(int j=0;j<height();j++)
            {
                if(j==0)
                {
                    if (pathen[i - 1][0] > pathen[i - 1][1]) {
                        pathen[i][0] = pathen[i - 1][1] + energy[i][0];
                        pre[i][0] = 1;
                    } else {
                        pathen[i][0]=pathen[i-1][0]+energy[i][0];
                        pre[i][0]=0;
                    }
                } else if (j == height() - 1) {
                    if (pathen[i - 1][height() - 1] > pathen[i - 1][height() - 2]) {
                        pathen[i][height() - 1] = pathen[i - 1][height() - 2] + energy[i][height() - 1];
                        pre[i][height() - 1] = height() - 2;
                    } else {
                        pathen[i][height() - 1] = pathen[i - 1][height() - 1] + energy[i][height() - 1];
                        pre[i][j] = height() - 1;
                    }
                } else {
                    double min=Math.min(Math.min(pathen[i-1][j-1],pathen[i-1][j]),pathen[i-1][j+1]);
                    if(pathen[i-1][j-1]==min)
                    {
                        pathen[i][j]=pathen[i-1][j-1]+energy[i][j];
                        pre[i][j]=j-1;
                    } else if (pathen[i - 1][j] == min) {
                        pathen[i][j] = pathen[i - 1][j] + energy[i][j];
                        pre[i][j] = j;
                    } else {
                        pathen[i][j]=pathen[i-1][j+1]+energy[i][j];
                        pre[i][j]=j+1;
                    }
                }
            }
        double min=Double.MAX_VALUE;
        int minnode=-1;
        for(int i=0;i<height();i++)
        {
            if(pathen[width()-1][i]<min)
            {
                min=pathen[width()-1][i];
                minnode=i;
            }
        }
        path[width()-1]=minnode;
        for(int i=width()-1;i>0;i--)
        {
            path[i-1]=pre[i][minnode];
            minnode=pre[i][minnode];
        }
        return path;
    }
    public   int[] findVerticalSeam()              // sequence of indices for vertical seam
    {
        int[][] pre=new int[width()][height()];
        double[][] energy=new double[width()][height()];
        double[][] pathen=new double[width()][height()];
        int[] path=new int[height()];
        for(int i=0;i<height();i++)
            for(int j=0;j<width();j++)
            {
                energy[j][i]=energy(j,i);
            }
        for(int i=0;i<width();i++) {
            pre[i][0] = 0;
            pathen[i][0]=energy[i][0];
        }
        for(int i=1;i<height();i++)
            for(int j=0;j<width();j++)
            {
                if(j==0)
                {
                    if (pathen[0][i-1] > pathen[1][i-1]) {
                        pathen[0][i] = pathen[1][i-1] + energy[0][i];
                        pre[0][i] = 1;
                    } else {
                        pathen[0][i]=pathen[0][i-1]+energy[0][i];
                        pre[0][i]=0;
                    }
                } else if (j == width() - 1) {
                    if (pathen[width()-1][i-1] > pathen[width()-2][i-1]) {
                        pathen[width()-1][i] = pathen[width()-2][i-1] + energy[width()-1][i];
                        pre[width()-1][i] = width() - 2;
                    } else {
                        pathen[j][i] = pathen[j][i-1] + energy[j][i];
                        pre[j][i] = width() - 1;
                    }
                } else {
                    double min=Math.min(Math.min(pathen[j-1][i-1],pathen[j][i-1]),pathen[j+1][i]);
                    if(pathen[j-1][i-1]==min)
                    {
                        pathen[j][i]=pathen[j-1][i-1]+energy[j][i];
                        pre[j][i]=j-1;
                    } else if (pathen[j ][i-1] == min) {
                        pathen[i][j] = pathen[j][i-1] + energy[j][i];
                        pre[j][i] = j;
                    } else {
                        pathen[j][i]=pathen[j+1][i]+energy[j][i];
                        pre[j][i]=j+1;
                    }
                }
            }
        double min=Double.MAX_VALUE;
        int minnode=-1;
        for(int i=0;i<width();i++)
        {
            if(pathen[i][height()-1]<min)
            {
                min=pathen[i][height()-1];
                minnode=i;
            }
        }
        path[height()-1]=minnode;
        for(int i=height()-1;i>0;i--)
        {
            path[i-1]=pre[minnode][i];
            minnode=pre[minnode][i];
        }
        return path;
    }
    public    void removeHorizontalSeam(int[] seam)   // remove horizontal seam from picture
    {
        SeamRemover.removeHorizontalSeam(picture,findHorizontalSeam());
    }
    public    void removeVerticalSeam(int[] seam)     // remove vertical seam from picture
    {
        SeamRemover.removeVerticalSeam(picture,findVerticalSeam());
    }
}



