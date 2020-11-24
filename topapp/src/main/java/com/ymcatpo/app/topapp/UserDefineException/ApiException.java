package com.ymcatpo.app.topapp.UserDefineException;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
	private static final long serialVersionUID = 1L;
		private HttpStatus status;
		private String message;
		private Date timeStamp;
	
	public ApiException(String message, HttpStatus status) {
			super();
			this.status = status;
			this.message = message;
		}

	public ApiException(String message, Date timeStamp) {
			super();
			this.message = message;
			this.timeStamp = timeStamp;
		}

	public HttpStatus getStatus() {
			return status;
		}

		public void setStatus(HttpStatus status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(Date timeStamp) {
			this.timeStamp = timeStamp;
		}

	

}
