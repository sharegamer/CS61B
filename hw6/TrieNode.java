public class TrieNode {
    private char c;
    private TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode(char c) {
        this.children = new TrieNode[53];
        this.isEndOfWord = false;
        this.c=c;
    }

    public char getC() {
        return c;
    }


    public TrieNode getchild(char c){
        if(c-97>=0 && c-122<=0 )
            return children[c-96];
        else if (c-65>=0 && c-90<=0) {
            return children[c-65+27];
        }
        else
            return children[0];
    }
    public void addchild(char c){
        if(c-97>=0 && c-122<=0 ) {
            if(children[c-96]==null)
                children[c - 96] = new TrieNode(c);
        }
        else if (c-65>=0 && c-90<=0) {
            if(children[c-38]==null)
                children[c-65+27]=new TrieNode(c);
        }
        else {
            if(children[0]==null)
                children[0] = new TrieNode(c);
        }
    }

    public void endword(){
        isEndOfWord=true;
    }
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}
