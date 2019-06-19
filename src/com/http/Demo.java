package com.http;

import java.io.Serializable;

public class Demo implements Serializable {
	public String recId;
	public String errorFlag = " ";
	public String workUserId;
	public String workTime;
	public String remark;
	public String workResult;
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	public String getWorkUserId() {
		return workUserId;
	}
	public void setWorkUserId(String workUserId) {
		this.workUserId = workUserId;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkResult() {
		return workResult;
	}
	public void setWorkResult(String workResult) {
		this.workResult = workResult;
	}
	
	
	
}
