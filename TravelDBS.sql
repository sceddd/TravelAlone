CREATE TABLE [User] (
  [userID] int,
  [fullName] varchar(50),
  [phoneNumber] varchar(10),
  [email] nvarchar(50),
  [visitDay] date,
  [returnDay] date,
  [visitedDay] date
)
GO

CREATE TABLE [Location] (
  [locationID] int,
  [LocationDes] varchar(1000),
  [locationName] varchar(50),
  [rating] float,
  [suggestionDay] date,
  [phoneNumber] varchar(10)
)
GO
