package metube.web.filters;


import metube.domain.models.binding.TubeCreateBindingModel;
import util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        if (req.getMethod().toLowerCase().equals("post")) {
            TubeCreateBindingModel tube = new TubeCreateBindingModel();
            tube.setName(req.getParameter("name"));
            tube.setDescription(req.getParameter("description"));
            tube.setYouTubeLink(req.getParameter("youTube-link"));
            tube.setUploader(req.getParameter("uploader"));
            req.setAttribute("tube", tube);
        }
        filterChain.doFilter(req, resp);
    }


}
