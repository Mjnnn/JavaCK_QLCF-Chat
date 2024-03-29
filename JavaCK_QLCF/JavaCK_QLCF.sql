USE [JavaCK]
GO
ALTER TABLE [dbo].[DetailBill] DROP CONSTRAINT [FK_DetailBill_Table]
GO
ALTER TABLE [dbo].[DetailBill] DROP CONSTRAINT [FK_DetailBill_Product]
GO
ALTER TABLE [dbo].[Bill] DROP CONSTRAINT [FK_Bill_Table]
GO
/****** Object:  Table [dbo].[Table]    Script Date: 7/6/2022 10:10:08 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Table]') AND type in (N'U'))
DROP TABLE [dbo].[Table]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 7/6/2022 10:10:08 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Product]') AND type in (N'U'))
DROP TABLE [dbo].[Product]
GO
/****** Object:  Table [dbo].[DetailBill]    Script Date: 7/6/2022 10:10:08 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DetailBill]') AND type in (N'U'))
DROP TABLE [dbo].[DetailBill]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 7/6/2022 10:10:08 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Bill]') AND type in (N'U'))
DROP TABLE [dbo].[Bill]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/6/2022 10:10:08 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Account]') AND type in (N'U'))
DROP TABLE [dbo].[Account]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/6/2022 10:10:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[TaiKhoan] [nvarchar](100) NOT NULL,
	[MatKhau] [nvarchar](100) NULL,
	[HoVaTen] [nvarchar](100) NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](100) NULL,
	[GioiTinh] [nvarchar](100) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[TaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 7/6/2022 10:10:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[MaHD] [nvarchar](100) NOT NULL,
	[MaBan] [int] NOT NULL,
	[ThoiGian] [date] NULL,
	[TongTien] [int] NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC,
	[MaBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetailBill]    Script Date: 7/6/2022 10:10:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailBill](
	[MaBan] [int] NULL,
	[MaSP] [nvarchar](100) NULL,
	[TenSp] [nvarchar](100) NULL,
	[SoLuong] [int] NULL,
	[ThanhTien] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 7/6/2022 10:10:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[MaSP] [nvarchar](100) NOT NULL,
	[TenSp] [nvarchar](100) NULL,
	[Gia] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Table]    Script Date: 7/6/2022 10:10:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Table](
	[MaBan] [int] NOT NULL,
 CONSTRAINT [PK_Table] PRIMARY KEY CLUSTERED 
(
	[MaBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([TaiKhoan], [MatKhau], [HoVaTen], [NgaySinh], [DiaChi], [GioiTinh]) VALUES (N'hieu21it3', N'123123', N'Nguyễn Trung Hiếu', CAST(N'2022-07-03' AS Date), N'Quảng Bình', N'Nam')
INSERT [dbo].[Account] ([TaiKhoan], [MatKhau], [HoVaTen], [NgaySinh], [DiaChi], [GioiTinh]) VALUES (N'huong21it4', N'123123', N'Hoàng Diệu Hương', CAST(N'2022-07-04' AS Date), N'Đà Nẵng', N'Nữ')
INSERT [dbo].[Account] ([TaiKhoan], [MatKhau], [HoVaTen], [NgaySinh], [DiaChi], [GioiTinh]) VALUES (N'minh21it6', N'123123', N'Từ Công Minh', CAST(N'2022-07-04' AS Date), N'Đà Nẵng', N'Nam')
INSERT [dbo].[Account] ([TaiKhoan], [MatKhau], [HoVaTen], [NgaySinh], [DiaChi], [GioiTinh]) VALUES (N'phat21it3', N'123123', N'Nguyễn Thành Phát', CAST(N'2022-07-05' AS Date), N'Quảng Bình', N'Nam')
GO
INSERT [dbo].[Bill] ([MaHD], [MaBan], [ThoiGian], [TongTien]) VALUES (N'hd01', 4, CAST(N'2022-12-02' AS Date), 30000)
INSERT [dbo].[Bill] ([MaHD], [MaBan], [ThoiGian], [TongTien]) VALUES (N'hd02', 4, CAST(N'2022-02-02' AS Date), 67500)
GO
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'C1', N'Nước Cam', 20000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'C4', N'CaCao Nóng', 20000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'C5', N'Sinh Tố Cam', 30000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'D1', N'Nước Dâu', 25000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'D5', N'Sinh Tố Dâu', 30000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'E2', N'CaFe Trung Nguyên', 25000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'E3', N'CaFe Nguyên Chất', 28000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'E4', N'CaFe Đen', 20000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'E5', N'CaFe Sữa', 20000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'H2', N'Pepsi', 12000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'L3', N'Sting', 12000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'S1', N'Nước Suối', 10000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'S2', N'SoDa', 18000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'S3', N'Sữa Chua', 15000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'T2', N'CoCa', 12000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'TD', N'Trà Đào', 20000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'TG', N'Trà Gừng', 15000)
INSERT [dbo].[Product] ([MaSP], [TenSp], [Gia]) VALUES (N'TS', N'Trà Sữa', 15000)
GO
INSERT [dbo].[Table] ([MaBan]) VALUES (1)
INSERT [dbo].[Table] ([MaBan]) VALUES (2)
INSERT [dbo].[Table] ([MaBan]) VALUES (3)
INSERT [dbo].[Table] ([MaBan]) VALUES (4)
INSERT [dbo].[Table] ([MaBan]) VALUES (5)
INSERT [dbo].[Table] ([MaBan]) VALUES (6)
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Table] FOREIGN KEY([MaBan])
REFERENCES [dbo].[Table] ([MaBan])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Table]
GO
ALTER TABLE [dbo].[DetailBill]  WITH CHECK ADD  CONSTRAINT [FK_DetailBill_Product] FOREIGN KEY([MaSP])
REFERENCES [dbo].[Product] ([MaSP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DetailBill] CHECK CONSTRAINT [FK_DetailBill_Product]
GO
ALTER TABLE [dbo].[DetailBill]  WITH CHECK ADD  CONSTRAINT [FK_DetailBill_Table] FOREIGN KEY([MaBan])
REFERENCES [dbo].[Table] ([MaBan])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DetailBill] CHECK CONSTRAINT [FK_DetailBill_Table]
GO
