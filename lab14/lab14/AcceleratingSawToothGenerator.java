package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int state;
    private int period;
    private double accele;
    public AcceleratingSawToothGenerator(int period,double a)
    {
        state=0;
        this.period=period;
        accele=a;
    }
    public double next()
    {
        state+=1;
        state=state%period;
        if(state==0)
            period*=accele;
        return -1+2*(double) state/period;

    }
}
