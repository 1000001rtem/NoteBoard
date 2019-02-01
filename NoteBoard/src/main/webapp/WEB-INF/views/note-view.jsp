<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 08.01.2019
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="NoteBoard"/>
</jsp:include>

<div class="categoryContainer">
    <div class="categoryBox">
        <a href="">Категории</a>
        <c:forEach var="category" items="${categories}" varStatus="status">
            <a href="noteByCategory/boardId=${board.id}&categoryId=${category.id}">${category.name}</a>
        </c:forEach>
        <form action="create-category/${board.id}" method="get">
            <button type="submit">Новая</button>
        </form>
    </div>
</div>

<div class="noteContainer">
    <c:forEach var="note" items="${notes}" varStatus="status">
        <p>${note.data}</p>
    </c:forEach>
</div>


<jsp:include page="foot.jsp"/>


