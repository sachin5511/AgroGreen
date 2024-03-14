package com.jsp.agro.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String msg;
	private int status;
	private T data;

}
