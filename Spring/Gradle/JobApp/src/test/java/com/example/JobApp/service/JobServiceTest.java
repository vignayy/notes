package com.example.JobApp.service;

import com.example.JobApp.model.JobPost;
import com.example.JobApp.repo.JobRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations for JUnit 5
class JobServiceTest {

    @Mock // Mocks the JobRepo dependency
    private JobRepo jobRepo;

    @InjectMocks // Injects the mocked JobRepo into JobService
    private JobService jobService;

    // This method runs before each test method
    @BeforeEach
    void setUp() {
        // Initializes mocks if not using @ExtendWith(MockitoExtension.class)
        // MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllJobs() {
        // Arrange: Create a list of dummy job posts
        List<JobPost> mockJobs = Arrays.asList(
                new JobPost(1, "Software Engineer", "Description 1", 3, List.of("Java")),
                new JobPost(2, "Data Scientist", "Description 2", 5, List.of("Python"))
        );

        // Define the behavior of the mocked jobRepo.findAll() method
        when(jobRepo.findAll()).thenReturn(mockJobs);

        // Act: Call the service method
        List<JobPost> result = jobService.getAllJobs();

        // Assert: Verify the result and that findAll() was called
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Software Engineer", result.get(0).getPostProfile());
        verify(jobRepo, times(1)).findAll(); // Verify that findAll() was called exactly once
    }

    @Test
    void testAddJobPost() {
        // Arrange: Create a dummy job post
        JobPost newJob = new JobPost(3, "Frontend Developer", "New job description", 2, List.of("React"));

        // Act: Call the service method
        jobService.addJobPost(newJob);

        // Assert: Verify that save() was called with the correct job post
        verify(jobRepo, times(1)).save(newJob);
    }

    @Test
    void testGetJob() {
        // Arrange: Create a dummy job post and an ID
        int postId = 1;
        JobPost mockJob = new JobPost(postId, "Software Engineer", "Description 1", 3, List.of("Java"));

        // Define the behavior of the mocked jobRepo.findById() method
        when(jobRepo.findById(postId)).thenReturn(Optional.of(mockJob));

        // Act: Call the service method
        JobPost result = jobService.getJob(postId);

        // Assert: Verify the result and that findById() was called
        assertNotNull(result);
        assertEquals(postId, result.getPostId());
        assertEquals("Software Engineer", result.getPostProfile());
        verify(jobRepo, times(1)).findById(postId); // Verify that findById() was called exactly once
    }

    @Test
    void testGetJob_NotFound() {
        // Arrange: Define an ID for a non-existent job
        int postId = 99;

        // Define the behavior for a non-existent job (empty Optional)
        when(jobRepo.findById(postId)).thenReturn(Optional.empty());

        // Act & Assert: Expect a NoSuchElementException when trying to get() from an empty Optional
        assertThrows(java.util.NoSuchElementException.class, () -> jobService.getJob(postId));
        verify(jobRepo, times(1)).findById(postId); // Verify that findById() was called
    }

    @Test
    void testUpdateJob() {
        // Arrange: Create a dummy job post for update
        JobPost updatedJob = new JobPost(1, "Updated Software Engineer", "Updated description", 4, List.of("Java", "Spring Boot"));

        // Act: Call the service method
        jobService.updateJob(updatedJob);

        // Assert: Verify that save() was called with the updated job post
        verify(jobRepo, times(1)).save(updatedJob);
    }

    @Test
    void testDeleteJob() {
        // Arrange: Define an ID for the job to be deleted
        int postId = 1;

        // Act: Call the service method
        jobService.deleteJob(postId);

        // Assert: Verify that deleteById() was called with the correct ID
        verify(jobRepo, times(1)).deleteById(postId);
    }

    @Test
    void testLoad() {
        // Act: Call the service method
        jobService.load();

        // Assert: Verify that saveAll() was called exactly once with a list of 5 job posts
        verify(jobRepo, times(1)).saveAll(anyList()); // anyList() matches any List argument
    }

    @Test
    void testSearchByKeyword() {
        // Arrange: Define a keyword and mock job posts that match
        String keyword = "Engineer";
        List<JobPost> mockSearchResults = Arrays.asList(
                new JobPost(1, "Software Engineer", "Description for engineer", 3, List.of("Java")),
                new JobPost(4, "Network Engineer", "Description for network", 4, List.of("Cisco"))
        );

        // Define the behavior of the mocked search method
        when(jobRepo.findByPostDescContainingOrPostProfileContaining(keyword, keyword))
                .thenReturn(mockSearchResults);

        // Act: Call the service method
        List<JobPost> result = jobService.searchByKeyword(keyword);

        // Assert: Verify the result and that the search method was called
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Software Engineer", result.get(0).getPostProfile());
        verify(jobRepo, times(1)).findByPostDescContainingOrPostProfileContaining(keyword, keyword);
    }
}
