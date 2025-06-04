package com.example.JobApp.repo;

import com.example.JobApp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByPostDescContainingOrPostProfileContaining(String keyword1, String keyword2);

}