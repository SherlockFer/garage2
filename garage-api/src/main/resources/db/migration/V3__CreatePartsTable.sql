CREATE TABLE parts (
	id int8 NOT NULL,
	created_at timestamp NULL,
	modified_at timestamp NULL,
	"name" varchar(255) NULL,
	price int4 NULL,
	sku varchar(255) NOT NULL,
	CONSTRAINT parts_pkey PRIMARY KEY (id),
	CONSTRAINT uk_4til4yvsido1bqt0vt7rxgwl0 UNIQUE (sku)
);

CREATE SEQUENCE part_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;