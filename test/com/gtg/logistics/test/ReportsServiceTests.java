package com.gtg.logistics.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gtg.logistics.service.ReportsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class ReportsServiceTests {
	private ReportsService service;

	@Autowired
	public void setService(ReportsService service) {
		this.service = service;
	}
   
	
	@Test
	public void testfill(){
		

		System.out.println(service.getCount());
	}
}
