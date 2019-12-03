package com.epam.preprod.biletska.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 */
@WebFilter(
        urlPatterns = "/*",
        initParams = @WebInitParam(name = "encoding", value = "UTF-8")
)
public class EncodingFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(EncodingFilter.class);
    private String encoding;

    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        LOG.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOG.trace("Requested URI: " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Requested encoding = null, encoding set to " + encoding);
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml = " + encoding);
        LOG.debug("Filter initialization finished");
    }
}