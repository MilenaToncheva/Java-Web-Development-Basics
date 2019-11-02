package app.web.mbeans;

import app.domain.models.view.JobApplicationViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<JobApplicationViewModel> jobApplicationViewModels;
private ModelMapper modelMapper;
    private JobApplicationService jobApplicationService;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, JobApplicationService jobApplicationService) {
        this.modelMapper = modelMapper;
        this.jobApplicationService = jobApplicationService;
    }
    @PostConstruct
    public void init(){
        this.jobApplicationViewModels= Arrays.stream(this.modelMapper.map(this.jobApplicationService.findAll(), JobApplicationViewModel[].class))
                .collect(Collectors.toList());
        System.out.println();
    }

    public List<JobApplicationViewModel> getJobApplicationViewModels() {
        return jobApplicationViewModels;
    }

    public void setJobApplicationViewModels(List<JobApplicationViewModel> jobApplicationViewModels) {
        this.jobApplicationViewModels = jobApplicationViewModels;
    }
}
