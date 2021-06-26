CREATE SCHEMA IF NOT EXISTS rooms_with_bulbs_test;

USE rooms_with_bulbs_test ;

CREATE TABLE IF NOT EXISTS rooms (
                            id INT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(50) NOT NULL,
                            country VARCHAR(50) NOT NULL,
                            bulb_status VARCHAR(5) NOT NULL DEFAULT 'OFF',
                            PRIMARY KEY (id));

INSERT INTO rooms (id, name, country, bulb_status)
    VALUES (1, 'Test Room 1','Belarus','OFF'),(2, 'Test Room 2','Finland','ON'),(3, 'Test Room 3','Denmark','OFF');