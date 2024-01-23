CREATE DATABASE SISDB;
USE SISDB;

CREATE TABLE Students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    email VARCHAR(100),
    phone_number BIGINT
);

CREATE TABLE Teacher (
    teacher_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE Courses (
    course_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT,
    teacher_id BIGINT,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);

CREATE TABLE Enrollments (
    enrollment_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Payments (
    payment_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    amount DOUBLE,
    payment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

INSERT INTO Students (student_id, first_name, last_name, date_of_birth, email, phone_number)
VALUES
    (1, 'Kavya', 'Kaja', '2002-04-12', 'kavya@gmail.com', '9734567890'),
    (2, 'Ramya', 'Gowda', '2001-06-18', 'ramya@gmail.com', '9876543210'),
    (3, 'Usha', 'Manda', '2006-12-05', 'usha@gmail.com', '8667890123'),
    (4, 'Raju', 'Kalaga', '2005-11-15', 'raju@gmail.com', '7434567890'),
    (5, 'Ravi', 'Mutta', '2009-10-20', 'ravi@gmail.com', '9876543210'),
    (6, 'Divya', 'Varma', '2000-03-21', 'divya@gmail.com', '9467890123'),
    (7, 'Ram', 'Ganta', '2004-04-06', 'ram@gmail.com', '8234567890'),
    (8, 'Mohan', 'Gurram', '2003-11-03', 'mohan@gmail.com', '9876543210'),
    (9, 'Lakshmi', 'Reddy', '2004-01-11', 'lakshmi@gmail.com', '6767890123'),
    (10, 'Vani', 'Koduri', '2006-06-10', 'vani@gmail.com', '7367890123');
    
SELECT * FROM Students;
    
INSERT INTO Teacher (teacher_id, first_name, last_name, email)
VALUES
    (11, 'Surya' , 'Pendyala', 'surya@hexaware.com'),
    (12, 'Ramesh', 'Jasthi', 'ramesh@hexaware.com'),
    (13, 'Pooja', 'Palakurthi', 'pooja@hexaware.com'),
	(14, 'John', 'Paluri', 'john@hexaware.com'),
    (15, 'Sony', 'Konduri', 'sony@hexaware.com'),
    (16, 'Mary', 'Dasari', 'mary@hexaware.com'),
	(17, 'Chaya', 'Parimi', 'chaya@hexaware.com'),
    (18, 'Pragna', 'Pathuri', 'pragna@hexaware.com'),
    (19, 'Rohan', 'Katta', 'rohan@hexaware.com'),
	(20, 'Rohith', 'Mavuri', 'rohith@hexaware.com');
    
SELECT * FROM Teacher;

INSERT INTO Courses (course_id, course_name, credits, teacher_id)
VALUES
    (21, 'Introduction to Programming', 3, 11),
    (22, 'Database Management', 4, 12),
    (23, 'Web Development', 3, 13),
    (24, 'Java', 4, 14),
    (25, 'C Programming', 3, 15),
    (26, 'MYSQL', 4, 12),
    (27, 'Full Stack', 4, 14),
    (28, 'Python', 3, 15),
    (29, 'Software Engineering', 4, 19),
    (30, 'data Structures and Algorithms', 3, 20);
    
SELECT * FROM Courses;

INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date)
VALUES
    (31, 1, 21, '2023-01-05'),
    (32, 2, 22, '2023-03-10'),
    (33, 3, 21, '2023-01-05'),
	(34, 4, 24, '2023-06-25'),
    (35, 5, 25, '2023-05-04'),
    (36, 2, 26, '2023-09-15'),
	(37, 4, 27, '2023-12-05'),
    (38, 5, 28, '2023-11-10'),
    (39, 9, 29, '2023-09-06'),
	(40, 10,29, '2023-09-06');
    
SELECT * FROM Enrollments;
    
INSERT INTO Payments (payment_id, student_id, amount, payment_date)
VALUES
    (41, 1, 10000, '2023-01-10'),
    (42, 2, 7000, '2023-03-15'),
    (43, 3, 10000, '2023-01-10'),
	(44, 4, 9000, '2023-06-30'),
    (45, 5, 13000, '2023-05-08'),
    (46, 2, 8000, '2023-09-20'),
	(47, 4, 16000, '2023-12-10'),
    (48, 5, 12000, '2023-11-15'),
    (49, 9, 15000, '2023-09-12'),
    (50, 10, 15000, '2023-09-12');
    
SELECT * FROM Payments;

INSERT INTO Students (first_name, last_name, date_of_birth, email, phone_number)
VALUES ('John', 'Doe', '1995-08-15', 'john.doe@example.com', '1234567890');

SELECT * FROM Students;

INSERT INTO Enrollments (student_id, course_id, enrollment_date)
VALUES (1 , 26, '2024-01-07');

SELECT * FROM Enrollments;

UPDATE Teacher
SET email = 'Surya123@gmail.com'
WHERE first_name = 'Surya';

SELECT * FROM Teacher;

DELETE FROM Enrollments WHERE student_id = 2 AND course_id = 22;

SELECT * FROM Enrollments;

UPDATE Courses
SET teacher_id = 11 WHERE course_id = 25;

SELECT * FROM courses;

DELETE FROM Enrollments
WHERE student_id = 10;

SELECT * FROM enrollments;

SET foreign_key_checks=0;
DELETE FROM Students
WHERE student_id = 10;

SELECT * FROM students;

UPDATE Payments
SET amount = 18000
WHERE payment_id = 41;

SELECT * FROM Payments;





SELECT s.student_id, s.first_name, s.last_name, SUM(p.amount) AS total_payments
FROM students s JOIN payments p ON s.student_id = p.student_id WHERE s.student_id = 2
GROUP BY s.student_id, s.first_name, s.last_name;

SELECT c.course_id, c.course_name, COUNT(e.student_id) AS enrolled_students_count
FROM Courses c LEFT JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name;

SELECT s.student_id, s.first_name, s.last_name FROM students s
LEFT JOIN enrollments e ON s.student_id = e.student_id
WHERE e.student_id IS NULL;

SELECT s.first_name, s.last_name, c.course_name FROM students s
JOIN enrollments e ON s.student_id = e.student_id
LEFT OUTER JOIN courses c ON e.course_id = c.course_id;

SELECT t.first_name AS teacher_first_name, t.last_name AS teacher_last_name, c.course_name
FROM teacher t
JOIN courses c ON t.teacher_id = c.teacher_id;

SELECT s.first_name, s.last_name, e.enrollment_date
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
WHERE c.course_name = 'MYSQL';

SELECT s.first_name,s.last_name FROM students s
LEFT JOIN payments p ON s.student_id = p.student_id
WHERE p.student_id IS NULL;

SELECT c.course_id,c.course_name FROM courses c
LEFT JOIN enrollments e ON c.course_id = e.course_id
WHERE e.course_id IS NULL;

SELECT e1.student_id, s.first_name, s.last_name, COUNT(e1.course_id) AS enrollments_count
FROM Enrollments e1 JOIN Students s ON e1.student_id = s.student_id
GROUP BY e1.student_id, s.first_name, s.last_name
HAVING COUNT(e1.course_id) > 1;

SELECT t.teacher_id,t.first_name,t.last_name FROM teacher t
LEFT JOIN courses c ON t.teacher_id = c.teacher_id
WHERE c.course_id IS NULL;





SELECT c.course_id, c.course_name, AVG(enrollment_count) AS average_students_enrolled
FROM Courses c
JOIN (
    SELECT course_id, COUNT(DISTINCT student_id) AS enrollment_count
    FROM Enrollments
    GROUP BY course_id
) e ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name;

SELECT s.student_id, s.first_name, s.last_name, p.amount AS highest_payment
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
WHERE p.amount = (SELECT MAX(amount) FROM Payments);
    
SELECT c.course_id, c.course_name, COUNT(e.enrollment_id) AS enrollment_count
FROM Courses c
JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name
HAVING COUNT(e.enrollment_id) = (
        SELECT MAX(enrollment_count)
        FROM
            (
                SELECT course_id, COUNT(enrollment_id) AS enrollment_count
                FROM Enrollments
                GROUP BY course_id
            ) AS max_enrollments
    );

SELECT t.teacher_id, t.first_name AS teacher_first_name, t.last_name AS teacher_last_name, COALESCE(SUM(p.amount), 0) AS total_payments
FROM Teacher t
LEFT JOIN Courses c ON t.teacher_id = c.teacher_id
LEFT JOIN Enrollments e ON c.course_id = e.course_id
LEFT JOIN Payments p ON e.student_id = p.student_id
GROUP BY t.teacher_id, t.first_name, t.last_name;

SELECT s.student_id, s.first_name, s.last_name
FROM Students s
WHERE
    (
        SELECT COUNT(c.course_id)
        FROM Courses c
        ) = (
        SELECT COUNT(DISTINCT e.course_id)
        FROM Enrollments e
        WHERE e.student_id = s.student_id
    );
    
SELECT teacher_id, first_name, last_name
FROM Teacher
WHERE teacher_id NOT IN (
        SELECT DISTINCT t.teacher_id
        FROM Teacher t
        JOIN Courses c ON t.teacher_id = c.teacher_id
    );

SELECT AVG(age) AS average_age
FROM (
    SELECT TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age
    FROM Students
) AS student_ages;

SELECT course_id, course_name
FROM Courses
WHERE course_id NOT IN (
        SELECT DISTINCT course_id
        FROM Enrollments
    );
    
SELECT e.student_id, e.course_id, COALESCE(SUM(p.amount), 0) AS total_payments
FROM Enrollments e
LEFT JOIN Payments p ON e.student_id = p.student_id
GROUP BY e.student_id, e.course_id;

SELECT s.student_id, s.first_name, s.last_name
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
GROUP BY s.student_id, s.first_name, s.last_name
HAVING COUNT(p.payment_id) > 1;

SELECT s.student_id, s.first_name, s.last_name, COALESCE(SUM(p.amount), 0) AS total_payments
FROM Students s
LEFT JOIN Payments p ON s.student_id = p.student_id
GROUP BY s.student_id, s.first_name, s.last_name;
    
SELECT c.course_name, COUNT(e.student_id) AS enrolled_students_count
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name;
    
SELECT s.student_id, AVG(p.amount) AS average_payment_amount
FROM Students s
JOIN Payments p ON s.student_id = p.student_id
GROUP BY s.student_id;












