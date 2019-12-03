<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<li class="nav-item dropdown">
     <a class="nav-link dropdown-toggle text-light" id="navbarDropdown" data-toggle="dropdown"
          aria-haspopup="true" aria-expanded="false"><fmt:message key="header.language"/></a>
     <div class="dropdown-menu" aria-labelledby="dropdown09">
         <a class="dropdown-item" href="<my:replaceParam name='lang' value='ru' />"><span><fmt:message key="header.lang.ru"/></span></a>
         <a class="dropdown-item" href="<my:replaceParam name='lang' value='en' />"><span><fmt:message key="header.lang.eng"/></span></a>
     </div>
</li>