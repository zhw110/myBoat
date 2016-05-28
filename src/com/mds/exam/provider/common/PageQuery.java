package com.mds.exam.provider.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PageQuery<T> { // T= DataDO is the format of the Json,just one item
	
	private T queryObject;  // set for the name filter
	private String sort;    // set for the ranking sequence
    
    /*results*/
    private List<T> queryResult;
    
    
	// for divide the page
    private static int DEFAULT_PAGE_SIZE = 20;  // the total numbers of each web page
    private int currentPage = 0; 				// get the current page of the web page
    private int size = DEFAULT_PAGE_SIZE; 		//design a size 
    private int totalSize;
    private int pageNum;
    
    private Map<String, Object> extra = new HashMap<String, Object>();

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    
    public T getQueryObject() {// return the Query Object
        return queryObject;
    }

    public void setQueryObject(T queryObject) {
        this.queryObject = queryObject;
    }

    public List<T> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(List<T> queryResult) {
        this.queryResult = queryResult;
    }
    


    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }



    public int getCurrentPage() {
        if (currentPage == 0) {
            return 1;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartNum() {// start number of the current page
        return (getCurrentPage() - 1) * size;
    }
    
    public int getPageNum() {// how many pages the web need?
        return (getTotalSize() + size - 1) / size;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getSize() { // used in some pages
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    public void addExtra(String type, Object val) {
//        extra.put(type, val);
//    }

 
}
