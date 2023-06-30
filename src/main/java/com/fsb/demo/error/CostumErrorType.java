package com.fsb.demo.error;

import java.io.Serializable;

public class CostumErrorType implements Serializable{
	
	private String errorMessage;
	
    public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CostumErrorType(String errorMessage) {
		setErrorMessage(errorMessage);
    }
}
