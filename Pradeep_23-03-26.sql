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
select distinct bank_name from salary_accounts;
select name from employees where name like 'A%' or name like 'E%';
select name, salary,
  case
     when salary >= 90000 then 'High Bracket'
     when salary >= 80000 then 'Mid Bracket'
  end as salary_tier
from employees;
select name, salary,
  case
     when salary >= 90000 then 'High Bracket'
     when salary >= 80000 then 'Mid Bracket'
  end as salary_tier
from employees;
select name, salary,
  case
     when salary >= 90000 then 'High Bracket'
     when salary >= 80000 then 'Mid Bracket'
  end as salary_tier
from employees
select name, salary,
  case
     when salary >= 90000 then 'High Bracket'
     when salary >= 80000 then 'Mid Bracket' else 'Standard Bracket'
  end as salary_tier
from employees;
select name, salary,
  case
     when salary >= 90000 then 'High Bracket'
     when salary >= 80000 then 'Mid Bracket' else 'Standard Bracket'
  end as salary_tier
from employees;
select department_id, Count(*) as total_employees, Round(avg(salary),2) as avg_salary
from employees
group by department_id
having avg(salary) > 75000;
select e.name, d.name as department_name
from employees e
inner join departments d on e.department_id = d.id;
select e.name, s.bank_name
from employees e
left join salary_accounts s on e.id = s.employee_id;
select name, salary
from employees
where salary > (select avg(salary) from employees);
with EngineeringDept As (
 select id from departments where name = 'Engineering'
),
HighEarners As(
 select name, department_id from employees where salary > 85000
)
select h.name
from HighEarners h
Inner Join EngineeringDept ed ON h.department_id = ed.id;
select * from employees;
select * from departments;
\q
\s my_history.sql
\q
\l
create database day19_sql;
\l
alter database day19_sql rename to day19_psql;
\l
drop table if exists system_logs cascade;
create table system_logs(
  id int generated always as identity primary key,
  user_id int not null,
  status varchar(50) not null,
  created_at timestamp not null,
  payload Jsonb not null
);
\dt
\c day19_psql
drop table if exists system_logs cascade;
\dt
create table system_logs(
  id int generated always as identity primary key,
  user_id int not null,
  status varchar(50) not null,
  created_at timestamp not null,
  payload Jsonb not null
);
\dt
\q
\dt
drop table if exists system_logs cascade;
\dt
\c day19_psql
\dtt
--Insert
-- 500,000 rows of random dummy data using generate_series to simulate real-world production table
insert into system_logs (user_id, status, created_at, payload)
select
    ( random() * 10000) :: INT, 
    CASE WHEN random() > 0.5 THEN 'SUCCESS' ELSE 'ERROR' END,
    NOW() - (random() * interval '365 days'),
    ('{"browser": "Chrome", "load_time_ms": ' || (random() * 1000) :: INT || '}'):: jsonb
From generate_series(1, 500000);
select * from system_logs
limit 1000;
-- Before indexing : Seq Scan checks all 500,00 rows and this slow way
explain 
select * from system_logs where user_id = 5432;
Explain Analyze
select * from system_logs where user_id = 5432;
-- searching inside Jsonb without an index is also very slow
explain analyze
select * from system_logs where payload @> '{"browser": "Chrome"}';
-- Creating indexes
-- B tree Index: Perfect for exact matches, ranges, and sorting
-- indexing user_id and created_at attributes
create index idx_logs_user_id on system_logs(user_id);
create index idx_logs_created_at on system_logs(created_at);
-- hash index: slightly faster than B-Tree, but only for exact equality (=)
create index idx_log_status on system_logs Using HASH(status);
create index idx_logs_payload on system_logs using GIN (payload);
-- After indexing (fast way)
explain analyze
select * from system_logs where user_id = 5432;
Explain Analyze
select * from system_logs where payload @> '{"browser": "Chrome"}';
Update system_logs
SET payload = '{"browser": "Firefox", "load_time_ms": 150}' :: jsonb
where id <= 50;
Vacuum Analyze system_logs;
Select * from system_logs where payload @> '{"browser" : "Firefox"}';
Explain Analyze
Select * from system_logs where payload @> '{"browser" : "Firefox"}';
Explain Analyze
select * from system_logs where payload @> '{"browser": "Chrome"}';
\q
\c day19_psql
\dt
DROP TABLE IF EXISTS audit_logs CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
CREATE TABLE accounts(
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  owner_name VARCHAR(100) NOT NULL,
  balance NUMERIC(10,2) NOT NULL CHECK(balance >=0 )
);
\dt
CREATE TABLE audit_logs(
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  account_id INT,
  old_balance NUMERIC(10,2),
  new_balance NUMERIC(10,2),
  changed_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMPT
);
CREATE TABLE audit_logs(
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  account_id INT,
  old_balance NUMERIC(10,2),
  new_balance NUMERIC(10,2),
  changed_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO accounts (owner_name, balance) VALUES
('Alice', 100.00),
('Bob', 500.00);
-- Transactions (BEGIN, COMMIT, ROLLBACK)
BEGIN;
 UPDATE accounts SET  balance = balance - 100 WHERE owner_name = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE owner_name = 'Bob';
COMMIT;
-- Above transaction is example for successful transaction
-- now failed transaction (Atomicity)
BEGIN;
 UPDATE accounts SET balance = balance - 2000 WHERE owner_name = 'Bob';
UPDATE accounts SET balance = balance + 2000 WHERE owner_name = 'Alice';
ROLLBACK;
-- WE UNDo the transaction safely
SELECT * FROM accounts;
-- Triggers and trigger functions (Automation)
CREATE OR REPLACE FUNCTION log_balance()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.balance <> OLD.balance THEN
       INSERT INTO audit_logs (account_id, old_balance, new_balance)
       VALUES (OLD.id, OLD.balance, NEW.balance);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Attaching the function to the table as a Trigger
CREATE TRIGGER trg_audit_account_balances
AFTER UPDATE ON accounts
FOR EACH ROW
EXECUTE FUNCTION log_balance();
-- testing the trigger!
UPDATE accounts SET balance = 1500.00 WHERE owner_name = 'Alice';
select * from accounts;
select * from audit_logs;
CREATE OR REPLACE PROCEDURE safe_transfer(
 sender_name VARCHAR,
 receiver_name VARCHAR,
 transfer_amount NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
   UPDATE accounts SET balance = balance - transfer_amount WHERE owner_name = sender_name;
   UPDATE accounts SET balance = balance + transfer_amount WHERE owner_name = receiver_name;

COMMIT;

END;
$$;
CALL safe_transfer('Alice','Bob',250.00);
select * from accounts;
select * from audit_logs;
\s my_history.sql
sudo cp /var/lib/postgresql/my_history.sql ~/Desktop/
sudo chown $USER:$USER ~/Desktop/my_history.sql
;
\q
sudo cp /var/lib/postgresql/my_history.sql ~/Desktop/
sudo chown $USER:$USER ~/Desktop/my_history.sql
;
ALTER USER postgres PASSWORD 'Reset@123';
\l
CREATE DATABASE day21_psql
;
\l
\c day21_psql
\dt
CREATE TABLE company_orders(
  id INT GENERATED ALWAYS AS IDENTITY,
  customer_name VARCHAR (100),
  amount NUMERIC(10,2),
  order_date DATE NOT NULL,
  PRIMARY KEY(id, order_date)
) PARTITION BY RANGE (order_date);
-- here we append 'Partition by range' and specify the column we are splitting by
-- the partition key must be part of the primary key
-- Now creating the physical "Child" partitions (we are splitting the data by year)
CREATE TABLE orders_2025 PARTITION OF company_orders
  FOR VALUES FROM ('2025-01-01') TO ('2026-01-01');
CREATE TABLE orders_2026 PARTITION OF company_orders
  FOR VALUES FROM ('2026-01-01') TO ('2027-01-01');
\DT
\dt
-- Inserting data into the Master table
-- PostgreSQL is smart enough to route these to the correct physical child tables
INSERT INTO company_orders (customer_name, amount, order_date) VALUES
 ('Alice', 150.00, '2025-05-15'),
 ('Bob',200.00, '2026-03-20');
select * from company_orders;
SELECT * FROM company_orders WHERE order_date = '2026-03-20';
-- using EXPLAIN ANALYZE on this query, so we will see Postgres ONLY scaning the orders_2026 table
EXPLAIN ANALYZE
SELECT * FROM company_orders WHERE order_date = '2026-03-20';
-- 2. Full-Text Search
-- Creating a table for blog posts
CREATE TABLE knowledge_base (
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title VARCHAR(255),
  content TEXT,
  -- this below special column will hold our searchable, optimized tokens
  search_tokens tsvector
);
select * from knowledge_base;
INSERT INTO knowledge_base (title, content) VALUES
 ('How to setup Ubuntu', 'Installing Ubuntu 24.04 is very easy and fast. The installation takes minutes.'),
('PostgreSQL Backup','Always backup your database using pg_dump to prevent data loss.');
-- Populating the tsvector column using the 'to_tsvector' function
-- 'english' tells Postgres which dictionary to use so it knows English grammar rules.
UPDATE knowledge_base
SET search_tokens = to_tsvector('english', title || ' ' || content);
-- Creating a GIN index on the vector column so searches are instant
CREATE INDEX idx_kb_search ON knowledge_base USING GIN (search_tokens);
-- Executing a full-text search
-- The @@ operator means "does the vetor match the query?"
SELECT title, content
FROM knowledge_base
WHERE search_tokens @@ to_tsquery('english', 'install & fast');
Explain Analyze
SELECT title, content
FROM knowledge_base
WHERE search_tokens @@ to_tsquery('english', 'install & fast');
\q
\l
\c day21_psql_recovered
\dt
select * from knowledge_base;
\s my_history.sql


---Backup & Restore
/*
1. Create a Backup (pg_dump)
This command exports your day21_psql into a compressed custom file (.dump) and saves it directly to your Desktop.

pg_dump -h localhost -U postgres -F c -d day21_psql -f ~/Desktop/day21_psql.dump

(It will ask for your postgres password.  -F c means "Format Custom", which is compressed and best for restores.)

2. Restore a Backup (pg_restore)
Let's pretend your server crashed and you created a brand new, empty database named day21_psql_recovered. You would restore the data from your Desktop like this:
pg_restore -h localhost -U postgres -d day21_psql_recovered -1 ~/Desktop/day21_psql.dump

(The -1 flag wraps the whole restore in a single transaction. If it fails halfway through, it safely rolls back so you don't have a half-broken database).
*/


pradeepreddy@adminpc-ThinkPad-E14-Gen-7:~$ pg_dump -h localhost -U postgres -F c -d day21_psql -f ~/Desktop/company_backup.dump
Password: 
pradeepreddy@adminpc-ThinkPad-E14-Gen-7:~$ psql -h localhost -U postgres -c "CREATE DATABASE day21_psql_recovered;"
Password for user postgres: 
CREATE DATABASE
pradeepreddy@adminpc-ThinkPad-E14-Gen-7:~$ pg_restore -h localhost -U postgres -d day21_psql_recovered -1 ~/Desktop/day21_psql.dump
pg_restore: error: could not open input file "/home/pradeepreddy/Desktop/day21_psql.dump": No such file or directory
pradeepreddy@adminpc-ThinkPad-E14-Gen-7:~$ pg_restore -h localhost -U postgres -d day21_psql_recovered -1 ~/Desktop/day21_psql.dump
Password: 

