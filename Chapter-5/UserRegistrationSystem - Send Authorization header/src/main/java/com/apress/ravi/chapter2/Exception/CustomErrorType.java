package com.apress.ravi.chapter2.Exception;

import com.apress.ravi.chapter2.dto.UsersDTO;

public class CustomErrorType extends UsersDTO {

	private String errorMessage;
	 
    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
}
