package metube.web.servlets;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.service.TubeService;
import util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {
    private final ModelMapper modelMapper;
    private final TubeService tubeService;

    @Inject
    public TubeCreateServlet(TubeRepository tubeRepository, ModelMapper modelMapper, ModelMapper modelMapper1, TubeService tubeService) {
        this.modelMapper = modelMapper1;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute("tube");
        TubeServiceModel tubeServiceModel = this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class);
        this.tubeService.saveTube(tubeServiceModel);
        resp.sendRedirect(String.format("/tubes/details?name=%s",tubeServiceModel.getName()));
    }
}
