# GradingSystem
Uses sqlite3 again. The database is located in file `grader.db`. `sqlite-jdbc-3.27.2.1.jar` is provided in `src/`.

## Git committing
```
# make some changes to files

git status  # look at what files you've modified. Red means you need to add it

git add .   # add files for commit

git status  # green files show what files you're going to commit

git commit -m "message"  # commit all the green files

git push origin 1.0      # push your commits to the repo on github

# if git push fails, you may need to pull all recent changes:
git pull origin 1.0
```

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
This is a change exampladsflasdfe
