CREATE TABLE customers (
customer_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
user_name VARCHAR(20) NOT NULL UNIQUE,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
PRIMARY KEY (customer_id)
);

CREATE TABLE customerServices (
custServ_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
user_name VARCHAR(20) NOT NULL UNIQUE,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
PRIMARY KEY (custServ_id)
);

CREATE TABLE onlineChatCustomers (
olc_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
user_id INT NOT NULL,
user_name VARCHAR(20) NOT NULL UNIQUE,
online Boolean NOT NULL,
PRIMARY KEY (olc_id)
);

CREATE TABLE onlineChatCustServs (
olcs_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
user_id INT NOT NULL,
user_name VARCHAR(20) NOT NULL UNIQUE,
online Boolean NOT NULL,
PRIMARY KEY (olcs_id)
);

CREATE TABLE chatLog (
chatLog_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
msgFrom VARCHAR(20) NOT NULL,
msgTo VARCHAR(20) NOT NULL,
dateTime timestamp NOT NULL,
msgContent VARCHAR(500) NOT NULL,
PRIMARY KEY (chatLog_id)
);


INSERT INTO customers(user_name,first_name,last_name,password) VALUES
('Chris', 'Christian', 'Bale', 'password'),
('Anne', 'Anne', 'Hathaway', 'password');

INSERT INTO customerServices(user_name,first_name,last_name,password) VALUES
('Vivien', 'Vivien', 'Leigh', 'password');
