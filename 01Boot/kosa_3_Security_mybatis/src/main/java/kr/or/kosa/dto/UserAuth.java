package kr.or.kosa.dto;

import lombok.Data;

/*
AUTH_NO NOT NULL NUMBER        
USER_ID NOT NULL VARCHAR2(100) 
AUTH    NOT NULL VARCHAR2(100) 
*/
@Data
public class UserAuth {
	private int authNo;
	private String userId;
	private String  auth;
}
