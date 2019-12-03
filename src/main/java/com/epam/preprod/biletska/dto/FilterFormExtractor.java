package com.epam.preprod.biletska.dto;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestParamInt;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestParamString;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestPramBool;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.setRequestAttr;

/**
 * Class for extracting the filterFormDto from the request.
 */
public class FilterFormExtractor {

    private static final int DEFAULT_MIN_PRICE = 0;
    private static final int DEFAULT_MAX_PRICE = 1000000;

    /**
     * Extract filter form bean.
     *
     * @param httpServletRequest the http servlet request
     * @param manufacturerList   the manufacturer list
     * @param categoryList       the category list
     * @return the filter form bean
     */
    public FilterFormDto extract(HttpServletRequest httpServletRequest, List<ManufacturerDto> manufacturerList, List<CategoryDto> categoryList) {
        FilterFormDto filterFormBean = new FilterFormDto();
        if (getRequestPramBool(httpServletRequest, "clear").orElse(false)) {
            filterFormBean.setMinPrice(new BigDecimal(DEFAULT_MIN_PRICE));
            filterFormBean.setMaxPrice(new BigDecimal(DEFAULT_MAX_PRICE));
            setRequestAttr(httpServletRequest, "minPrice", DEFAULT_MIN_PRICE);
            setRequestAttr(httpServletRequest, "maxPrice", DEFAULT_MAX_PRICE);
            return filterFormBean;
        }

        getRequestParamString(httpServletRequest, "searchText").ifPresent(s -> filterFormBean.setName(s));

        Optional<Integer> minPriceOptional = getRequestParamInt(httpServletRequest, "minPrice");
        filterFormBean.setMinPrice(new BigDecimal(minPriceOptional.orElse(DEFAULT_MIN_PRICE)));

        Optional<Integer> maxPriceOptional = getRequestParamInt(httpServletRequest, "maxPrice");
        filterFormBean.setMaxPrice(new BigDecimal(maxPriceOptional.orElse(DEFAULT_MAX_PRICE)));

        setRequestAttr(httpServletRequest, "minPrice", minPriceOptional.orElse(DEFAULT_MIN_PRICE));
        setRequestAttr(httpServletRequest, "maxPrice", maxPriceOptional.orElse(DEFAULT_MAX_PRICE));

        filterFormBean.setListOfCategories(applySelectedCategories(httpServletRequest, categoryList));
        filterFormBean.setListOfManufacturers(applySelectedManufacturers(httpServletRequest, manufacturerList));

        if (filterFormBean.isFilteringEnabled()) {
            return filterFormBean;
        }
        return null;
    }

    private List<CategoryDto> applySelectedCategories(HttpServletRequest httpServletRequest, List<CategoryDto> categoryDtoList) {
        Optional.ofNullable(httpServletRequest.getParameterValues("category")).ifPresent(reqParams -> {
            categoryDtoList.stream().forEach(catDto -> catDto.setValue(Stream.of(reqParams).anyMatch(reqParamValue -> reqParamValue.equals(catDto.getName()))));
        });
        return categoryDtoList;
    }

    private List<ManufacturerDto> applySelectedManufacturers(HttpServletRequest httpServletRequest, List<ManufacturerDto> manufacturerDtoList) {
        Optional.ofNullable(httpServletRequest.getParameterValues("manufacturer")).ifPresent(reqParams -> {
            manufacturerDtoList.stream().forEach(mfDto -> mfDto.setValue(Stream.of(reqParams).anyMatch(reqParamValue -> reqParamValue.equals(mfDto.getName()))));
        });
        return manufacturerDtoList;
    }

//    private boolean isAnyCategoriesPresented(HttpServletRequest request, List<CategoryDto> categoryDtoList) {
//        return categoryDtoList.stream().anyMatch(e -> getRequestParamString(request, e.getName()).isPresent());
//    }
//
//    private boolean isAnydManufacturersPresented(HttpServletRequest request, List<ManufacturerDto> manufacturerDtoList) {
//        return manufacturerDtoList.stream().anyMatch(e -> getRequestParamString(request, e.getName()).isPresent());
//    }
}
