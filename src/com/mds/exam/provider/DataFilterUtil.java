package com.mds.exam.provider;

import java.util.ArrayList;
import java.util.List;


public class DataFilterUtil {

    public static interface FilterFunction<T> {
        public boolean isSelected(T t);
    }
    // filter the data as the parameter and function
    public static <T> List<T> filter(List<T> list, FilterFunction<T> function) {
        List<T> rList = new ArrayList<T>();
        if (list == null) {   // no list ,return the rlist
            return rList;
        }
        if (function == null) { // no function as a list,robustness
            return list;
        }
        for (T t : list) {
            if (function.isSelected(t)) { //isSelected is not implement? how to filter??
                rList.add(t);
            }
        }
        return rList;	
    }
}

