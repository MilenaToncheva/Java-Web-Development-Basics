package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter({"/faces/view/index.xhtml", "/faces/view/register.xhtml", "/faces/view/login.xhtml",
        "/", "/view/index.xhtml", "/view/register.xhtml", "/view/login.xhtml", "/index.xhtml", "/register.xhtml", "/login.xhtml",})
public class UserAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.getAttribute("userId") != null) {
            resp.sendRedirect("/view/home.xhtml");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
