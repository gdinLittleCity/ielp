package com.gdinxiao.common.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
/**
 * 监听进度,将进度放入session
 * session.setAttribute("progress", (double)pBytesRead/pContentLength);
 * @author administor
 *
 */
public class FileUploadProgressListener implements ProgressListener {
	 private HttpSession session;

	  public FileUploadProgressListener(HttpSession session) {
	    this.session = session;
	  }

	  //pBytesRead  已经上传的大小
	  //pContentLength   文件总大小
	  @Override
	  public void update(long pBytesRead, long pContentLength, int pItems) {
	          session.setAttribute("progress", (double)pBytesRead/pContentLength);
	  }
	

}
