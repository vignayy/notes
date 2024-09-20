package jaava.collectns;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Iterator;


public class SetIntro {
    public static void main(String[] args) {

//    	Set<Integer> nums=new HashSet<Integer>();
//    	Set<Integer> nums=new TreeSet<Integer>();
        Collection<Integer> nums = new TreeSet<Integer>();
        nums.add(36);
        nums.add(18);
        nums.add(9);
        nums.add(27);
        //nums.add("45");

        Iterator<Integer> values = nums.iterator();
        while (values.hasNext())
            System.out.println(values.next());

        System.out.println(nums);

//    	for(int n:nums)
//    	{
//    		System.out.println(n);
//   	}
    }
}






