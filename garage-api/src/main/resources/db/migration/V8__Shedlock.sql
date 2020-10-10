CREATE TABLE shedlock (
  name VARCHAR(64),
  lock_until TIMESTAMP,
  locked_at TIMESTAMP,
  locked_by  VARCHAR(255),
  PRIMARY KEY (name)
);