import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols)
    {
        Map<Character,Integer> map=new HashMap<>();
        for (int n=0;n<inputSymbols.length;n++)
        {
            char c=inputSymbols[n];
            if(map.containsKey(c))
                map.put(c,map.get(c)+1);
            else
                map.put(c,1);
        }
        return map;
    }
    public static void main(String[] args)
    {
        char[] allchar=FileUtils.readFile(args[0]);
        Map<Character,Integer> ft=buildFrequencyTable(allchar);
        BinaryTrie bt=new BinaryTrie(ft);
        ObjectWriter ow=new ObjectWriter(args[0]+".huf");
        ow.writeObject(bt);
        Map<Character,BitSequence> map=bt.buildLookupTable();
        List<BitSequence> list=new ArrayList<>();
        for(int i=0;i<allchar.length;i++)
        {
            list.add(map.get(allchar[i]));
        }
        BitSequence allbit=BitSequence.assemble(list);
        ow.writeObject(allbit);
    }
}
