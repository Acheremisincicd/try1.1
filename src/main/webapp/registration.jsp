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
    <title>leopet || sign in</title>
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
    <link rel="stylesheet" href="css/nice-select.css">
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
<br/>

    <header class="page-header">
        <div style="text-align: center">
            <h1 style="color: rgb(22, 53, 88)">Registration Page.</h1>
        </div>
    </header>

    <div class="container mt-4">
        <form action="register" id="registration-form" method="POST" enctype="multipart/form-data" novalidate>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="user-name">User name:</label>
                    <input type="text" class="form-control" value="${form.firstName}" id="user-name" name="user_name"
                           placeholder="Please, enter your user-name">
                    <c:if test="${not empty requestScope.registrationErrors.errorFirstName}">
                        <div id="registrationFirstNameError" class="alert alert-danger" role="alert">
                            <p>${requestScope.registrationErrors.errorFirstName}<p>
                        </div>
                    </c:if>
                </div>
                <div class="valid-feedback">
                    Correct.
                </div>
                <div class="invalid-feedback">
                    Incorrect!
                </div>
                <div class="col-md-4 mb-3">
                    <label for="user-surname">User surname:</label>
                    <input type="text" class="form-control" value="${form.lastName}" id="user-surname"
                           name="user_surname" placeholder="Please, enter your user-surname">
                    <c:if test="${not empty requestScope.registrationErrors.errorLastName}">
                        <div id="registrationLastNameError" class="alert alert-danger" role="alert">
                            <p>${requestScope.registrationErrors.errorLastName}<p>
                        </div>
                    </c:if>
                    <div class="valid-feedback">
                        Correct.
                    </div>
                    <div class="invalid-feedback">
                        Incorrect!
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="user-password">User password:</label>
                    <input type="password" class="form-control" id="user-password" name="user_password"
                           placeholder="Please, enter your user-pwd">
                    <c:if test="${not empty requestScope.registrationErrors.errorPassword}">
                        <div id="registrationPasswordError" class="alert alert-danger" role="alert">
                            <p>${requestScope.registrationErrors.errorPassword}<p>
                        </div>
                    </c:if>
                    <div class="valid-feedback">
                        Correct.
                    </div>
                    <div class="invalid-feedback">
                        Incorrect!
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="user-password-confirm">Repeat password:</label>
                    <input type="password" class="form-control" id="user-password-confirm" name="confirm_password"
                           placeholder="Please, enter your user-pwd">
                    <c:if test="${not empty requestScope.registrationErrors.errorConfirmPassword}">
                        <div id="registrationConfirmPasswordError" class="alert alert-danger" role="alert">
                            <p>${requestScope.registrationErrors.errorConfirmPassword}<p>
                        </div>
                    </c:if>
                    <div class="valid-feedback">
                        Correct.
                    </div>
                    <div class="invalid-feedback">
                        Incorrect!
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="user-email">Email:</label>
                    <input type="text" class="form-control" value="${form.email}" id="user-email" name="user_email"
                           placeholder="Please, enter your email">
                    <c:if test="${not empty requestScope.registrationErrors.errorEmail}">
                        <div id="registrationEmailError" class="alert alert-danger" role="alert">
                            <p>${requestScope.registrationErrors.errorEmail}<p>
                        </div>
                    </c:if>
                    <div class="valid-feedback">
                        Correct.
                    </div>
                    <div class="invalid-feedback">
                        Incorrect!
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="notification" name="notification" value="true">
                    <label class="form-check-label" for="notification">
                        Receive mail notifications
                    </label>
                </div>
            </div>

             <!-- For image upload -->
                                  <div class="col-sm-12">
                                      <div class="row">
                                          <div class="col-xs-4">
                                              <label class="pass">Add avatar image:</label>
                                          </div>
                                          <div class="col-xs-8">
                                              <input type="file" name="file"/>
                                              <c:if test="${not empty requestScope.registrationErrors.errorImage}">
                                                  <div id="registrationImageError" class="alert alert-danger" role="alert">
                                                      <p>${requestScope.registrationErrors.errorImage}<p>
                                                  </div>
                                              </c:if>
                                          </div>
                                      </div>
                                  </div>

            <!-----Captcha---->
            <tag:CaptchaTag /><br>
            <button class="btn btn-primary" type="submit" style= "background-color: rgb(22, 53, 88)">Submit form</button>
        </form>
    </div>
    <br>

    <script src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="./js/validation.js"></script>

    <!-- footer part start-->
    <footer class="footer_area padding_top">
        <div class="container">
            <div class="row justify-content-center ">
                <div class="col-lg-8 col-xl-6">
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
                        <p class="footer-text"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="ti-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
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
    <script src="js/formValidation.js"></script>
    <!-- <script src="js/formValidationJQuery.js"></script> -->
  </body>

 </html>