CREATE TABLE category (

	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(225),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY(id)
);

INSERT into category (name,description,image_url,is_active) values ('Laptop','This is laptop','CAT_2.png',true);
INSERT into category (name,description,image_url,is_active) values ('MP3 Player','This is Player','CAT_4.png',true);



CREATE TABLE user_detail (
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);

INSERT into user_detail (first_name,last_name,role,enabled,email,password,contact_number) values ('Tousif','shaikh','Admin',true,'tous@g.com','tous','909999');

CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id),	
);	


INSERT into product (code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views) values ('123','Iphone','Apple','This is Apple iphone',10.2,10,true,(SELECT id FROM USER_DETAIL),(SELECT id FROM CATEGORY where id=1),0,0);