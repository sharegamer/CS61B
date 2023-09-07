package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int state;
    private int period;
    public StrangeBitwiseGenerator(int period)
    {
        state=0;
        this.period=period;
    }
    public double next()
    {
        state+=1;
        int weirdState = state & (state >> 7) % period;
        return -1+2*(double) weirdState/period;

    }
}
