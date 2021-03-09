Use master
Go

IF EXISTS (SELECT * FROM sys.databases WHERE name='ItemManagement')
	DROP DATABASE ItemManagement
Go

Create Database ItemManagement
Go

Use ItemManagement
Go

Create table tblSuppliers
(
	supCode			varchar(10)		not null	Primary Key,
	supName			nvarchar(50),	
	address			nvarchar(50),
	collaborating	bit
)
Go

Create table tblItems 
(
	itemCode		varchar(10)		not null	Primary Key,
	itemName		nvarchar(50),
	supCode			varchar(10),	
	unit			varchar(50),
	price			float,
	supplying		bit
)
go

Create table tblUsers
(
userID				varchar(10)		not null	Primary Key,
fullName			nvarchar(50),
password			varchar(50),
status				bit
)
go

insert into tblSuppliers values('TA','Thien An Co.','123, Le Loi, Q1','True')
insert into tblSuppliers values('HT','Hoang Tuan Co.','452 Tran Hung Dao, Q5, HCM','True')
insert into tblSuppliers values('MT','Minh Trang Co.','37, Hai Ba Trung, Q1','True')
go

insert into tblItems values('E0001', 'Mouse Proview','MT','block 10',30,'True')
insert into tblItems values('E0002','Keyboard Proview','MT','block 10',40,'True')
insert into tblItems values('E0003','LCD','MT','1-unit',90,'True')
insert into tblItems values('E0004','Main Asus MK1234','HT','1-unit',78,'True')
insert into tblItems values('E0005','Main Gigabyte GM34A','HT','1-unit',67,'False')
insert into tblItems values('E0006','Laptop Compaq 6250','HT','1-unit',620,'True')
insert into tblItems values('E0007','Blank DVD Giga','TA','block-100',43,'True')
insert into tblItems values('E0008','Blank CD BW','TA','block-100',15,'True')
insert into tblItems values('E0009','USB 2.0 Kingston-4GB','TA','unit-1',10,'False')
go

insert into tblUsers values('admin','tran cong minh hieu','admin123','True')
insert into tblUsers values('sa','tran cong minh hieu','123','True')
go






































