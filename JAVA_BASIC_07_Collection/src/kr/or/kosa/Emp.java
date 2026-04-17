package kr.or.kosa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Emp {
	private int empno;
	private String enmae;
	private String job;
}
