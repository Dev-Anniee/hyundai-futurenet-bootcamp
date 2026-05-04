/*
 M : model( DTO(VO) , DAO(DB연결+함수) , SERVICE(요청처리)
 V : JSP (Console 화면)
 C : Servlet ( Program 자바 > main() )
 */
import java.util.List;
import kr.or.kosa.dao.DeptDao;
import kr.or.kosa.dto.Dept;

public class Program {

	public static void main(String[] args) {
		
		//나는 DB작업을 위해서는 DAO가 필요해 .. (객체: 의존 > 상속 , 포함)

		DeptDao deptDao = new DeptDao();
		System.out.println("[ 전체조회 ]");
		
		List<Dept> deptList = deptDao.getDeptAllList();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		System.out.println("[ 조건조회 ]");
		Dept dept = deptDao.getDeptListByDeptno(10);
		if(dept != null) {
			deptPrint(dept);
		}else {
			System.out.println("부서가 없습니다");
		}
		
		System.out.println("[데이터 삽입]");
		//데이터 한건 : Dept 객체 하나
		int insertRow = deptDao.insertDept(new Dept(99,"IT","SEOUL"));
		if(insertRow > 0 ) {
			System.out.println("row : " + insertRow);
		}else {
			System.out.println("no row : " + insertRow);
		}
		System.out.println("[방금전 INSERT 한 데이터 조회]");
		deptList = deptDao.getDeptAllList();
		
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		System.out.println("[방금전 INSERT  한 데이터 UPDATE]");
		//99 , IT_UP , SEOUL_UP
		int updateRow = deptDao.updateDept(new Dept(99,"IT_UP","SEOUL_UP"));
		if(updateRow > 0) {
			System.out.println("row : " + updateRow);
		}else {
			System.out.println("no row : " + updateRow);
		}
		
		System.out.println("[방금전 UPDATE 한 데이터 조회]");
		deptList = deptDao.getDeptAllList();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
				
		System.out.println("[방금전 UPDATE  한 데이터 DELETE]");
		int deleteRow = deptDao.deleteDept(99);
		if(deleteRow > 0) {
			System.out.println("row : " + deleteRow);
		}else {
			System.out.println("no row : " + deleteRow);
		}
		
		System.out.println("[다시 데이터 조회]");
		deptList = deptDao.getDeptAllList();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
	}
	private static void deptPrint(List<Dept> list) {
		for(Dept dept : list) {
			System.out.println(dept.toString());
		}
	}
	private static void deptPrint(Dept dept) {
		System.out.println(dept.toString());
	}

}

