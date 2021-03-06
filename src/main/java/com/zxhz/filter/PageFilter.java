package com.zxhz.filter;

import com.zxhz.utils.PaginationContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 分页过滤器
 *
 * @author lixingwu
 */
@Order(4)
@WebFilter(filterName = "pageFilter", urlPatterns = "/*")
public class PageFilter implements Filter {
    public PageFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        PaginationContext.setPageNum(getPageNum(httpRequest));
        PaginationContext.setPageSize(getPageSize(httpRequest));

        try {
            chain.doFilter(request, response);
        }
        // 使用完Threadlocal，将其删除。使用finally确保一定将其删除
        finally {
            PaginationContext.removePageNum();
            PaginationContext.removePageSize();
        }
    }

    /**
     * 获得pager.offset参数的值
     *
     * @param request
     * @return
     */
    protected int getPageNum(HttpServletRequest request) {
        int pageNum = 1;
        try {
            String pageNums = request.getParameter("page");
            if (pageNums != null && StringUtils.isNumeric(pageNums)){
                pageNum = Integer.parseInt(pageNums);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageNum;
    }

    /**
     * 设置默认每页大小
     *
     * @return
     */
    protected int getPageSize(HttpServletRequest request){
        int pageSize = 10;    // 默认每页10条记录
        try {
            String pageSizes = request.getParameter("limit");
            if (pageSizes != null && StringUtils.isNumeric(pageSizes)){
                pageSize = Integer.parseInt(pageSizes);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageSize;
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}