
create database TravelDBS;

use TravelDBS
drop table Location;
CREATE TABLE [Users] (
  [userID] int IDENTITY(1,1) PRIMARY KEY,
  [fullName] varchar(50),
  [phoneNumber] varchar(10),
  [email] nvarchar(50),
)
GO
CREATE TABLE [Location] (
  [locationID] int IDENTITY(1,1) PRIMARY KEY,
  [locationName] varchar(50),
  [rating] float,
  [suggestionDay] varchar (10),
  [phoneNumber] varchar(10),
  [visitedTime] int default 0
)
GO
CREATE TABLE [HistoryBook](
	[histoID] int IDENTITY(1,1) Primary Key,
	[userID] int ,
	[locationID] int ,
	[visitDay] Date ,
	[returnDay] Date,
	[status] bit default 0,
	CONSTRAINT FK_UserOrder Foreign Key (userID) references Users(userID),
	CONSTRAINT FK_LocationOrder Foreign Key (locationID) references Location(locationID)
)
drop table HistoryBook
insert into Location(locationName,rating,suggestionDay,phoneNumber) values ('Indonesia','4.5','13/5','0911485802')
select * from Users
SELECT * FROM USERS
select * from Location
select * from HistoryBook
SELECT userID FROM [USERS] WHERE phoneNumber = '0911485802' and email = 'socdia07@gmail.com'
SELECT userID FROM USERS WHERE phoneNumber = '0911485802' and email = 'socdia07@gmail.com'
