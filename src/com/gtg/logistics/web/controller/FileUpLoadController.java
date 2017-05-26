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
		public String uploadFile( @RequestParam("fileImg") MultipartFile file){
		        ReadExcel readExcel=new ReadExcel(file);
		         List<Map<Integer,Object>> list = readExcel.getExcelInfo(1);
		         System.out.println(list);
		        return null;
		}
	
	
	@RequestMapping("/upload2")
	@ResponseBody
	public String uploadFile2(@RequestParam("gameId") String ename,  @RequestParam(value="fileImg") MultipartFile[] files,
			HttpServletRequest request) {

		String uploadPath = request.getServletContext().getRealPath("/upload");
		File upload = new File(uploadPath);
		if (!upload.exists()) {
			upload.mkdir();
		}
		
		List<String> filepaths = new ArrayList<>();
		
		for(MultipartFile file:files){
			
			if(!file.isEmpty()){
				String newFileName = getNewFile(file.getOriginalFilename());
				try {
					FileCopyUtils.copy(file.getBytes(), new File(uploadPath, newFileName));
					System.out.println(uploadPath);
					filepaths.add(newFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		  String res = "";
	        return res;
	}
	
	@RequestMapping("/testHttpMessageDown")
	public ResponseEntity<byte[]> download() throws IOException {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", "1");
		map.put("sex", "男");
		map.put("name", "xiaohei");
		list.add(map);
		String [] columnNames={"id","姓名","性别"};
		String [] keys={"id","name","sex"};
		Workbook book = ExportExcelUtil.createWorkBook(list,keys,columnNames);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		book.write(os);;
        byte[] b = os.toByteArray();
        os.close();

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attchement;filename=xm.xls");
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(b, headers, statusCode);
	    return entity;
	}
	
	public String getNewFile(String fileName) {

		String newFileName = "";
		if (fileName != null) {

			String extension = fileName.substring(fileName.lastIndexOf("."));
			newFileName = UUID.randomUUID().toString().replaceAll("-", "") + extension;
		}
		return newFileName;

	}

}
