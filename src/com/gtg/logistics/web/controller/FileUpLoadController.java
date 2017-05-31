package com.gtg.logistics.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gtg.logistics.daomain.Customer;
import com.gtg.logistics.util.ExportExcelUtil;
import com.gtg.logistics.util.ReadExcel;

@Controller
@RequestMapping("/reports")
public class FileUpLoadController {

	
	@RequestMapping("/topage")
	public String touploadPage() {
		return "fileupload";
	}

		@RequestMapping("/upload")
		@ResponseBody
		public List<Map<String,Object>> uploadFile( @RequestParam("fileImg") MultipartFile file){
		        ReadExcel readExcel=new ReadExcel(file);
		        //参数0决定从第几行开始获取数据一行
		         List<Map<String,Object>> list = readExcel.getExcelInfo(0);
		         System.out.println(list);
		        return list;
		}
	

	


}
