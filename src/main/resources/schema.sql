CREATE TABLE IF NOT EXISTS users_table
(
    id SERIAL PRIMARY KEY,
    -- first_name VARCHAR(50)   NOT NULL,
    -- last_name VARCHAR(50)  NOT NULL,
    password VARCHAR(500)  NOT NULL,
    address VARCHAR(100)  ,
    -- NOT NULL
    email VARCHAR(50) UNIQUE NOT NULL,
    roles VARCHAR [] NOT NULL,
    enabled boolean DEFAULT true
);
-- TRUNCATE userdata RESTART IDENTITY;

-- INSERT INTO userdata (first_name, last_name, password, address, email,  roles)
-- VALUES ('mahade','hassan', 'yUIixNNy9y/I3Y3goS4IDOtklaJmm8QNAUv2cpwXOE8=', 'Bangladesh', 'abc.test@abc.com', '{"ROLE_USER"}');
CREATE Table if NOT EXISTS Author
(
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);
CREATE Table if NOT EXISTS Book
(
     id SERIAL PRIMARY KEY ,
     title VARCHAR(50) NOT NULL,
     isbn VARCHAR(50) NOT NULL,
     pageCount VARCHAR(50) NOT NULL,
     author_id INT,
     FOREIGN KEY (author_id) REFERENCES
     Author(id)
);