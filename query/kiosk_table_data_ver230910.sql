/* 주문상세내역 */
CREATE TABLE order_detail (
	order_detail_num VARCHAR2(20) NOT NULL, /* 주문상세번호 */
	order_serial_num VARCHAR2(20), /* 주문일렬번호 */
	product_code VARCHAR2(10), /* 상품코드 */
	product_quantity NUMBER(2), /* 수량 */
	ice_hot CHAR(1), /* ICE/HOT */
	cup_size CHAR(1) /* 사이즈 */
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

/* 결제 */
CREATE TABLE payment (
	payment_code VARCHAR2(10) NOT NULL, /* 결제코드 */
	order_serial_num VARCHAR2(20) NOT NULL, /* 주문일렬번호 */
	payment_method_code CHAR(1), /* 결제방법코드 */
	payment_status_code CHAR(1), /* 결제상태코드 */
	order_price NUMBER(7), /* 주문금액 */
	discount_price NUMBER(5), /* 할인금액 */
	purchase_price NUMBER(7), /* 결제금액 */
	payment_date DATE /* 결제일시 */
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

/* 재고 */
CREATE TABLE inventory (
	inventory_code VARCHAR2(10) NOT NULL, /* 재고번호 */
	inventory_type_code VARCHAR2(10), /* 재고종류코드 */
	inventory_name VARCHAR2(30), /* 재고명 */
	inventory_quantity NUMBER(4), /* 수량 */
	inventory_date DATE /* 관리일 */
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

/* 결제상태 */
CREATE TABLE payment_status (
	payment_status_code CHAR(1) NOT NULL, /* 결제상태코드 */
	payment_status_name VARCHAR2(20) /* 결제상태이름 */
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

/* 결제방법 */
CREATE TABLE payment_method (
	payment_method_code CHAR(1) NOT NULL, /* 결제방법코드 */
	payment_method_name VARCHAR2(20) /* 결제방법이름 */
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

/* 상품 */
CREATE TABLE product (
	product_code VARCHAR2(10) NOT NULL, /* 상품코드 */
	product_type_code VARCHAR2(10), /* 상품종류코드 */
	image VARCHAR2(30), /* 이미지명 */
	product_name VARCHAR2(30), /* 상품명 */
	product_price NUMBER(5), /* 가격 */
	product_delete CHAR(1), /* 삭제여부 */
	product_date DATE /* 등록일 */
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

/* 재고종류 */
CREATE TABLE inventory_type (
	inventory_type_code VARCHAR2(10) NOT NULL, /* 재고종류코드 */
	inventory_type_name VARCHAR2(20) /* 종류이름 */
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

/* 상품종류 */
CREATE TABLE product_type (
	product_type_code VARCHAR2(10) NOT NULL, /* 상품종류코드 */
	product_type_name VARCHAR2(15) /* 종류이름 */
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

/* 주문옵션 */
CREATE TABLE order_option (
	option_num VARCHAR2(7) NOT NULL, /* 옵션번호 */
	order_detail_num VARCHAR2(20), /* 주문상세번호 */
	option_name VARCHAR2(20), /* 옵션이름 */
	option_price NUMBER(4) /* 가격 */
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

/* 주문 */
CREATE TABLE order_menu (
	order_serial_num VARCHAR2(20) NOT NULL, /* 주문일렬번호 */
	user_phone CHAR(13), /* 전화번호 */
	order_num VARCHAR2(4), /* 주문번호 */
	order_status VARCHAR2(30), /* 주문상태 */
	order_time DATE /* 주문일시 */
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

/* 관리자 */
CREATE TABLE admin (
	admin_num VARCHAR2(20) NOT NULL, /* 관리자번호 */
	admin_pw VARCHAR2(20), /* 비밀번호 */
	admin_date DATE /* 가입일 */
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

/* 회원 */
CREATE TABLE user_info (
	user_phone CHAR(13) NOT NULL, /* 전화번호 */
	user_point NUMBER(5), /* 잔여적립금 */
	user_withdrawal CHAR(1), /* 탈퇴여부 */
	user_date DATE /* 가입일 */
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

/* 상품_재고 */
CREATE TABLE product_inventory_detail (
	product_inventory_num VARCHAR2(10) NOT NULL, /* 상품재고번호 */
	product_code VARCHAR2(10), /* 상품코드 */
	inventory_code VARCHAR2(10) /* 재고번호 */
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

/* 사용재고 */
CREATE TABLE use_inventory (
	use_inventory_num VARCHAR2(20) NOT NULL, /* 상품재고번호 */
	product_code VARCHAR2(10), /* 상품코드 */
	inventory_code VARCHAR2(10), /* 재고번호 */
	use_quantity NUMBER(5) /* 사용량 */
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


---------------------------- 가데이터 insert -------------------------------------

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
values('cof','커피');
insert into product_type(product_type_code,product_type_name)
values('bev','음료');
insert into product_type(product_type_code,product_type_name)
values('des','디저트');

--[ inventory type ]
insert into inventory_type(inventory_type_code,inventory_type_name)
values('MD','md');
insert into inventory_type(inventory_type_code,inventory_type_name)
values('milk','유제품');
insert into inventory_type(inventory_type_code,inventory_type_name)
values('food','푸드');
insert into inventory_type(inventory_type_code,inventory_type_name)
values('cup','컵');


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
values( 'order_serial_'||order_serial_seq.nextval, '', order_num_seq.nextval, '준비중', sysdate );
insert into order_menu( order_serial_num, user_phone, order_num, order_status, order_time )
values( 'order_serial_'||order_serial_seq.nextval, '', order_num_seq.nextval, '준비중', sysdate );
insert into order_menu( order_serial_num, user_phone, order_num, order_status, order_time )
values( 'order_serial_'||order_serial_seq.nextval, '', order_num_seq.nextval, '준비중', sysdate );


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
VALUES ('cof_' || cof_seq.nextval, 'cof', 'coffee.jpg', '아메리카노', 3000, 'N', sysdate );
INSERT INTO product(PRODUCT_CODE, product_type_code, image, product_name, product_price, product_delete, product_date)
VALUES ('bev_' || bev_seq.nextval, 'bev', 'ade.jpg', '자몽에이드', 4000, 'N', sysdate );
INSERT INTO product(PRODUCT_CODE, product_type_code, image, product_name, product_price, product_delete, product_date)
VALUES ('des_' || des_seq.nextval, 'des', 'cake.jpg', '치즈케이크', 5000, 'N', sysdate );

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

--MD바꾸기
INSERT INTO inventory(inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date)
VALUES ('MD_' || md_seq.nextval, 'MD', '과테말라원두', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code, inventory_name,inventory_quantity,inventory_date)
VALUES ('MD_' || md_seq.nextval, 'MD', '에이드시럽', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code, inventory_name,inventory_quantity,inventory_date)
VALUES ('CUP_' || cup_seq.nextval, 'cup', '레귤러컵', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code, inventory_name,inventory_quantity,inventory_date)
VALUES ('CUP_' || cup_seq.nextval, 'cup', '엑스트라컵', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date)
VALUES ('MILK_' || milk_seq.nextval, 'milk', '서울우유', 300, sysdate);
INSERT INTO inventory(inventory_code,inventory_type_code,inventory_name,inventory_quantity,inventory_date)
VALUES ('FOOD_' || food_seq.nextval, 'food', '치즈케이크', 300, sysdate);

--[ order_detail table ]
create sequence order_detail_seq
increment by 1
start with 1
maxvalue 999999
cycle;

insert into order_detail( order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)
values( 'order_detail_'||order_detail_seq.nextval, 'order_serial_1' ,'cof_1', 1, 'Y', 'T' );
--음료1, 수량1
insert into order_detail( order_detail_num, order_serial_num, product_code, product_quantity, ice_hot, cup_size)
values( 'order_detail_'||order_detail_seq.nextval, 'order_serial_2', 'bev_1', 1, 'Y', 'G' );
--디저트1, 커피1
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
values('Y','결제완료');
insert into payment_status( payment_status_code,payment_status_name)
values('N','결제취소');


--[ payment_method ]
insert into payment_method(payment_method_code, payment_method_name)
values('0', '카드');
insert into payment_method(payment_method_code, payment_method_name)
values('1', '현금');

--[ order_option ]
CREATE SEQUENCE opt_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
CYCLE;

insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_1', '샷추가', 500 );
insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_2', '휘핑', 1000 );
insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_3', '샷추가', 500 );
insert into order_option(option_num, order_detail_num, option_name, option_price)
values('opt_'||opt_seq.nextval,'order_detail_3', '휘핑', 1000 );


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

