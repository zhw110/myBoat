package com.mds.exam.provider.manager;

import com.mds.exam.provider.manager.impl.UserDataManager;

public class Managers { 
    // Signal Instance£ºwhole part just one Static dataManager.
	// because just need one manager to control all the data
    private static IUserDataManager dataManager = new UserDataManager();
    // private static IUserDataManager dataManager = new UserDataBaseManager(); // if I design a database program

    public static IUserDataManager getDataManager() {
//        if (dataManager == null) {
//            synchronized (IUserDataManager.class) { //more thirds ,it is for safe problem.
//                if (dataManager == null) {
//                    dataManager = new UserDataManager();
//                }
//            }
//        }
        return dataManager;
    }
}
