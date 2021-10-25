INSERT INTO audience(id, audience_number)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5)
ON CONFLICT DO NOTHING;

INSERT INTO student_groups(id, group_number)
VALUES (1, 100),
       (2, 101),
       (3, 102),
       (4, 103),
       (5, 104)
ON CONFLICT DO NOTHING;

INSERT INTO lectures(id, title_lecture, audience_id, group_id, lecture_day)
VALUES (1, 'Biology', 1, 1, 'MONDAY'),
       (2, 'Mathematics', 2, 2, 'TUESDAY'),
       (3, 'Chemistry', 3, 3, 'WEDNESDAY'),
       (4, 'Mathematics', 4, 4, 'THURSDAY'),
       (5, 'Biology', 5, 5, 'FRIDAY'),
       (6, 'Chemistry', 1, 1, 'SATURDAY'),
       (7, 'Biology', 2, 2, 'SUNDAY'),
       (8, 'Physics', 3, 3, 'MONDAY'),
       (9, 'Mathematics', 4, 4, 'TUESDAY'),
       (10, 'Physics', 5, 5, 'WEDNESDAY')
ON CONFLICT DO NOTHING;

INSERT INTO student(id, group_id, course, first_name, last_name)
VALUES (1, 1, 1, 'Ivan', 'Homenko'),
       (2, 1, 1, 'Nikita', 'Pohodski'),
       (3, 2, 2, 'Denis', 'Gonchar'),
       (4, 2, 2, 'Nikita', 'Mikloshevich'),
       (5, 3, 3, 'Darya', 'Bandzik'),
       (6, 3, 3, 'Vladimir', 'Trusov'),
       (7, 4, 4, 'Ihor', 'Bubka'),
       (8, 4, 4, 'Oleg', 'Bukato'),
       (9, 5, 1, 'Anastasia', 'Korotkova'),
       (10, 5, 1, 'Ignat', 'Keda')
ON CONFLICT DO NOTHING;