package kr.or.kosa.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Users {
	private Long userId;
	private String username;
	private String password;
	private String email;
	private Integer deptno;
	private String deptName;
	private Integer enabled;
	private Date regdate;

	private List<UserAuth> authList;
}
