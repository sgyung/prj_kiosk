-----[ drop table ]-----

/* �ֹ��󼼳��� */
DROP TABLE order_detail 
	CASCADE CONSTRAINTS;

/* ���� */
DROP TABLE payment 
	CASCADE CONSTRAINTS;

/* ��� */
DROP TABLE inventory 
	CASCADE CONSTRAINTS;

/* �������� */
DROP TABLE payment_status 
	CASCADE CONSTRAINTS;

/* ������� */
DROP TABLE payment_method 
	CASCADE CONSTRAINTS;

/* ��ǰ */
DROP TABLE product 
	CASCADE CONSTRAINTS;

/* ������� */
DROP TABLE inventory_type 
	CASCADE CONSTRAINTS;

/* ��ǰ���� */
DROP TABLE product_type 
	CASCADE CONSTRAINTS;

/* �ֹ��ɼ� */
DROP TABLE order_option 
	CASCADE CONSTRAINTS;

/* �ֹ� */
DROP TABLE order_menu 
	CASCADE CONSTRAINTS;

/* ������ */
DROP TABLE admin 
	CASCADE CONSTRAINTS;

/* ȸ�� */
DROP TABLE user_info 
	CASCADE CONSTRAINTS;

/* ��ǰ_��� */
DROP TABLE product_inventory_detail 
	CASCADE CONSTRAINTS;

/* ������ */
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
