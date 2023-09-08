import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";
    static TrieNode wordtrie;
    static int w;
    static int h;
    static char[][] table;
    static List<String> toadd;
    static boolean[][] visited;
    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE
        wordtrie=new TrieNode('a');
        File file=new File(dictPath);
        if(!file.exists())
            throw new IllegalArgumentException("error dic path");
        if(k<=0)
            throw new IllegalArgumentException("k must >0");
        In dic=new In(dictPath);
        String[] wordinfile=dic.readAllStrings();
        for(int i=0;i<wordinfile.length;i++)
        {    String word=wordinfile[i];
            addword(word);
        }

        In charstream=new In(boardFilePath);
        int width=0;
        int height=0;
        String[] wordtable=charstream.readAllStrings();
        width=wordtable[0].length();
        height=wordtable.length;
        table=new char[height][width];
        for(int i=0;i<wordtable.length;i++)
            if(wordtable[i].length()!=width)
                throw new IllegalArgumentException("not rectangular");
            else
                for(int j=0;j<width;j++)
                   table[i][j]=wordtable[i].charAt(j);

        visited=new boolean[height][width];
        charstream=new In(boardFilePath);
        w=width;
        h=height;


        PriorityQueue<comparestring> finedword=new PriorityQueue<>();
        toadd=new ArrayList<>();
        List<String> finalstring=new ArrayList<>();
        for(int x=0;x<h;x++)
            for (int y=0;y<w;y++)
            {
                findword("",wordtrie,x,y);

            }
        for(String s:toadd)
        {
            comparestring st=new comparestring(s);
            if(!finedword.contains(st))
                finedword.add(st);
        }
        for(int i=0;i<k;i++)
        {
            if(finedword.isEmpty())
                break;
            comparestring order=finedword.remove();
            finalstring.add(order.string());
        }
        return finalstring;
    }
    private static void findword(String s,TrieNode pre,int x,int y)
    {
        s=s+table[x][y];
        visited[x][y]=true;
        pre=pre.getchild(table[x][y]);
        if(pre==null) {
            visited[x][y]=false;
            return;
        }
        if(pre.isEndOfWord())
            toadd.add(s);
        if(x!=0 && y!=0 && !visited[x - 1][y - 1])
            findword(s,pre,x-1,y-1);
        if(x!=0 && !visited[x-1][y])
            findword(s,pre,x-1,y);
        if(x!=0 && y!=w-1 &&!visited[x-1][y+1])
            findword(s,pre,x-1,y+1);
        if(y!=0 && !visited[x][y-1])
            findword(s,pre,x,y-1);
        if(y!=w-1 && !visited[x][y+1])
            findword(s,pre,x,y+1);
        if(x!=h-1 && y!=0 && !visited[x+1][y-1])
            findword(s,pre,x+1,y-1);
        if(x!=h-1 && !visited[x+1][y])
            findword(s,pre,x+1,y);
        if(x!=h-1 && y!=w-1 && !visited[x+1][y+1])
            findword(s,pre,x+1,y+1);
        visited[x][y]=false;
    }


    private static void addword(String string)
    {
        TrieNode track=wordtrie;
        for(int i=0;i<string.length();i++)
        {
            char c=string.charAt(i);
            if(i==string.length()-1)
            {
                track.addchild(c);
                track.getchild(c).endword();
            }
            else
            {
                track.addchild(c);
                track=track.getchild(c);
            }
        }
    }
    public static void main(String[] args) {
        Stopwatch stopwatch=new Stopwatch();
        System.out.println(Boggle.solve(7,"exampleBoard.txt").toString());
        System.out.println(stopwatch.elapsedTime());
    }
}


