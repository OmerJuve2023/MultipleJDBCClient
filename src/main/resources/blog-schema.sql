CREATE TABLE Post (
                      id VARCHAR(255) PRIMARY KEY NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      date DATE NOT NULL,
                      timeToRead INT NOT NULL,
                      tags VARCHAR(255)
);

INSERT INTO Post (id, title, content, date, timeToRead, tags)
VALUES ('1', 'Título del post', 'Contenido del post', '2024-05-03', 10, 'etiqueta1, etiqueta2');
INSERT INTO Post (id, title, content, date, timeToRead, tags)
VALUES ('2', 'Título del post 2', 'Contenido del post 2', CURRENT_DATE, 5, 'etiqueta1, etiqueta2');