import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HuffmanDecoder {
    public static void main(String[] args)
    {
        ObjectReader ob=new ObjectReader(args[0]);
        Object trie=ob.readObject();
        Object bits=ob.readObject();
        BinaryTrie from=(BinaryTrie) trie;
        BitSequence bitSequence=(BitSequence) bits;
        List<Character> text=new ArrayList<>();
        while (bitSequence.length()!=0)
        {
            Match match=from.longestPrefixMatch(bitSequence);
            text.add(match.getSymbol());
            bitSequence=bitSequence.allButFirstNBits(match.getSequence().length());
        }
        char[] c=new char[text.size()];
        for(int i=0;i<text.size();i++)
            c[i]=text.get(i);
        FileUtils.writeCharArray(args[1], c);
    }
}
