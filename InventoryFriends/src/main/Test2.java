package main;

import java.util.*;
import java.io.*;

public class Test2 {

    public static void main(String[] args)
    {

        // create linked hash map instance
        LinkedHashMap<Integer, Integer> lhm
                = new LinkedHashMap<Integer, Integer>();

        // Add mappings
        lhm.put(2, 5);
        lhm.put(4, 3);
        lhm.put(1, 10);
        lhm.put(3, 12);
        lhm.put(5, 6);

        // get the key set
        Set<Integer> keySet = lhm.keySet();

        Integer[] keyArray
                = keySet.toArray(new Integer[keySet.size()]);

        // taking input of index
        Integer index = 2;
        Integer key = keyArray[index - 1];

        // get value from the LinkedHashMap for the key
        System.out.println("Value at index " + index
                + " is : " + lhm.get(key));
    }
}