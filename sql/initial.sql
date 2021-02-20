CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    nickname VARCHAR(100),
    age VARCHAR(100),
    gender VARCHAR(100),
    position VARCHAR(100),
    position_id VARCHAR(100),
    active_date VARCHAR(100),
    salary varchar(100),
    p_id VARCHAR(11) NOT NULL

);
CREATE TABLE office (
    id BIGSERIAL PRIMARY KEY,
    structure VARCHAR(100),
    position VARCHAR(100),
    position_id VARCHAR(100)
);

CREATE TABLE salary (
    id BIGSERIAL PRIMARY KEY,
    emp_id BIGSERIAL,
    deduction FLOAT,
    accurancy FLOAT,
    bonuses FLOAT
);

CREATE TABLE custom_salary (
    id BIGSERIAL PRIMARY KEY,
    emp_id BIGSERIAL,
    component1 FLOAT,
    component2 FLOAT,
    component3 FLOAT,
    component4 FLOAT,
    component5 FLOAT,
    component6 FLOAT,
    total FLOAT
);

