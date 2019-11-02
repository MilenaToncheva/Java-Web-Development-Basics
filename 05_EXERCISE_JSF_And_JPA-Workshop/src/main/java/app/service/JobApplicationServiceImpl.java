package app.service;

import app.domain.entities.JobApplication;
import app.domain.models.service.JobApplicationServiceModel;
import app.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createJob(JobApplicationServiceModel jobApplicationServiceModel) {
        this.jobApplicationRepository.save(this.modelMapper.map(jobApplicationServiceModel, JobApplication.class));
    }

    @Override
    public JobApplicationServiceModel findById(String id) {
        return this.modelMapper.map(this.jobApplicationRepository.findById(id),JobApplicationServiceModel.class);
    }

    @Override
    public List<JobApplicationServiceModel> findAll() {
        return Arrays.stream(this.modelMapper.map(this.jobApplicationRepository.findAll(), JobApplicationServiceModel[].class))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }
}
