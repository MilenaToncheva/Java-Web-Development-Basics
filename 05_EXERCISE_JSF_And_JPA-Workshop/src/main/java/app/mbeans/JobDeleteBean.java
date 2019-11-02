package app.web.mbeans;

import app.domain.models.service.JobApplicationServiceModel;
import app.domain.models.view.JobApplicationDeleteViewModel;
import app.domain.models.view.JobApplicationDetailsViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class JobDeleteBean extends BaseBean{

    private JobApplicationDeleteViewModel jobDeleteViewModel;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobDeleteBean() {
    }
@Inject
    public JobDeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        HttpServletRequest req= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id=req.getParameter("id");
        JobApplicationServiceModel jobApplicationServiceModel=this.jobApplicationService.findById(id);
        this.jobDeleteViewModel=this.modelMapper.map(jobApplicationServiceModel,JobApplicationDeleteViewModel.class);
    }
    public void delete(){
        this.jobApplicationService.delete(this.jobDeleteViewModel.getId());
        this.redirect("/home");
    }

    public JobApplicationDeleteViewModel getJobDeleteViewModel() {
        return jobDeleteViewModel;
    }

    public void setJobDeleteViewModel(JobApplicationDeleteViewModel jobDeleteViewModel) {
        this.jobDeleteViewModel = jobDeleteViewModel;
    }
}
