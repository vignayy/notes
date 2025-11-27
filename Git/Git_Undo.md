## **Git Restore, Reset, and Revert Cheat Sheet**

| Scenario                                             | Command                                                         | Description                                                                           |
| ---------------------------------------------------- | --------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| **Discard unstaged changes**                         | `git restore .`                                                 | Removes modifications from the working directory, restoring files to the last commit. |
| **Unstage files (keep working changes)**             | `git restore --staged .`                                        | Removes files from the staging area but keeps changes in the working directory.       |
| **Reset to a previous commit (mixed, default)**      | `git reset --mixed HEAD~1` or `git reset --mixed <commit-hash>` | Resets HEAD, **keeps changes in working directory**, **unstages them**.               |
| **Reset but keep changes staged (soft)**             | `git reset --soft HEAD~1` or `git reset --soft <commit-hash>`   | Moves HEAD, **keeps all changes staged**.                                             |
| **Reset and discard everything (hard)**              | `git reset --hard HEAD~1` or `git reset --hard <commit-hash>`   | Resets HEAD and **discards all changes** (working + staged).                          |
| **Using any reset after pushing (needs force push)** | `git reset --soft/mixed/hard <commit>` → `git push --force`     | Any reset after pushing rewrites history → remote requires force push.                |
| **Undo pushed commits safely (no history rewrite)**  | `git revert <commit-hash>` → `git push`                         | Creates a new commit that undoes an earlier commit safely.                            |

---

## **Git Stash Cheat Sheet**

| Scenario / Purpose                                    | Command                                                                    | Description                                            |
| ----------------------------------------------------- | -------------------------------------------------------------------------- | ------------------------------------------------------ |
| **Create a stash (tracked changes only)**             | `git stash`                                                                | Saves tracked staged + unstaged changes.               |
| **Include untracked files**                           | `git stash -u`                                                             | Stashes tracked + **untracked** files.                 |
| **Include all files (tracked + untracked + ignored)** | `git stash -a`                                                             | Stashes everything including ignored files.            |
| **Create stash with message (tracked only)**          | `git stash -m "message"`                                                   | Creates a stash with a custom label.                   |
| **Create stash with untracked + message**             | `git stash push -u -m "my stash"` *(same as `git stash -u -m "my stash"`)* | Saves tracked + untracked files with a custom message. |
| **List stashes**                                      | `git stash list`                                                           | Shows all stash entries.                               |
| **Apply and remove latest stash**                     | `git stash pop`                                                            | Applies **stash@{0}** and deletes it.                  |
| **Apply stash (but keep it)**                         | `git stash apply`                                                          | Applies the latest stash without removing it.          |
| **Apply a specific stash**                            | `git stash apply stash@{n}`                                                | Restores a specific stash entry.                       |
| **Show stash contents**                               | `git stash show -p`                                                        | Displays the diff stored in the stash.                 |
| **Delete a specific stash**                           | `git stash drop stash@{n}`                                                 | Removes a specific stash entry.                        |
| **Clear all stashes**                                 | `git stash clear`                                                          | Deletes all stash entries.                             |

---