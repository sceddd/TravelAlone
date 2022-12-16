drop database TravelDBS;
create database TravelDBS;
go
use TravelDBS

CREATE TABLE [Users] (
  [userID] int IDENTITY(1,1) PRIMARY KEY,
  [fullName] varchar(50),
  [phoneNumber] varchar(10),
  [email] nvarchar(50),
)
GO
CREATE TABLE Location(City_ID int primary key,Name NVARCHAR(50),Region NVARCHAR(50),Longtitude FLOAT, Latitude FLOAT,Rating FLOAT);
GO
CREATE TABLE [HistoryBook](
	[histoID] int IDENTITY(1,1) Primary Key,
	[userID] int ,
	[locationID] int ,
	[visitDay] Date ,
	[returnDay] Date,
	[status] bit default 0,
	CONSTRAINT FK_UserOrder Foreign Key (userID) references Users(userID),
	CONSTRAINT FK_LocationOrder Foreign Key (locationID) references Location(City_ID)
)
GO
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (1,'An Giang','Southwest',105.41833,10.37528,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (2,N'Bà Rịa – Vũng Tàu','Southeast',107.08472,10.34583,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (3,N'Bắc Giang','Northeast',106.18694,21.29139,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (4,N'Bắc Kạn','Northeast',105.756246,22.306994,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (5,N'Bạc Liêu','Southwest',105.75194,9.25889,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (6,N'Bắc Ninh','Mekong Delta River',106.05639,21.18528,1);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (7,N'Bến Tre','Southwest',106.385507,10.247866,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (8,N'Bình Định','South Central',109.23333,13.775,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (9,N'Bình Dương','Southeast',106.65611,10.99333,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (10,N'Bình Phước','Southeast',106.875545,11.809676,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (11,N'Bình Thuận','South Central',108.10944,10.92222,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (12,N'Cà Mau','Southwest',105.15,9.18361,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (13,N'Cần Thơ','Southwest',105.78389,10.03278,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (14,N'Cao Bằng;','Northeast',106.26028,22.66694,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (15,N'Đà Nẵng','South Central',108.21528,16.05194,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (16,N'Đắk Lắk','Central Highlands',108.03889,12.66667,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (17,N'Đắk Nông','Central Highlands',107.600133,12.142623,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (18,N'Điện Biên','Northwest',103.00861,21.36667,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (19,N'Đồng Nai','Southeast',107.01361,10.79306,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (20,N'Đồng Tháp','Southwest',105.76667,10.3,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (21,N'Gia Lai','Central Highlands',1080,13.98361,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (22,N'Hà Giang','Northeast',105.031311,22.806907,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (23,N'Hà Nam','Mekong Delta River',106.009828,20.541459,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (24,N'Hà Nội','Mekong Delta River',105.85,21.03333,1);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (25,N'Hà Tĩnh','North Central',105.9075,18.34083,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (26,N'Hải Dương','Mekong Delta River',106.3125,20.93972,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (27,N'Hải Phòng','Mekong Delta River',106.68028,20.86194,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (28,N'Hậu Giang','Southwest',105.568145,9.803426,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (29,N'Hòa Bình','Northwest',105.41224,20.643344,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (30,N'Hưng Yên','Mekong Delta River',106.05694,20.63667,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (31,N'Khánh Hòa','South Central',109.13694,11.91361,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (32,N'Kiên Giang','Southwest',105.09028,10.02083,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (33,N'Kon Tum','Central Highlands',107.99861,14.35,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (34,N'Lai Châu','Northwest',103.432731,22.313624,1);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (35,N'Lâm Đồng','Central Highlands',108.44222,11.94556,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (36,N'Lạng Sơn','Northeast',106.76167,21.85417,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (37,N'Lào Cai','Northwest',104.00278,22.44028,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (38,N'Long An','Southwest',106.49306,10.63194,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (39,N'Nam Định','Mekong Delta River',106.16833,20.42,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (40,N'Nghệ An','North Central',105.68167,18.68083,1);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (41,N'Ninh Bình','Mekong Delta River',105.975,20.25111,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (42,N'Ninh Thuận','South Central',108.99167,11.56667,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (43,N'Phú Thọ','Northeast',105.247305,21.418988,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (44,N'Phú Yên','South Central',109.31611,13.08222,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (45,N'Quảng Bình','North Central',106.59944,17.46861,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (46,N'Quảng Nam','South Central',108.49444,15.56528,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (47,N'Quảng Ngãi','South Central',108.81167,15.12389,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (48,N'Quảng Ninh','Northeast',107.04528,20.97194,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (49,N'Quảng Trị','North Central',107.19389,16.74694,1);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (50,N'Sóc Trăng','Southwest',105.97417,9.60389,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (51,N'Sơn La','Northwest',103.91417,21.32722,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (52,N'Tây Ninh','Southeast',106.11917,11.36778,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (53,N'Thái Bình','Mekong Delta',106.3375,20.4475,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (54,N'Thái Nguyên','Northeast',105.82556,21.5675,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (55,N'Thanh Hóa','North Central',105.77639,19.8075,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (56,N'Thừa Thiên Huế','North Central',107.58472,16.46278,5);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (57,N'Tiền Giang','Southwest',106.36528,10.35417,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (58,N'Thành phố Hồ Chí Minh','Southeast',106.63278,10.83333,4);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (59,N'Trà Vinh','Southwest',106.33472,9.95139,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (60,N'Tuyên Quang','Northeast',105.21167,21.81861,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (61,N'Vĩnh Long','Southwest',105.95833,10.24583,3);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (62,N'Vĩnh Phúc','Mekong Delta River',105.60611,21.29889,2);
INSERT INTO Location (City_ID,Name,Region,Longtitude,Latitude,Rating) VALUES (63,N'Yên Bái','Northwest',104.89861,21.71667,4);
GO
drop table HistoryBook
SELECT top 10 * FROM Location ORDER BY Rating DESC
select * from Users
SELECT * FROM USERS
select * from Location
SELECT * FROM LOCATION WHERE City_ID IN (44,61,63)
SELECT userID FROM [USERS] WHERE phoneNumber = '0911485802' and email = 'socdia07@gmail.com'
SELECT userID FROM USERS WHERE phoneNumber = '0911485802' and email = 'socdia07@gmail.com'