<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서별 사원 조회</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Segoe UI', sans-serif;
    }
    .container {
        margin-top: 60px;
    }
    .card {
        border-radius: 15px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }
    .card-header {
        font-size: 1.3rem;
        font-weight: bold;
        background: #a8d8ea;
        color: #333;
        border-radius: 15px 15px 0 0 !important;
    }
    table {
        margin-top: 15px;
    }
    thead {
        background-color: #b8e0d2;
        color: #333;
    }
</style>
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-header text-center">
            부서별 사원 조회
        </div>
        <div class="card-body">
            <div class="row mb-4">
                <div class="col-md-6 offset-md-3 text-center">
                    <label for="deptSelect" class="form-label fw-bold">부서 선택</label>
                    <select id="deptSelect" class="form-select text-center">
                        <option value="">-- 부서번호 선택 --</option>
                    </select>
                </div>
            </div>

            <div class="table-responsive">
                <table id="empTable" class="table table-hover table-bordered align-middle text-center">
                    <thead>
                        <tr>
                            <th>사번</th>
                            <th>이름</th>
                            <th>급여</th>
                            <th>입사일</th>
                            <th>부서번호</th>
                            <th>부서이름</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td colspan="5" class="text-muted">부서를 선택하세요.</td></tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
// 부서번호 목록 로드
$.getJSON("DeptnoList.do", function(data){
    $.each(data, function(i, deptno){
        $("#deptSelect").append("<option value='"+deptno+"'>"+deptno+"</option>");
    });
});

// 선택 시 사원 목록 로드
$("#deptSelect").change(function(){
    let deptno = $(this).val();
    if(!deptno) {
        $("#empTable tbody").html("<tr><td colspan='5' class='text-muted'>부서를 선택하세요.</td></tr>");
        return;
    }

    $.getJSON("EmpListByDept.do?deptno=" + deptno, function(data){
        let rows = "";
        if(data.length === 0){
            rows = "<tr><td colspan='6' class='text-muted'>사원이 없습니다.</td></tr>";
        } else {
            $.each(data, function(i, emp){
                rows += "<tr>"
                      + "<td>"+emp.empno+"</td>"
                      + "<td>"+emp.ename+"</td>"
                      + "<td>"+emp.sal.toLocaleString()+"만원</td>"
                      + "<td>"+emp.hiredate+"</td>"
                      + "<td>"+emp.deptno+"</td>"
                      + "<td>"+emp.dname+"</td>"  
                      + "</tr>";
            });
        }
        $("#empTable tbody").html(rows);
    });

});
</script>

</body>
</html>
