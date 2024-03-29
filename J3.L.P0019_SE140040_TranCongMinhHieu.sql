Use master
Go

IF EXISTS (SELECT * FROM sys.databases WHERE name='YellowMoonShop')
	DROP DATABASE [YellowMoonShop]
Go

Create Database [YellowMoonShop]
Go

USE [YellowMoonShop]
GO
/****** Object:  Table [dbo].[tblCategories]    Script Date: 23-Sep-21 3:55:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategories](
	[CategoryID] [int] NOT NULL,
	[CategoryName] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_tblCategories] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 23-Sep-21 3:55:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_tblOrderDetails] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 23-Sep-21 3:55:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [numeric](18, 0) NULL,
	[ContactName] [nvarchar](50) NOT NULL,
	[Phonenumber] [varchar](20) NOT NULL,
	[OrderDate] [date] NULL,
	[ShipAddress] [nvarchar](150) NOT NULL,
	[Payments] [varchar](20) NOT NULL,
	[PaymentsStatus] [bit] NOT NULL,
	[Total] [float] NOT NULL,
 CONSTRAINT [PK_tblOrders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProducts]    Script Date: 23-Sep-21 3:55:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProducts](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](40) NOT NULL,
	[SupplierID] [int] NOT NULL,
	[CategoryID] [int] NOT NULL,
	[QuantityPerUnit] [int] NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[ProductImage] [nvarchar](100) NOT NULL,
	[Status] [bit] NOT NULL,
	[isDelete] [bit] NOT NULL,
	[createDate] [date] NULL,
	[expirationDate] [date] NULL,
	[lastUpdateDate] [date] NOT NULL,
	[userIDUpdate] [numeric](18, 0) NULL,
 CONSTRAINT [PK_tblProducts] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSuppliers]    Script Date: 23-Sep-21 3:55:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSuppliers](
	[SupplierID] [int] NOT NULL,
	[CompanyName] [nvarchar](40) NOT NULL,
	[Address] [nvarchar](60) NULL,
	[Phone] [varchar](20) NULL,
 CONSTRAINT [PK_tblSuppliers] PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 23-Sep-21 3:55:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[UserID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[Username] [varchar](25) NOT NULL,
	[Password] [varchar](max) NOT NULL,
	[Fullname] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[isEnable] [bit] NULL,
	[Role] [bit] NULL,
	[Phone] [varchar](20) NULL,
 CONSTRAINT [PK_tblUser_1] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (1, N'Cantonese Mooncake', N'Cantonese-style mooncake is easily characterised by its lotus-like shape, with a decorative embossed pattern on top. It’s often gifted among relatives and friends several weeks before the Mid-Autumn Festival.')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (2, N'Shanghai Mooncake', N'Shanghai mooncake is characterised by a skin made of shortcrust pastry. ')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (3, N'Suzhou Mooncake', N'If you’re more into savoury baked goods, Suzhou mooncake would be a perfect snack. ')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (4, N'Teochew Mooncake', N'It’s obvious how the Teochew mooncake got its nickname. ')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (5, N'Yunnan-style Mooncake', N'Yunnan-style mooncake is another type of savoury mooncake. It has golden skin with a crisp exterior, but is soft inside.')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (6, N'Hokkien Mooncake', N'In China, the Hokkien mooncake is often given as a good luck charm for students when they’re studying for their exams. ')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (7, N'Hopia', N'Hopia is characterised by its small size and thin, flaky skin. ')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (8, N'Snow Skin Mooncake', N'Snow skin mooncake is possibly the first and most popular type of no-bake mooncake. ')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (9, N'Ice Cream Mooncake ', N'Ice cream mooncake is made of ice cream coated with either snow skin dough or melted chocolate. It’s moulded in a traditional mooncake mould, giving it a similar appearance to traditional mooncakes.')
INSERT [dbo].[tblCategories] ([CategoryID], [CategoryName], [Description]) VALUES (10, N'Jelly Mooncake', N'It might only resemble a mooncake in terms of appearance, but the Jelly mooncake gets as much love as the other types of mooncakes. ')
SET IDENTITY_INSERT [dbo].[tblProducts] ON 

INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (1, N'Cantonese Mooncake S', 1, 1, 20, 300, N'1.jpg', 1, 0, CAST(N'2021-09-16' AS Date), CAST(N'2021-09-16' AS Date), CAST(N'2021-09-22' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (2, N'Cantonese Mooncake L', 1, 1, 0, 450, N'2.jpg', 0, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (3, N'Cantonese Mooncake XL', 1, 1, 20, 200, N'3.jpg', 0, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (4, N'Shanghai Mooncake S', 2, 2, 30, 220, N'4.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (5, N'Shanghai Mooncake L', 2, 2, 30, 320, N'5.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (6, N'Shanghai Mooncake XL', 2, 2, 20, 500, N'6.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (7, N'Suzhou Mooncake S', 1, 3, 40, 230, N'7.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (8, N'Suzhou Mooncake L', 1, 3, 30, 350, N'8.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (9, N'Suzhou Mooncake XL', 1, 3, 20, 400, N'9.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (10, N'Teochew Mooncake S', 3, 4, 30, 300, N'10.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (11, N'Teochew Mooncake L', 3, 4, 20, 450, N'11.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (12, N'Teochew Mooncake XL', 3, 4, 15, 600, N'12.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (13, N'Yunnan-style Mooncake S', 2, 5, 20, 150, N'13.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (14, N'Yunnan-style Mooncake L', 2, 5, 0, 220, N'14.jpg', 0, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (15, N'Yunnan-style Mooncake XL', 2, 5, 15, 300, N'15.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (16, N'Hokkien Mooncake S', 1, 6, 20, 100, N'16.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (17, N'Hokkien Mooncake L', 1, 6, 20, 150, N'17.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (18, N'Hokkien Mooncake XL', 1, 6, 30, 250, N'18.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (19, N'Hopia S', 3, 7, 0, 60, N'19.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-20' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (20, N'Hopia L', 3, 7, 20, 120, N'20.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (21, N'Hopia XL', 3, 7, 15, 200, N'21.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (22, N'Snow Skin Mooncake S', 2, 8, 20, 50, N'22.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (23, N'Snow Skin Mooncake L', 2, 8, 30, 80, N'23.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (24, N'Snow Skin Mooncake XL', 2, 8, 0, 120, N'24.jpg', 0, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (25, N'Ice Cream Mooncake S', 1, 9, 20, 60, N'25.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (26, N'Ice Cream Mooncake  L', 1, 9, 20, 100, N'26.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (27, N'Ice Cream Mooncake XL', 1, 9, 20, 150, N'27.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (28, N'Jelly Mooncake S', 3, 10, 15, 70, N'28.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (29, N'Jelly Mooncake L', 3, 10, 30, 130, N'29.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (30, N'Jelly Mooncake XL', 3, 10, 25, 180, N'30.jpg', 1, 0, CAST(N'2021-09-10' AS Date), CAST(N'2021-12-12' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (31, N'abc', 1, 1, 1, 200, N'1', 1, 1, CAST(N'2020-12-03' AS Date), CAST(N'2022-12-03' AS Date), CAST(N'2021-09-10' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (32, N'Cantonese Mooncake S', 1, 1, 20, 300, N'1.jpg', 0, 1, CAST(N'2021-09-10' AS Date), CAST(N'2022-12-03' AS Date), CAST(N'2021-09-20' AS Date), CAST(3 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (33, N'ccc', 1, 1, 1, 1, N'1', 1, 1, CAST(N'2021-09-10' AS Date), CAST(N'2022-12-03' AS Date), CAST(N'2021-09-20' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (34, N'abc', 1, 1, 2, 2, N'1', 1, 1, CAST(N'2021-09-06' AS Date), CAST(N'2022-01-03' AS Date), CAST(N'2021-09-20' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (35, N'Cantonese Mooncake SX', 1, 1, 90, 5000, N'3.jpg', 1, 0, CAST(N'2021-09-03' AS Date), CAST(N'2021-09-29' AS Date), CAST(N'2021-09-20' AS Date), CAST(1 AS Numeric(18, 0)))
INSERT [dbo].[tblProducts] ([ProductID], [ProductName], [SupplierID], [CategoryID], [QuantityPerUnit], [UnitPrice], [ProductImage], [Status], [isDelete], [createDate], [expirationDate], [lastUpdateDate], [userIDUpdate]) VALUES (36, N'Cantonese Mooncake SLX', 1, 1, 90, 5000, N'1.jpg', 1, 0, CAST(N'2021-09-15' AS Date), CAST(N'2021-09-30' AS Date), CAST(N'2021-09-20' AS Date), CAST(1 AS Numeric(18, 0)))
SET IDENTITY_INSERT [dbo].[tblProducts] OFF
INSERT [dbo].[tblSuppliers] ([SupplierID], [CompanyName], [Address], [Phone]) VALUES (1, N'MK1', N'Distric 9', N'0123456789')
INSERT [dbo].[tblSuppliers] ([SupplierID], [CompanyName], [Address], [Phone]) VALUES (2, N'MK2', N'Distric 9', N'0123456788')
INSERT [dbo].[tblSuppliers] ([SupplierID], [CompanyName], [Address], [Phone]) VALUES (3, N'MK3', N'Distric 9', N'0123456787')
SET IDENTITY_INSERT [dbo].[tblUser] ON 

INSERT [dbo].[tblUser] ([UserID], [Username], [Password], [Fullname], [Email], [isEnable], [Role], [Phone]) VALUES (CAST(1 AS Numeric(18, 0)), N'admin123', N'123456abc', N'Admin1', N'admin@gmail.com', 1, 1, N'0123456777')
INSERT [dbo].[tblUser] ([UserID], [Username], [Password], [Fullname], [Email], [isEnable], [Role], [Phone]) VALUES (CAST(2 AS Numeric(18, 0)), N'customer123', N'123456abc', N'Customer1', N'customer@gmail.com', 1, 0, N'0123456786')
INSERT [dbo].[tblUser] ([UserID], [Username], [Password], [Fullname], [Email], [isEnable], [Role], [Phone]) VALUES (CAST(3 AS Numeric(18, 0)), N'admin', N'123456abc', N'Admin2', N'admin2@gmail.com', 1, 1, N'0123456788')
SET IDENTITY_INSERT [dbo].[tblUser] OFF
ALTER TABLE [dbo].[tblOrders] ADD  CONSTRAINT [DF_tblOrders_OrderDate]  DEFAULT (getdate()) FOR [OrderDate]
GO
ALTER TABLE [dbo].[tblProducts] ADD  CONSTRAINT [DF_tblProducts_Status]  DEFAULT ((1)) FOR [Status]
GO
ALTER TABLE [dbo].[tblProducts] ADD  CONSTRAINT [DF_tblProducts_isDelete]  DEFAULT ((0)) FOR [isDelete]
GO
ALTER TABLE [dbo].[tblProducts] ADD  DEFAULT (getdate()) FOR [lastUpdateDate]
GO
ALTER TABLE [dbo].[tblUser] ADD  CONSTRAINT [DF_tblUser_isEnable]  DEFAULT ((0)) FOR [isEnable]
GO
ALTER TABLE [dbo].[tblUser] ADD  CONSTRAINT [DF_tblUser_Role]  DEFAULT ((0)) FOR [Role]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblOrders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[tblOrders] ([OrderID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblOrders]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK_tblOrders_tblUser] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblUser] ([UserID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK_tblOrders_tblUser]
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD  CONSTRAINT [FK_tblProducts_tblCategories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[tblCategories] ([CategoryID])
GO
ALTER TABLE [dbo].[tblProducts] CHECK CONSTRAINT [FK_tblProducts_tblCategories]
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD  CONSTRAINT [FK_tblProducts_tblSuppliers] FOREIGN KEY([SupplierID])
REFERENCES [dbo].[tblSuppliers] ([SupplierID])
GO
ALTER TABLE [dbo].[tblProducts] CHECK CONSTRAINT [FK_tblProducts_tblSuppliers]
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD  CONSTRAINT [FK_tblProducts_tblUser] FOREIGN KEY([userIDUpdate])
REFERENCES [dbo].[tblUser] ([UserID])
GO
ALTER TABLE [dbo].[tblProducts] CHECK CONSTRAINT [FK_tblProducts_tblUser]
GO
