package com.ymcatpo.app.topapp.UserDefineException;

import java.util.Date;

public class ErrorDetails {

	
		  private Date timestamp;
		  private String message;
		  private int status;
 		  public ErrorDetails(Date timestamp, String message, int status) {
			super();
			this.timestamp = timestamp;
			this.message = message;
			this.status = status;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public ErrorDetails() {}
		
		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

			
}
