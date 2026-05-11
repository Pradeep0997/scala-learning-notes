/*Completed Topics:
Day 16: PostgreSQL - Introduction & Basics 

- Overview of PostgreSQL 

- Installation & setup  

- Connecting to PostgreSQL (psql, PgAdmin) 

- Creating and managing databases 

- Creating, altering, and deleting tables 

- Understanding data types (INT, TEXT, BOOLEAN, DATE, JSON, etc.) 

Day 17: PostgreSQL - SQL Queries & Data Manipulation 

- SELECT, INSERT, UPDATE, DELETE 

- Filtering with WHERE, ORDER BY, LIMIT 

- Using DISTINCT, CASE, and LIKE 

- Aggregate functions (COUNT, SUM, AVG, MIN, MAX) 

- Grouping with GROUP BY and HAVING 


*/
create DATABASE testdb;
\l
ALTER USER postgres PASSWORD '1234';
\q
psql -U postgres -d testdb
\dt
\q
ALTER USER postgres PASSWORD '1234';
\q
Create Database slickdb;
create user slickuser with password 'slick123';
grant all privileges on database slickdb to slickuser;
\q
\c slickdb
GRANT ALL ON SCHEMA public TO slickuser;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO slickuser;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO slickuser;
\q
SELECT version();
CREATE TABLE departments(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE  employees(
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  department_id INT, 
  job_title VARCHAR(100),
  salary NUMERIC(10, 2),
  is_active BOOLEAN DEFAULT TRUE,
  CONSTRAINT fk_department FOREIGN KEY(department_id) REFERENCES departments(id)
);
CREATE TABLE salary_accounts (
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  employee_id INT UNIQUE,
  bank_name VARCHAR(100),
  account_number VARCHAR(50),
  CONSTRAINT fk_employee_account FOREIGN KEY(employee_id) REFERENCES employees(id) ON DELETE CASCADE
);
q
quit
\q
psql -U postgres
\l
create database company_db;
create database company_db;
\l
\c company_db;
drop database company_db;
\q
\l
drop database company_db;
\l
create database company_db;
\l
\c company_db;
tables
\q
create table employees(
  emp_id INT Primary Key,
  full_name TEXT Not Null,
  is_active BOOLEAN Default True,
  hire_date DATE,
  metadata JSONB
);
\dt
\d employees
select * from employees;
\d departments
\d salary_accounts
alter table employees
add column if not exists metadata Jsonb Not null default '{}'::jsonb;
\d employees
alter table employees
add column if not exists email varchar(255);
\d employees
alter table employees
add constraint unique_employee_email unique(email);
\d
\dt
\d employees
alter table employees
rename column full_name to name;
\d employees
alter table employees 
rename column name to full_name;
\d employees
alter table employees
rename column full_name to name;
create table employee_dup(
 name text not null,
 emp_id int primary key,
 is_active boolean default true,
 hire_date date,
 metadata jsonb
);
\dt
drop table if exists employee_dup cascade;
\dt
\l
\q
create database day18_psql;
\c day18_psql;
\dt
\d
create table departments(
 id int generated always as identity primary key,
 name varchar(100) not null
);
\dt
create table employees(
 id int generated always as identity primary key,
 name varchar(255) not null,
 department_id int,
 job_title varchar(100),
 salary numeric(10,2),
 is_active boolean default true,
 constraint fk_department foreign key(department_id) references departments(id)
);
\dt
\d employees
create table salary_accounts(
 id int generated always as identity primary key,
 employee_id int unique,
 bank_name varchar(100),
 account_number varchar(50),
 constraint fk_employee_account foreign key(employee_id) references employees(id) on delete cascade
);
create table projects(
 id int generated always as identity primary key,
 title varchar(255) not null
);
\dt
create table employee_projects(
 employee_id int,
 project_id int,
 primary key (employee_id, project_id),
 constraint fk_emp foreign key(employee_id) references employees(id),
 constraint fk_proj foreign key(project_id) references projects(id)
);
\dt
\d employee_projects
insert into departments (name) values ('Engineering'), ('HR'), ('Marketing');
\d departments
select * from departments;
insert into employees (name, department_id, job_title, salary, is_active) Values
 ('Alice Smith',1,'Frontend Developer', 85000, True); 
select * from employees;
insert into employees (name, department_id, job_title, salary, is_active) Values
 ('Bob Jones',1,'Backend Developer', 90000, True)
, ('Charlie Brown',2,'HR Manager', 70000, TRUE),
('Diana Prince',3, 'Marketing Lead', 75000, False),
('Evan Wright', 1 , 'Devops Engineer', 95000,True);
select * from employees; 
insert into salary_accounts (employee_id, bank_name, account_number) values
 (1, 'Kotak Mahindra Bank', 'KKBK00012324');
select * from salary_accounts;
insert into salary_accounts (employee_id, bank_name, account_number) values
 (10, 'Kotak Mahindra Bank', 'KKBK00012324');
insert into salary_accounts (employee_id, bank_name, account_number) values
 (2, 'HDFC Bank', 'HDFC0005678'),;
insert into salary_accounts (employee_id, bank_name, account_number) values
 (2, 'HDFC Bank', 'HDFC0005678'),
(3, 'Kotak Mahindra Bank', 'KKBK0009985');
select * from salary_accounts; 
insert into projects (title) values ('website redesign'), ('cloud migration');
insert into employee_projects (employee_id, project_id) values
 (1,1),(2,1),(2,2), (5,2);
select * from projects;
select * from employee_projects;
update employees set salary = 92000 where name = 'Bob Jones';
select * from employees;
delete from employees where is_active = FALSE;
select * from employees;
select name,job_title, salary
from employees 
where salary > 80000
order by salary desc
limit 2;

\q
\s my_history.sql
