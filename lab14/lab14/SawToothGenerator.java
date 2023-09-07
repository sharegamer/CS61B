package lab14;
import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;
public class SawToothGenerator implements Generator{
    private int state;
    private int period;
    public SawToothGenerator(int period)
    {
        state=0;
        this.period=period;
    }
    public double next()
    {
        state+=1;
        state=state%period;
        return -1+2*(double) state/period;

    }

}
