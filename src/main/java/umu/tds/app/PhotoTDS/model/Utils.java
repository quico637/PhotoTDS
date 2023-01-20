package umu.tds.app.PhotoTDS.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
			return null;
		}

	}

	public static String DateToStringNoHour(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat(FORMAT);
		String strDate = dateFormat.format(fecha);
		return strDate;
	}
	
	
	public static boolean isHashtag(String s) {
		if((s == null) || (s.length() == 0))
			return false;
		return s.charAt(0) == '#';
	}
	
	public static List<String> getHashTagFromFinder(String text) {
//		List<HashTag> lh = new LinkedList<>();
		List<String> lh = new LinkedList<>();
		
		String[] listaPalabras = text.split(" "); 
		for(String palabra : listaPalabras) {
			
			if(Utils.isHashtag(palabra)) {
//				HashTag has = HashTag.createHashtag(palabra);
//				if(has != null)
//					lh.add(has);
				lh.add(palabra);
			}
		}
		return lh;
	}

}

 