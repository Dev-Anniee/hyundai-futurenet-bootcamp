<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Dept REST</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script type="text/javascript">
    $(function() {
      const apiUrl = "${pageContext.request.contextPath}/dept";

      function resetWriteForm() {
        $("#formdeptno").val("");
        $("#formdname").val("");
        $("#formloc").val("");
      }

      function showWriteForm(show) {
        $("#writeform").toggleClass("visually-hidden", !show);
      }

      function showUpdateForm(show) {
        $("#updateform").toggleClass("visually-hidden", !show);
      }

      function renderRows(data) {
        $("#deptList").empty();

        if (!data || data.length === 0) {
          $("#deptList").append('<tr><td colspan="5" class="text-center">부서 데이터가 없습니다.</td></tr>');
          return;
        }

        let html = "";
        $.each(data, function() {
          html += "<tr>";
          html += "<td>" + this.deptno + "</td>";
          html += "<td>" + this.dname + "</td>";
          html += "<td>" + this.loc + "</td>";
          html += '<td><button type="button" class="btn btn-outline-primary deptupdate" data-deptno="' + this.deptno + '">수정</button></td>';
          html += '<td><button type="button" class="btn btn-outline-danger deptdelete" data-deptno="' + this.deptno + '">삭제</button></td>';
          html += "</tr>";
        });

        $("#deptList").append(html);
      }

      function deptList() {
        $.ajax({
          type: "get",
          url: apiUrl,
          contentType: "application/json; charset=utf-8",
          success: function(data) {
            renderRows(data);
          },
          error: function() {
            alert("부서 목록 조회 실패");
          }
        });
      }

      function searchByDeptno(deptno) {
        $.ajax({
          type: "get",
          url: apiUrl + "/" + deptno,
          success: function(data) {
            renderRows([data]);
          },
          error: function() {
            $("#deptList").empty();
            $("#deptList").append('<tr><td colspan="5" class="text-center">조회된 부서가 없습니다.</td></tr>');
          }
        });
      }

      function insertDept() {
        const requestdata = {
          deptno: $("#formdeptno").val(),
          dname: $("#formdname").val(),
          loc: $("#formloc").val()
        };

        $.ajax({
          type: "post",
          url: apiUrl,
          data: JSON.stringify(requestdata),
          dataType: "text",
          contentType: "application/json; charset=utf-8",
          success: function(data) {
            showWriteForm(false);
            resetWriteForm();
            deptList();
            alert(data);
          },
          error: function() {
            alert("부서 등록 실패");
          }
        });
      }

      function updateDept() {
        const requestdata = {
          deptno: $("#formdeptno1").val(),
          dname: $("#formdname1").val(),
          loc: $("#formloc1").val()
        };

        $.ajax({
          type: "put",
          url: apiUrl,
          data: JSON.stringify(requestdata),
          dataType: "text",
          contentType: "application/json; charset=utf-8",
          success: function(data) {
            showUpdateForm(false);
            deptList();
            alert(data);
          },
          error: function() {
            alert("부서 수정 실패");
          }
        });
      }

      deptList();

      $("#deptnosearchbtn").click(function() {
        const deptno = $("#deptnosearch").val();
        if (deptno === "" || deptno == null) {
          deptList();
        } else {
          searchByDeptno(deptno);
        }
      });

      $("#deptwritebtn").click(function() {
        resetWriteForm();
        showWriteForm(true);
      });

      $("#deptwritesubmit").click(function() {
        insertDept();
      });

      $("#deptwritereset").click(function() {
        showWriteForm(false);
      });

      $(document).on("click", ".deptdelete", function() {
        $.ajax({
          type: "delete",
          url: apiUrl + "/" + $(this).data("deptno"),
          success: function() {
            deptList();
          },
          error: function() {
            alert("부서 삭제 실패");
          }
        });
      });

      $(document).on("click", ".deptupdate", function() {
        const tr = $(this).closest("tr");
        $("#formdeptno1").val(tr.find("td:eq(0)").text());
        $("#formdname1").val(tr.find("td:eq(1)").text());
        $("#formloc1").val(tr.find("td:eq(2)").text());
        showUpdateForm(true);
      });

      $("#deptwritesubmit1").click(function() {
        updateDept();
      });

      $("#deptwritereset1").click(function() {
        showUpdateForm(false);
      });
    });
  </script>
</head>
<body>
  <main class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h1 class="h3 mb-0">부서 관리</h1>
      <button type="button" class="btn btn-primary" id="deptwritebtn">부서 등록</button>
    </div>

    <div class="row g-2 align-items-center mb-4">
      <div class="col-auto">
        <label for="deptnosearch" class="col-form-label">부서번호</label>
      </div>
      <div class="col-auto">
        <input type="text" class="form-control" id="deptnosearch">
      </div>
      <div class="col-auto">
        <button type="button" class="btn btn-outline-secondary" id="deptnosearchbtn">검색</button>
      </div>
    </div>

    <div id="writeform" class="row visually-hidden mb-4">
      <div class="col-lg-8">
        <h2 class="h5">부서 등록</h2>
        <table class="table table-striped">
          <tbody>
            <tr>
              <td>DEPTNO</td>
              <td><input type="text" class="form-control" id="formdeptno" required></td>
            </tr>
            <tr>
              <td>DNAME</td>
              <td><input type="text" class="form-control" id="formdname"></td>
            </tr>
            <tr>
              <td>LOC</td>
              <td><input type="text" class="form-control" id="formloc"></td>
            </tr>
          </tbody>
        </table>
        <button type="button" class="btn btn-outline-primary" id="deptwritesubmit">확인</button>
        <button type="button" class="btn btn-outline-danger" id="deptwritereset">취소</button>
      </div>
    </div>

    <div id="updateform" class="row visually-hidden mb-4">
      <div class="col-lg-8">
        <h2 class="h5">부서 수정</h2>
        <table class="table table-striped">
          <tbody>
            <tr>
              <td>DEPTNO</td>
              <td><input type="text" class="form-control" id="formdeptno1" readonly></td>
            </tr>
            <tr>
              <td>DNAME</td>
              <td><input type="text" class="form-control" id="formdname1"></td>
            </tr>
            <tr>
              <td>LOC</td>
              <td><input type="text" class="form-control" id="formloc1"></td>
            </tr>
          </tbody>
        </table>
        <button type="button" class="btn btn-outline-primary" id="deptwritesubmit1">확인</button>
        <button type="button" class="btn btn-outline-danger" id="deptwritereset1">취소</button>
      </div>
    </div>

    <table class="table table-striped">
      <thead>
        <tr>
          <th>DEPTNO</th>
          <th>DNAME</th>
          <th>LOC</th>
          <th>수정</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody id="deptList"></tbody>
    </table>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
