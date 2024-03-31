package com.abans.JobApp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

   private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postJob(@RequestBody Job job){
        jobService.postJob(job);
        return new ResponseEntity<>("Job posted successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
       Job job = jobService.getJobById(id);
       if(job != null) return ResponseEntity.ok(job);
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated) return ResponseEntity.ok("Job Updated Successfully!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJob(id);
        if(deleted) return ResponseEntity.ok("Job Deleted Successfully!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
