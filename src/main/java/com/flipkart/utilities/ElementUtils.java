package com.flipkart.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
	
	WebDriver driver;
	private static JavascriptExecutor executor;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static XSSFRow row1;
	private static int totCols,totRows; 
	private static File file;
	private static FileInputStream fis;
	private static Object[][] excelData;
	private static DataFormatter formatter;
	private static XSSFCell cell;
	private static Robot rb;
	
	public ElementUtils(WebDriver driver) {
		super();
		this.driver = driver;
	} 
	
	public static void chooseValueFromDropDown(List<WebElement> dropdownOptions, String optionToSelect) throws InterruptedException {
		
		for(WebElement e: dropdownOptions) {
			 if(e.getText().toLowerCase().trim().contains(optionToSelect.toLowerCase())) {
				 System.out.println("matched value in dropdown or list: "+e.getText());
				 e.click();
				 break;
			 }
				
		}
	}
	
	public static boolean verifyValueOnPage(List<WebElement> pageValuesList, String textToVerify) throws InterruptedException {
		
		boolean flag = false;
		
		for(WebElement e: pageValuesList) {
			if(e.getText().contains(textToVerify)) {
				System.out.println("found "+e.getText()+ "on page!");
				flag=true;
				break; 
			}
		}
		return flag;
	}
	
	public static Object[][] loadDataFromExcelFile(String sheetName, String fileLocation) throws IOException, EncryptedDocumentException, InvalidFormatException {
		
		file = new File(fileLocation);
		fis = new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
		//Workbook wb2 = WorkbookFactory.create(fis);
		//Sheet sheet2 = wb2.getSheet(sheetName);
		//sheet2.getRow(0).getCell(1).setCellType(CellType.STRING);
		sheet=wb.getSheet(sheetName);
		//XSSFSheet sheet1=wb.getSheetAt(1);
		row1= sheet.getRow(1);
		totCols = row1.getLastCellNum();
		totRows = ((sheet.getLastRowNum()) - (sheet.getFirstRowNum()));
		
		excelData = new Object[totRows][totCols];
		formatter = new DataFormatter();
		
		for(int i=0;i<totRows;i++) {
			
			System.out.println("Row "+(i+1)+" data is: ");
			for(int j=0;j<totCols;j++) {
				
				cell = sheet.getRow(i+1).getCell(j);
				excelData[i][j] = formatter.formatCellValue(cell);
				System.out.println(excelData[i][j]+",");
			}
			System.out.println();
		}
		return excelData;
	}
	
	
	  public static void sendKeysUsingJS(WebElement element, WebDriver driver,
	  String text) {
	  
		  System.out.println(element.getTagName());
		  System.out.println(text);
	  executor = (JavascriptExecutor) driver;
	  executor.executeScript("arguments[0].value='"+text+"';", element); }
	 
	
	public static void clickButtonUsingJS(WebElement element, WebDriver driver) {
		
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		
	}
	
	public static void scrollToPageEnd(WebDriver driver) {
			
			//scroll down to end of page before collecting results
					executor = (JavascriptExecutor) driver;
					
					try {
					    long lastHeight=((Number)executor.executeScript("return document.body.scrollHeight")).longValue();
					    while (true) {
					        executor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
					        Thread.sleep(2000);
	
					        long newHeight = ((Number)executor.executeScript("return document.body.scrollHeight")).longValue();
					        if (newHeight == lastHeight)
					            break;
					        lastHeight = newHeight;
					    }
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
		}

	public static void chooseColorfromPicker(List<WebElement> colourPickerList, String colourCode) {

			for(WebElement e: colourPickerList) {
				if(e.getAttribute("style").trim().contains(colourCode)) {
					e.click();
					break;
				}
			}
		
	}

	public static void uploadFile(WebElement fileUploadBtn, String filePath, WebDriver driver) throws AWTException, InterruptedException {
		
		 //rb = new Robot();
		 
		 System.out.println(fileUploadBtn.isEnabled());
		 System.out.println(fileUploadBtn.getAttribute("type"));
		 Thread.sleep(2000);
		 //fileUploadBtn.click();
		 fileUploadBtn.sendKeys(filePath);
		 //ElementUtils.clickButtonUsingJS(fileUploadBtn, driver);
		 //ElementUtils.sendKeysUsingJS(fileUploadBtn, driver, filePath);
		 Thread.sleep(10000);
		 
/*		 rb.setAutoDelay(10000); //same as Thread.sleep(2000)
		 
	//actions to simulate CTRL+C to Clipboard 
		 StringSelection stringSelection = new StringSelection(filePath);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		 
	//actions to simulate CTRL+V using Robot class
		 rb.keyPress(KeyEvent.VK_CONTROL);
		 rb.keyPress(KeyEvent.VK_V);
		 
	//key-release simulation	 
		 rb.keyRelease(KeyEvent.VK_CONTROL);
		 rb.keyRelease(KeyEvent.VK_V);
		 
		 rb.setAutoDelay(10000);
	
	//simulate press and release of Enter button
		 rb.keyPress(KeyEvent.VK_ENTER);
		 rb.keyRelease(KeyEvent.VK_ENTER); 		 */
		
	}
	
}
