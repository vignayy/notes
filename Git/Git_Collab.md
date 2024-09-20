### Forking

Forking creates a copy of the original repository under your GitHub account, allowing you to work on the code
independently. Here's how you can do it:

1. **Fork the Repository**:
   - Go to your friend's GitHub repository (`john/GitRun` in this case).
   - Click on the "Fork" button in the top-right corner of the repository page.
   - This action will create a copy of the repository under your GitHub account (`yourusername/GitRun`).

2. **Clone Your Forked Repository**:
   - Once you have forked the repository, navigate to your forked repository on
     GitHub (`https://github.com/yourusername/GitRun`).
   - Click on the green "Code" button and copy the HTTPS or SSH URL.
   - Open your terminal or Git Bash.
   - Navigate to the directory where you want to clone the repository.
   - Use the `git clone` command to clone your forked repository:
     ```
     git clone https://github.com/yourusername/GitRun.git
     ```
   Replace `https://github.com/yourusername/GitRun.git` with the actual URL of your forked repository.

3. **Add Upstream Remote**:
   - After cloning, navigate into the project directory:
     ```
     cd GitRun
     ```
   - Add a remote called "upstream" that points to your friend's original repository:
     ```
     git remote add upstream https://github.com/john/GitRun.git
     ```

4. **Sync Your Forked Repository**:
   - Fetch the branches and commits from the upstream repository to sync your fork with the original project:
     ```
     git fetch upstream
     ```
   - Switch to the main branch (or whichever branch you want to work on):
     ```
     git checkout main
     ```
   - Merge the changes from the upstream main branch into your main branch:
     ```
     git merge upstream/main
     ```

5. **Create a New Branch**:
   - Create a new branch for your frontend development work:
     ```
     git checkout -b frontend-dev
     ```

6. **Make Changes, Commit, and Push**:
   - Work on your frontend code, stage and commit changes, and push them to your forked repository as explained in the
     previous response.

7. **Create Pull Request**:
   - After pushing your changes to your forked repository, go to your forked repository on
     GitHub (`https://github.com/yourusername/GitRun`).
   - GitHub should detect your recent push and suggest creating a new pull request. Click on the "Compare & pull
     request" button.

8. **Review and Merge**:
   - Your friend John, who owns the original repository, can review your pull request, add comments, and merge the
     changes if everything looks good.

By forking the repository, you create a separate space to work on the project while still being able to collaborate and
contribute changes back to the original repository through pull requests. This workflow is commonly used in open-source
projects and collaborative development environments.

---

### Direct Cloning

1. **Clone the Remote Repository**:
- Open your terminal or Git Bash (if you're on Windows).
- Navigate to the directory where you want to store your local copy of the repository.
- Use the `git clone` command to clone the remote repository:
```
git clone https://github.com/john/GitRun.git
```
Replace `https://github.com/john/GitRun.git` with the actual URL of your friend's GitHub repository.

2. **Set Up Branches**:
- Once the repository is cloned, navigate into the project directory:
```
cd GitRun
```
- Create a new branch for your frontend development work:
```
git checkout -b frontend-dev
```

3. **Make Changes**:
- Start working on your frontend code in the project directory. Create, modify, and add files as needed for the
frontend of the website.

4. **Stage and Commit Changes**:
- After making changes, stage the files you want to include in the commit:
```
git add .
```
This command stages all changes. If you want to stage specific files, replace `.` with the file names.
- Commit the staged changes with a descriptive commit message:
```
git commit -m "Add frontend features"
```

5. **Push Changes to Remote**:
- Push your committed changes to the remote repository:
```
git push origin frontend-dev
```
This command pushes your changes to the `frontend-dev` branch on the remote repository.

6. **Create Pull Request**:
- On GitHub, go to your friend's repository (`john/GitRun`).
- GitHub should detect your recent push and suggest creating a new pull request. Click on the "Compare & pull
request" button.

7. **Review and Merge**:
- Your friend John can now review the changes in the pull request, add comments, and discuss any modifications
needed.
- Once everything looks good, John can merge the pull request into the main branch (e.g., `main` or `master`).

8. **Sync Your Local Repository**:
- After the pull request is merged, switch to the main branch on your local repository:
```
git checkout main
```
- Pull the latest changes from the remote repository to sync your local copy:
```
git pull origin main
```

9. **Work on Backend**:
- Now, it's John's turn to work on the backend. He can follow a similar process:
- Clone the repository to his local machine.
- Create a new branch for backend development (`git checkout -b backend-dev`).
- Make changes, stage, commit, and push them to the remote repository.
- Create a pull request for his changes, which you can review and merge.

10. **Repeat Collaboration**:
- Continue this collaborative workflow, alternating between frontend and backend development, creating branches for
each task, and merging changes via pull requests.

This process allows both you and John to collaborate effectively on the project, share code changes, review each other's
work, and maintain a clean and organized development workflow using Git and GitHub.

---

