<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${empty loggedUser}">
        <form action="login" method="POST" name="loginForm" style= "float: right; padding-top : 15px; padding-right: 50px">
            <input name="login_enter" id="login_enter" type="text" placeholder="Enter your login">
            <input name="password_enter" id="password_enter" type="password" placeholder="Enter your password">
            <input type="submit" class="btn btn-primary" value="login" style= "background-color: rgb(22, 53, 88)">
            <a href="register" class="btn btn-primary" style= "background-color: rgb(22, 53, 88)"><span class="register">Register</span></a>
            <input type="hidden" name="currentURI" value="${pageContext.request.requestURI}">
        </form>
    </c:when>
    <c:otherwise>
        <form action="logout" method="POST" name="logoutForm" style= "float: right; padding-top : 15px; padding-right: 50px">
            <p style ="color:white"><span><img width="39" height="39" src="image?image_id=${loggedUser.key}"/></span>Good
                day ${loggedUser.firstName} ${loggedUser.lastName}
                <input type="submit" class="btn btn-primary" value="logout" style= "background-color: rgb(22, 53, 88)">
            </p>
            <input type="hidden" name="currentURI" value="${pageContext.request.requestURI}">
        </form>
    </c:otherwise>
</c:choose>
