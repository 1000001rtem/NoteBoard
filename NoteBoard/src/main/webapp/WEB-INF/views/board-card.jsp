<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 08.01.2019
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="NoteBoard"/>
</jsp:include>

<a href="note/${param.id}">
    <div class="boardContainer">
        <div class="boardName">
            <h3>${param.boardName}</h3>
        </div>
        <div class="boardDate">
            <h3>${param.boardDate}</h3>
        </div>
        <form action="/delete-board/${param.id}" method="get">
            <button type="submit">Delete</button>
        </form>
        <form action="/edit-board/${param.id}" method="get">
            <button type="submit">Edit</button>
        </form>
    </div>
</a>

<jsp:include page="foot.jsp"/>
