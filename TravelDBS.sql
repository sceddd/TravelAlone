
create database TravelDBS;

use TravelDBS
drop table Location;
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
  [locationName] varchar(50),
  [rating] float,
  [suggestionDay] varchar (10),
  [phoneNumber] varchar(10)
)
GO

insert into Location(locationName,rating,suggestionDay,phoneNumber) values ('Indonesia','4.5','13/5','0911485802')