package kr.or.kosa.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  이름       널?       유형           
-------- -------- ------------ 
EMPNO    NOT NULL NUMBER       
ENAME             VARCHAR2(10) 
JOB               VARCHAR2(9)  
MGR               NUMBER       
HIREDATE          DATE         
SAL               NUMBER       
COMM              NUMBER       
DEPTNO            NUMBER  
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private int comm;
	private int deptno;
	private int sal;
	private String dname; 
}


