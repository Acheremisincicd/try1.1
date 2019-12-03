package com.epam.preprod.biletska.servlets;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.impl.ImageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.raiseExceptionIfNoParam;

/**
 * Image servlet.
 */
public class ImageServlet extends HttpServlet {

    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        imageService = raiseExceptionIfNoParam(config, Constants.IMAGE_SERVICE, ImageService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, String> errors = new HashMap<>();
        User loggedUser = (User) session.getAttribute(CommonDefinitions.LOGGED_USER);
        if (!imageService.uploadImage(req, loggedUser.getEmail())) {
            errors.put(CommonDefinitions.ERROR_IMAGE, "Wrong extension");
        }
    }

    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(CommonDefinitions.LOGGED_USER) == null) {
            request.getRequestDispatcher(Constants.INDEX_JSP).forward(request, response);
        }

        User loggedUser = (User) session.getAttribute(CommonDefinitions.LOGGED_USER);
        String fileName = imageService.getActualImageLocation(request.getServletContext().getInitParameter(Constants.INIT_IMAGES_ADDRESS))
                + File.separator + imageService.login2ImageName(loggedUser.getEmail())
                + ".png";

        String mimeType = request.getServletContext().getMimeType(fileName);
        response.setContentType(mimeType);
        /**
         *  Read if file exists
         */
        if (new File(fileName).exists()) {
            try (ServletOutputStream out = response.getOutputStream(); FileInputStream fin = new FileInputStream(fileName)) {
                BufferedInputStream bin = new BufferedInputStream(fin);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                int ch;
                while ((ch = bin.read()) != -1) {
                    bout.write(ch);
                }
                bout.flush();
            }
        }
    }

    private boolean checkImage(String filename) {
        return new File(filename).exists();
    }
}