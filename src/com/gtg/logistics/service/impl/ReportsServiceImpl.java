package com.gtg.logistics.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtg.logistics.dao.ReportsMapper;
import com.gtg.logistics.service.ReportsService;
@Service
public class ReportsServiceImpl implements ReportsService{
	
	@Autowired
	private  ReportsMapper dao;
	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return dao.getCount();
	}

}
