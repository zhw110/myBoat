package com.mds.exam.provider.manager.impl;

import com.alibaba.fastjson.JSON;
import com.mds.exam.provider.DataFilterUtil;
import com.mds.exam.provider.common.PageQuery;
import com.mds.exam.provider.common.Result;
import com.mds.exam.provider.dataobject.DataDO;
import com.mds.exam.provider.manager.IUserDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class UserDataManager implements IUserDataManager {


    @Override
    public Result<PageQuery<DataDO>> query(final PageQuery<DataDO> query) {
        List<DataDO> list = readData(); // read the csv data into list DataDO.

        final Boolean isAsc = query.getSort().equals("asc");// input query sort para

        Collections.sort(list, new Comparator<DataDO>() {
            @Override
            public int compare(DataDO a, DataDO b) {
                if (a.getVesselname() == null) {
                    return isAsc ? 1 : -1;
                }
                if (b.getVesselname() == null) {
                    return isAsc ? -1 : 1;
                }
                if (isAsc) {
                    return a.getVesselname().compareTo(b.getVesselname());
                } else {
                    return b.getVesselname().compareTo(a.getVesselname());
                }
            }
        });

        final DataDO q = query.getQueryObject();
        List<DataDO> rList = DataFilterUtil.filter(list, new DataFilterUtil.FilterFunction<DataDO>() {
            @Override
            public boolean isSelected(DataDO d) {
                if (q == null) {
                    return true; // default is Ture ,output the all results
                } else {
                	//here list some other Parameters to filter the database !!!
//                    if (q.getType() != null) {
//                        if (!q.getType().equals(d.getType())) {
//                            return false;
//                        }
//                    }
//                    if (q.getImo() != null) {
//                        if (q.getImo().equals(d.getImo())) {
//                            return false;
//                        }
//                    }
//                    if (q.getLatitude() != null) {
//                        if (!q.getLatitude().equals(d.getLatitude())) {
//                            return false;
//                        }
//                    }
//                    if (q.getMmsi() != null) {
//                        if (!q.getMmsi().equals(d.getMmsi())) {
//                            return false;
//                        }
//                    }
                    if (q.getVesselname() != null) { // check the name in the list
                        if (d.getVesselname() != null && d.getVesselname().indexOf(q.getVesselname()) < 0) {
                            return false;
                        }
                    }
                }

                return true;
            }
        });

        //Divide the page, if there are too much content in one page
        
//        query.setTotalSize(rList.size());
//        List<DataDO> listFinal = new ArrayList<DataDO>();
//        int startIdx = (query.getCurrentPage() - 1) * query.getSize();
//        
//        if (startIdx < 0) {
//            startIdx = 0;
//        }
//        if (startIdx < rList.size()) {
//            for (int i = startIdx, total = 1; (i < rList.size() || total == query.getSize()); i++, total++) {
//                listFinal.add(rList.get(i));
//            }
//        }
//        query.setQueryResult(listFinal);
        
        
        query.setQueryResult(rList);
        Result<PageQuery<DataDO>> r = new Result<PageQuery<DataDO>>();
        r.setSuccess(true);
        r.setModel(query);
        return r;
    }


    List<DataDO> readData() { // read all the CVS data into the List DataDO
        List<DataDO> list = new ArrayList<DataDO>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("vessels.csv");
        if (is == null) { //robust
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|"); 
                DataDO dataDO = new DataDO();
                dataDO.setVesselname(arr[0].trim()); //Calling a copy of the string object, 
                dataDO.setImo(arr[1].trim());        //but all of the starting and ending spaces are removed
                dataDO.setMmsi(arr[2].trim());
                dataDO.setType(arr[3].trim());
                dataDO.setLongitude(arr[4].trim());
                dataDO.setLatitude(arr[5].trim());
                list.add(dataDO);
            }
        } catch (IOException e) {
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new UserDataManager().readData()));
    }
    
}
