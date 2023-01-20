package umu.tds.app.PhotoTDS.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.app.PhotoTDS.model.User;

public class PDFGenerator {
	
	
	public static void generatePDF(String u, String path, List<User> usuariosSeguidores) {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document.open();
		PdfPTable table = new PdfPTable(4);

		table.addCell("User-Nº");
		table.addCell("username");
		table.addCell("e-mail");
		table.addCell("description");

		int n = 1;
		for (User f : usuariosSeguidores) {
			table.addCell(Integer.toString(n));

			table.addCell(f.getUsername());
			table.addCell(f.getEmail());
			table.addCell(f.getDescripcion());
			n++;
		}

		try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		
	}
	
}
