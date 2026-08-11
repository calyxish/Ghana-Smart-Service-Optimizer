# Contribution Guide

How to use Git and GitHub to submit your work for the Ghana Smart Service Operations Optimizer.

---

## Before You Start

Make sure you have completed everything in [Guide_Installation.md](Guide_Installation.md) first. You should have:
- Git installed
- Java 17 installed
- The repo cloned on your computer
- Your IDE set up

---

## Branch Strategy

We use a simple branch model to keep things organized:

```
main              (protected - only Team Lead merges here)
 │
dev               (integration branch - all work goes here first)
 │
feature/your-name/your-task    (your personal working branch)
```

**You will never touch `main` directly.** You create a branch from `dev`, do your work, push it, and create a Pull Request (PR) to merge into `dev`. The Team Lead reviews and merges.

---

## Step-by-Step Workflow

### Step 1: Always Start From the Latest `dev`

Every time you start working, make sure you have the latest code:

```bash
git checkout dev
git pull origin dev
```

This downloads any new changes your teammates have pushed.

### Step 2: Create Your Branch

Create a new branch for your specific task. Use this naming format:

```
feature/<your-first-name>/<short-task-description>
```

**Examples:**

```bash
git checkout -b feature/caleb/database-schema
git checkout -b feature/prince/linked-list
git checkout -b feature/emelia/unit-tests
git checkout -b feature/kennedy/dijkstra-algorithm
```

**What this command does:** It creates a new branch AND switches you to it.

### Step 3: Do Your Work

Write your code in the correct folder (check [Guide_Implementation.md](Guide_Implementation.md) for where your code goes).

### Step 4: Save Your Work (Commit)

After writing some code, save it to Git:

```bash
# 1. See what files you changed
git status

# 2. Add the files you want to save
git add src/main/java/com/stackoverflow/smartservice/structures/LinkedList.java

# 3. Commit with a clear message
git commit -m "feat: Implement singly linked list with addFirst and addLast"
```

**IMPORTANT:** Do NOT use `git add .` (this adds everything). Add only the files you worked on.

### Step 5: Push Your Branch to GitHub

```bash
git push origin feature/your-name/your-task
```

If this is your first push on this branch, Git will tell you to set upstream. Just copy the command it gives you, or use:

```bash
git push -u origin feature/your-name/your-task
```

### Step 6: Create a Pull Request (PR)

1. Go to our GitHub repository in your browser
2. You will see a yellow banner saying your branch was recently pushed. Click **"Compare & pull request"**
3. Make sure it says: `base: dev` <-- `compare: feature/your-name/your-task`
4. Fill in the PR with this template:

```markdown
## What does this PR do?
(Briefly describe what you implemented)

## Role
(Which role number are you? e.g., Role 4: Linear Structures Lead)

## Checklist
- [ ] My code compiles without errors
- [ ] I did NOT use built-in Java collections for core logic
- [ ] I tested my code manually
- [ ] I only changed files in my assigned folder
- [ ] I used Java 17
```

5. Click **"Create pull request"**
6. Wait for the Team Lead to review and merge

### Step 7: After Your PR is Merged

Once your PR is merged, **delete your branch** and start fresh for the next task:

```bash
git checkout dev
git pull origin dev
git branch -d feature/your-name/your-task
```

**Never reuse a branch after it has been merged.** Always create a new one.

---

## Commit Message Format

Write clear commit messages so everyone can understand what changed:

```
<type>: <short description>
```

**Types you can use:**

| Type | When to use it |
|------|---------------|
| `feat` | You added a new feature or data structure |
| `fix` | You fixed a bug |
| `test` | You added or updated tests |
| `docs` | You updated documentation |
| `refactor` | You restructured code without changing behavior |
| `data` | You added or changed data files (CSV, SQL) |

**Good examples:**

```bash
git commit -m "feat: Implement binary search tree with insert and search"
git commit -m "feat: Add 50 Ghanaian locations to locations.csv"
git commit -m "fix: Fix off-by-one error in dynamic array resize"
git commit -m "test: Add edge case tests for empty stack"
git commit -m "data: Create database schema for locations and roads tables"
```

**Bad examples:**

```bash
git commit -m "update"           # Too vague
git commit -m "fixed stuff"      # What stuff??
git commit -m "asdfgh"           # Not helpful
```

---

## Common Git Problems and Solutions

### "I made changes but I'm on the wrong branch"

```bash
# Save your changes temporarily
git stash

# Switch to the correct branch
git checkout dev
git pull origin dev
git checkout -b feature/your-name/correct-branch

# Bring your changes back
git stash pop
```

### "Git says I have merge conflicts"

This means someone else changed the same file you changed. Do NOT panic.

1. Open the file Git is complaining about
2. Look for lines like:
   ```
   <<<<<<< HEAD
   (their code)
   =======
   (your code)
   >>>>>>> your-branch
   ```
3. Decide which version to keep (or combine them)
4. Remove the `<<<<<<<`, `=======`, and `>>>>>>>` markers
5. Save the file, then:
   ```bash
   git add the-file-you-fixed
   git commit -m "fix: Resolve merge conflict in FileName.java"
   ```

**If you are confused, ask the Team Lead for help before doing anything.**

### "I want to undo my last commit"

```bash
git reset --soft HEAD~1
```

This undoes the commit but keeps your code changes.

### "I accidentally committed to dev"

Tell the Team Lead immediately. Do not try to fix this yourself.

---

## Golden Rules

1. **Pull before you push.** Always `git pull origin dev` before creating a branch.
2. **One branch per task.** Don't mix multiple features in one branch.
3. **Small, frequent commits.** Commit after each meaningful piece of work.
4. **Never force push.** If `git push` fails, ask for help.
5. **Stay in your lane.** Only modify files in your assigned folder/package.
6. **Ask for help.** If Git is confusing, message the Team Lead. It is better to ask than to break something.
