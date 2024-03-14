package com.jsp.agro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDetail {
	private String recipient;
    private String msgBody;
    private String subject;
}
