CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    nickname VARCHAR(100),
    age VARCHAR(100),
    gender VARCHAR(100),
    position VARCHAR(100),
    position_id VARCHAR(100),
    active_date DATE,
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
    name VARCHAR(100),
    active_date VARCHAR(100)
)