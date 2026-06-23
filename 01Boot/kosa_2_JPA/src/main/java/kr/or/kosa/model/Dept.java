package kr.or.kosa.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
//Entity 설계 > 이 클래스를 가지고 Table 생성 (create table Dept pk, 컬럼명, 타입, NULL)

@Entity
@Data
@Table(name="DEPT")
public class Dept {
  @Id
  private int deptno;
  private String dname;
  private String loc;
}
