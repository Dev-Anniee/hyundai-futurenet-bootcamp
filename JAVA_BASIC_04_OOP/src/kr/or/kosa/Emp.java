package kr.or.kosa;

//DTO, VO, DOMAIN 데이터를 담는 클래스
public class Emp {
	private int empno;
	private String ename;
	
	public Emp(int empno, String ename) {
		super();
		this.empno = empno;
		this.ename = ename;
	}
	
	public int getEmpno() {
		return empno;
	}
	
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	public String getEname() {
		return ename;
	}
	
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Override
	public String toString() { //주소값보다는 멤버필드를 많이 뽑아낸다 -> 재정의 많이 한다
		return "Emp [empno=" + empno + ", ename=" + ename + "]";
	}
}
