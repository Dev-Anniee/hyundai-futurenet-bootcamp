package service;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import org.springframework.stereotype.Service;

import dao.DeptDao;
import lombok.RequiredArgsConstructor;
import vo.Dept;

@Service
@RequiredArgsConstructor
public class DeptService {
	private final DeptDao deptMapper;

	public List<Dept> getDepts() {
		List<Dept> list = new ArrayList<Dept>();
		try {
			list = deptMapper.getDepts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Dept getDept(int deptno) {
		Dept dept = null;
		try {
			dept = deptMapper.getDept(deptno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dept;
	}

	public String insert(Dept dept) {
		try {
			deptMapper.insert(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/dept/list.do";
	}

	public String update(Dept dept) {
		try {
			deptMapper.update(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/dept/list.do";
	}

	public String delete(int deptno) {
		try {
			deptMapper.delete(deptno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/dept/list.do";
	}

	public List<Dept> selectSearch(Map<String, Object> map) {
		List<Dept> list = new ArrayList<Dept>();
		try {
			list = deptMapper.selectSearch(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
