Day 18: PostgreSQL - Relationships & Joins 

- INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL OUTER JOIN 
-  Subqueries and Common Table Expressions (CTEs) 


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

--------
Day 20:
/* Creating stored procedures and functions using PL/pgSQL 
    Using triggers for automation 
*/

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
