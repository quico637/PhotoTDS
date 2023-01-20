package umu.tds.app.PhotoTDS.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import umu.tds.app.PhotoTDS.model.User;

public class ExcelGenerator {
	
	public static void generateExcel(User u, String path, List<User> usuariosSeguidores) {
		try {
			// declare file name to be create
			String filename = path;
			// creating an instance of HSSFWorkbook class
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("Users");
			// creating the 0th row using the createRow() method
			HSSFRow rowhead = sheet.createRow((short) 0);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(1).setCellValue("username");
			rowhead.createCell(2).setCellValue("e-mail");
			rowhead.createCell(3).setCellValue("presentation");

			int n = 1;
			for (User f : usuariosSeguidores) {
				// creating the 1st row
				HSSFRow row = sheet.createRow((short) n);
				// inserting data in the first row
				row.createCell(1).setCellValue(f.getUsername());
				row.createCell(2).setCellValue(f.getEmail());
				row.createCell(3).setCellValue(f.getDescripcion());
				n++;
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook
			workbook.close();
			// prints the message on the console
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
