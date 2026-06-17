package ncontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

  private NoticeDao noticedao;

  @Autowired
  public void setNoticedao(NoticeDao noticedao) {
    this.noticedao = noticedao;
  }

  private String saveUploadFile(MultipartFile file, HttpServletRequest request) {
    if (file == null || file.isEmpty()) {
      return null;
    }

    String filename = file.getOriginalFilename();
    if (filename == null || filename.equals("")) {
      return null;
    }

    filename = new File(filename).getName();
    String path = request.getServletContext().getRealPath("/customer/upload");
    if (path == null) {
      return null;
    }

    File uploadDir = new File(path);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }

    File target = new File(uploadDir, filename);
    try (FileOutputStream fs = new FileOutputStream(target)) {
      fs.write(file.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    return filename;
  }

  @RequestMapping("/notice.do")
  public String notice(String _page, String _field, String _query, Model model)
      throws SQLException, ClassNotFoundException {
    int page = 1;
    String field = "TITLE";
    String query = "";

    if (_page != null && !_page.equals("")) {
      page = Integer.parseInt(_page);
    }

    if (_field != null && !_field.equals("")) {
      field = _field;
    }

    if (_query != null && !_query.equals("")) {
      query = _query;
    }

    List<Notice> list = noticedao.getNotices(page, field, query);
    model.addAttribute("list", list);

    return "customer/notice";
  }

  @RequestMapping("/noticeDetail.do")
  public String noticeDetail(String seq, Model model) throws SQLException, ClassNotFoundException {
    Notice notice = noticedao.getNotice(seq);
    model.addAttribute("notice", notice);
    model.addAttribute("n", notice);

    return "customer/noticeDetail";
  }

  @GetMapping("/noticeReg.do")
  public String noticeReg() {
    return "customer/noticeReg";
  }

  @PostMapping("/noticeReg.do")
  public String noticeReg(Notice n,
                          @RequestParam(value = "file", required = false) MultipartFile file,
                          HttpServletRequest request) {
    String filename = saveUploadFile(file, request);
    n.setFileSrc(filename);

    try {
      noticedao.insert(n);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:notice.do";
  }

  @GetMapping("noticeEdit.do")
  public String noticeEdit(String seq, Model model) {
    Notice notice = null;

    try {
      notice = noticedao.getNotice(seq);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    model.addAttribute("notice", notice);

    return "customer/noticeEdit";
  }

  @PostMapping("noticeEdit.do")
  public String noticeEdit(Notice n,
                           String seq,
                           @RequestParam(value = "file", required = false) MultipartFile file,
                           HttpServletRequest request) {
    if ((n.getSeq() == null || n.getSeq().equals("")) && seq != null) {
      n.setSeq(seq);
    }

    String filename = saveUploadFile(file, request);
    if (filename != null) {
      n.setFileSrc(filename);
    } else {
      try {
        Notice oldNotice = noticedao.getNotice(n.getSeq());
        n.setFileSrc(oldNotice.getFileSrc());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    try {
      noticedao.update(n);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:noticeDetail.do?seq=" + n.getSeq();
  }

  @GetMapping("noticeDel.do")
  public String noticeDel(String seq) {
    try {
      noticedao.delete(seq);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return "redirect:notice.do";
  }
}
