package com.gtg.logistics.util;

import java.lang.reflect.Field;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Map;  
import java.util.Set;  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;  

/** 
 * @说明 动态插入数据和创建表 
 * @author xiaoqin
 */  
public class CommonTableInsert {  
    public static Log logger = LogFactory.getLog(CommonTableInsert.class);  
    /** 
     * 入口方法 
     * @param tableName 表前缀 
     * @param dateFormat 格式化方法 
     * @param obj 保存的对象 
     * @param noCol 过滤字段名 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static int insertObject(String tableName,Object obj, Map noCol){  
    	if (tableName==null||tableName.equals(null)) {
    		tableName=obj.getClass().getSimpleName();
		}
        int re = 0;          
        try {     
            JdbcTemplate jt =(JdbcTemplate) new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean("jdbcTemplate");
            
            re = createTable(jt,tableName,obj, noCol); // 动态创建表               
        } catch (Exception e) {     
            logger.error("公用方法插入数据入口方法错误", e);  
        }            
        return re;     
    }  
    /**   
     * 根据表名称 和 实体属性 创建一张表 
     * @param tableName   
     * @param obj 具体生成什么样的表看该对象 
     */    
    @SuppressWarnings("unchecked")  
    private static int createTable(JdbcTemplate jt,String tableName,Object obj, Map noCol){    
        StringBuffer sb = new StringBuffer("");     
        sb.append("CREATE TABLE " + tableName + " (");     
        sb.append(" id NUMBER(11) NOT NULL primary key,");  
        Class c = obj.getClass();  
        Field field[] = c.getDeclaredFields();  
        for (Field f : field) {  
        	System.out.println(f);
            if(noCol==null||noCol.get(f.getName()) == null){  
                String type = f.getType().toString();  
                if(type.equals("class java.lang.String")){// Str  
                     sb.append("" + f.getName() + " varchar(100) DEFAULT NULL,");     
                }else if(type.equals("int") || type.equals("class java.lang.Integer")){// int  
                    sb.append("" + f.getName() + " NUMBER(11) DEFAULT NULL,");     
                }else if(type.equals("double") || type.equals("class java.lang.Double")){// double  
                    sb.append("" + f.getName() + " double DEFAULT NULL,");     
                }  
            }  
        }      
        sb.deleteCharAt(sb.length()-1);  
        sb.append(")"); 
        System.out.println(sb);
        try {     
            jt.update(sb.toString());     
            return 1;     
        } catch (Exception e) {  
            logger.error("公用方法生成表时错误", e);  
            logger.error("公用方法生成表语句：" + sb.toString());  
        }     
        return 0;     
    }   
}  