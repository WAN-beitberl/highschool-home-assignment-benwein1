CREATE TABLE talbar.highschool_friendships (
    id INT NOT NULL AUTO_INCREMENT,
    friend_id INT NULL,
    other_friend_id INT NULL,
    PRIMARY KEY (id));
    
CREATE TABLE talbar.highschool (
id INT PRIMARY KEY,
first_name TEXT NOT NULL,
last_name TEXT NOT NULL,
email TEXT NOT NULL,
gender TEXT NOT NULL,
ip_address TEXT NOT NULL,
cm_height INT NOT NULL,
age INT NOT NULL,
has_car BOOLEAN NOT NULL,
car_color TEXT,
grade INT NOT NULL,
grade_avg DOUBLE  NOT NULL,
identification_card INT NOT NULL
);

CREATE VIEW talbar.StudentGrades as (
SELECT talbar.highschool.id, talbar.highschool.grade_avg
FROM talbar.highschool
GROUP BY id);