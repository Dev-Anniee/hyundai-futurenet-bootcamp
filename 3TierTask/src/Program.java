import java.util.List;
import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.Emp;

public class Program {
    public static void main(String[] args) {
        EmpDao empDao = new EmpDao();

        System.out.println("[전체조회]");
        List<Emp> empList = empDao.getEmpAllList();
        if(empList!=null)
            EmpPrint(empList);

        System.out.println("---------------------------");
        System.out.println("[조건조회]");
        Emp emp = empDao.getEmpByEmpno(7499);
        if (emp != null)
            EmpPrint(emp);
        else {
            System.out.println("조회된 사원 정보가 없습니다.");
        }

        System.out.println("---------------------------");
        System.out.println("LIKE  이름 검색 키워드 : A");
        List<Emp> empList2 = empDao.getEmpListByLikeEname("A");
        if(empList2!=null)
            EmpPrint(empList2);
        else
            System.out.println("조회된 사원 정보가 없습니다.");

        System.out.println("---------------------------");
        System.out.println("정규표현식 검색 : 'C'로 시작하거나 'D'로 끝남");
        List<Emp> empList3 = empDao.getEmpListByRegexp("^C|D$");
        if(empList3!=null)
            EmpPrint(empList3);
        else
            System.out.println("조회된 사원 정보가 없습니다.");
        System.out.println("---------------------------");
        System.out.println("데이터 삽입 : ");
        int insertRow = empDao.insertEmp(
            Emp.builder()
                .empno(9999)
                .ename("김철수")
                .deptno(10)
                .build()
        );

        if(insertRow>0)
            System.out.println("row : " + insertRow);
        else
            System.out.println("no row : " + insertRow);

        System.out.println("방금전 INSERT 한 데이터 조회 : ");
        empList = empDao.getEmpAllList();
        EmpPrint(empList);
        System.out.println("---------------------------");
        System.out.println("방금 전 INSERT한 데이터 UPDATE");
        int updateRow = empDao.updateEmp(Emp.builder()
            .empno(9999)
            .ename("김철수")
            .deptno(20)
            .build());

        if(updateRow > 0) {
            System.out.println("row : " + updateRow);
        }else {
            System.out.println("no row : " + updateRow);
        }

        System.out.println("방금전 UPDATE 한 데이터 조회");
        empList = empDao.getEmpAllList();
        EmpPrint(empList);

        System.out.println("---------------------------");
        System.out.println("방금전 UPDATE한 데이터 DELETE");
        int deleteRow = empDao.deleteEmp(9999);
        if(deleteRow>0)
            System.out.println("row : " + deleteRow);
        else
            System.out.println("no row : " + deleteRow);

        System.out.println("데이터 다시 조회");
        empList = empDao.getEmpAllList();
        EmpPrint(empList);
    }

    private static void EmpPrint(List<Emp> list){
        for (Emp emp : list){
            System.out.println(emp.toString());
        }
    }

    private static void EmpPrint(Emp emp){
        System.out.println(emp.toString());
    }
}
