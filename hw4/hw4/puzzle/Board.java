package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Board implements WorldState{

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    private int[][] tiles;
    private int size;
    public Board(int[][] tiles)
    {
        size=tiles[0].length;
        this.tiles=new int[size][size];
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
                this.tiles[i][j]=tiles[i][j];

    }
    public int tileAt(int i, int j)
    {
        if(i<0 || j<0 ||i>size-1||j>size-1)
            throw new IndexOutOfBoundsException("index out of bound");
        return tiles[i][j];
    }
    public int size()
    {
        return size;
    }
    public Iterable<WorldState> neighbors()
    {


        int x=-1,y=-1;
        List<WorldState> list=new ArrayList<>();
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
                if(tiles[i][j]==0)
                {
                    x=i;
                    y=j;
                }
        if(0<x && 0<y && x<size-1 && y<size-1)
        {
            up(x,y,list);
            down(x,y,list);
            right(x,y,list);
            left(x,y,list);
        } else if (x==0 && y==0) {
            up(x,y,list);
            right(x,y,list);
        } else if (x==0 && y==size-1) {
            left(x,y,list);
            up(x,y,list);
        } else if (x==size-1 && y==0) {
            down(x,y,list);
            right(x,y,list);
        } else if (x==size-1 && y==size-1) {
            down(x,y,list);
            left(x,y,list);
        } else if (y==0) {
            up(x,y,list);
            down(x,y,list);
            right(x,y,list);
        } else if (y==size-1) {
            up(x,y,list);
            down(x,y,list);
            left(x,y,list);
        } else if (x==0) {
            up(x,y,list);
            left(x,y,list);
            right(x,y,list);
        }else {
            down(x,y,list);
            left(x,y,list);
            right(x,y,list);
        }
        return list;
    }
    private void up(int x,int y,List<WorldState> list)
    {
        int[][] up=new int[size][size];
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if(i==x && j==y)
                {
                    up[i][j]=tiles[i+1][j];
                } else if (i==x+1 && j==y) {
                    up[i][j]=0;
                }
                else
                    up[i][j]=tiles[i][j];
            }
        Board uup=new Board(up);
        list.add(uup);
    }
    private void down(int x,int y,List<WorldState> list)
    {
        int[][] down=new int[size][size];
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if(i==x && j==y)
                {
                    down[i][j]=tiles[i-1][j];
                } else if (i==x-1 && j==y) {
                    down[i][j]=0;
                }
                else
                    down[i][j]=tiles[i][j];
            }
        Board ddown=new Board(down);
        list.add(ddown);
    }
    private void left(int x,int y,List<WorldState> list)
    {
        int[][] left=new int[size][size];
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if(i==x && j==y)
                {
                    left[i][j]=tiles[i][j-1];
                } else if (i==x && j==y-1) {
                    left[i][j]=0;
                }
                else
                    left[i][j]=tiles[i][j];
            }
        Board lleft=new Board(left);
        list.add(lleft);
    }
    private void right(int x,int y,List<WorldState> list)
    {
        int[][] right=new int[size][size];
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if(i==x && j==y)
                {
                    right[i][j]=tiles[i][j+1];
                } else if (i==x && j==y+1) {
                    right[i][j]=0;
                }
                else
                    right[i][j]=tiles[i][j];
            }
        Board rright=new Board(right);
        list.add(rright);
    }

    public int hamming()
    {
        int num=0;
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if(tiles[i][j]==0)
                    continue;
                if(tiles[i][j]!=i*size+j+1)
                    num++;
            }
        return num;
    }
    public int manhattan()
    {
        int num=0;
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if (tiles[i][j]==0)
                    continue;
                int x=(tiles[i][j]-1)%size;
                int y=(tiles[i][j]-1)/size;
                num+=Math.abs(x-j)+Math.abs(y-i);
            }
        return num;
    }
    public int estimatedDistanceToGoal()
    {
        return manhattan();
    }
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board x=(Board) o;
        if(size!=x.size())
            return false;
        for(int i=0;i<tiles[0].length;i++)
            for(int j=0;j<tiles[0].length;j++)
            {
                if(tiles[i][j]!=x.tileAt(i,j))
                    return false;
            }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
