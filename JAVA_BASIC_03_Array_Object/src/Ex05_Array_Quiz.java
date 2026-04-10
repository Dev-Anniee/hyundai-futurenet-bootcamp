import kr.or.kosa.Emp;

public class Ex05_Array_Quiz {
    public static void main(String[] args) {
//		Emp[] emps = new Emp[3];
//		emps[0] = new Emp(1000, "홍길동");
//		emps[1] = new Emp(2000, "김유신");
//		emps[2] = new Emp(3000, "유관순");
        Emp[] emplist = {new Emp(1000, "홍길동"), new Emp(2000, "김유신"), new Emp(3000, "유관순")};

        for (Emp emp : emplist) {
            System.out.println(emp);//tostring 생략
            System.out.println(emp.getEmpno() + emp.getEname());
        }
    }
}
