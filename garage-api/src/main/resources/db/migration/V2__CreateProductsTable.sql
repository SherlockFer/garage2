CREATE TABLE products (
	id int8 NOT NULL,
	category varchar(255) NULL,
	created_at timestamp NULL,
	modified_at timestamp NULL,
	"name" varchar(255) NULL,
	price int4 NULL,
	reference varchar(255) NOT NULL,
	CONSTRAINT products_pkey PRIMARY KEY (id),
	CONSTRAINT uk_klkck760tghhxldwjx22usej5 UNIQUE (reference)
);

CREATE SEQUENCE product_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;