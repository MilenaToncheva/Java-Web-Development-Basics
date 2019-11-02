package app.web.mbeans;

import app.domain.models.view.JobApplicationDetailsViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
@Named
@RequestScoped
public class JobDetailsBean extends BaseBean{
    private JobApplicationDetailsViewModel jobDetailsViewModel;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobDetailsBean() {
    }
@Inject
    public JobDetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }
    @PostConstruct
    public void init(){
       HttpServletRequest req= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
       String id=req.getParameter("id");
        this.jobDetailsViewModel=this.modelMapper.map(this.jobApplicationService.findById(id),JobApplicationDetailsViewModel.class);
        System.out.println();
    }

    public JobApplicationDetailsViewModel getJobDetailsViewModel() {
        return jobDetailsViewModel;
    }

    public void setJobDetailsViewModel(JobApplicationDetailsViewModel jobDetailsViewModel) {
        this.jobDetailsViewModel = jobDetailsViewModel;
    }
}
