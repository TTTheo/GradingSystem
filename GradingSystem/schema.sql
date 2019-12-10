CREATE TABLE User (username varchar (255), password varchar (255));

-- one-to-many with courses
CREATE TABLE Semester (
    semester_id INTEGER PRIMARY KEY AUTOINCREMENT,
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

-- Course is many-to-many with Category
CREATE TABLE Category (
    cid INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR (255),
    part_num INT,
    percentage DOUBLE
);

-- Category is one-to-many with Part
CREATE TABLE Part (
    pid INTEGER PRIMARY KEY AUTOINCREMENT,
    category_id INTEGER NOT NULL,
    name VARCHAR (255),
    total_score DOUBLE,
    percentage DOUBLE
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

-- Relates Courses and Categories
CREATE TABLE Course_Category (
    cid INTEGER NOT NULL,
    course_id VARCHAR (255) NOT NULL,
    FOREIGN KEY (cid) REFERENCES Category(cid),
    FOREIGN KEY (course_id) REFERENCES Course(course_id),
    PRIMARY KEY (cid, course_id)
);


INSERT INTO User (username, password) VALUES ('a', 'a');

INSERT INTO Student (sid, name) VALUES ('u123', 'name');
INSERT INTO Grade (sid, pid, grade) VALUES ('u123', 1, 12.2);

-- To get all grades for student with id 'u123'
-- SELECT * FROM Grade NATURAL JOIN Student WHERE sid = 'u123';
