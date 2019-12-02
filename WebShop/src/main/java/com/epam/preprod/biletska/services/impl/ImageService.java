package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.services.IImageService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Service for checking and uploading image to the server.
 */
public class ImageService implements IImageService {

    private List<String> allowedExtensions;
    private static final String WEB_PATH_SEPARATOR = "/";
    private static final String SPECIAL_CHARS_REPLACER = "_";

    public ImageService() {
        allowedExtensions = new LinkedList<>();
        allowedExtensions.add(".jpeg");
        allowedExtensions.add(".jpg");
        allowedExtensions.add(".png");
    }

    /**
     * Upload file to server.
     *
     * @param httpServletRequest httpServletRequest
     * @param login              login
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Override
    public boolean uploadImage(HttpServletRequest httpServletRequest, String login) throws IOException, ServletException {
        Part part = httpServletRequest.getPart("file");
        if ((part.getSize() < 1) || (part.getSize() > 0 && checkExtension(part))) {
            saveFile(part, httpServletRequest, login);
            return true;
        }
        return false;
    }

    public String getActualImageLocation(String preconfiguredLocation) {
        String usedSeparator = null;
        if (preconfiguredLocation.lastIndexOf(File.separator) >= 0) {
            usedSeparator = File.separator;
        } else if (preconfiguredLocation.lastIndexOf(WEB_PATH_SEPARATOR) >= 0) {
            usedSeparator = WEB_PATH_SEPARATOR;
        }

        /**
         *  default value if preconfigured couldn't be parsed
         */
        String imageBasePath = "webshop-images";
        if (usedSeparator != null) {
            imageBasePath = preconfiguredLocation.substring(preconfiguredLocation.lastIndexOf(usedSeparator) + 1);
        }

        String userHomeDirectory = System.getProperty("user.home");
        return userHomeDirectory + File.separator + imageBasePath;
    }

    public String login2ImageName(String login) {
        assert (login != null && login.length() > 0) : "Login shouldn't be empty";
        return login.replace("@", SPECIAL_CHARS_REPLACER);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String string : items) {
            if (string.trim().startsWith("filename")) {
                return string.substring(string.indexOf("."), string.length() - 1);
            }
        }
        return StringUtils.EMPTY;
    }

    private boolean checkExtension(Part part) {
        return allowedExtensions.contains(extractFileName(part));
    }

    private void saveFile(Part part, HttpServletRequest httpServletRequest, String login) throws IOException {
        String configuredImagePath = httpServletRequest.getServletContext().getInitParameter(Constants.INIT_IMAGES_ADDRESS);
        String actualImageLocation = getActualImageLocation(configuredImagePath);
        File imageDir = new File(actualImageLocation);
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
        String file = actualImageLocation + File.separator + login2ImageName(login) + ".png";
        part.write(file.toLowerCase());
    }
}
