package kr.or.kosa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Emp {
    private int empno;
    private String ename;

//	public Emp(int empno, String enameString) {
//		super();
//		this.empno = empno;
//		this.enameString = enameString;
//	}
//
//	@Override
//	public String toString() {
//		return "Emp [empno=" + empno + ", enameString=" + enameString + "]";
//	}
}