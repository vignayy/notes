Here’s a concise Git cheat sheet covering the most commonly used commands:

### **Git Basics**

- **`git init`**  
  Initialize a new Git repository in the current directory.

- **`git clone <url>`**  
  Clone an existing repository from a remote (e.g., GitHub).

- **`git status`**  
  Show the working directory and staging area status (modified, added, untracked files).

- **`git add <file>`**  
  Stage changes of a specific file.

- **`git add .`**  
  Stage all changes in the current directory.

- **`git commit -m "message"`**  
  Commit staged changes with a descriptive message.

### **Branching & Merging**

- **`git branch`**  
  List all branches in the repo.

- **`git branch <branch_name>`**  
  Create a new branch.

- **`git checkout <branch_name>`**  
  Switch to a specific branch.

- **`git checkout -b <branch_name>`**  
  Create and switch to a new branch.

- **`git merge <branch_name>`**  
  Merge the specified branch into the current branch.

- **`git branch -d <branch_name>`**  
  Delete a branch (if fully merged).

### **Stashing Changes**

- **`git stash`**  
  Save uncommitted changes for later.

- **`git stash apply`**  
  Re-apply stashed changes.

- **`git stash drop`**  
  Discard stashed changes.

### **Remote Repositories**

- **`git remote -v`**  
  List all remotes for the repository.

- **`git remote add origin <url>`**  
  Add a remote repository (typically named `origin`).

- **`git push -u origin <branch_name>`**  
  Push changes to the remote repository and set the upstream branch.

- **`git pull`**  
  Fetch and merge changes from the remote repository into the current branch.

- **`git fetch`**  
  Fetch changes from the remote without merging.

### **Viewing History**

- **`git log`**  
  Show the commit history for the current branch.

- **`git log --oneline`**  
  Show a simplified commit history (one line per commit).

- **`git diff`**  
  Show the differences between files in the working directory and the staging area.

- **`git diff <commit>`**  
  Show changes between working directory and a specific commit.

### **Undoing Changes**

- **`git reset <file>`**  
  Unstage a file (remove from staging area).

- **`git reset --hard <commit>`**  
  Reset the working directory and the index to a specific commit (destructive).

- **`git revert <commit>`**  
  Create a new commit that undoes changes from a specific commit (non-destructive).

### **Tagging**

- **`git tag <tag_name>`**  
  Create a new tag for the current commit.

- **`git tag -d <tag_name>`**  
  Delete a tag.

- **`git push origin <tag_name>`**  
  Push a tag to the remote repository.

### **Other Useful Commands**

- **`git rm <file>`**  
  Remove a file from both the working directory and the staging area.

- **`git mv <old_name> <new_name>`**  
  Rename or move a file.

- **`git rebase <branch_name>`**  
  Reapply commits from one branch on top of another branch.

This cheat sheet provides a quick reference to essential Git commands for version control.

---