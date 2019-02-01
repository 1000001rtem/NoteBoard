<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 04.01.2019
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="NoteBoard"/>
</jsp:include>

<c:forEach var="board" items="${boards}" varStatus="status">
    <jsp:include page="board-card.jsp">
        <jsp:param name="id" value="${board.id}"/>
        <jsp:param name="boardName" value="${board.name}"/>
        <jsp:param name="boardDate" value="${board.date.getTime()}"/>
    </jsp:include>
</c:forEach>

<form action="/create-board" method="get">
    <button type="submit">Create</button>
</form>



<h1 inline="text">Hello </h1>
<form action="/logout" method="post">
    <input type="submit" value="Sign Out"/>
</form>


<jsp:include page="foot.jsp"/>
