CREATE TABLE Subscriber
(
    id    INT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
INSERT INTO Subscriber (id, name, email)
VALUES (1, 'Nombre1', 'email1@example.com');
INSERT INTO Subscriber (id, name, email)
VALUES (2, 'Nombre2', 'email2@example.com');
