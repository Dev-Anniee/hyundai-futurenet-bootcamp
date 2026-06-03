<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String deptnoParam = request.getParameter("deptno");
    java.util.List<kr.or.kosa.dto.Dept> deptList = new java.util.ArrayList<>();
    java.util.List<kr.or.kosa.dto.EmpDto> empListInDept = new java.util.ArrayList<>();

    if (deptnoParam != null && !deptnoParam.trim().isEmpty()) {
        try {
            int deptno = Integer.parseInt(deptnoParam);
            kr.or.kosa.dao.DeptDao deptDao = new kr.or.kosa.dao.DeptDao();
            kr.or.kosa.dao.EmpDao empDao = new kr.or.kosa.dao.EmpDao();

            deptList = deptDao.getDeptListByDeptNo(deptno);
            empListInDept = empDao.getEmpListByDeptNo(deptno);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "부서번호는 숫자만 가능합니다.");
        }
    } else {
        request.setAttribute("message", "부서번호가 전달되지 않았습니다.");
    }

    request.setAttribute("deptList", deptList);
    request.setAttribute("empListInDept", empListInDept);
%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dept List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            background: radial-gradient(circle at top left, #dbeafe, transparent 34%),
                        linear-gradient(135deg, #f8fafc, #fef3c7);
        }

        .page-shell {
            max-width: 960px;
        }

        .panel {
            border: 0;
            border-radius: 24px;
            box-shadow: 0 18px 45px rgba(15, 23, 42, 0.12);
        }
    </style>
</head>
<body>
<main class="container page-shell py-5">
    <div class="mb-4">
        <a href="EmpList.jsp" class="btn btn-outline-secondary">&larr; 사원 목록</a>
    </div>

    <section class="card panel mb-4">
        <div class="card-body p-4 p-md-5">
            <p class="text-primary fw-semibold mb-1">DEPARTMENT DETAIL</p>
            <h1 class="h2 fw-bold mb-4">부서 정보</h1>

            <c:if test="${not empty message}">
                <div class="alert alert-warning">${message}</div>
            </c:if>

            <div class="table-responsive">
                <table class="table table-bordered align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>부서번호</th>
                        <th>부서명</th>
                        <th>지역</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dept" items="${deptList}">
                        <tr>
                            <td class="fw-bold">${dept.deptno}</td>
                            <td>${dept.dname}</td>
                            <td>${dept.loc}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty deptList}">
                        <tr>
                            <td colspan="3" class="text-center text-secondary py-4">조회된 부서가 없습니다.</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <section class="card panel">
        <div class="card-body p-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2 class="h4 fw-bold mb-0">해당 부서 사원</h2>
                <span class="badge text-bg-primary rounded-pill">${fn:length(empListInDept)}명</span>
            </div>

            <div class="row g-3">
                <c:forEach var="emp" items="${empListInDept}">
                    <div class="col-md-6">
                        <div class="border rounded-4 p-3 bg-light">
                            <div class="fw-bold">${emp.ename}</div>
                            <div class="text-secondary small">사번 ${emp.empno} / ${emp.job}</div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${empty empListInDept}">
                    <div class="col-12 text-secondary">해당 부서 사원이 없습니다.</div>
                </c:if>
            </div>
        </div>
    </section>
</main>
</body>
</html>
