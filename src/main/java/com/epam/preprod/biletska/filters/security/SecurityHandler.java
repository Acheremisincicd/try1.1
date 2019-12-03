package com.epam.preprod.biletska.filters.security;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Security handler
 */
public class SecurityHandler {

    private Map<String, List<String>> stringListMap;

    public SecurityHandler(Map<String, List<String>> stringListMap) {
        this.stringListMap = stringListMap;
    }

    public boolean isPathAcceptable(String role, String url) {
        if (StringUtils.isEmpty(role) || StringUtils.isEmpty(url)) {
            return false;
        }
        List<String> urls = stringListMap.get(role);
        return Objects.nonNull(urls) && (urls.contains(url) || urls.contains("/*"));
    }
}
