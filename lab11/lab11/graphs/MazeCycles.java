package lab11.graphs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cycle=false;
    private Set<Integer> set;


    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        set=new HashSet<>();
        edgeTo[0]=0;
        distTo[0]=0;
        DFS(0);

    }
    private void DFS (int i)
    {
        marked[i]=true;
        announce();
        for(int j:maze.adj(i))
        {
            if(cycle)
                return;
            if(marked[j]&&edgeTo[i]!=j)
            {
                cycle=true;
                edgeTo[j]=i;
                announce();
                int x=i;
                while (edgeTo[x]!=i)
                {
                    set.add(x);
                    x=edgeTo[x];
                }
                set.add(x);
                for(int y=0;y< maze.V();y++)
                    if(!set.contains(y))
                        edgeTo[y]=y;
                announce();
                return;
            }
            else if(!marked[j]){
                distTo[j]=distTo[i]+1;
                edgeTo[j]=i;
                DFS(j);
            }

        }
    }
    // Helper methods go here




}

