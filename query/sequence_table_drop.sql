-----[ drop table ]-----

/* 주문상세내역 */
DROP TABLE order_detail 
	CASCADE CONSTRAINTS;

/* 결제 */
DROP TABLE payment 
	CASCADE CONSTRAINTS;

/* 재고 */
DROP TABLE inventory 
	CASCADE CONSTRAINTS;

/* 결제상태 */
DROP TABLE payment_status 
	CASCADE CONSTRAINTS;

/* 결제방법 */
DROP TABLE payment_method 
	CASCADE CONSTRAINTS;

/* 상품 */
DROP TABLE product 
	CASCADE CONSTRAINTS;

/* 재고종류 */
DROP TABLE inventory_type 
	CASCADE CONSTRAINTS;

/* 상품종류 */
DROP TABLE product_type 
	CASCADE CONSTRAINTS;

/* 주문옵션 */
DROP TABLE order_option 
	CASCADE CONSTRAINTS;

/* 주문 */
DROP TABLE order_menu 
	CASCADE CONSTRAINTS;

/* 관리자 */
DROP TABLE admin 
	CASCADE CONSTRAINTS;

/* 회원 */
DROP TABLE user_info 
	CASCADE CONSTRAINTS;

/* 상품_재고 */
DROP TABLE product_inventory_detail 
	CASCADE CONSTRAINTS;

/* 사용재고 */
DROP TABLE use_inventory 
	CASCADE CONSTRAINTS;
	
	
------[ drop sequence ]-------	

drop sequence order_serial_seq;

drop sequence order_num_seq;

drop SEQUENCE cof_seq;

drop SEQUENCE bev_seq;

drop SEQUENCE des_seq;

drop sequence md_seq;

drop sequence milk_seq;

drop sequence food_seq;

drop sequence order_detail_seq;

drop sequence pay_seq;

drop sequence opt_seq;

drop sequence pd_inven_seq;

drop sequence use_inven_seq;
