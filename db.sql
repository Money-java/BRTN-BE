use money;
select * from habit;

select * from postcomment;

select count(*) from postcomment where post_id = 1;

select * from postcomment;

select post_id from postcommunity;
select * from postcommunity;
INSERT INTO postCommunity VALUES (
             2,
             1,
             1,
             10,
             NULL,
             '샘플',
             '#커피#카페',
             '2024-10-07 15:22:51'
         );


select * from postcomment;

SELECT COUNT(habit_id)
FROM HabitCheck
WHERE check_date = '2024-09-01 00:00:00';

SELECT DATE_FORMAT(check_date, '%Y-%m-%d') AS check_date, COUNT(habit_id) AS habit_count
FROM HabitCheck
WHERE check_date BETWEEN '2024-09-01 00:00:00' AND NOW()
GROUP BY check_date;

INSERT INTO habitcheck
VALUES ('2024-10-2 00:00:00', 1, 1, 1);
INSERT INTO habitcheck
VALUES
    ('2024-10-5 00:00:00', 1, 1, 1),
    ('2024-10-5 00:00:00', 2, 1, 2),
    ('2024-10-6 00:00:00', 1, 1, 1),
    ('2024-10-7 00:00:00', 1, 1, 1),
    ('2024-10-7 00:00:00', 3, 1, 4),
    ('2024-10-10 00:00:00', 1, 1, 1);


INSERT INTO habitcheck
VALUES
    ('2024-10-10 00:00:00', 2, 1, 2),
    ('2024-10-10 00:00:00', 3, 1, 4);

select * from habitcheck;
SELECT mh.my_habit_id, mh.user_id, h.habit_id, h.habit_title, h.category_title, mh.state
FROM MyHabit mh
         JOIN HABIT h ON mh.habit_id = h.habit_id
WHERE mh.user_id = 1;
