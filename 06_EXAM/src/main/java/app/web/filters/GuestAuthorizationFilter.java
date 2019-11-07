package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/view/home.xhtml",
        "/view/delete-hero.xhtml",
        "/view/details-hero.xhtml",
        "/view/create-hero.xhtml",
        "/faces/view/home.xhtml",
        "/faces/view/delete-hero.xhtml",
        "/faces/view/details-hero.xhtml",
        "/faces/view/create-hero.xhtml",
        "/home.xhtml",
        "/delete-hero.xhtml",
        "/details-hero.xhtml",
        "/create-hero.xhtml",
})
public class GuestAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.getAttribute("userId") == null) {
            resp.sendRedirect("/view/index.xhtml");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
