package com.userdefinedlibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.*;

public class ExcelReadWrite {
	public static String[] data;

	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFCell cell;

	//READS EXCEL FILE BASED ON SHEETNAME
	public static void readExcelData(String sheetName) throws Exception {

		File src = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\datatables\\BookingData.xlsx");
		FileInputStream file = new FileInputStream(src);
		wb = new XSSFWorkbook(file);

		sheet = wb.getSheet(sheetName);
		XSSFRow row = sheet.getRow(0);

		int colNum = row.getLastCellNum();
		data = new String[11];

		for (int i = 0; i < colNum - 2; i++) {

			cell = sheet.getRow(1).getCell(i);

			switch (cell.getCellType()) {
			case STRING:
				data[i] = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
					data[i] = dateFormat.format(cell.getDateCellValue());
				} else
					data[i] = Double.toString(cell.getNumericCellValue());
				break;

			default:
				data[i] = "";
			}
			
		}

		wb.close();

	}

	//WRITES DATA INTO THE EXCEL FILE BASED ON SHEETNAME 
	public static void excelWriteData(String sheetName, String title, String result) {
		File src = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\datatables\\BookingData.xlsx");
		FileInputStream file;
		try {
			file = new FileInputStream(src);

			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet;
			XSSFCell Titlecell;
			switch (sheetName) {
			case "cab_booking":
				sheet = wb.getSheet(sheetName);
				Titlecell = sheet.getRow(1).createCell(8);
				XSSFCell LowPricecell = sheet.getRow(1).createCell(9);
				Titlecell.setCellValue(title);
				LowPricecell.setCellValue(result);
				break;

			case "giftcardsValid":
				sheet = wb.getSheet(sheetName);
				Titlecell = sheet.getRow(1).createCell(10);
				XSSFCell resultcell = sheet.getRow(1).createCell(11);
				Titlecell.setCellValue(title);
				resultcell.setCellValue(result);
				break;

			case "giftcardsInvalid":
				sheet = wb.getSheet(sheetName);
				Titlecell = sheet.getRow(1).createCell(10);
				XSSFCell Inv_resultcell = sheet.getRow(1).createCell(11);
				Titlecell.setCellValue(title);
				Inv_resultcell.setCellValue(result);
				break;

			case "hotel_booking":
				sheet = wb.getSheet(sheetName);
				Titlecell = sheet.getRow(1).createCell(1);
				Titlecell.setCellValue(title);
				for (int i = 1; i <= Integer.parseInt(result); i++) {
					XSSFRow row = sheet.getRow(i);
					if (row == null)
						row = sheet.createRow(i);
					XSSFCell NoOfAdults = row.createCell(2);
					NoOfAdults.setCellValue(i);
				}
				break;

			}

			FileOutputStream output = new FileOutputStream(src);
			wb.write(output);
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
