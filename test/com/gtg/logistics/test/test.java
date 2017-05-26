package com.gtg.logistics.test;

import java.util.HashMap;
import java.util.Map;

import com.gtg.logistics.daomain.Customer;
import com.gtg.logistics.util.CommonTableInsert;

public class test {
	
	
   public static void main(String[] args) {
	//   Map<String, String> map=new HashMap<String, String>();
	  // map.put("Customer1", "Customer1");
	  // CommonTableInsert.insertObject(null, new Customer(), map);
	  Threaddome threaddome = new Threaddome();
	   new Thread(threaddome).start();
	   while(true){
		   
			   if(threaddome.isFalg()){
				   System.out.println("-----------");
				   break;
			   }
		  
		  
	   }
   }
}


class Threaddome implements Runnable{
	private volatile boolean falg=false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		falg=true;
		System.out.println("falg=" +isFalg());
		
	}
	
	public boolean isFalg() {
		return falg;
	}


	public void setFalg(boolean falg) {
		this.falg = falg;
	}

	
}  