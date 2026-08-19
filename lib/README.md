# lib/

The SQLite JDBC driver is required to compile/run anything under
`data/database` or `data/loader`, but `.gitignore` excludes `*.jar` from
version control (it's a ~14MB binary), so it is **not** committed here.

Each teammate needs to download it once:

```bash
curl -L -o lib/sqlite-jdbc.jar \
  https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.46.1.3/sqlite-jdbc-3.46.1.3.jar
```

Or manually from https://github.com/xerial/sqlite-jdbc/releases — grab
`sqlite-jdbc-3.46.1.3.jar` and save it as `lib/sqlite-jdbc.jar`.

## Compiling and running the data loader

From the repo root:

```bash
javac -cp "lib/sqlite-jdbc.jar" -d out $(find src/main/java -name "*.java")
java -cp "out;lib/sqlite-jdbc.jar" com.stackoverflow.smartservice.data.loader.DataLoader
```

(On macOS/Linux use `-cp "out:lib/sqlite-jdbc.jar"` — colon, not semicolon.)

This creates `database/ug_campus.db`, applies `database/schema.sql`, loads
all four CSV files from `data/csv/`, and prints a load summary.
