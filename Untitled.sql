CREATE TABLE [User] (
  [userID] int,
  [fullName] String,
  [phoneNumber] String,
  [email] String,
  [visitDay] date,
  [returnDay] date,
  [visitedDay] date
)
GO

CREATE TABLE [Location] (
  [locationID] int,
  [LocationDes] String,
  [locationName] String,
  [rating] float,
  [suggestionDay] date,
  [phoneNumber] String
)
GO
