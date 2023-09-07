public class comparestring implements Comparable{
    private String string;
    public comparestring(String s)
    {
        string=s;
    }

    @Override
    public int compareTo(Object o) {
        comparestring middle=(comparestring) o;
        if(string.length()!=middle.length())
            return -string.length()+middle.length();
        else
        {
            return string.compareTo(middle.string());
        }
    }
    public int length()
    {
        return string.length();
    }
    public String string()
    {
        return string;
    }
    @Override
    public boolean equals(Object o)
    {
        comparestring middle=(comparestring) o;
        if(string.equals(middle.string()))
            return true;
        return false;
    }


}
