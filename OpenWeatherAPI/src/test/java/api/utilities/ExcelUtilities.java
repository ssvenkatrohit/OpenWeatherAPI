package api.utilities;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilities {

	static String excelFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\testData.xlsx";
    static String testDataSheet = "test_data"; 
    static String apiSheet ="API_Key";
    
    private static String key;
    
    
    
    	
	public static String getAPI_Key() {
				
			    FileInputStream fis;
				try {
					fis = new FileInputStream(excelFilePath);
				
		        	
		             Workbook workbook = WorkbookFactory.create(fis); 

		            Sheet sheet = workbook.getSheet(apiSheet);
		            if (sheet == null) {
		                throw new IllegalArgumentException("Api_Key sheet not found");
		            }

		            
		            Row row = sheet.getRow(1);
		            if(row.getCell(0)==null) {
		            	throw new Exception("API key not found");
		            }
		            key =  row.getCell(0).toString();
		            
		}
				catch(Exception e) {
					
				}
				return key;
		}
	
	
	    /** Type either "fiveDayForecast" or "currentWeather" to get the
	     * list from testData
	     * 
	     * 
	     * 
	     */	
		
	    public static List<String> getList(String columnName)  {
	    	
	    	
	    	
	        
	    	List<String> list = new ArrayList();
	        FileInputStream fis;
			try {
				fis = new FileInputStream(excelFilePath);
			
	        	
	             Workbook workbook = WorkbookFactory.create(fis); 

	            Sheet sheet = workbook.getSheet("test_data");
	            if (sheet == null) {
	                throw new IllegalArgumentException("column not found in testData sheet");
	            }

	            // Iterate through rows
	            Row header = sheet.getRow(0);
	            int targetColumnIndex=-1;
	            for (Cell cell : header) {
	                if (cell.getStringCellValue().equals(columnName)) {
	                    targetColumnIndex = cell.getColumnIndex();
	                    break;
	                }
	            }
	             for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	                  Row row = sheet.getRow(rowIndex);
	                  if (row.getCell(targetColumnIndex) != null) {
	                  Cell cell = row.getCell(targetColumnIndex);
	                  list.add(cell.toString());
	           
	        } 
	        }
	             
			} catch (IOException | EncryptedDocumentException e) {
				
				e.printStackTrace();
			}
			return list;
	          
	    }
	    public static void writeListToExcel(String filePath, List<String> dataList) throws IOException {
	        // Create a new Workbook
	    	  //FileOutputStream fileOut = new FileOutputStream(filePath);
		            
		        
	    	XSSFWorkbook workbook = new XSSFWorkbook(); // true parameter indicates create a new workbook

	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("Sheet1");
	        Row row =sheet.createRow(0);
	        Cell cell=row.createCell(0); 
	        cell.setCellValue("API_Data");
	        // Iterate through the list and write to the Excel file
	        for (int i = 1; i <= dataList.size(); i++) {
	            row = sheet.createRow(i); // Create a new row
	            cell = row.createCell(0); // Create a new cell in the first column
	            cell.setCellValue(dataList.get(i-1)); // Set the cell value from the list
	        }
	        

	        // Write the workbook to a file
	        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	            workbook.write(fileOut);
	        }
	        System.out.println("data is written to output");

	        // Close the workbook
	        workbook.close();
	    }
	}



