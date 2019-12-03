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
    <title>leopet || Home</title>
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

 <!-- Products in cart -->
    <div class="container main-header">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <!-- Common table container -->
                <table class="common-table" border="0">
                    <!-- Errors from the server -->
                    <tr>
                        <td colspan="2">
                            <span>${shoppingCartErrors.errorItemPickup}</span>
                        </td>
                    </tr>
                    <tr>
                        <!-- Products in Cart -->
                        <td style="vertical-align: top;">
                            Order has been checkedout. Thank you!
                        </td>
                        <!-- End of Products in Cart -->
                    </tr>
                </table>
                <!-- End of Common table container -->
            </div>
        </div>
    </div>

<!-- banner part start-->
<section class="banner_part">
    <div class="container">
        <div class="row align-content-center">
            <div class="col-lg-7 col-xl-6">
                <div class="banner_text">
                    <h5>Welcome to IWorld</h5>
                    <h1>Follow Your
                        Dreams</h1>
                    <a href="./product" class="btn_1">OUR PRODUCTS</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- banner part start-->

<!-- service part start-->
<section class="service_part section_padding services_bg">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="section_tittle text-center">
                    <img src="img/logo1.jpg" alt="">
                    <h2>We Provide Best Services</h2>
                    <p>Apple technology is incredible. Therefore, the approach to its repair should be at the level.
                        Our specialists have been trained, passed exams perfectly and are allowed to work in Apple
                        systems.
                        They have all the necessary equipment for work, original parts and experience. Choose a
                        professional
                        approach!</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-sm-6">
                <div class="single_service_part">
                    <img src="img/apple.png" alt="#">
                    <h3>Repair any Apple gadgets</h3>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="single_service_part">
                    <img src="img/phone4.jpg" alt="#">
                    <h3>Free device diagnostics</h3>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="single_service_part">
                    <img src="img/guarranty.png" alt="#">
                    <h3>180 days repair warranty</h3>
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