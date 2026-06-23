package kr.or.kosa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kosa.model.Dept;
import kr.or.kosa.repository.DeptRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {

	private final DeptRepository deptRepository;
	//자동 주입 (DeptRepository 인터페이스를 구현하는 객체가 자동 생성되고 그 객체의 주소가 주동 주입)
	
	
	public List<Dept> findAll(){
		return deptRepository.findAll();  // key (자동화)
	}
	
	public Dept findById(int deptno) {
		return deptRepository.findById(deptno).orElse(null); // key (자동화)
	}
	
	public void save(Dept dept) {
		deptRepository.save(dept);  // key (자동화)
	}
	
	public void delete(int deptno) {
		deptRepository.deleteById(deptno);// key (자동화)
	}
}
