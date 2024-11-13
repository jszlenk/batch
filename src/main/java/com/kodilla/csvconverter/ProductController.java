package com.kodilla.csvconverter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class ProductController {

    private final JobLauncher jobLauncher;
    private final Job changePriceJob;

    public ProductController(JobLauncher jobLauncher, Job changePriceJob) {
        this.jobLauncher = jobLauncher;
        this.changePriceJob = changePriceJob;
    }

    @GetMapping("/run")
    public String runJob() {
        try {
            jobLauncher.run(changePriceJob, new JobParameters());
            return "Job executed successfully!";
        } catch (Exception e) {
            return "Job execution failed: " + e.getMessage();
        }
    }
}