import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void test()
    {

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2=new ArrayDequeSolution<>();
        String log="";
        int n;
        int num;
        for (int i = 0; i < 100; i += 1) {
             if(sad1.isEmpty())
             {
                 n= StdRandom.uniform(2);
                 num=StdRandom.uniform(100);
                 switch (n) {
                     case 0:
                         log=log+"addFirst("+num+")\n";
                         sad1.addFirst(num);
                         sad2.addFirst(num);
                         break;
                     case 1:
                         log=log+"addLast("+num+")\n";
                         sad1.addLast(num);
                         sad2.addLast(num);
                         break;
                 }
             }
             else
             {
                 n=StdRandom.uniform(4);
                 num=StdRandom.uniform(100);
                 switch (n){
                     case 0:
                         log=log+"addFirst("+num+")\n";
                         sad1.addFirst(num);
                         sad2.addFirst(num);
                         break;
                     case 1:
                         log=log+"addLast("+num+")\n";
                         sad1.addLast(num);
                         sad2.addLast(num);
                         break;
                     case 2:
                         log=log+"removeFirst()\n";
                         assertEquals(log,sad1.removeFirst(),sad2.removeFirst());
                         break;
                     case 3:
                         log=log+"removeLast()\n";
                         assertEquals(log,sad1.removeLast(),sad2.removeLast());
                         break;
                 }
             }
        }


    }
}
