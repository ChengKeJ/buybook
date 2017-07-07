/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2017/4/19 23:27:34                           */
/*==============================================================*/


create database Shopping  --库名为Shopping
on
( name='Shopping_data',
  filename='d:\Shopping_data.mdf',
  size=10mb
)
log on
( name='Shopping_log',
  filename='d:\Shopping_log.ldf',
  size=10mb
)
go 
use Shopping
go


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('book') and o.name = 'FK_BOOK_REFERENCE_CATALOG')
alter table book
   drop constraint FK_BOOK_REFERENCE_CATALOG
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"order"') and o.name = 'FK_ORDER_REFERENCE_USES')
alter table "order"
   drop constraint FK_ORDER_REFERENCE_USES
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('orderitem') and o.name = 'FK_ORDERITE_REFERENCE_ORDER')
alter table orderitem
   drop constraint FK_ORDERITE_REFERENCE_ORDER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('orderitem') and o.name = 'FK_ORDERITE_REFERENCE_BOOK')
alter table orderitem
   drop constraint FK_ORDERITE_REFERENCE_BOOK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('book')
            and   type = 'U')
   drop table book
go

if exists (select 1
            from  sysobjects
           where  id = object_id('catalog')
            and   type = 'U')
   drop table catalog
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"order"')
            and   type = 'U')
   drop table "order"
go

if exists (select 1
            from  sysobjects
           where  id = object_id('orderitem')
            and   type = 'U')
   drop table orderitem
go

if exists (select 1
            from  sysobjects
           where  id = object_id('uses')
            and   type = 'U')
   drop table uses
go

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book (
   bookid               int                  identity,
   catalogid            int                  null,
   bookname             varchar(50)          not null,
   author               varchar(50)          null,
   publishHouse         varchar(200)         null,
   price                numeric(10,3)        null,
   image                varchar(50)          null,
   constraint PK_BOOK primary key (bookid)
)
go

/*==============================================================*/
/* Table: catalog                                               */
/*==============================================================*/
create table catalog (
   catalogid            int                  identity,
   catalogname          varchar(20)          not null,
   constraint PK_CATALOG primary key (catalogid)
)
go

/*==============================================================*/
/* Table: "order"                                               */
/*==============================================================*/
create table "order" (
   orderid              int                  identity,
   orderdate            datetime             null,
   userid               int                  null,
   constraint PK_ORDER primary key (orderid)
)
go

/*==============================================================*/
/* Table: orderitem                                             */
/*==============================================================*/
create table orderitem (
   orderitemid          int                 identity,
   quantity             int                  null,
   orderid              int                  null,
   bookid               int                  null,
   constraint PK_ORDERITEM primary key (orderitemid)
)
go

/*==============================================================*/
/* Table: users                                                  */
/*==============================================================*/
create table users (
   userid               int                  identity,
   username             varchar(20)          not null,
   password             varchar(20)          not null,
   email                varchar(50)          null,
   tel                  varchar(13)          null,
   grade                int                  null,
   constraint PK_USES primary key (userid)
)
go

alter table book
   add constraint FK_BOOK_REFERENCE_CATALOG foreign key (catalogid)
      references catalog (catalogid)
go

alter table "order"
   add constraint FK_ORDER_REFERENCE_USES foreign key (userid)
      references users (userid)
go

alter table orderitem
   add constraint FK_ORDERITE_REFERENCE_ORDER foreign key (orderid)
      references "order" (orderid)
go

alter table orderitem
   add constraint FK_ORDERITE_REFERENCE_BOOK foreign key (bookid)
      references book (bookid)
go

insert into users values('wl','123','wl@126.com','1303030333',2)
insert into users values('admin','123','admin@126.com','346789',2)


insert into catalog values('计算机类图书')
insert into catalog values('科普读物')
insert into catalog values('儿童读物')
insert into catalog values('投资理财')
insert into catalog values('教材教辅')



insert into book values(1,'jsp应用开发详解','刘晓华','电子工业出版社',59.80,'1.jpg')

insert into book values(1,'Java项目开发案例全程实录(第2版)','李钟尉,陈丹丹','清华大学出版社',69.80,'2.jpg')

insert into book values(1,'ASP.NET项目开发案例全程实录(第2版)','郑齐心,房大伟','清华大学出版社',74.00,'3.jpg')

insert into book values(1,'jquery技术内幕：深入解析jquery架构设计与实现原理','高云','机械工业出版社',99.00,'4.jpg')

insert into book values(1,'轻量级java ee企业应用实战（第3版）','李刚','电子工业出版社',99.00,'5.jpg')

insert into book values(1,'Struts+Spring+Hibernate框架及应用开发','王建国,王建英','清华大学出版社',69,'6.jpg')

insert into book values(1,'Java编程思想(第4版)','Bruce Eckel','机械工业出版社',108,'7.jpg')

insert into book values(1,'Java从入门到精通(第3版)','明日科技','清华大学出版社',59.80,'8.jpg')

insert into book values(1,'java核心技术基础知识(原书第9版)','Cay S.Horstmann;Gary Cornell','机械工业出版社',119,'9.jpg')

insert into book values(1,'C#从入门到精通(第3版)','明日科技','清华大学出版社',69.80,'10.jpg')

insert into book values(1,'JSP & Servlet学习笔记','林信良','清华大学出版社',58.00,'11.jpg')

insert into book values(1,'JAVA开发实战经典','李兴华','清华大学出版社',79.80,'12.jpg')

insert into book values(1,'Linux设备驱动开发详解','宋宝华','机械工业出版社',89.00,'13.jpg')

insert into book values(1,'Spring实战(第3版)','(美)沃尔斯','人民邮电出版社',59.00,'14.jpg')

insert into book values(1,'C++ Primer第5版','Stanley B. Lippman','电子工业出版社',128.00,'15.jpg')

insert into book values(1,'Java Web整合开发王者归来','刘京华','清华大学出版社',99.80,'16.jpg')

insert into book values(1,'PHP从入门到精通','明日科技','清华大学出版社',69.80,'17.jpg')

insert into book values(1,'Android从入门到精通','明日科技','清华大学出版社',69.80,'18.jpg')

insert into book values(1,'电脑办公从新手到高手','无','人民邮电出版社',49.00,'19.jpg')

insert into book values(1,'Visual C++从入门到精通','明日科技','清华大学出版社',69.80,'20.jpg')



