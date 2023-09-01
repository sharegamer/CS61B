package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> q;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        q=new ArrayDeque<>();
        q.add(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        if(q.isEmpty())
            return;
        int i=q.remove();
        marked[i]=true;
        announce();
        if(i==t)
        {
            targetFound=true;
            return;
        }
        for(int j:maze.adj(i))
        {
            if(targetFound)
                return;
            if(marked[j] || q.contains(j))
                continue;
            else
            {
                distTo[j]=distTo[i]+1;
                edgeTo[j]=i;
                q.add(j);
                if(j==t)
                {
                    marked[j]=true;
                    announce();
                    return;
                }

            }

        }
        bfs();


    }


    @Override
    public void solve() {
        // bfs();
        bfs();
    }
}

