# Installation Guide

Everything you need to set up your computer to work on this project. Follow every step in order.

---

## Step 1: Install Java 17

Everyone **must** use Java 17. Not Java 8, not Java 21. **Java 17.**
This avoids "it works on my machine" problems.

### Windows

1. Go to: https://adoptium.net/temurin/releases/
2. Select:
   - **Operating System:** Windows
   - **Architecture:** x64
   - **Package Type:** JDK
   - **Version:** 17
3. Download the `.msi` installer
4. Run the installer. **Check the box that says "Set JAVA_HOME variable"**
5. After install, open **Command Prompt** and verify:

```bash
java -version
```

You should see something like:

```
openjdk version "17.0.x" ...
```

If you see a different version or an error, the installation did not work. Try restarting your computer first.

### macOS

1. Open **Terminal** (search for it in Spotlight)
2. If you have Homebrew installed:

```bash
brew install openjdk@17
```

3. If you do NOT have Homebrew, go to https://adoptium.net/temurin/releases/ and download the macOS `.pkg` installer for JDK 17

4. Verify:

```bash
java -version
```

### Linux (Ubuntu/Debian)

```bash
sudo apt update
sudo apt install openjdk-17-jdk
java -version
```

### How to Check You Have the Right Version

Run this in your terminal:

```bash
java -version
javac -version
```

Both should show version **17**. If they show something else, you need to fix your `JAVA_HOME` and `PATH` environment variables. Ask the Team Lead for help if stuck.

---

## Step 2: Install Git

### Windows

1. Go to: https://git-scm.com/download/win
2. Download and run the installer
3. Use the default settings (just click Next through everything)
4. After install, open **Git Bash** (not Command Prompt) and verify:

```bash
git --version
```

### macOS

Git comes pre-installed on most Macs. Open Terminal and check:

```bash
git --version
```

If not installed, it will prompt you to install Xcode Command Line Tools. Click **Install**.

### Linux

```bash
sudo apt install git
git --version
```

### Configure Git (Everyone Must Do This)

After installing Git, set your name and email. Use your **real name** and **university email**:

```bash
git config --global user.name "Your Full Name"
git config --global user.email "your.student.id@st.ug.edu.gh"
```

For example:

```bash
git config --global user.name "Prince Boateng"
git config --global user.email "22017084@st.ug.edu.gh"
```

---

## Step 3: Install an IDE

You can use any IDE, but we recommend one of these:

### Option A: IntelliJ IDEA (Recommended)

1. Go to: https://www.jetbrains.com/idea/download/
2. Download the **Community Edition** (free)
3. Install and open it
4. When creating/opening a project, make sure the **Project SDK** is set to **Java 17**

### Option B: VS Code

1. Go to: https://code.visualstudio.com/
2. Download and install
3. Open VS Code, go to Extensions (Ctrl+Shift+X / Cmd+Shift+X)
4. Install these extensions:
   - **Extension Pack for Java** (by Microsoft)
   - **Java Test Runner** (by Microsoft)

---

## Step 4: Clone the Repository

This downloads the project to your computer.

1. Open your terminal (Git Bash on Windows, Terminal on macOS/Linux)
2. Navigate to where you want to store the project:

```bash
cd ~/Documents
```

3. Clone the repository:

```bash
git clone https://github.com/calyxish/Ghana-Smart-Service-Optimizer.git
```

4. Enter the project folder:

```bash
cd Ghana-Smart-Service-Optimizer
```

5. Switch to the `dev` branch (this is where all development happens):

```bash
git checkout dev
```

6. Verify you are on the `dev` branch:

```bash
git branch
```

You should see:

```
* dev
  main
```

The `*` means you are on `dev`. Good.

---

## Step 5: Open the Project in Your IDE

### IntelliJ IDEA

1. Open IntelliJ
2. Click **Open** (or File > Open)
3. Navigate to the `Ghana-Smart-Service-Optimizer` folder and select it
4. IntelliJ may ask to import as a project. Click **OK/Trust**
5. Go to **File > Project Structure > Project**
6. Make sure the **SDK** is set to **17**
7. Make sure the **Language Level** is set to **17**

### VS Code

1. Open VS Code
2. Click **File > Open Folder**
3. Select the `Ghana-Smart-Service-Optimizer` folder
4. VS Code will detect Java files and set up automatically

---

## Step 6: Set Up SQLite (For Roles 2 and 3)

If your role involves the database, you need SQLite:

### Windows

1. Go to: https://www.sqlite.org/download.html
2. Download **sqlite-tools-win-x64** (the precompiled binaries)
3. Extract the zip file to `C:\sqlite`
4. Add `C:\sqlite` to your system PATH:
   - Search "Environment Variables" in Windows
   - Under System Variables, find `Path`, click Edit
   - Click New, add `C:\sqlite`
5. Open a new Command Prompt and verify:

```bash
sqlite3 --version
```

### macOS

SQLite comes pre-installed. Verify:

```bash
sqlite3 --version
```

### Linux

```bash
sudo apt install sqlite3
sqlite3 --version
```

### Java SQLite Driver

The SQLite JDBC driver JAR will be included in the project's `lib/` folder. You do NOT need to install it separately. When it is added, your IDE will pick it up automatically.

---

## Step 7: Verify Everything Works

Run these commands one by one and make sure none of them give errors:

```bash
java -version          # Should show 17
javac -version         # Should show 17
git --version          # Should show some version
git branch             # Should show dev with a *
```

If all four commands work, you are ready to start coding.

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `java -version` shows wrong version | Set `JAVA_HOME` to point to Java 17 installation folder |
| `git` command not found | Restart your terminal after installing Git |
| Cannot clone the repo | Make sure you have been added as a collaborator on GitHub. Ask the Team Lead |
| IntelliJ shows red errors everywhere | Go to File > Project Structure and set SDK to Java 17 |
| VS Code does not recognize Java | Install the "Extension Pack for Java" extension |

---

## Need Help?

If you are stuck on any step:

1. Take a screenshot of the error
2. Send it to the Team Lead (Kwakye Ishmael Affum) on the group chat
3. Include which step you are stuck on and what OS you are using (Windows/Mac/Linux)

Do NOT skip steps or try to work around errors. Get help first.
