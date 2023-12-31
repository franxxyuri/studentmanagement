package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ProFilter")
public class ProFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        if (url.equals("/SportsManager_war_exploded/login.jsp")) {
            chain.doFilter(request, response);
        } else {
            Object obj = req.getSession().getAttribute("user");
            if (obj != null) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect("login.jsp");
            }
        }

    }
}
