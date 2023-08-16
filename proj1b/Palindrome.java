public class Palindrome {
    public Deque<Character> wordToDeque(String word)
    {
        Deque<Character> que=new LinkedListDeque<Character>();
        int i=0;
        while(i<word.length())
        {
            que.addLast(word.charAt(i));
            i+=1;
        }
        return que;
    }

    public boolean isPalindrome(String word)
    {
        if (word.length()<=1)
            return true;
        if(word.charAt(0)==(word.charAt(word.length()-1)))
        {
            if (word.length()==2)
                return true;
            return isPalindrome(word.substring(1,word.length()-1));
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc)
    {
        if (word.length()<=1)
            return true;
        if(cc.equalChars(word.charAt(0),(word.charAt(word.length()-1))))
        {
            if (word.length()==2)
                return true;
            return isPalindrome(word.substring(1,word.length()-2),cc);
        }
        return false;
    }

}
