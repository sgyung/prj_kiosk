/* �ֹ��󼼳��� */
CREATE TABLE order_detail (
	order_detail_num VARCHAR2(20) NOT NULL, /* �ֹ��󼼹�ȣ */
	order_serial_num VARCHAR2(20), /* �ֹ��ϷĹ�ȣ */
	product_code VARCHAR2(10), /* ��ǰ�ڵ� */
	product_quantity NUMBER(2), /* ���� */
	ice_hot CHAR(1), /* ICE/HOT */
	cup_size CHAR(1) /* ������ */
);

CREATE UNIQUE INDEX PK_order_detail
	ON order_detail (
		order_detail_num ASC
	);

ALTER TABLE order_detail
	ADD
		CONSTRAINT PK_order_detail
		PRIMARY KEY (
			order_detail_num
		);

/* ���� */
CREATE TABLE payment (
	payment_code VARCHAR2(10) NOT NULL, /* �����ڵ� */
	order_serial_num VARCHAR2(20) NOT NULL, /* �ֹ��ϷĹ�ȣ */
	payment_method_code CHAR(1), /* ��������ڵ� */
	payment_status_code CHAR(1), /* ���������ڵ� */
	order_price NUMBER(7), /* �ֹ��ݾ� */
	discount_price NUMBER(5), /* ���αݾ� */
	purchase_price NUMBER(7), /* �����ݾ� */
	payment_date DATE /* �����Ͻ� */
);

CREATE UNIQUE INDEX PK_payment
	ON payment (
		payment_code ASC,
		order_serial_num ASC
	);

ALTER TABLE payment
	ADD
		CONSTRAINT PK_payment
		PRIMARY KEY (
			payment_code,
			order_serial_num
		);

/* ��� */
CREATE TABLE inventory (
	inventory_code VARCHAR2(10) NOT NULL, /* ����ȣ */
	inventory_type_code VARCHAR2(10), /* ��������ڵ� */
	inventory_name VARCHAR2(30), /* ���� */
	inventory_quantity NUMBER(4), /* ���� */
	inventory_date DATE /* ������ */
);

CREATE UNIQUE INDEX PK_inventory
	ON inventory (
		inventory_code ASC
	);

ALTER TABLE inventory
	ADD
		CONSTRAINT PK_inventory
		PRIMARY KEY (
			inventory_code
		);

/* �������� */
CREATE TABLE payment_status (
	payment_status_code CHAR(1) NOT NULL, /* ���������ڵ� */
	payment_status_name VARCHAR2(20) /* ���������̸� */
);

CREATE UNIQUE INDEX PK_payment_status
	ON payment_status (
		payment_status_code ASC
	);

ALTER TABLE payment_status
	ADD
		CONSTRAINT PK_payment_status
		PRIMARY KEY (
			payment_status_code
		);

/* ������� */
CREATE TABLE payment_method (
	payment_method_code CHAR(1) NOT NULL, /* ��������ڵ� */
	payment_method_name VARCHAR2(20) /* ��������̸� */
);

CREATE UNIQUE INDEX PK_payment_method
	ON payment_method (
		payment_method_code ASC
	);

ALTER TABLE payment_method
	ADD
		CONSTRAINT PK_payment_method
		PRIMARY KEY (
			payment_method_code
		);

/* ��ǰ */
CREATE TABLE product (
	product_code VARCHAR2(10) NOT NULL, /* ��ǰ�ڵ� */
	product_type_code VARCHAR2(10), /* ��ǰ�����ڵ� */
	image VARCHAR2(30), /* �̹����� */
	product_name VARCHAR2(30), /* ��ǰ�� */
	product_price NUMBER(5), /* ���� */
	product_delete CHAR(1), /* �������� */
	product_date DATE /* ����� */
);

CREATE UNIQUE INDEX PK_product
	ON product (
		product_code ASC
	);

ALTER TABLE product
	ADD
		CONSTRAINT PK_product
		PRIMARY KEY (
			product_code
		);

/* ������� */
CREATE TABLE inventory_type (
	inventory_type_code VARCHAR2(10) NOT NULL, /* ��������ڵ� */
	inventory_type_name VARCHAR2(20) /* �����̸� */
);

CREATE UNIQUE INDEX PK_inventory_type
	ON inventory_type (
		inventory_type_code ASC
	);

ALTER TABLE inventory_type
	ADD
		CONSTRAINT PK_inventory_type
		PRIMARY KEY (
			inventory_type_code
		);

/* ��ǰ���� */
CREATE TABLE product_type (
	product_type_code VARCHAR2(10) NOT NULL, /* ��ǰ�����ڵ� */
	product_type_name VARCHAR2(15) /* �����̸� */
);

CREATE UNIQUE INDEX PK_product_type
	ON product_type (
		product_type_code ASC
	);

ALTER TABLE product_type
	ADD
		CONSTRAINT PK_product_type
		PRIMARY KEY (
			product_type_code
		);

/* �ֹ��ɼ� */
CREATE TABLE order_option (
	option_num VARCHAR2(7) NOT NULL, /* �ɼǹ�ȣ */
	order_detail_num VARCHAR2(20), /* �ֹ��󼼹�ȣ */
	option_name VARCHAR2(20), /* �ɼ��̸� */
	option_price NUMBER(4) /* ���� */
);

CREATE UNIQUE INDEX PK_order_option
	ON order_option (
		option_num ASC
	);

ALTER TABLE order_option
	ADD
		CONSTRAINT PK_order_option
		PRIMARY KEY (
			option_num
		);

/* �ֹ� */
CREATE TABLE order_menu (
	order_serial_num VARCHAR2(20) NOT NULL, /* �ֹ��ϷĹ�ȣ */
	user_phone CHAR(13), /* ��ȭ��ȣ */
	order_num VARCHAR2(4), /* �ֹ���ȣ */
	order_status VARCHAR2(30), /* �ֹ����� */
	order_time DATE /* �ֹ��Ͻ� */
);

CREATE UNIQUE INDEX PK_order_menu
	ON order_menu (
		order_serial_num ASC
	);

ALTER TABLE order_menu
	ADD
		CONSTRAINT PK_order_menu
		PRIMARY KEY (
			order_serial_num
		);

/* ������ */
CREATE TABLE admin (
	admin_num VARCHAR2(20) NOT NULL, /* �����ڹ�ȣ */
	admin_pw VARCHAR2(20), /* ��й�ȣ */
	admin_date DATE /* ������ */
);

CREATE UNIQUE INDEX PK_admin
	ON admin (
		admin_num ASC
	);

ALTER TABLE admin
	ADD
		CONSTRAINT PK_admin
		PRIMARY KEY (
			admin_num
		);

/* ȸ�� */
CREATE TABLE user_info (
	user_phone CHAR(13) NOT NULL, /* ��ȭ��ȣ */
	user_point NUMBER(5), /* �ܿ������� */
	user_withdrawal CHAR(1), /* Ż�𿩺� */
	user_date DATE /* ������ */
);

CREATE UNIQUE INDEX PK_user_info
	ON user_info (
		user_phone ASC
	);

ALTER TABLE user_info
	ADD
		CONSTRAINT PK_user_info
		PRIMARY KEY (
			user_phone
		);

/* ��ǰ_��� */
CREATE TABLE product_inventory_detail (
	product_inventory_num VARCHAR2(10) NOT NULL, /* ��ǰ����ȣ */
	product_code VARCHAR2(10), /* ��ǰ�ڵ� */
	inventory_code VARCHAR2(10) /* ����ȣ */
);

CREATE UNIQUE INDEX PK_product_inventory_detail
	ON product_inventory_detail (
		product_inventory_num ASC
	);

ALTER TABLE product_inventory_detail
	ADD
		CONSTRAINT PK_product_inventory_detail
		PRIMARY KEY (
			product_inventory_num
		);

/* ������ */
CREATE TABLE use_inventory (
	use_inventory_num VARCHAR2(20) NOT NULL, /* ��ǰ����ȣ */
	product_code VARCHAR2(10), /* ��ǰ�ڵ� */
	inventory_code VARCHAR2(10), /* ����ȣ */
	use_quantity NUMBER(5) /* ��뷮 */
);

CREATE UNIQUE INDEX PK_use_inventory
	ON use_inventory (
		use_inventory_num ASC
	);

ALTER TABLE use_inventory
	ADD
		CONSTRAINT PK_use_inventory
		PRIMARY KEY (
			use_inventory_num
		);

ALTER TABLE order_detail
	ADD
		CONSTRAINT FK_product_TO_order_detail
		FOREIGN KEY (
			product_code
		)
		REFERENCES product (
			product_code
		);

ALTER TABLE order_detail
	ADD
		CONSTRAINT FK_order_menu_TO_order_detail
		FOREIGN KEY (
			order_serial_num
		)
		REFERENCES order_menu (
			order_serial_num
		);

ALTER TABLE payment
	ADD
		CONSTRAINT FK_order_menu_TO_payment
		FOREIGN KEY (
			order_serial_num
		)
		REFERENCES order_menu (
			order_serial_num
		);

ALTER TABLE payment
	ADD
		CONSTRAINT FK_payment_method_TO_payment
		FOREIGN KEY (
			payment_method_code
		)
		REFERENCES payment_method (
			payment_method_code
		);

ALTER TABLE payment
	ADD
		CONSTRAINT FK_payment_status_TO_payment
		FOREIGN KEY (
			payment_status_code
		)
		REFERENCES payment_status (
			payment_status_code
		);

ALTER TABLE inventory
	ADD
		CONSTRAINT FK_inventory_type_TO_inventory
		FOREIGN KEY (
			inventory_type_code
		)
		REFERENCES inventory_type (
			inventory_type_code
		);

ALTER TABLE product
	ADD
		CONSTRAINT FK_product_type_TO_product
		FOREIGN KEY (
			product_type_code
		)
		REFERENCES product_type (
			product_type_code
		);

ALTER TABLE order_option
	ADD
		CONSTRAINT FK_ordr_detail_TO_ordr_optn
		FOREIGN KEY (
			order_detail_num
		)
		REFERENCES order_detail (
			order_detail_num
		);

ALTER TABLE order_menu
	ADD
		CONSTRAINT FK_user_info_TO_order_menu
		FOREIGN KEY (
			user_phone
		)
		REFERENCES user_info (
			user_phone
		);

ALTER TABLE product_inventory_detail
	ADD
		CONSTRAINT FK_prdct_TO_prdct_dtl
		FOREIGN KEY (
			product_code
		)
		REFERENCES product (
			product_code
		);

ALTER TABLE product_inventory_detail
	ADD
		CONSTRAINT FK_invntr_TO_prdct_dtl
		FOREIGN KEY (
			inventory_code
		)
		REFERENCES inventory (
			inventory_code
		);

ALTER TABLE use_inventory
	ADD
		CONSTRAINT FK_product_TO_use_inventory
		FOREIGN KEY (
			product_code
		)
		REFERENCES product (
			product_code
		);

ALTER TABLE use_inventory
	ADD
		CONSTRAINT FK_inventory_TO_use_inventory
		FOREIGN KEY (
			inventory_code
		)
		REFERENCES inventory (
			inventory_code
		);


---------------------------- �������� insert -------------------------------------

 --[ admin ]
insert into admin (admin_num, admin_pw, admin_date) values('admin_0001','1234',sysdate);
                                                
--[ user_info ]
insert into user_info(user_phone,user_point,user_withdrawal,user_date)
values('010-2041-4596',12345,'n',sysdate);
insert into user_info(user_phone,user_point,user_withdrawal,user_date)
values('010-2042-4596',1000,'y',sysdate);
insert into user_info(user_phone,user_point,user_withdrawal,user_date)
values('010-2043-4596',3000,'n',sysdate);
insert into user_info(user_phone,user_point,user_withdrawal,user_date)
values('010-2044-4596',4000,'n',sysdate);
insert into user_info(user_phone,user_point,user_withdrawal,user_date)
values('010-2045-4596',5000,'n',sysdate);

--[ product type ]
insert into product_type(product_type_code,product_type_name)
values('cof','Ŀ��');
insert into product_type(product_type_code,product_type_name)
values('bev','����');
insert into product_type(product_type_code,product_type_name)
values('des','����Ʈ');

--[ inventory type ]
insert into inventory_type(inventory_type_code,inventory_type_name)
values('MD','md');
insert into inventory_type(inventory_type_code,inventory_type_name)
values('milk','����ǰ');
insert into inventory_type(inventory_type_code,inventory_type_name)
values('food','Ǫ��');
insert into inventory_type(inventory_type_code,inventory_type_name)
values('cup','��');


--[ order_menu table ]
create sequence order_serial_seq
increment by 1
start with 1
maxvalue 999999
cycle;

create sequence order_num_seq
increment by 1
start with 1
maxvalue 999
cycle;

insert into order_menu( order_serial_num, user_phone, order_num, order_status, order_time )
values( 'order_serial_'||order_serial_seq.nextval, '', order_num_seq.nextval, '�غ���', sysdate );
insert into order_menu( order_serial_num, user_phone, order_num, order_status, order_time )
values( 'order_serial_'||order_serial_seq.nextval, '', order_num_seq.nextval, '�غ���', sysdate );
insert into order_menu( order_serial_num, user_phone, order_num, order_status, order_time )
values( 'order_serial_'||order_serial_seq.nextval, '', order_num_seq.nextval, '�غ���', sysdate );


--[ product ]
CREATE SEQUENCE cof_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
CYCLE;

CREATE SEQUENCE bev_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
CYCLE;

CREATE SEQUENCE des_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
CYCLE;

INSERT INTO product(PRODUCT_CODE, product_type_code, image, product_name, product_price, product_delete, product_date)
VALUES ('cof_' || cof_seq.nextval, 'cof', 'coffee.jpg', '�Ƹ޸�ī��', 3000, 'N', sysdate );
INSERT INTO product(PRODUCT_CODE, product_type_code, image, product_name, product_price, product_delete, product_date)
VALUES ('bev_' || bev_seq.nextval, 'bev', 'ade.jpg', '�ڸ����̵�', 4000, 'N', sysdate );
INSERT INTO product(PRODUCT_CODE, product_type_code, image, product_name, product_price, product_delete, product_date)
VALUES ('des_' || des_seq.nextval, 'des', 'cake.jpg', 'ġ������ũ', 5000, 'N', sysdate );

select * from order_menu;

--[ inventory ]
create sequence md_seq
increment by 1
start with 1
maxvalue 9999
cycle;

create sequence milk_seq
increment by 1
start with 1
maxvalue 9999
cycle;

create sequence food_seq
increment by 1
start with 1
maxvalue 9999
cycle;

create sequence cup_seq
increment by 1
start with 1
maxvalue 9999
cycle;

--MD�ٲٱ�
INSERT INTO inventory(inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date)
VALUES ('MD_' || md_seq.nextval, 'MD', '���׸������', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code, inventory_name,inventory_quantity,inventory_date)
VALUES ('MD_' || md_seq.nextval, 'MD', '���̵�÷�', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code, inventory_name,inventory_quantity,inventory_date)
VALUES ('CUP_' || cup_seq.nextval, 'cup', '���ַ���', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code, inventory_name,inventory_quantity,inventory_date)
VALUES ('CUP_' || cup_seq.nextval, 'cup', '����Ʈ����', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date)
VALUES ('MILK_' || milk_seq.nextval, 'milk', '�������', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date)
VALUES ('FOOD_' || food_seq.nextval, 'food', 'ġ������ũ', 300, sysdate);

--[ order_detail table ]
create sequence order_detail_seq
increment by 1
start with 1
maxvalue 999999
cycle;

insert into order_detail( order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)
values( 'order_detail_'||order_detail_seq.nextval, 'order_serial_1' ,'cof_1', 1, 'Y', 'T' );
--����1, ����1
insert into order_detail( order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)
values( 'order_detail_'||order_detail_seq.nextval, 'order_serial_2', 'bev_1', 1, 'Y', 'G' );
--����Ʈ1, Ŀ��1
insert into order_detail( order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)
values( 'order_detail_'||order_detail_seq.nextval, 'order_serial_3', 'cof_1', 1, 'Y', 'T' );
insert into order_detail( order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)
values( 'order_detail_'||order_detail_seq.nextval, 'order_serial_3', 'des_1', 1, '', '' );


--[ payment ]
create sequence pay_seq
increment by 1
start with 1
maxvalue 999999
cycle;

insert into payment(payment_code,order_serial_num,payment_method_code,payment_status_code,order_price,discount_price,purchase_price,payment_date)
values( 'pay_'||pay_seq.nextval,'order_serial_1','0','Y',3000,0,3000,sysdate);
insert into payment(payment_code,order_serial_num,payment_method_code,payment_status_code,order_price,discount_price,purchase_price,payment_date)
values( 'pay_'||pay_seq.nextval,'order_serial_2','0','Y',4000,0,4000,sysdate);
insert into payment(payment_code,order_serial_num,payment_method_code,payment_status_code,order_price,discount_price,purchase_price,payment_date)
values( 'pay_'||pay_seq.nextval,'order_serial_3','0','Y',8000,0,8000,sysdate);


--[ payment_status table ]
insert into payment_status( payment_status_code,payment_status_name)
values('Y','�����Ϸ�');
insert into payment_status( payment_status_code,payment_status_name)
values('N','�������');


--[ payment_method ]
insert into payment_method(payment_method_code, payment_method_name)
values('0', 'ī��');
insert into payment_method(payment_method_code, payment_method_name)
values('1', '����');

--[ order_option ]
CREATE SEQUENCE opt_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
CYCLE;

insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_1', '���߰�', 500 );
insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_2', '����', 1000 );
insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_3', '���߰�', 500 );
insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_3', '����', 1000 );


--[ use_inventory ]
CREATE SEQUENCE use_inven_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
CYCLE;

insert into use_inventory( use_inventory_num, product_code, inventory_code, use_quantity)
values( 'use_inven_'|| use_inven_seq.nextval, 'cof_1', 'MD_1', 20 );
insert into use_inventory( use_inventory_num, product_code, inventory_code, use_quantity)
values( 'use_inven_'|| use_inven_seq.nextval, 'cof_1', 'CUP_1', 1 );
insert into use_inventory( use_inventory_num, product_code, inventory_code, use_quantity)
values( 'use_inven_'|| use_inven_seq.nextval, 'bev_1', 'MD_2', 20 );
insert into use_inventory( use_inventory_num, product_code, inventory_code, use_quantity)
values( 'use_inven_'|| use_inven_seq.nextval, 'bev_1', 'CUP_1', 1 );
insert into use_inventory( use_inventory_num, product_code, inventory_code, use_quantity)
values( 'use_inven_'|| use_inven_seq.nextval, 'des_1', 'FOOD_1', 1 ) ;

