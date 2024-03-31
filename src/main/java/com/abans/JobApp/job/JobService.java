package com.abans.JobApp.job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    void postJob(Job job);
    Job getJobById(Long id);
    boolean updateJob(Long id, Job updatedJob);
    boolean deleteJob(Long id);
}
