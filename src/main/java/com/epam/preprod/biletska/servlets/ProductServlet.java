package com.epam.preprod.biletska.servlets;


import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.dto.CategoryDto;
import com.epam.preprod.biletska.dto.FilterFormDto;
import com.epam.preprod.biletska.dto.FilterFormExtractor;
import com.epam.preprod.biletska.dto.ManufacturerDto;
import com.epam.preprod.biletska.dto.SortDirection;
import com.epam.preprod.biletska.dto.SortDto;
import com.epam.preprod.biletska.entity.Product;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.impl.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.LOGGED_USER;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestParamInt;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getRequestParamString;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getSessionAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.getSessionAttrString;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.removeRequestAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.removeSessionAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.setRequestAttr;
import static com.epam.preprod.biletska.servlets.CommonDefinitions.setSessionAttr;

/**
 * Servlet for processing the user access to products with specified criterias.
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    public ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(Constants.PRODUCT_SERVICE);
        super.init();
    }

    private <T, R> List<R> convertFromTo(List<T> source, Function<T, R> fn) {
        return source.stream().map(fn).collect(Collectors.toList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Goto login page if no user logged
        if (!getSessionAttr(req, User.class, LOGGED_USER).isPresent()) {
            resp.sendRedirect(req.getContextPath() + Constants.INDEX_JSP);
            return;
        }

        List<CategoryDto> productCategories = convertFromTo(productService.getAllCategories(), (e) -> new CategoryDto(e.getName(), e.getDescription(), false));
        List<ManufacturerDto> productManufacturers = convertFromTo(productService.getAllManufacturers(), (e) -> new ManufacturerDto(e.getName(), e.getDescription(), false));

        FilterFormDto filterFormDto = new FilterFormExtractor().extract(req, productManufacturers, productCategories);
        // Try to remember current filter string to pass it to products page. There it will be added to pagination links
        if (filterFormDto != null) {
            Optional.ofNullable(req.getQueryString()).ifPresent(e -> {
                String result = e;
                int idx = e.lastIndexOf("&page");
                if (idx >= 0) {
                    result = e.substring(0, idx);
                }
                setRequestAttr(req, "filterQuery", result);
            });
        } else {
            removeRequestAttr(req, "filterQuery");
        }
        int size = getItemCount(req);
        int numberPages = checkNumberPagingOnPage(req, size, filterFormDto);

        List<Product> productList = productService.getAllProductsOnPageByCriteria(size, getPage(req, numberPages), filterFormDto, getSort(req));

        setRequestAttr(req, "productList", productList);
        setRequestAttr(req, "productCategories", productCategories);
        setRequestAttr(req, "productManufacturers", productManufacturers);
        setRequestAttr(req, "filterForm", filterFormDto);

        String nextPage = Constants.PRODUCTS_JSP;
        RequestDispatcher disp = req.getRequestDispatcher(nextPage);
        disp.forward(req, resp);
    }

    private SortDto getSort(HttpServletRequest request) {
        String field = getRequestParamString(request, "sort").orElse("");
        String direction = getRequestParamString(request, "direction").orElse("");
        if (field.isEmpty()) {
            removeSessionAttr(request, "sortField");
            return null;
        }
        if (direction.isEmpty()) {
            direction = SortDirection.ASC.name();
        }
        String newDirection = SortDirection.ASC.name();
        if (field.equals(getSessionAttrString(request, "sortField").orElse(""))
                && SortDirection.valueOf(direction).equals(SortDirection.ASC)) {
            newDirection = SortDirection.DESC.name();
        }
        setRequestAttr(request, "sort", field);
        setRequestAttr(request, "direction", newDirection);
        setSessionAttr(request, "sortField", field);
        return new SortDto(field, SortDirection.valueOf(direction));
    }

    private int getItemCount(HttpServletRequest request) {
        int itemCount = getRequestParamInt(request, "itemCount").orElse(5);
        setRequestAttr(request, "itemCount", itemCount);
        return itemCount;
    }

    private int getPage(HttpServletRequest request, int numberPages) {
        Optional<Integer> pageOptional = getRequestParamInt(request, "page");
        int page = 1;
        if (pageOptional.isPresent()) {
            page = pageOptional.get();
        }
        if (page > numberPages) {
            page = numberPages;
        }
        setRequestAttr(request, "page1", page);
        return page;
    }

    private int checkNumberPagingOnPage(HttpServletRequest request, int size, FilterFormDto filterFormDto) {
        int numbPages;
        if (!getRequestParamInt(request, "pages").isPresent()) {
            numbPages = productService.getNumberPages(size, filterFormDto);
            setRequestAttr(request, "pages", numbPages);
            return numbPages;
        }
        numbPages = productService.getNumberPages(size, getRequestParamInt(request, "page").get(), filterFormDto);
        setRequestAttr(request, "pages", numbPages);
        return numbPages;
    }
}