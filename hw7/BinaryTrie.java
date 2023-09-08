import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinaryTrie implements Serializable {
    public Map<Character,BitSequence> map=new HashMap<>();
    private class Trie{
        Trie leftnode=null;
        Trie rightnode=null;
        int weight=0;
        char c;
        boolean isleaf;
        public Trie(char c,int w)
        {
            this.c=c;
            weight=w;
            isleaf=true;
        }
        public Trie()
        {
            isleaf=false;
        }
        public void setLeftnode(Trie left)
        {
            weight+=left.weight;
            leftnode=left;
        }
        public void setRightnode(Trie right)
        {
            weight+=right.weight;
            rightnode=right;
        }

    }
    private Trie connect(Trie left,Trie right)
    {
        Trie newtrie=new Trie();
        newtrie.setLeftnode(left);
        newtrie.setRightnode(right);
        return newtrie;
    }
    private void createmap(Trie root,String s)
    {
        if(root.isleaf)
            map.put(root.c,new BitSequence(s));
        if (root.leftnode!=null) {
            createmap(root.leftnode,s+'0');}
        if (root.rightnode!=null) {
            createmap(root.rightnode,s+'1');
        }
    }
    public  BinaryTrie()
    {
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable)
    {
        Set<Trie> set=new HashSet<>();
        Set<Character> cset=frequencyTable.keySet();
        for(char ch:cset)
        {
            Trie toadd=new Trie(ch,frequencyTable.get(ch));
            set.add(toadd);
        }

        Trie m1=null,m2=null;

        while(set.size()!=1)
        {
            int min2=Integer.MAX_VALUE;
            int min1=Integer.MAX_VALUE;
            for(Trie t:set)
            {
                if(t.weight<min1)
                {
                    if(min1<min2)
                    {
                        min2=min1;
                        m2=m1;
                    }
                    min1= t.weight;
                    m1=t;
                } else if (t.weight<min2) {
                    min2=t.weight;
                    m2=t;
                }
            }
            Trie newnode=connect(m1,m2);
            set.add(newnode);
            set.remove(m1);
            set.remove(m2);
        }
        for(Trie t:set)
            createmap(t,"");
    }
    public Match longestPrefixMatch(BitSequence querySequence)
    {
        for(int n=1;n<=querySequence.length();n++)
            for(char c:map.keySet())
                if(map.get(c).equals(querySequence.firstNBits(n)))
                    return new Match(map.get(c),c);
        return null;
    }

    public Map<Character, BitSequence> buildLookupTable()
    {
        return map;
    }
}
