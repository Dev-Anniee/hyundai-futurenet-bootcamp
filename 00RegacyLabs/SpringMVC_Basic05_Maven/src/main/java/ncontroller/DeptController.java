package ncontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import service.DeptService;
import vo.Dept;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dept/")
public class DeptController {
	private final DeptService deptService;

	@GetMapping("list.do")
	public String list(String column, String search, String deptnoList, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (search != null && !search.trim().equals("")) {
			map.put("column", column);
			map.put("search", search.trim());
		}

		if (deptnoList != null && !deptnoList.trim().equals("")) {
			List<Integer> deptnos = new ArrayList<>();

			String[] arr = deptnoList.split(",");
			for (String deptno : arr) {
				String trimmedDeptno = deptno.trim();
				if (!trimmedDeptno.equals("")) {
					deptnos.add(Integer.parseInt(trimmedDeptno));
				}
			}

			if (deptnos.size() > 0) {
				map.put("deptnos", deptnos);
			}
		}

		if (map.size() > 0) {
			model.addAttribute("list", deptService.selectSearch(map));
		} else {
			model.addAttribute("list", deptService.getDepts());
		}

		return "dept/list";
	}

	@GetMapping("detail.do")
	public String detail(int deptno, Model model) {
		model.addAttribute("dept", deptService.getDept(deptno));
		return "dept/detail";
	}

	@GetMapping("reg.do")
	public String reg() {
		return "dept/reg";
	}

	@PostMapping("reg.do")
	public String reg(Dept dept) {
		return deptService.insert(dept);
	}

	@GetMapping("edit.do")
	public String edit(int deptno, Model model) {
		model.addAttribute("dept", deptService.getDept(deptno));
		return "dept/edit";
	}

	@PostMapping("edit.do")
	public String edit(Dept dept) {
		return deptService.update(dept);
	}

	@GetMapping("del.do")
	public String del(int deptno) {
		return deptService.delete(deptno);
	}
}
