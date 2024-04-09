CREATE TABLE `users`
(
    user_id      INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(20)  NOT NULL,
    last_name    VARCHAR(20)  NOT NULL,
    email        VARCHAR(50)  NOT NULL,
    role         VARCHAR(20)  NOT NULL,
    phone_number VARCHAR(12)
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
#     bio                TEXT NOT NULL,
#     ig_link            VARCHAR(255),
#     profile_image_link VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE `workouts`
(
    workout_id          INT PRIMARY KEY AUTO_INCREMENT,
    trainer_id          INT          NOT NULL,
    title               VARCHAR(255) NOT NULL,
    description         TEXT,
    FOREIGN KEY (trainer_id) REFERENCES trainers (user_id)
);

CREATE TABLE `appointments`
(
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    start_time     DATETIME NOT NULL,
    end_time       DATETIME NOT NULL,
    workout_id     INT      NOT NULL,
    trainer_id     INT      NOT NULL,
    client_id      INT      NOT NULL,
    FOREIGN KEY (workout_id) REFERENCES workouts (workout_id),
    FOREIGN KEY (trainer_id) REFERENCES trainers (user_id),
    FOREIGN KEY (client_id) REFERENCES clients (user_id)
);

