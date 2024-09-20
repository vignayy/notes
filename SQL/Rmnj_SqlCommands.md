# SQL Commands

## Create a Database

```sql
-- Create a database
CREATE DATABASE ust_db;
```

## Switch to the Database

```sql
-- Switch to the database
USE ust_db;
```

## Create a Table

```sql
-- Create a table
CREATE TABLE trainee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    trainee_name VARCHAR(50),
    location VARCHAR(50)
);
```

## Insert Some Records

```sql
-- Insert some records
INSERT INTO trainee (trainee_name, location) VALUES 
    ('Sandra John', 'Kerala'),
    ('Shreya Gupta', 'Bangalore'),
    ('Suhas', 'Bengaluru'),
    ('Gururaj', 'Bengaluru'),
    ('Rose', 'Kerala'),
    ('Vishnu', 'Kerala'),
    ('Archit', 'Bangalore'),
    ('Ashwini', 'Bengaluru'),
    ('Varsha', 'Bangalore'),
    ('SUNNY', 'BANGALORE'),
    ('Abhishek', 'Bangalore');
```

## Retrieve the Records

```sql
-- Retrieve the records
SELECT * FROM trainee;
```

## Update the Data

```sql
-- Update the data
UPDATE trainee SET location = 'Bengaluru' WHERE location = 'Bangalore';
UPDATE trainee SET location = 'Kochi' WHERE id = 5;
UPDATE trainee SET location = 'Kozhikode' WHERE id = 6;
UPDATE trainee SET location = 'Kochi' WHERE id = 1;
```

## Truncate Records

```sql
-- Truncate records
TRUNCATE TABLE trainee;
```

## Drop the Table

```sql
-- Drop the table
DROP TABLE trainee;
```

## Alter a Table

```sql
-- Alter a table
-- Create a new column gender
ALTER TABLE trainee ADD gender CHAR(6);

-- Update gender values
UPDATE trainee SET gender = 'F' WHERE id IN (1, 2, 5, 8, 9);
UPDATE trainee SET gender = 'M' WHERE id IN (3, 4, 6, 7, 10, 11);
```

## Delete Some Records

```sql
-- Delete some records
DELETE FROM trainee WHERE id = 12;
```

## Retrieving Data Conditionally

```sql
-- Find all the trainees from Bengaluru
SELECT * FROM trainee WHERE location = 'Bengaluru';

-- Find all the trainees from Bengaluru who are female
SELECT * FROM trainee WHERE location = 'Bengaluru' AND gender = 'F';
```

## Group Functions

```sql
-- Count the trainees location wise
SELECT location, COUNT(id) AS 'total trainees' 
FROM trainee 
GROUP BY location;

-- Find the location where most trainees belong to
SELECT location, COUNT(*) 
FROM trainee 
GROUP BY location 
ORDER BY 2 DESC 
LIMIT 1;
```

## Create a New Table

```sql
-- Create a new table project with columns id, title
CREATE TABLE project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(40)
);
```

## Insert Some Data

```sql
-- Insert some data
INSERT INTO project (title) VALUES 
    ('Library Management System'),
    ('Hospital Management'),
    ('Amazon Clone'),
    ('Baking App');
```

## Add a New Column to Trainee

```sql
-- Add a new column project_id to trainee
ALTER TABLE trainee ADD project_id INT;
```

## Create a Foreign Key

```sql
-- Create a foreign key
ALTER TABLE trainee 
ADD CONSTRAINT fk_project_id 
FOREIGN KEY (project_id) 
REFERENCES project(id);

-- Update project_id for a trainee
UPDATE trainee SET project_id = 1 WHERE id = 2;
```

## Find the Project with No Trainee

```sql
-- Find the project that does not have any trainee
SELECT p.id, title 
FROM project p 
LEFT JOIN trainee t ON p.id = t.project_id 
WHERE t.project_id IS NULL;

SELECT p.id, title 
FROM trainee t 
RIGHT JOIN project p ON p.id = t.project_id 
WHERE t.project_id IS NULL;
```