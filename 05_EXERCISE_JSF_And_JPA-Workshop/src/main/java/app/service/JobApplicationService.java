package app.service;

import app.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    void createJob(JobApplicationServiceModel jobApplicationServiceModel);

    JobApplicationServiceModel findById(String id);

    List<JobApplicationServiceModel> findAll();
void delete(String id );


}
