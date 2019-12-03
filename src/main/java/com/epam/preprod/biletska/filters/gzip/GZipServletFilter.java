package com.epam.preprod.biletska.filters.gzip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GZIP filter
 */
public class GZipServletFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(GZipServletFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("GZIP filter starts");

        HttpServletRequest  httpRequest  = (HttpServletRequest)  request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ( !acceptsGZipEncoding(httpRequest) ) {
            chain.doFilter(request, response);
            return;
        }
        httpResponse.addHeader("Content-Encoding", "gzip");
        GZipServletResponseWrapper gzipResponse =
                new GZipServletResponseWrapper(httpResponse);
        chain.doFilter(request, gzipResponse);
        gzipResponse.close();
        LOG.debug("GZIP filter ends");
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding =
                httpRequest.getHeader("Accept-Encoding");

        return acceptEncoding != null &&
                acceptEncoding.contains("gzip");
    }
}