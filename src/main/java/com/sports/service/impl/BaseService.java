package com.sports.service.impl;

import com.sports.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    protected IBaseDao baseDao;

}
