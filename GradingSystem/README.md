# GradingSystem
Uses sqlite3 again. The database is located in file `grader.db`. `sqlite-jdbc-3.27.2.1.jar` is provided in `src/`.

## Running (command line)
Start in the `src` directory:
```
javac $(find . -name "*.java")  # Compile all java classes
java -classpath ".;sqlite-jdbc-3.27.2.1.jar" Main  # Run with sqlite JDBC
```


## Login
There's currently a user made for testing purposes:
```
username: a
password: a
```
Signing up is not supported yet.


## Example
This is a change example
