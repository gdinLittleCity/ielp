package cn.edu.gdin.ilep.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class TimeUtils {
	
	public static final String Time_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String Time_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	
	private static SimpleDateFormat dateFormat = null;
	private static Date date=null;
	static
	{
		date = new Date();
		
	}
	
	/**
	 * format 为格式 例如：yyyy-MM-dd .请使用预定义的格式
	 * 格式默认使用 yyyy-MM-dd
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(String formate){
		
		if(StringUtils.isEmpty(formate))
			dateFormat = new SimpleDateFormat(Time_FORMAT_YYYY_MM_DD);
		
		dateFormat = new SimpleDateFormat(formate);
		
		
		return dateFormat.format(date);
		
	}
	
	public static Date getCurrentTime(){
		return date;
	}
	
	
	public static String time2String(String formate,Date date){
		if(StringUtils.isEmpty(formate)||null==date)
			return "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
		
		return  dateFormat.format(date);
		
		
	}
	
	
}
