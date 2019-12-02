package com.epam.preprod.biletska.servlets;

import com.epam.preprod.biletska.dao.IUserDao;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.ICaptchaService;
import com.epam.preprod.biletska.services.IImageService;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.validation.impl.ValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The RegistrationServlet servlet test.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    IUserService userService;
    @Mock
    ICaptchaService captchaService;
    @InjectMocks
    RegistrationServlet registrationServlet;
    @Mock
    User user;
    @Mock
    IUserDao userDao;
    @Mock
    ServletContext servletContext;
    @Mock
    IImageService imageService;

    ValidationService validationService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        validationService = new ValidationService();
    }

    /**
     * Should set attributes and forward to jsp.
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Deprecated
    @Test
    public void shouldSetAttributesAndForwardToJsp() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(session.getAttribute("registrationErrors")).thenReturn("registrationErrors");
        when(session.getAttribute("form")).thenReturn("form");
        registrationServlet.doGet(request, response);
        verify(request, times(1)).setAttribute(eq("captchaKey"), anyLong());
        verify(request, times(1)).setAttribute("form", "form");
        verify(request, times(1)).setAttribute("registrationErrors", "registrationErrors");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    /**
     * Should not register user with invalid parameters
     *
     * @throws IOException the io exception
     */
    @Test
    public void shouldNotRegisterUserWithInvalidParameters() throws IOException, ServletException {
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("validationService")).thenReturn(validationService);
        when(request.getParameter(anyString())).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(captchaService.removeExpiredCaptcha(() -> request)).thenReturn(true);
        when(userService.addUser(user)).thenReturn(user);
        doNothing().when(captchaService).draw(any(), any());
        when(session.getServletContext()).thenReturn(servletContext);
        when(session.getServletContext().getAttribute("userService")).thenReturn(userService);
        when((imageService.uploadImage(anyObject(), anyString()))).thenReturn(true);
        registrationServlet.doPost(request, response);
        verify(response, times(1)).sendRedirect("./register");
    }

    /**
     * Should register user with valid parameters.
     *
     * @throws IOException the io exception
     */
    @Test
    public void shouldRegisterUserWithValidParameters() throws IOException, ServletException {
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("validationService")).thenReturn(validationService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("user_name")).thenReturn("Olha");
        when(request.getParameter("user_surname")).thenReturn("Biletska");
        when(request.getParameter("user_password")).thenReturn("Biletska1");
        when(request.getParameter("confirm_password")).thenReturn("Biletska1");
        when(request.getParameter("user_email")).thenReturn("email@google.com");
        when(request.getParameter("notification")).thenReturn("true");
        when(captchaService.removeExpiredCaptcha(any())).thenReturn(true);
        when(userService.addUser(user)).thenReturn(user);
        doNothing().when(captchaService).draw(any(), any());
        when(session.getServletContext()).thenReturn(servletContext);
        when(session.getServletContext().getAttribute("userDao")).thenReturn(userDao);
        when((imageService.uploadImage(anyObject(), anyString()))).thenReturn(true);
        registrationServlet.doPost(request, response);
        verify(response, times(1)).sendRedirect("null/index.jsp");
    }
}
