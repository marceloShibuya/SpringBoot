INSERT INTO users (name, email, pass, git_hub_user) VALUES
('Marcelo', 'marcelo@gmail.com', '$2a$10$CqzuQA66hFso6.CQt28UR.41QPqq4rNJN30ttsC771gDjYDbn/I5a','marceloShibuya'),
('Pedro', 'pedro@gmail.com', '$2a$10$CqzuQA66hFso6.CQt28UR.41QPqq4rNJN30ttsC771gDjYDbn/I5a','Pedro0176');

INSERT INTO role (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO USERS_ROLES VALUES (1,1), (2,2);


INSERT INTO tasks (title, description, point, status, user_id) VALUES
('Analise', 'analise completa do sistemas', 100, 90, null),
('Protype', 'prototipacao das telas', 100, 70, null),
('Data base', 'criacao do banco de dados', 50, 10, 1),
('Teste', 'teste de integração', 80, 20, 2);