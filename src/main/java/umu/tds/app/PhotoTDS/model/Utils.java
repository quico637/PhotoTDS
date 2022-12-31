package umu.tds.app.PhotoTDS.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	private final static String FORMATHOUR = "d MMM y HH:mm:ss";
	private final static String FORMAT = "d MMM y";

	public static Date StringToDate(String fecha) {
		try {
			return (Date) new SimpleDateFormat(FORMATHOUR).parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String DateToString(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat(FORMATHOUR);
		String strDate = dateFormat.format(fecha);
		return strDate;
	}
	
	public static Date StringToDateNoHour(String fecha) {
		try {
			return (Date) new SimpleDateFormat(FORMAT).parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String DateToStringNoHour(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat(FORMAT);
		String strDate = dateFormat.format(fecha);
		return strDate;
	}
	
	
//	public static String listToString(List<String> lista) {
//		String stringLista = "";
//		for(String s : lista) {
//			stringLista = stringLista = "&&" + s;
//		}
//		return stringLista;
//	}

}

 