<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>leopet || services</title>
    <link rel="icon" href="img/favicon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <!-- themify CSS -->
    <link rel="stylesheet" href="css/themify-icons.css">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="css/flaticon.css">
    <!-- font awesome CSS -->
    <link rel="stylesheet" href="css/magnific-popup.css">
    <!-- style CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<c:set var="isUserLogged" value="${not empty loggedUser}"/>
<!--::header part start::-->
<header class="header_area">
    <div class="sub_header">
        <div class="container">
            <div class="row align-items-center" style="height: 30px">
                <div class="col-4 col-md-4 col-xl-6" style="height: 50px">
                    <div id="logo">
                        <a href="index.jsp"><img src="img/logo1.jpg" alt="" title="" style="height: 50px"/></a>
                    </div>
                </div>
                <div class="col-8 col-md-8 col-xl-6 " style="height: 50px">
                    <div class="sub_header_social_icon float-right">
                        <a href="#"><i class="flaticon-phone"></i>+38 (063) 373 - 33 -33</a>
                        <a href="./register" class="btn_1 d-none d-md-inline-block">Sign in</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

     <!-- Login tag  -->
                             <div class="header">
                                 <ul class="logreg">
                                     <li><tag:login/> </li>
                                 </ul>
                             </div>
                             <!-- Login tag ends -->

    <div class="main_menu">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <i class="ti-menu"></i>
                        </button>

                        <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
                            <ul class="navbar-nav">
                                <li class="nav-item active">
                                    <a class="nav-link active" href="index.jsp"><fmt:message key="header.home"/></a>
                                </li>
                                <li class="nav-item">
                                    <a href="about.html" class="nav-link"><fmt:message key="header.about"/></a>
                                </li>
                                <li class="nav-item">
                                    <a href="./product" class="nav-link"><fmt:message key="header.catalogue"/></a>
                                </li>
                                <li class="nav-item">
                                    <a href="gallery.html" class="nav-link"><fmt:message key="header.gallery"/></a>
                                </li>
                                <my:locale/>
                                <li class="nav-item">
                                    <a href="blog.html" class="nav-link"><fmt:message key="header.license"/></a>
                                </li>
                                <li class="nav-item">
                                    <a href="contact.html" class="nav-link"><fmt:message key="header.contacts"/></a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header part end-->

<br />
<!-- Product filter -->
<div class="container">
<%--    <div class="row">--%>
        <div class="col-lg-12 col-md-12 col-sm-12">
            <table class="common-table" border="0">
                <tr>
                    <td colspan="2" style="text-align: right">
                        <span style="color: red;">${shoppingCartErrors.errorItemPickup}</span>
                    </td>
                </tr>
                <tr>
                    <!-- Form part of table -->
                    <td style="vertical-align: top; padding: 80px 10px 10px;">
                            <form action="product" id="filterProduct">
                                <table id="filter-table">
                                    <!-- Name -->
                                    <tr>
                                        <td width="80px"><label><fmt:message key="product.filter.text.name"/>:</label></td>
                                        <td><input name="searchText" id="searchText" style="width: 80%" value="${filterForm.name}"/></td>
                                    </tr>
                                    <!-- Prices -->
                                    <tr>
                                        <td><label><fmt:message key="product.filter.text.price.from"/>:</label></td>
                                        <td><label><input name="minPrice" value="${filterForm.minPrice}"/></label></td>
                                    </tr>
                                    <tr>
                                        <td><label><fmt:message key="product.filter.text.price.to"/>:</label></td>
                                        <td><label><input name="maxPrice" value="${filterForm.maxPrice}"/></label></td>
                                    </tr>
                                    <!-- Categories -->
                                    <tr>
                                        <td colspan="2"><label><fmt:message key="product.filter.text.category"/>:</label></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                           <select id="category" name="category" size="3" multiple>
                                               <c:forEach items="${productCategories}" var="category">
                                               <option value="${category.name}" <c:if test="${category.value}">selected="true"</c:if>">${category.description}</option>
                                               </c:forEach>
                                           </select>
                                        </td>
                                    </tr>
                                    <!-- Manufacturers -->
                                    <tr>
                                        <td colspan="2"><label><fmt:message key="product.filter.text.manufacturer"/>:</label></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="margin-right: 10px">
                                           <select id="manufacturer" name="manufacturer" size="3" multiple>
                                               <c:forEach items="${productManufacturers}" var="manufacturer">
                                                   <option value="${manufacturer.name}" <c:if test="${manufacturer.value}">selected="true"</c:if>">${manufacturer.description}</option>
                                               </c:forEach>
                                           </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: center; padding-top: 10px">
                                            <input type="submit" value="<fmt:message key="product.filter.button.filter"/>"/>
                                        </td>
                                        <td style="text-align: center; padding-top: 10px">
                                            <input type="button" value="<fmt:message key="product.filter.button.clear"/>" onclick="clearFilterForm($('#filterProduct'));"/>
                                            <input type="hidden" name="clear" value="false"/>
                                        </td>
                                    </tr>

                                </table>
                                <input type="hidden" name="itemCount" value="${itemCount}"/>
                            </form>
                    </td>
                    <!-- Product filter result part of table -->
                    <td style="vertical-align: top;">
                            <c:if test="${not empty direction}">
                                <c:set var="directionParam" value="&direction=${direction}"/>
                            </c:if>
                                <c:choose>
                                    <c:when test="${empty productList}">
                                            <span>There are no products!</span>
                                    </c:when>
                                    <c:otherwise>
                                        <table border="0" cellpadding="5" cellspacing="5" id="product-table">
                                            <tr>
                                                <!-- Items on thr page cell -->
                                                <th colspan="3" style="text-align: right;">
                                                    <select name="itemCount" onchange="window.location.href='product?itemCount='+$(this)[0].value">
                                                        <option name="5"<c:if test="${itemCount eq 5}">selected="true"</c:if>>5</option>
                                                        <option name="10"<c:if test="${itemCount eq 10}">selected="true"</c:if>>10</option>
                                                        <option name="15"<c:if test="${itemCount eq 15}">selected="true"</c:if>>15</option>
                                                    </select>
                                                </th>
                                                <td>&nbsp;</td>
                                                <!-- Cart cell -->
                                                <td colspan="2">
                                                    <c:choose>
                                                        <c:when test="${not empty shoppingCart and fn:length(shoppingCart.productItems) gt 0}">
                                                            <span><a href="cart?itemCount=${itemCount}"><fmt:message key="product.header.cart"/> [${fn:length(shoppingCart.productItems)}]</a></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span><fmt:message key="product.header.cart.empty"/></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th id="product-header-name" style="text-align: center; width: 70%;"><a href="product?sort=name${directionParam}&itemCount=${itemCount}"><fmt:message key="product.table.header.name"/></a><c:if test="${(not empty direction) and (sort == 'name')}">&nbsp;<img src="images/sort-${direction}.jpg"/></c:if></th>
                                                <th id="product-header-category" style="text-align: center"><a href="product?sort=category${directionParam}&itemCount=${itemCount}"><fmt:message key="product.table.header.category"/></a><c:if test="${(not empty direction) and (sort == 'category')}">&nbsp;<img src="images/sort-${direction}.jpg"/></c:if></th>
                                                <th id="product-header-manufacturer"style="text-align: center"><a href="product?sort=manufacturer${directionParam}&itemCount=${itemCount}"><fmt:message key="product.table.header.manufacturer"/></a><c:if test="${(not empty direction) and (sort == 'manufacturer')}">&nbsp;<img src="images/sort-${direction}.jpg"/></c:if></th>
                                                <th id="product-header-price"style="text-align: center"><a href="product?sort=price${directionParam}&itemCount=${itemCount}"><fmt:message key="product.table.header.price"/></a><c:if test="${(not empty direction) and (sort == 'price')}">&nbsp;<img src="images/sort-${direction}.jpg"/></c:if></th>
                                                <th colspan="2">&nbsp;</th>
                                            </tr>
                                            <c:forEach var="product" items="${productList}">
                                            <tr>
                                                <td id="product-cell-name">${(empty product.description) ? "-" : product.description}</td>
                                                <td id="product-cell-category">${(empty product.category.description) ? "-" : product.category.description}</td>
                                                <td id="product-cell-manufacturer">${(empty product.manufacturer.name) ? "-" : product.manufacturer.name}</td>
                                                <td id="product-cell-price">${(empty product.price) ? "-" : product.price}</td>
                                                <c:remove  var="disabledAddToCart"/>
                                                <c:if test="${empty product.price}">
                                                    <c:set value="disabled" var="disabledAddToCart"/>
                                                </c:if>
                                                <td>
                                                    <!-- Add to cart form -->
                                                    <form name="cartForm" action="cart" method="POST">
                                                    <table>
                                                        <tr>
                                                            <td>
                                                                <input type="hidden" name="productId" value="${product.productId}"/>
                                                                <input type="hidden" name="action" value="buy"/>
                                                                <input type="hidden" name="itemCount" value="${itemCount}"/>
                                                                <input type="number" name="productAmount" ${disabledAddToCart} value="0" style="width:50px;" onchange="productAmountChecking(this, 10)" title="Only limited amount allowed"/>
                                                            </td>
                                                            <td width="10px;">
                                                                <input type="submit" value="<fmt:message key="product.button.add"/>" ${disabledAddToCart}/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    </form>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </table>
                                    </c:otherwise>
                                </c:choose>

                            <%--For displaying Page numbers.
                            The when condition does not display a link for the current page--%>
                            <table border="0" cellpadding="5" cellspacing="5">
                                <tr>
                                    <c:forEach begin="1" end="${pages}" var="i">
                                        <c:choose>
                                            <c:when test="${page1 eq i}">
                                                <td><span style="color: silver;">${i}</span></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a href="product?${filterQuery}&page=${i}">${i}</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <%--For displaying Previous link except for the 1st page --%>
                                    <c:if test="${page1 gt 1}">
                                    <td><a href="product?${filterQuery}&page=${page1 - 1}"><fmt:message key="product.footer.paging.prev"/></a></td>
                                    </c:if>
                                    <td>&nbsp;</td>
                                    <%--For displaying Next link --%>
                                    <c:if test="${page1 lt pages}">
                                    <td><a href="product?${filterQuery}&page=${page1 + 1}"><fmt:message key="product.footer.paging.next"/></a></td>
                                    </c:if>
                                </tr>
                            </table>
                    </td>

                </tr>

            </table>
        </div>
<%--    </div>--%>
</div>
<!--::Product filter part end::-->

<!-- service part start-->
<section class="service_part section_padding">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="section_tittle text-center">
                    <img src="img/logo1.jpg" alt="">
                    <h2>Products</h2>
                    <p>On the product page you will find the quick selection of goods, according to the parameters you
                        need,
                        in each section there are simple and convenient filters for technical specifications, as well as
                        our recommendations for quick selection.</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-sm-6">
                <div class="single_service_part">
                    <img src="img/iphone4.jpg" alt="#">
                    <h3>Old Phones' Collection</h3>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="single_service_part">
                    <img src="img/iphone11.jpg" alt="#">
                    <h3>New Phones' Collection</h3>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="single_service_part">
                    <img src="img/airlap.jpg" alt="#">
                    <h3>LapTops</h3>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- service part end-->


<!-- client review part here -->
<section class="client_review">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-7">
                <div class="client_review_content owl-carousel">
                    <div class="singke_client_review">
                        <img src="img/phone1.jpg" alt="#">
                        <h4>" Make your life colorful with IWorld! "</h4>
                    </div>
                    <div class="singke_client_review">
                        <img src="img/laptop1.jpg" alt="#">
                        <h4>" Break the rules with Us! "</h4>
                    </div>
                    <div class="singke_client_review">
                        <img src="img/phone2.jpg" alt="#">
                        <h4>" Think positively and smile often! "</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- client review part end -->

<!-- footer part start-->
<footer class="footer_area padding_top">
    <div class="container">
        <div class="row justify-content-center ">
            <div class="col-lg-8 col-xl-6">
                <div class="subscribe_part_text text-center">
                    <h2>Subscribe Newsletter</h2>
                    <div class="subscribe_form">
                        <form action="#" name="#">
                            <div class="input-group align-items-center">
                                <input type="email" class="form-control" placeholder="enter your email">
                                <div class="subscribe_btn input-group-append">
                                    <div class="btn_1">free trail</div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row justify-content-between section_padding">
            <div class="col-xl-2 col-sm-6 col-md-3 mb-4 mb-xl-0 single-footer-widget">
                <h4>Menu</h4>
                <ul>
                    <li><a href="#">home</a></li>
                    <li><a href="#">about</a></li>
                    <li><a href="#">shop</a></li>
                    <li><a href="#">contact</a></li>
                </ul>
            </div>
            <div class="col-xl-2 col-sm-6 col-md-3 mb-4 mb-xl-0 single-footer-widget">
                <h4>contact</h4>
                <ul>
                    <li><a href="#">+38 (063) 373 - 33 -33</a></li>
                    <li><a href="#">reserve@manicure.com</a></li>
                </ul>
            </div>
            <div class="col-xl-2 col-sm-6 col-md-3 mb-4 mb-xl-0 single-footer-widget">
                <h4>Address</h4>
                <ul>
                    <li><a href="#">St. August 23rd, 33a, Kharkiv, Ukraine</a></li>
                    <li><a href="#">Get Direction</a></li>
                </ul>
            </div>
            <div class="col-xl-2 col-sm-6 col-md-3 mb-4 mb-xl-0 single-footer-widget">
                <h4>Opening Hour</h4>
                <ul>
                    <li><a href="#">Mon-Fri (9.00-6.00)</a></li>
                    <li><a href="#">Sat-Sun (Closed)</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="copyright_text">
                    <img src="img/footer_logo.png" alt="#">
                    <p class="footer-text">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                        All rights reserved | This template is made with <i class="ti-heart" aria-hidden="true"></i> by
                        <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- footer part end-->
<script type="text/javascript" src="js/main.js"></script>
<!-- jquery plugins here-->

<!-- jquery -->
<script src="js/jquery-1.12.1.min.js"></script>
<!-- popper js -->
<script src="js/popper.min.js"></script>
<!-- bootstrap js -->
<script src="js/bootstrap.min.js"></script>
<!-- counterup js -->
<script src="js/jquery.counterup.min.js"></script>
<!-- waypoints js -->
<script src="js/waypoints.min.js"></script>
<!-- easing js -->
<script src="js/jquery.magnific-popup.js"></script>
<!-- particles js -->
<script src="js/owl.carousel.min.js"></script>
<!-- custom js -->
<script src="js/custom.js"></script>
<script src="js/main.js"></script>
</body>

</html>