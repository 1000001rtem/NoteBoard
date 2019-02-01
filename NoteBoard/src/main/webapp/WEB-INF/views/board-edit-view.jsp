<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Eremin Artem
  Date: 26.01.2019
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="head.jsp">
    <jsp:param name="title" value="NoteBoard"/>
</jsp:include>

<form:form method="post" action="/board-save" modelAttribute="board">
    <form:input path="id"/>
    <form:input path="name"/>
    <button type="submit">Save</button>
</form:form>

<jsp:include page="foot.jsp"/>
