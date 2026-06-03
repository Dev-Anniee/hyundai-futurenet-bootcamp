<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    kr.or.kosa.dao.EmpDao empDao = new kr.or.kosa.dao.EmpDao();
    java.util.List<kr.or.kosa.dto.EmpDto> empList = empDao.getEmpList();
    request.setAttribute("empList", empList);
%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Emp List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #eef2ff 0%, #ecfeff 48%, #fff7ed 100%);
        }

        .page-shell {
            max-width: 1100px;
        }

        .hero-card {
            border: 0;
            border-radius: 24px;
            box-shadow: 0 18px 45px rgba(15, 23, 42, 0.12);
            overflow: hidden;
        }

        .hero-head {
            background: linear-gradient(135deg, #0f172a, #0369a1);
            color: white;
        }

        .dept-link {
            text-decoration: none;
            font-weight: 700;
        }
    </style>
</head>
<body>
<main class="container page-shell py-5">
    <section class="card hero-card">
        <div class="hero-head p-4 p-md-5">
            <p class="mb-1 text-info fw-semibold">JDBC EMPLOYEE DIRECTORY</p>
            <h1 class="display-6 fw-bold mb-0">사원 목록</h1>
        </div>

        <div class="card-body p-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <span class="badge text-bg-primary rounded-pill">총 ${fn:length(empList)}명</span>
                <span class="text-secondary small">부서번호를 클릭하면 해당 부서 정보로 이동합니다.</span>
            </div>

            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>사번</th>
                        <th>이름</th>
                        <th>직무</th>
                        <th>입사일</th>
                        <th class="text-end">급여</th>
                        <th>부서</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="emp" items="${empList}">
                        <tr>
                            <td class="fw-semibold">${emp.empno}</td>
                            <td>${emp.ename}</td>
                            <td>${emp.job}</td>
                            <td>${emp.hiredate}</td>
                            <td class="text-end">${emp.sal}</td>
                            <td>
                                <a class="dept-link badge text-bg-info"
                                   href="DeptList.jsp?deptno=${emp.deptno}">
                                    ${emp.deptno} ${emp.dname}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty empList}">
                        <tr>
                            <td colspan="6" class="text-center text-secondary py-4">조회된 사원이 없습니다.</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>
</body>
</html>
