package kr.or.kosa.controller;
import java.util.ArrayList;
import java.util.List;
import kr.or.kosa.dto.Dept;
import kr.or.kosa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {
  private DeptService deptService;

  @Autowired
  public void setDeptService(DeptService deptService) {
    this.deptService = deptService;
  }

  @GetMapping
  public ResponseEntity<List<Dept>> deptList(){

    List<Dept> list = new ArrayList<Dept>();
    try {
      list = deptService.selectAllDeptList();
      return new ResponseEntity<>(list, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("{deptno}")
  public Dept deptlistByDeptno(@PathVariable("deptno") int deptno) {
    return deptService.selectByDeptno(deptno);
  }

  @PostMapping
  public ResponseEntity<String> insert(@RequestBody Dept dept){
    try {
      System.out.println("insert 실행");
      System.out.println(dept.toString());
      deptService.insert(dept);
      return new ResponseEntity<String>("insert success",HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>("insert fail",HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  public ResponseEntity<String> update(@RequestBody Dept dept){
    try {
      System.out.println("update 실행");
      System.out.println(dept.toString());
      deptService.update(dept);
      return new ResponseEntity<String>("update success",HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>("update fail",HttpStatus.BAD_REQUEST);
    }
  }

  //@GetMapping("{empno}") 형식으로
  @DeleteMapping("{deptno}")
  public ResponseEntity<String> delete(@PathVariable("deptno") int deptno){
    try {
      System.out.println("delete 실행");
      deptService.delete(deptno);
      return new ResponseEntity<String>("delete success",HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>("delete fail",HttpStatus.BAD_REQUEST);
    }
  }
}
