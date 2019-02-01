package cn.edu.gdin.ilep.util;

public class ValidateUtils {
	
	/**
	 * 检验文件类型<br/>
	 * 白名单机制<br/>
	 * 允许的文件类型：doc,docx,xls,xlsx,txt,pdf,ppt,pptx,zip,rar
	 * @param fileName
	 * @return 允许--true 禁止--false
	 */
	public static boolean checkFile(String fileName){
		String type = Constant.UPLOAD_FILE_TYPE;
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		if(type.contains(fileType))
			return true;
		else
			return false;
		
	}
}	
