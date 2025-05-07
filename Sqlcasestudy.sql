create database PM;
use PM;

-- Creating Project table
create table Project (
    id int primary key,
    projectname varchar(100),
    description text,
    start_date date,
    project_status enum('started', 'dev', 'build', 'test', 'deployed')
    );
    
-- Creating Employee table    
    create table Employee (
    id int primary key,
    name varchar(100),
    designation varchar(50),
    gender varchar(10),
    salary decimal(10,2),
    project_id int,
    constraint fk_project foreign key (project_id) references project(id) on delete cascade
);

-- Creating Task table
create table task (
    task_id int primary key,
    task_name varchar(100),
    project_id int,
    employee_id int,
    task_status enum('assigned', 'started', 'completed'),
	constraint fk_projectid foreign key (project_id) references project(id) on delete cascade,
    constraint fk_employee foreign key (employee_id) references employee(id) on delete cascade
);

insert into Project (id, projectname, description, start_date, project_status) values
(1, 'Smart CRM', 'A CRM system for customer engagement', '2025-04-10', 'started'),
(2, 'E-commerce Platform', 'An online marketplace solution', '2025-03-20', 'dev'),
(3, 'AI Chatbot', 'Developing an AI-based chatbot assistant', '2025-05-01', 'build'),
(4, 'Healthcare App', 'A telemedicine platform for doctors', '2025-02-15', 'test'),
(5, 'Finance Tracker', 'A budgeting and expense tracking application', '2025-01-25', 'deployed');

insert into Employee (id, name, designation, gender, salary, project_id) values
(1, 'Aryan Sharma', 'Software Engineer', 'Male', 75000.50, 1),
(2, 'Meera Kapoor', 'UI/UX Designer', 'Female', 67000.00, 2),
(3, 'Kabir Singh', 'Project Manager', 'Male', 95000.00, 3),
(4, 'Ananya Roy', 'QA Engineer', 'Female', 72000.75, 1),
(5, 'Rohan Verma', 'Data Analyst', 'Male', 68000.25, 4);

insert into Task (task_id, task_name, project_id, employee_id, task_status) values
(1, 'Design Login Page', 1, 2, 'assigned'),
(2, 'Develop API', 2, 1, 'started'),
(3, 'Database Optimization', 3, 5, 'completed'),
(4, 'User Testing', 4, 4, 'started'),
(5, 'Project Report Preparation', 5, 3, 'assigned');

