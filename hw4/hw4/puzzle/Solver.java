package hw4.puzzle;
import  edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private List<WorldState> way=new ArrayList<>();
    private int steps;
    private class searchnode implements Comparable<searchnode>
    {
        private searchnode pre;
        private int moves;
        private int estimatedDistance;
        private WorldState worldState;
        public searchnode(searchnode s,int m,WorldState w)
        {
            pre=s;
            moves=m;
            estimatedDistance=w.estimatedDistanceToGoal();
            worldState=w;
        }
        public int moves()
        {
            return moves;
        }
        public int estimatedDistance()
        {
            return estimatedDistance;
        }
        public searchnode pre()
        {
            return pre;
        }
        public WorldState worldState()
        {
            return worldState;
        }

        @Override
        public int compareTo(searchnode o) {
            return Integer.compare(moves+estimatedDistance,o.moves()+o.estimatedDistance());
        }
    }
    public Solver(WorldState initial)
    {
        if(initial.isGoal())
        {
            way.add(initial);
            return;
        }
        MinPQ<searchnode> pq=new MinPQ<>();
        boolean find=false;
        searchnode first=new searchnode(null,0,initial);
        searchnode target=null;
        pq.insert(first);

        while(true)
        {
            searchnode out=pq.delMin();
            for(WorldState worldState:out.worldState.neighbors())
            {
                searchnode insert=new searchnode(out,out.moves()+1,worldState);
                if(insert.worldState().isGoal()) {
                    find = true;
                    target=insert;
                    break;
                }
                if(out.pre()==null)
                {
                    pq.insert(insert);
                    continue;
                }
                if(!insert.worldState().equals(out.pre().worldState()))
                {
                    pq.insert(insert);
                }
            }
            if (find)
                break;
        }
        steps=target.moves();
        Stack<WorldState> original=new Stack<>();
        while (target!=null) {
            original.push(target.worldState());
            target=target.pre();
        }
        while (!original.isEmpty())
            way.add(original.pop());

    }
    public int moves()
    {
        return steps;
    }
    public Iterable<WorldState> solution()
    {
        return way;
    }

    @Override
    public int hashCode() {

        return steps;
    }
}
