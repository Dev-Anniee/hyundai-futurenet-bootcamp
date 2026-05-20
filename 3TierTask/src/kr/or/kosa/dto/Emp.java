package kr.or.kosa.dto;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor; //전체 인자 생성자
import lombok.NoArgsConstructor;

//데이터를 실어 나르는 바구니
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private int empno;
    private String ename;
    private int deptno;
    private String dname;
}