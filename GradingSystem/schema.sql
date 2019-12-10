CREATE TABLE User (username varchar (255), password varchar (255));

-- one-to-many with courses
CREATE TABLE Semester (
    semester_id INTEGER PRIMARY KEY,
    term varchar(10),
    year int
);

-- Student is many-to-many with Course
-- Student is one-to-many with Grade
CREATE TABLE Student (
    sid VARCHAR (255) PRIMARY KEY,
    name VARCHAR (255)
);

-- Semester is one-to-many with Course
-- Student is many-to-many with Course
CREATE TABLE Course (
    course_id VARCHAR (255) PRIMARY KEY,  -- eg 'cs591'
    semester_id INTEGER NOT NULL,
    name VARCHAR (255),
    FOREIGN KEY (semester_id) REFERENCES Semester(semester_id)
);

-- Course is one-to-many with Category
CREATE TABLE Category (
    cid INTEGER PRIMARY KEY,
    course_id VARCHAR (255) NOT NULL,
    name VARCHAR (255),
    percentage DOUBLE,
    FOREIGN KEY (course_id) REFERENCES Course(course_id)
);

-- Category is one-to-many with Part
CREATE TABLE Part (
    pid INTEGER PRIMARY KEY,
    cid INTEGER NOT NULL,
    name VARCHAR (255),
    total_score DOUBLE,
    percentage DOUBLE,
    FOREIGN KEY (cid) REFERENCES Category(cid)
);

-- Student is one-to-many with Grade
-- Part is one-to-many with Grade
CREATE TABLE Grade (
    sid VARCHAR (255) NOT NULL,
    pid INTEGER NOT NULL,
    grade DOUBLE,
    PRIMARY KEY (sid, pid), -- Composite key
    FOREIGN KEY (sid) REFERENCES Student(sid),
    FOREIGN KEY (pid) REFERENCES Part(pid)
);

-- many-to-many tables:

-- Relates Students and Courses
CREATE TABLE Course_Student (
    sid VARCHAR (255) NOT NULL,
    course_id VARCHAR (255) NOT NULL,
    FOREIGN KEY (sid) REFERENCES Student(sid),
    FOREIGN KEY (course_id) REFERENCES Course(course_id),
    PRIMARY KEY (sid, course_id)
);


-- Test data

INSERT INTO User (username, password) VALUES ('a', 'a');

INSERT INTO Semester (semester_id, term, year) VALUES (1, 'Fall', 2019);
INSERT INTO Course (course_id, semester_id, name)
    VALUES ('cs591', 1, 'OOP');
INSERT INTO Course (course_id, semester_id, name)
    VALUES ('cs101', 1, 'Intro');

INSERT INTO Student (sid, name) VALUES ('U09', 'Jerry');
INSERT INTO Student (sid, name) VALUES ('U10', 'Emma');

-- U09 is taking 2 courses
INSERT INTO Course_Student (sid, course_id) VALUES ('U09', 'cs591');
INSERT INTO Course_Student (sid, course_id) VALUES ('U09', 'cs101');

-- U10 is taking 1 course
INSERT INTO Course_Student (sid, course_id) VALUES ('U10', 'cs591');

INSERT INTO Category (cid, course_id, name, percentage)
    VALUES (1, "cs591", "Homework", 20);
INSERT INTO Part (pid, cid, name, total_score, percentage)
    VALUES (1, 1, "homework1", 100, 30);
INSERT INTO Part (pid, cid, name, total_score, percentage)
    VALUES (2, 1, "homework2", 100, 70);

INSERT INTO Category (cid, course_id, name, percentage)
    VALUES (2, "cs591", "Exam", 80);
INSERT INTO Part (pid, cid, name, total_score, percentage)
    VALUES (3, 2, "Midterm", 100, 40);
INSERT INTO Part (pid, cid, name, total_score, percentage)
    VALUES (4, 2, "Final", 100, 60);

INSERT INTO Grade (sid, pid, grade) VALUES ('U09', 1, 60); -- hw 1
INSERT INTO Grade (sid, pid, grade) VALUES ('U09', 2, 50); -- hw 2
INSERT INTO Grade (sid, pid, grade) VALUES ('U09', 3, 70); -- midterm
INSERT INTO Grade (sid, pid, grade) VALUES ('U09', 4, 80); -- final

INSERT INTO Grade (sid, pid, grade) VALUES ('U10', 1, 52); -- hw 1
INSERT INTO Grade (sid, pid, grade) VALUES ('U10', 2, 80); -- hw 2
INSERT INTO Grade (sid, pid, grade) VALUES ('U10', 3, 100); -- midterm
INSERT INTO Grade (sid, pid, grade) VALUES ('U10', 4, 84); -- final

-- To get all grades for student with id 'U09'
-- SELECT Student.Name, Grade.grade FROM Grade NATURAL JOIN Student WHERE sid = 'U09';

-- To get all parts of a Category
-- SELECT * FROM Category, Part WHERE Category.cid = Part.cid;
