package app.web.mbeans;

import app.domain.entities.JobApplication;
import app.domain.entities.Sector;
import app.domain.models.binding.JobCreateBindingModel;
import app.domain.models.service.JobApplicationServiceModel;
import app.domain.models.view.JobApplicationViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobAddBean extends BaseBean {

private JobCreateBindingModel jobCreateBindingModel;
private ModelMapper modelMapper;
private JobApplicationService jobApplicationService;

    public JobAddBean() {
    }
@Inject
    public JobAddBean(ModelMapper modelMapper, JobApplicationService jobApplicationService) {
        this.modelMapper = modelMapper;
        this.jobApplicationService = jobApplicationService;
    }

    @PostConstruct
    public void init(){
        this.jobCreateBindingModel=new JobCreateBindingModel();

    }
    public void create(){
        JobApplicationServiceModel jobApplicationServiceModel=this.modelMapper.map(this.jobCreateBindingModel,JobApplicationServiceModel.class);
        jobApplicationServiceModel.setSector(Sector.valueOf(this.jobCreateBindingModel.getSector().toUpperCase()));
        this.jobApplicationService.createJob(jobApplicationServiceModel);
        this.redirect("/home");
    }

    public JobCreateBindingModel getJobCreateBindingModel() {
        return jobCreateBindingModel;
    }

    public void setJobCreateBindingModel(JobCreateBindingModel jobCreateBindingModel) {
        this.jobCreateBindingModel = jobCreateBindingModel;
    }
}
