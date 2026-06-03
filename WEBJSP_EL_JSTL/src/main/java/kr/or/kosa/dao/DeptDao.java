package kr.or.kosa.dao;

import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Dept;

public class DeptDao {

		//DB 연결 했다고 하고
	
		//전체 조회 (select 했다 치고)
	
		public List<Dept> getAllDeptList(){
			
			//select...
			List<Dept> list = new ArrayList<Dept>();
			
			list.add(new Dept(10,"AA","AAA"));
			list.add(new Dept(20,"BB","BBB"));
			list.add(new Dept(30,"CC","CCC"));
			list.add(new Dept(40,"DD","DDD"));
			
			return list;
			
		}
}
