<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 입력</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- jQuery UI -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>

<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Segoe UI', sans-serif;
    }
    .container {
        margin-top: 60px;
        max-width: 700px;
    }
    .card {
        border-radius: 15px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }
    .card-header {
        font-size: 1.5rem;
        font-weight: bold;
        background: #a8d8ea;
        color: #333;
        border-radius: 15px 15px 0 0 !important;
    }
    .form-label {
        font-weight: 600;
    }
    .msg {
        margin-left: 10px;
        font-size: 0.9rem;
    }
</style>

<script>
$(function(){
    // 사번 중복 체크
    $("#empno").blur(function(){
        let empno = $(this).val();
        if(empno.trim() == "") return;

        $.ajax({
            url: "EmpnoCheck",
            type: "get",
            data: { empno: empno },
            success: function(result){
                if(result.trim() === "DUPLICATE"){
                    $("#empnoMsg").text("이미 존재하는 사번입니다 ❌").css("color","red");
                } else if(result.trim() === "AVAILABLE"){
                    $("#empnoMsg").text("사용 가능한 사번입니다 ✅").css("color","green");
                }
            }
        });
    });

 // 부서번호 목록 로딩
    $.getJSON("DeptnoListAjax.do", function(data){
        $.each(data, function(i, deptno){
            $("#deptno").append("<option value='"+deptno+"'>"+deptno+"</option>");
        });
    });

    // 관리자 사번 목록 로딩
    $.getJSON("MgrListAjax.do", function(data){
        $.each(data, function(i, mgr){
            $("#mgr").append("<option value='"+mgr+"'>"+mgr+"</option>");
        });
    });


    // 급여 숫자 검증
    $("#sal").on("input", function(){
        let val = $(this).val();
        if(!/^[0-9]+$/.test(val)){
            $("#salMsg").text("숫자만 입력 가능 ❌").css("color","red");
        } else {
            $("#salMsg").text("");
        }
    });
    
    // 입사일 datepicker
    $("#hiredate").datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        changeYear: true,
        maxDate: 0 // 오늘 이후 날짜 선택 불가
    });
});
</script>
</head>

<body>
<div class="container">
    <div class="card">
        <div class="card-header text-center">
            사원 입력
        </div>
        <div class="card-body">
            <form action="InsertEmp.do" method="post">
                <div class="mb-3">
                    <label for="empno" class="form-label">사번</label>
                    <input type="text" class="form-control" name="empno" id="empno">
                    <span id="empnoMsg" class="msg"></span>
                </div>

                <div class="mb-3">
                    <label for="ename" class="form-label">이름</label>
                    <input type="text" class="form-control" name="ename" id="ename">
                </div>

                <div class="mb-3">
                    <label for="sal" class="form-label">급여</label>
                    <input type="text" class="form-control" name="sal" id="sal">
                    <span id="salMsg" class="msg"></span>
                </div>

                <div class="mb-3">
                    <label for="hiredate" class="form-label">입사일</label>
                    <input type="text" class="form-control" name="hiredate" id="hiredate" placeholder="yyyy-mm-dd">
                </div>

                <div class="mb-3">
                    <label for="deptno" class="form-label">부서번호</label>
                    <select name="deptno" id="deptno" class="form-select">
                        <option value="">-- 선택 --</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="mgr" class="form-label">관리자 사번</label>
                    <select name="mgr" id="mgr" class="form-select">
                        <option value="">-- 선택 --</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="job" class="form-label">직무</label>
                    <input type="text" class="form-control" name="job" id="job">
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary px-4">사원 등록</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
