import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    private static int max;
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sorted=new String[asciis.length];
        sorted= Arrays.copyOf(asciis,asciis.length);
        max=Integer.MIN_VALUE;
        for(String string:asciis)
            max=max>string.length()?max:string.length();
        for(int i=1;i<=max;i++)
            sortHelperLSD(sorted,i);
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] count=new int[256];
        for(String s:asciis)
        {
            if(max-s.length()>=index)
                count[0]++;
            else {
                int j=(int)(s.charAt(max-index));
                count[j]++;
            }
        }
        int pos=0;
        int[] start=new int[256];
        for(int j=0;j<256;j++)
        {
            start[j]=pos;
            pos+=count[j];
        }
        String[] sort=new String[asciis.length];
        for(String s:asciis)
        {
            if(max-s.length()>=index) {
                sort[start[0]] = s;
                start[0]++;
            }
            else {
                sort[start[(int) (s.charAt(max-index))]] = s;
                start[(int) (s.charAt(max-index))]++;
            }


        }
        int x=0;
        for(String s:sort)
            asciis[x++]=s;
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
