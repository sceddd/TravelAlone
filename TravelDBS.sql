create database TravelDBS;

use TravelDBS
CREATE TABLE [User] (
  [userID] int IDENTITY(1,1) PRIMARY KEY,
  [fullName] varchar(50),
  [phoneNumber] varchar(10),
  [email] nvarchar(50),
  [visitDay] date,
  [returnDay] date,
  [visitedDay] date
)
GO

CREATE TABLE [Location] (
  [locationID] int IDENTITY(1,1) PRIMARY KEY,
  [LocationDes] varchar(1000),
  [locationName] varchar(50),
  [rating] float,
  [suggestionDay] date,
  [phoneNumber] varchar(10)
)
GO
insert into Location(LocationDes,locationName,rating,suggestionDay,phoneNumber) values ('','Indonesia')