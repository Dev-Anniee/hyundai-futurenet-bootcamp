package kr.or.kosa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
DB  Dept 테이블에 있는 데이터를 담는다

select deptno , dname , loc from dept

1건: new Dept()
여러건 : List<Dept> list = new ArrayList<>();
       list.add(new Dept());
       list.add(new Dept());

*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
}
