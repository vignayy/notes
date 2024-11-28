package com.example.springbootrest.controller;

import com.example.springbootrest.model.JobPost;
import com.example.springbootrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobPosts")
@CrossOrigin
public class JobRestController {

    @Autowired
    private JobService service;

    @GetMapping
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId) {
        return service.getJob(postId);
    }

    @GetMapping("/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword) {
        return service.searchByKeyword(keyword);
    }

    @PostMapping
    public void addJob(@RequestBody JobPost jobPost) {
        service.addJobPost(jobPost);
    }
//    @PostMapping("jobPost")
//    public JobPost addJob(@RequestBody JobPost jobPost) {
//        service.addJobPost(jobPost);
//        return service.getJob(jobPost.getPostId());
//    }

    @PutMapping
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("/{postId}")
    public String deleteJob(@PathVariable int postId) {
        service.deleteJob(postId);
        return "Deleted";
    }

    @PostMapping("/load")
    public String loadJobs() {
        service.load();
        return "Loaded Jobs";
    }
}
