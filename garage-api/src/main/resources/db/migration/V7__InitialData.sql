INSERT INTO users (id,created_at,email,full_name,mobile,modified_at,"password","role") VALUES 
(nextval('user_id_seq'),CURRENT_TIMESTAMP,'admin@garage.com','Administrator','123456789',CURRENT_TIMESTAMP,'123456','admin'),
(nextval('user_id_seq'),CURRENT_TIMESTAMP,'mechanic-1@garage.com','Mechanic-1','123456789',CURRENT_TIMESTAMP,'123456','mechanic'),
(nextval('user_id_seq'),CURRENT_TIMESTAMP,'mechanic-2@garage.com','Mechanic-2','123456789',CURRENT_TIMESTAMP,'123456','mechanic'),
(nextval('user_id_seq'),CURRENT_TIMESTAMP,'mechanic-3@garage.com','Mechanic-3','123456789',CURRENT_TIMESTAMP,'123456','mechanic')
;

INSERT INTO products (id,category,created_at,modified_at,name,price,reference) VALUES 
(nextval('product_id_seq'),'base',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Annual Service',50,'00f74ee0-2b40-46b1-b2e9-41d41a96da56'),
(nextval('product_id_seq'),'base',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Major Service',60,'769317b0-1f11-4e7a-b3cf-9474e257a115'),
(nextval('product_id_seq'),'base',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Repair or Fault',70,'4bbbadba-3044-422a-a71e-dc415cc19b46'),
(nextval('product_id_seq'),'base',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Major Repair',80,'c964bdec-b5e9-4055-9eaf-14059e8e581f'),
(nextval('product_id_seq'),'extra',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Wheel alignment',90,'c86b7723-4f32-43ac-a408-d5a22f37a88e'),
(nextval('product_id_seq'),'extra',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Grease and lubricat',70,'4587d5c8-b0a0-4005-9f1a-616365342eb9'),
(nextval('product_id_seq'),'extra',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Suspension',70,'f0f5ab78-f193-4c28-aafe-1567565098a3')
;

INSERT INTO parts (id,created_at,modified_at,name,price,sku) VALUES 
(nextval('part_id_seq'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Engine motor oil',50,'a408617e-fdd6-4342-b064-41f83710e575'),
(nextval('part_id_seq'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Filter oil',60,'b870f861-c768-4b5b-a4dd-f317d8940bad'),
(nextval('part_id_seq'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Filer ai',70,'835906b3-e201-4672-b1e4-2364d98dc588')
;