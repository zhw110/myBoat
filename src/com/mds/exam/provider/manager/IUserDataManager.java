package com.mds.exam.provider.manager;

import com.mds.exam.provider.common.PageQuery;
import com.mds.exam.provider.common.Result;
import com.mds.exam.provider.dataobject.DataDO;

public interface IUserDataManager { 
    public Result<PageQuery<DataDO>> query(PageQuery<DataDO> query);// only define a method:page query
}
//easy for extend the functions