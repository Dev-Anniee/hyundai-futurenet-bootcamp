package kr.or.kosa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.dto.Emp;
import kr.or.kosa.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	private EmpService empservice;

	@Autowired
	public void setEmpservice(EmpService empservice) {
		this.empservice = empservice;
	}
	
	//전체조회
	//비동기 함수 (view 찿지 않고 데이터 전달  + @Controller + @ResponseBody)
	@GetMapping   //  /emp + GET
	public ResponseEntity<List<Emp>> empList(){
		//동기 나 비동기 같은 코드
		//동기 return view 주소 (데이터 Model 담아서 forward) view 데이터 구성 
		//비동기 return data(json , xml) > 클라이언트가 view 구성 
		List<Emp> list = new ArrayList<Emp>();
		try {
			  System.out.println("정상실행");
			  list = empservice.selectAllEmpList();
			  return new ResponseEntity<List<Emp>>(list,HttpStatus.OK);
		} catch (Exception e) {
			  return new ResponseEntity<List<Emp>>(list,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	//조건조회
    //  /emp/7788 + GET
	//@RequestMapping(value="{empno}" ,method = RequestMethod.GET);
	@GetMapping("{empno}")
	public Emp emplistByEmpno(@PathVariable("empno") int empno) {
		return empservice.selectEmpByEmpno(empno);
	}
	
	//데이터 삽입 (POST)
	//고민 : 데이터 전달 > ?empno=5000&ename=아무개 (x)
	//Client (json 객체의 형태를 가지는 문자열)
	// http://localhost:8090/emp  > POST > data {json}
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Emp emp){
		try {
			   System.out.println("insert 실행");
			   System.out.println(emp.toString());
			   empservice.insert(emp);
			   return new ResponseEntity<String>("insert success",HttpStatus.OK);
		} catch (Exception e) {
			   e.printStackTrace();
			   return new ResponseEntity<String>("insert fail",HttpStatus.BAD_REQUEST);
		}
	}
	
	//요기까지 POSTMAN 테스트 (클라이언트 코드 개발전에 TEST)
	//update 
	// /emp  + PUT + JSON문자열(Emp)
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Emp emp){
		try {
			   System.out.println("update 실행");
			   System.out.println(emp.toString());
			   empservice.update(emp);
			   return new ResponseEntity<String>("update success",HttpStatus.OK);
		} catch (Exception e) {
			   e.printStackTrace();
			   return new ResponseEntity<String>("update fail",HttpStatus.BAD_REQUEST);
		}
	}
	
	//@GetMapping("{empno}") 형식으로
	@DeleteMapping("{empno}")
	public ResponseEntity<String> delete(@PathVariable("empno") int empno){
		try {
			   System.out.println("delete 실행");
			   empservice.delete(empno);
			   return new ResponseEntity<String>("delete success",HttpStatus.OK);
		} catch (Exception e) {
			   e.printStackTrace();
			   return new ResponseEntity<String>("delete fail",HttpStatus.BAD_REQUEST);
		}
	}
	
}
/*

    @RestController
	public class HomeController {
	   //여러개 PathVariable 사용하기
       //http://localhost:8090/kglim/hello 
       @RequestMapping("/{name}/{message}")
       public Home home(
                   @PathVariable String name,
                   @PathVariable String message) {
            Home home = new Home();
            home.setName(name);
            home.setMessage(message);
            
            return home;
      }
}


*/