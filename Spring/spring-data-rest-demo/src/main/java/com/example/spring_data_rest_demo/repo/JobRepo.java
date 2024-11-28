package com.example.spring_data_rest_demo.repo;


import java.util.ArrayList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_data_rest_demo.model.JobPost;

@Repository


public interface JobRepo extends JpaRepository<JobPost, Integer> {

    public List<JobPost> findByPostDescContainingOrPostProfileContaining(String keyword1, String keyword2);

}


