<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data View</title>
</head>
<body>
    <h1>Data View</h1>
    <table border="1">
        <tr>
            <c:forEach var="columnName" items="${columnNames}">
                <th>${columnName}</th>
            </c:forEach>
        </tr>
        <c:forEach var="i" begin="0" end="${rowCount - 1}">
            <tr>
                <c:forEach var="columnName" items="${columnNames}">
                    <td>${model.getValue(columnName, i)}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
