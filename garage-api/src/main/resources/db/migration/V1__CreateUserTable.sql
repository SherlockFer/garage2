CREATE TABLE users (
	id int8 NOT NULL,
	created_at timestamp NULL,
	email varchar(255) NOT NULL,
	full_name varchar(255) NOT NULL,
	mobile varchar(255) NOT NULL,
	modified_at timestamp NULL,
	"password" varchar(255) NULL,
	"role" varchar(255) NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
