### Forking Workflow

Certainly! Here is the complete workflow from forking the project to contributing multiple features in collaboration:

1. **Fork the Original Repository**:
    - Go to the original repository on GitHub (`https://github.com/john/GitRun`).
    - Click on the "Fork" button to create a copy under your GitHub account (`https://github.com/wick/GitRun`).

2. **Clone Your Forked Repository**:
    - Open your terminal or Git Bash.
    - Clone your forked repository to your local machine:
      ```
      git clone https://github.com/wick/GitRun.git
      ```
    - Navigate to the cloned repository:
      ```
      cd GitRun
      ```

3. **Add Upstream Remote**:
    - Add the original repository as the upstream remote:
      ```
      git remote add upstream https://github.com/john/GitRun.git
      ```

4. **Create and Switch to "feature_1" Branch**:
    - Create a new branch named "feature_1":
      ```
      git checkout -b feature_1
      ```

5. **Make Changes and Commit**:
    - Make changes to the codebase as needed.
    - Stage and commit your changes:
      ```
      git add .
      git commit -m "Add feature_1 changes"
      ```

6. **Push "feature_1" Branch to Your Fork**:
    - Push your "feature_1" branch to your forked repository on GitHub:
      ```
      git push origin feature_1
      ```

7. **Stay Synced with Upstream**:
    - Periodically fetch and merge changes from the upstream repository:
      ```
      git fetch upstream
      git checkout main
      git merge upstream/main
      git push origin main
      ```

8. **Create Pull Request for "feature_1"**:
    - Go to your forked repository on GitHub (`https://github.com/wick/GitRun`).
    - Create a pull request from your "feature_1" branch to the main branch of the original repository (`john/GitRun`).
    - Provide a descriptive title and description for the pull request.

9. **Review and Merge Pull Request**:
    - Wait for the pull request to be reviewed by project maintainers or collaborators.
    - Address any feedback or comments provided during the review process.
    - Once approved, the pull request can be merged into the main branch of the original repository (`john/GitRun`).

10. **Cleanup "feature_1" Branch**:
    - After the pull request is merged, delete the "feature_1" branch locally and on GitHub:
      ```
      git checkout main
      git branch -d feature_1  # delete locally
      git push origin --delete feature_1  # delete on GitHub
      ```

11. **Create and Switch to "feature_2" Branch**:
    - Create a new branch named "feature_2":
      ```
      git checkout -b feature_2
      ```

12. **Make Changes and Commit for "feature_2"**:
    - Make changes to the codebase as needed for "feature_2".
    - Stage and commit your changes:
      ```
      git add .
      git commit -m "Add feature_2 changes"
      ```

13. **Push "feature_2" Branch to Your Fork**:
    - Push your "feature_2" branch to your forked repository on GitHub:
      ```
      git push origin feature_2
      ```

14. **Create Pull Request for "feature_2"**:
    - Go to your forked repository on GitHub (`https://github.com/wick/GitRun`).
    - Create a pull request from your "feature_2" branch to the main branch of the original repository (`john/GitRun`).
    - Provide a descriptive title and description for the pull request.

15. **Review and Merge Pull Request for "feature_2"**:
    - Follow the same process as before for reviewing, addressing feedback, and merging the pull request for "feature_2"
      into the main branch of the original repository.

16. **Cleanup "feature_2" Branch**:
    - After the "feature_2" pull request is merged, delete the branch locally and on GitHub similar to the cleanup steps
      for "feature_1".

By following this workflow, you can effectively collaborate on a project, contribute multiple features, create pull
requests for each feature, review and merge changes, and maintain a clean and organized development process. Adjust the
branch names and repository URLs as needed for your specific project.

---

### Direct Cloning Workflow

Certainly! Here's the workflow for directly cloning the original repository, contributing changes, and creating pull
requests for collaboration:

1. **Clone the Original Repository**:
    - Open your terminal or Git Bash.
    - Clone the original repository to your local machine:
      ```
      git clone https://github.com/john/GitRun.git
      ```
    - Navigate to the cloned repository:
      ```
      cd GitRun
      ```

2. **Create and Switch to "feature_1" Branch**:
    - Create a new branch named "feature_1":
      ```
      git checkout -b feature_1
      ```

3. **Make Changes and Commit for "feature_1"**:
    - Make changes to the codebase as needed for "feature_1".
    - Stage and commit your changes:
      ```
      git add .
      git commit -m "Add feature_1 changes"
      ```

4. **Push "feature_1" Branch to Your Fork**:
    - Push your "feature_1" branch to your forked repository on GitHub (assuming you have already forked the
      repository):
      ```
      git push origin feature_1
      ```

5. **Create Pull Request for "feature_1"**:
    - Go to your forked repository on GitHub (`https://github.com/wick/GitRun`).
    - Create a pull request from your "feature_1" branch to the main branch of the original repository (`john/GitRun`).
    - Provide a descriptive title and description for the pull request.

6. **Review and Merge Pull Request for "feature_1"**:
    - Wait for the pull request to be reviewed by project maintainers or collaborators.
    - Address any feedback or comments provided during the review process.
    - Once approved, the pull request can be merged into the main branch of the original repository (`john/GitRun`).

7. **Cleanup "feature_1" Branch**:
    - After the pull request is merged, delete the "feature_1" branch locally and on GitHub:
      ```
      git checkout main
      git branch -d feature_1  # delete locally
      git push origin --delete feature_1  # delete on GitHub
      ```

8. **Create and Switch to "feature_2" Branch**:
    - Create a new branch named "feature_2":
      ```
      git checkout -b feature_2
      ```

9. **Make Changes and Commit for "feature_2"**:
    - Make changes to the codebase as needed for "feature_2".
    - Stage and commit your changes:
      ```
      git add .
      git commit -m "Add feature_2 changes"
      ```

10. **Push "feature_2" Branch to Your Fork**:
    - Push your "feature_2" branch to your forked repository on GitHub:
      ```
      git push origin feature_2
      ```

11. **Create Pull Request for "feature_2"**:
    - Go to your forked repository on GitHub (`https://github.com/wick/GitRun`).
    - Create a pull request from your "feature_2" branch to the main branch of the original repository (`john/GitRun`).
    - Provide a descriptive title and description for the pull request.

12. **Review and Merge Pull Request for "feature_2"**:
    - Follow the same process as before for reviewing, addressing feedback, and merging the pull request for "feature_2"
      into the main branch of the original repository.

13. **Cleanup "feature_2" Branch**:
    - After the "feature_2" pull request is merged, delete the branch locally and on GitHub similar to the cleanup steps
      for "feature_1".

This workflow outlines the steps for directly cloning the original repository, creating branches, making changes,
pushing changes to your forked repository, creating pull requests for collaboration, reviewing and merging pull
requests, and cleaning up branches after merging changes. Adjust branch names and repository URLs as needed for your
specific project.