create table employees
(
ers_user_id number primary key,
ers_username varchar2(50) not null,
ers_password varchar2(50) not null,
user_first_name varchar2(100) not null,
user_last_name varchar2(100) not null,
user_email varchar(150) not null,
user_role_id number not null,
unique (ers_username, ers_password)
);

create table employee_roles
(
ers_user_role_id number primary key,
user_role varchar2(10) not null
);

create table reimbursement_types
(
reimb_type_id number primary key,
reimb_type varchar2(10) not null
);

create table reimbursement_status
(
reimb_status_id number primary key,
reimb_status varchar2(10) not null
);

create table reimbursement
(
reimb_id number primary key,
reimb_amount number not null,
reimb_submited date not null,
reimb_resolved date,
reimb_description varchar2(250),
reimb_receipt blob,
reimb_author number not null references employees(ers_user_id),
reimb_resolver number not null references employees(ers_user_id),
reimb_status_id number not null references reimbursement_status,
reimb_type_id number not null references reimbursement_types
);

describe employees;
describe reimbursement;
--inserting data into employee_roles table
insert into employee_roles
values (333, 'Employee');

insert into employee_roles
values (444, 'Manager');

--inserting into reinbursement_types table
insert into reimbursement_types
values (100, 'LODGING');

insert into reimbursement_types
values (101, 'travel');

insert into reimbursement_types
values (102, 'food');

insert into reimbursement_types
values (103, 'other');

delete reimbursement_types;
commit;

--selecting tables to check if data is inserted correctly
select * from  employees;
select * from employee_roles;
select * from reimbursement_types;
select * from reimbursement_status;
select * from reimbursement;