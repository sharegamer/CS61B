package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    private int g;
    private int b;
    private int r;
    public Clorus(double e)
    {
        super("clorus");
        energy=e;
    }
    public Clorus()
    {
        this(1);
    }

    @Override
    public Color color() {
        return  color(34,0,231);
    }
    public void attack(Creature c)
    {
        energy+=c.energy();
    }
    public void move() {
        energy-=0.03;
    }
    public void stay()
    {
        energy-=0.01;
    }
    public Clorus replicate()
    {
        energy/=2;
        double baby=energy;
        return new Clorus(baby);
    }
    public Action chooseAction(Map<Direction, Occupant> neighbors)
    {
        List<Direction> empty=getNeighborsOfType(neighbors,"empty");
        List<Direction> plip=getNeighborsOfType(neighbors,"plip");
        if(empty.size()==0)
            return new Action(Action.ActionType.STAY);
        else if (plip.size()>0) {
            return new Action(Action.ActionType.ATTACK,HugLifeUtils.randomEntry(plip));
        } else if (energy>=1) {
            return new Action(Action.ActionType.REPLICATE,HugLifeUtils.randomEntry(empty));
        }else
            return new Action(Action.ActionType.MOVE,HugLifeUtils.randomEntry(empty));
    }
}
