CREATE TABLE `users`
(
    user_id      INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(20)  NOT NULL,
    last_name    VARCHAR(20)  NOT NULL,
    email        VARCHAR(50)  NOT NULL,
    password     VARCHAR(100)  NOT NULL,
    role         VARCHAR(20)  NOT NULL
);

CREATE TABLE `admins`
(
    user_id int NOT NULL PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE `clients`
(
    user_id int NOT NULL PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE `trainers`
(
    user_id            INT  NOT NULL PRIMARY KEY,
    bio                TEXT NOT NULL,
    ig_link            VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE `workouts`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title               VARCHAR(255) NOT NULL,
    description         TEXT,
    start_time     DATETIME NOT NULL,
    end_time       DATETIME NOT NULL,
    trainer_id          INT          NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES trainers (user_id)
);

CREATE TABLE `appointments`
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    workout_id     INT      NOT NULL,
    trainer_id     INT      NOT NULL,
    client_id      INT      NOT NULL,
    FOREIGN KEY (workout_id) REFERENCES workouts (id),
    FOREIGN KEY (trainer_id) REFERENCES trainers (user_id),
    FOREIGN KEY (client_id) REFERENCES clients (user_id)
);

CREATE TABLE `ratings`
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    trainer_id INT NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES trainers (user_id),
    FOREIGN KEY (client_id) REFERENCES clients (user_id)
);

# CREATE TABLE `refresh_tokens`
# (
#     id SERIAL PRIMARY KEY,
#     user_id INT NOT NULL,
#     token TEXT NOT NULL,
#     expiry_date TIMESTAMP NOT NULL,
#     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
#     FOREIGN KEY (user_id) REFERENCES users (user_id)
# );

