package kr.or.kosa.dto;

import lombok.Data;

@Data
public class UserAuth {
	private Long userId;
	private Long roleId;
	private String auth;
}
