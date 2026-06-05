<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, kr.or.kosa.dto.Emp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 리스트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #fafafa;
            font-family: 'Segoe UI', sans-serif;
        }
        .card {
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            border: none;
        }
        .card-header {
            background: linear-gradient(90deg, #a8d8ea 0%, #dcedc2 100%); /* 파스텔 블루 → 민트 */
            font-size: 1.2rem;
            font-weight: bold;
            padding: 15px 20px;
            color: #333;
        }
        .table thead {
            background-color: #fce1e4; /* 파스텔 핑크 */
            color: #333;
            font-weight: bold;
        }
        .table-hover tbody tr:hover {
            background-color: #f9f9f9;
            transition: 0.2s;
        }
        .salary {
            font-weight: bold;
            color: #555;
        }
        .search-box {
            max-width: 250px;
        }
        .badge-dept {
            background-color: #d0e6a5; /* 파스텔 라임 */
            color: #333;
        }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container mt-5">
    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <span>📋 사원 목록</span>
            <div class="search-box input-group input-group-sm">
                <span class="input-group-text bg-white"><i class="bi bi-search"></i></span>
                <input type="text" id="searchInput" class="form-control" placeholder="이름 검색">
            </div>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped text-center mb-0 align-middle">
                    <thead>
                        <tr>
                            <th>사번</th>
                            <th>이름</th>
                            <th>직무</th>
                            <th>관리자</th>
                            <th>급여</th>
                            <th>입사일</th>
                            <th>부서번호</th>
                            <th>부서명</th>
                        </tr>
                    </thead>
                    <tbody id="empTable">
                        <%
                            List<Emp> emplist = (List<Emp>)request.getAttribute("emplist");
                            if(emplist != null && !emplist.isEmpty()){
                                for(Emp emp : emplist){
                        %>
                        <tr>
                            <td><%=emp.getEmpno()%></td>
                            <td><%=emp.getEname()%></td>
                            <td><%=emp.getJob()%></td>
                            <td><%=emp.getMgr()%></td>
                            <td class="salary"><%=String.format("%,d 원", emp.getSal())%></td>
                            <td><%=emp.getHiredate()%></td>
                            <td><span class="badge badge-dept"><%=emp.getDeptno()%></span></td>
                            <td><%=emp.getDname()%></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr><td colspan="8" class="text-muted">사원이 없습니다.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // 검색 필터
    $("#searchInput").on("keyup", function() {
        let value = $(this).val().toLowerCase();
        $("#empTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
</script>

</body>
</html>
