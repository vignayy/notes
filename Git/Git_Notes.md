Branching:
Here are the Git commands used in the process:

1. **Create a feature branch**:
   ```
   git checkout -b feature_branch_name
   ```

2. **Make changes in the feature branch**:
   ```
   (Make necessary changes to files)
   ```

3. **Add and commit changes in the feature branch**:
   ```
   git add .
   git commit -m "Commit message describing changes"
   ```

4. **Switch back to the main branch**:
   ```
   git checkout main
   ```

5. **Merge the feature branch into the main branch**:
   ```
   git merge feature_branch_name
   ```

6. **Resolve any merge conflicts if they occur**:
   ```
   (Open conflicted files, resolve conflicts, then add and commit changes)
   git add .
   git commit -m "Merge conflicts resolved"
   ```

7. **Push changes to the remote repository** (if necessary):
   ```
   git push origin main
   ```

Remember, the specific branch names and commit messages should be customized based on your project's requirements.

---
### **Change the default branch for new repositories**

To permanently change the default branch name from `master` to `main` in Git

```bash
git config --global init.defaultBranch main
```

This command sets the `init.defaultBranch` configuration variable globally, which means it will apply to all new Git
repositories created on your system. The value "main" specifies the desired initial branch name for new repositories.
From now on, whenever you initialize a new Git repository, the default branch will be named "main".

---

The `.gitignore` file is used to specify intentionally untracked files that Git should ignore. These are typically files
that you don't want to include in your version control system, such as compiled binaries, log files, temporary files, or
files containing sensitive information like API keys or passwords.

Here's why the `.gitignore` file is useful:

1. **Prevents Accidental Commits**: By specifying files or directories in `.gitignore`, you can prevent them from being
   accidentally committed to your repository. This helps keep your repository clean and focused on the essential files.

2. **Reduces Repository Size**: Ignoring unnecessary files helps reduce the size of your repository, making it more
   efficient to clone, pull, and push changes.

3. **Maintains Privacy and Security**: You can use `.gitignore` to exclude sensitive information, such as configuration
   files containing passwords or API keys, from being exposed in your repository.

4. **Improves Collaboration**: Ignoring editor-specific files, build artifacts, or operating system-generated files
   ensures that collaborators with different development environments won't clutter the repository with irrelevant
   files.

5. **Customizable to Project Needs**: The `.gitignore` file is customizable to suit the specific needs of your project.
   You can include rules to ignore files globally across all repositories or specify rules for individual projects.

Here's an example of a simple `.gitignore` file:

```
# Ignore compiled binaries
*.exe
*.dll

# Ignore log files
*.log

# Ignore temporary files
tmp/

# Ignore configuration files with sensitive information
config.ini
secrets.txt
```

To use `.gitignore`, simply create a file named `.gitignore` in the root directory of your Git repository and list the
files or patterns you want Git to ignore. Git will then automatically disregard these files when checking for changes to
commit.

---

The `git config` command is used to set or get configuration variables that control various aspects of Git's operation
and behavior. These configuration variables can be set at three different levels: system, global, and local (
repository-specific).

Here are some common uses of `git config`:

1. **Setting Configuration Variables**:
    - You can use `git config` to set various configuration variables such as user name, email, default branch name,
      preferred text editor, etc.
    - For example, to set your username, you can use:
      ```
      git config --global user.name "Your Name"
      ```

2. **Getting Configuration Variables**:
    - You can also use `git config` to get the value of a specific configuration variable.
    - For example, to get your configured user name, you can use:
      ```
      git config user.name
      ```

3. **Listing Configuration Variables**:
    - You can list all the configured variables and their values using the `--list` option.
    - For example:
      ```
      git config --list
      ```

4. **Editing Configuration Files**:
    - You can edit the Git configuration files directly using the `--edit` option.
    - For example:
      ```
      git config --edit
      ```

5. **Setting Different Levels of Configuration**:
    - You can set configuration variables at different levels: system, global, or local to the repository.
    - For example, to set a configuration variable only for a specific repository, you can omit the `--global` option:
      ```
      git config user.email "your@email.com"
      ```

6. **Unsetting Configuration Variables**:
    - You can also unset or remove a configuration variable using the `--unset` option.
    - For example, to remove the user.email configuration:
      ```
      git config --unset user.email
      ```

`git config` is a powerful command that allows you to customize Git's behavior according to your preferences and
requirements. It's essential for managing your Git workflow effectively.

---

