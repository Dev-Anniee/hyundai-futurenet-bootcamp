package kr.or.kosa.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/*
USER_NO  NOT NULL NUMBER        
USER_ID  NOT NULL VARCHAR2(100) 
USER_PW  NOT NULL VARCHAR2(200) 
NAME     NOT NULL VARCHAR2(100) 
EMAIL             VARCHAR2(200) 
REG_DATE NOT NULL TIMESTAMP(6)  
UPD_DATE NOT NULL TIMESTAMP(6)  
ENABLED  NOT NULL NUMBER(1)     

*/

@Data
public class Users {
	private int userNo;
	private String userId;
	private String userPw;
	private String name;
	private String email;
	private Date regDate;
	private Date updDate;
	private int enabled;
	
	//권한 목록 관리
	List<UserAuth> authList; //한명의 사용자는 여러개의 권한을 가질 수 있다
	
}
