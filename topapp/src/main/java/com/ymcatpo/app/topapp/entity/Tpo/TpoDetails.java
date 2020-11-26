package com.ymcatpo.app.topapp.entity.Tpo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TpoDetails {

		@Id
		private String tpoId;
		private String name;
		private String phoneNo;
		private String tpoEmail;
		private String branch;
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public String getTpoId() {
			return tpoId;
		}
		public void setTpoId(String tpoId) {
			this.tpoId = tpoId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getTpoEmail() {
			return tpoEmail;
		}
		public void setTpoEmail(String tpoEmail) {
			this.tpoEmail = tpoEmail;
		}
		public TpoDetails() {
		
		}
		public TpoDetails(String tpoId, String name, String phoneNo, String tpoEmail, String branch) {
			super();
			this.tpoId = tpoId;
			this.name = name;
			this.phoneNo = phoneNo;
			this.tpoEmail = tpoEmail;
			this.branch = branch;
		}
		

}
