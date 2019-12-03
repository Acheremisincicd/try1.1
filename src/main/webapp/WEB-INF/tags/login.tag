<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${empty loggedUser}">
        <form action="login" method="POST" name="loginForm" style="float: right; padding-top : 15px; padding-right: 50px">
            <input name="login_enter" id="login_enter" type="text" placeholder="Enter your login" value="olha@example.com">
            <input name="password_enter" id="password_enter" type="password" placeholder="Enter your password">
            <input type="submit" class="btn btn-primary" value=<fmt:message key="header.login"/> style= "background-color: rgb(22, 53, 88)">
            <a href="register" class="btn btn-primary" style= "background-color: rgb(22, 53, 88)"><span class="register"><fmt:message key="header.registration"/></span></a>
            <input type="hidden" name="currentURI" value="${pageContext.request.requestURI}">
        </form>
    </c:when>
    <c:otherwise>
        <form action="logout" method="POST" name="logoutForm" style= "float: right; padding-top : 15px; padding-right: 50px">
            <p style ="color:white"><span><img width="39" height="39" src="image?image_id=${loggedUser.key}"/></span><fmt:message key="login.header.greening"/>, ${loggedUser.firstName} ${loggedUser.lastName}
                <input type="submit" class="btn btn-primary" value=<fmt:message key="header.logout"/> style= "background-color: rgb(22, 53, 88)">
            </p>
            <input type="hidden" name="currentURI" value="${pageContext.request.requestURI}">
        </form>
    </c:otherwise>
</c:choose>
