package lab11.graphs;
import  edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.Map;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Map<Integer,Integer> pqmap;


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toX(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        if(targetFound)
            return;;
        int min=Integer.MAX_VALUE;
        int minkey=-1;
        for(int i: pqmap.keySet())
        {
            int j=pqmap.get(i);
            if(j<min)
            {
                min=j;
                minkey=i;
            }
        }
        pqmap.remove(minkey);
        marked[minkey]=true;
        announce();
        for(int i:maze.adj(minkey))
        {
            if(targetFound)
                return;
            if(i==t)
            {
                distTo[i]=distTo[minkey]+1;
                edgeTo[i]=minkey;
                marked[i]=true;
                targetFound=true;
                return;
            }
            if(!marked[i]&&!pqmap.containsKey(i))
            {
                distTo[i]=distTo[minkey]+1;
                edgeTo[i]=minkey;
                pqmap.put(i,h(i));
            }
        }
        astar(s);

    }

    @Override
    public void solve() {
        edgeTo[s]=s;
        distTo[s]=0;
        pqmap=new HashMap<>();
        pqmap.put(s,h(s));
        astar(s);
        announce();
    }

}

