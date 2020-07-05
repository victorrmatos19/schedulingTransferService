USE mydb;

CREATE TABLE mydb.transfer (id int(20) NOT NULL AUTO_INCREMENT, rate DECIMAL(9,2), value DECIMAL(9,2), transfer_date DATE, target_account varchar(255), source_account varchar(255), scheduling_date DATE, PRIMARY KEY(id));

CREATE TABLE mydb.rate (id int(20) NOT NULL AUTO_INCREMENT, type varchar(255) NOT NULL, min_interval int(20), max_interval int(20), value DECIMAL(9,2), percent DECIMAL(9,2), value_limit DECIMAL(9,2), PRIMARY KEY(id));

INSERT INTO rate (type, min_interval, max_interval, percent, value) VALUES ('A', 0, 0, 0.03, 3.0);
INSERT INTO rate (type, min_interval, max_interval, value) VALUES ('B', 1, 10, 12.0);
INSERT INTO rate (type, min_interval, max_interval, percent) VALUES ('C', 11, 20, 0.08);
INSERT INTO rate (type, min_interval, max_interval, percent) VALUES ('C', 21, 30, 0.06);
INSERT INTO rate (type, min_interval, max_interval, percent) VALUES ('C', 31, 40, 0.04);
INSERT INTO rate (type, min_interval, percent, value_limit) VALUES ('C', 41, 0.02, 100000.0);