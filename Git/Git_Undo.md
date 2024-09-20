### Summary of Commands for Undoing Changes in Different Scenarios:

| Scenario                            | Command                                               | Description                                                                                          |
|-------------------------------------|-------------------------------------------------------|------------------------------------------------------------------------------------------------------|
| **Unstaged changes (not added)**    | `git restore .`                                       | Discards changes in the working directory and reverts files to the last commit.                      |
| **Staged changes (added, not committed)** | `git restore --staged .` and `git restore .`      | Unstages and discards the changes, reverting the files to the last commit.                           |
| **Committed changes (not pushed)**  | `git reset --hard HEAD^`                              | Removes the commit and discards the changes, reverting to the last commit.                           |
| **Committed changes but keep changes** | `git reset --soft HEAD^`                          | Removes the commit but keeps the changes in the working directory (staged).                          |
| **Pushed changes, want to undo**    | `git reset --hard HEAD^ && git push --force`          | Resets to the previous commit, discards the changes, and force-pushes the changes to the remote.     |
| **Pushed changes, keep history**    | `git revert <commit-hash> && git push`                | Reverts the changes by creating a new commit that undoes the previous one, without rewriting history. |


---

Here's a structured list of commands and steps to undo changes in different Git scenarios. We assume that you have a previous commit that you have already pushed, and now you've made new changes that you want to undo.

### 1. **Scenario: You Have Made Changes but Have Not Added Them (Unstaged Changes)**
In this case, the changes are only in your working directory and have not been staged (i.e., not added using `git add`).

#### Command to Discard Unstaged Changes:
```bash
git restore .
```

#### Explanation:
- This will discard all changes made in the working directory and restore the files to the last committed state.
- If you want to discard changes in a specific file, you can run:
  ```bash
  git restore <filename>
  ```

---

### 2. **Scenario: You Have Added Changes but Have Not Yet Committed (Staged Changes)**
In this case, the changes have been added to the staging area using `git add`, but you haven't committed them yet.

#### Command to Unstage and Discard Changes:
```bash
git restore --staged .
git restore .
```

#### Explanation:
- `git restore --staged .`: This command removes the changes from the staging area but keeps them in the working directory.
- `git restore .`: This command discards the changes in the working directory, restoring the files to their last committed state.

---

### 3. **Scenario: You Have Committed Changes but Have Not Yet Pushed (Committed Locally)**
In this case, the changes have been committed locally using `git commit`, but they have not been pushed to the remote repository.

#### Command to Undo the Last Commit and Discard Changes:
```bash
git reset --hard HEAD^
```

#### Explanation:
- This command moves the `HEAD` pointer back to the previous commit (i.e., `HEAD^`) and removes the commit that hasn’t been pushed. It also resets the working directory and staging area to match the previous commit, discarding all changes.
- If you want to reset to a specific commit, you can replace `HEAD^` with the commit hash.

#### Command to Undo the Last Commit but Keep the Changes:
```bash
git reset --soft HEAD^
```

#### Explanation:
- This command removes the last commit but keeps your changes in the working directory. The changes remain staged, so you can re-commit them if needed.

---

### 4. **Scenario: You Have Pushed Changes but Want to Undo Them (Already Pushed)**
In case you've already pushed the commit and want to undo it, things get more complicated because the changes are now in the remote repository. Here’s what you can do:

#### To Undo the Commit and Push the Changes Back to the Remote (Force Push):
```bash
git reset --hard HEAD^
git push --force
```

#### Explanation:
- `git reset --hard HEAD^`: Moves the `HEAD` pointer back to the previous commit and discards the changes.
- `git push --force`: Updates the remote repository by force-pushing, which rewrites history on the remote. **Be cautious** when using `--force`, especially if others are working on the same branch.

Alternatively, you can use `git revert` if you want to keep the history but undo the changes:

#### To Revert a Pushed Commit:
```bash
git revert <commit-hash>
git push
```

#### Explanation:
- `git revert <commit-hash>`: Creates a new commit that undoes the changes introduced by the commit you specify, but keeps the history intact.
- This is a safer option if the commit has already been pushed to a shared repository.

---