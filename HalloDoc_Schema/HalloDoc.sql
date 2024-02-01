CREATE TABLE `User` (
  `User_ID (primary_key)` integer,
  `First_Name (NN)` varchar(255),
  `Last_Name (NN)` varchar(255),
  `Email (NN)` varchar(255),
  `PhoneNo (NN)` varchar(255),
  `Account_ID (foreign_key,NN)` integer,
  `Role_ID (foreign_key,NN)` integer
);

CREATE TABLE `Registered_User` (
  `Registered_User_ID (primary_key)` integer,
  `User_ID (foreign_key, NN)` integer,
  `Username (NN)` varchar(255),
  `Password (NN)` varchar(255)
);

CREATE TABLE `Address` (
  `Address_ID (primary key)` integer,
  `Address-1 (NN)` varchar(255),
  `Address-2` varchar(255),
  `City (NN)` varchar(255),
  `State (NN)` varchar(255),
  `ZIP` varchar(255)
);

CREATE TABLE `Provider` (
  `Provider_ID (primary_key)` integer,
  `User_ID (foreign_key,NN)` integer,
  `Account_Status (NN)` varchar(255),
  `Oncall_Status (NN)` varchar(255),
  `Alternate_Phone` varchar(255),
  `Business_Name` varchar(255),
  `Website` varchar(255),
  `Signature (NN)` image,
  `Admin_Notes` varchar(255),
  `Stop_Notification (NN)` bool,
  `Address_ID (foreign_key, NN)` int,
  `Photograph (NN)` image
);

CREATE TABLE `Admin` (
  `Admin_ID (primary_key)` integer,
  `User_ID (foreign_key,NN)` integer,
  `Status (NN)` varchar(255),
  `Alternate_Phone` varchar(255),
  `Address_ID (foreign_key, NN)` int
);

CREATE TABLE `Provider_Medical` (
  `Provider_ID (foreign_key,Unique,NN)` integer,
  `Medical_License` varchar(255),
  `NPI_NO` integer,
  `Synchronization_Mail` varchar(255),
  `ICA_Doc` varchar(255),
  `Background_Check` varchar(255),
  `HIPAA` varchar(255),
  `NDA` varchar(255),
  `License` varchar(255)
);

CREATE TABLE `Account_Type` (
  `Account_ID (primary_key)` integer,
  `Account_Name (NN,Unique)` varchar(255)
);

CREATE TABLE `Role_Type` (
  `Role_ID (primary_key)` integer,
  `Role_Name (NN,Unique)` varchar(255)
);

CREATE TABLE `Rights_Type` (
  `Rights_ID (primary_key)` integer,
  `Rights_Name (NN,UNique)` varchar(255)
);

CREATE TABLE `Account_Map_Rights` (
  `Account_ID (foreign_key,NN)` integer,
  `Rights_ID (foreign_key,NN)` integer
);

CREATE TABLE `Role_Map_Rights` (
  `Role_ID (foreign_key,NN)` integer,
  `Rights_ID (foreign_key,NN)` integer
);

CREATE TABLE `Region` (
  `Region_ID (primary_key)` integer,
  `Region_Name (Unique,NN)` varchar(255)
);

CREATE TABLE `User_Map_Region` (
  `Region_ID (foreign_key,NN)` integer,
  `User_ID (foreign_key,NN)` integer
);

CREATE TABLE `Patient` (
  `Patient_ID (primary_key)` integer,
  `User_ID (foreign_key,NN)` integer,
  `Date_of_Birth` date
);

CREATE TABLE `Request` (
  `Case_ID (primary_key)` integer,
  `Patient_ID (foreign_key,NN)` integer,
  `Request_Date (NN)` date,
  `Closing_Date` date,
  `Bool_isCancelled (NN)` bool,
  `Bool_isBlock (NN)` bool,
  `Bool_isClear (NN)` bool,
  `State_ID (foreign_key, NN)` integer,
  `Provider_ID (foreign_key, NN)` integer,
  `Relation_ID (foreign_key, NN)` integer,
  `Room` varchar(255),
  `Address_ID (foreign_key, NN)` int
);

CREATE TABLE `Relation` (
  `Relation_ID (primary_key)` integer,
  `Relation_Name (NN,Unique)` varchar(255)
);

CREATE TABLE `Documents` (
  `Doc_ID (primary_key)` integer,
  `Case_ID (foreign_key,NN)` integer,
  `Patient_ID (foreign_key,NN)` integer,
  `Uploader_Name (NN)` varchar(255),
  `Uploader_Email (NN)` varchar(255),
  `Document (NN)` varchar(255)
);

CREATE TABLE `Request_by_Others` (
  `Case_ID (foreign_key,Unique,NN)` integer,
  `Patient_ID (foreign_key,NN)` integer,
  `Requestor_Patient_ID (foreign_key,NN)` integer,
  `Bool_isRegistered (NN)` bool
);

CREATE TABLE `Notes` (
  `Notes_ID (primary_key)` integer,
  `Case_ID (foreign_key,NN)` integer,
  `Type (enum,NN)` varchar(255),
  `Content` varchar(255)
);

CREATE TABLE `Case_States` (
  `State_ID (primary_key)` integer,
  `State_Type (NN,Unique)` varchar(255)
);

CREATE TABLE `Family_Friend` (
  `Case_ID (foreign_key,NN,Unique)` integer,
  `First_Name (NN)` varchar(255),
  `Last_Name (NN)` varchar(255),
  `Email (NN)` varchar(255),
  `Phone_No (NN)` varchar(255),
  `Relation` varchar(255)
);

CREATE TABLE `Concierge` (
  `Case_ID (foreign_key,NN,Unique)` integer,
  `First_Name (NN)` varchar(255),
  `Last_Name (NN)` varchar(255),
  `Email (NN)` varchar(255),
  `Phone_No (NN)` varchar(255),
  `Hotel_Property` varchar(255)
);

CREATE TABLE `Business` (
  `Case_ID (foreign_key,NN,Unique)` integer,
  `First_Name (NN)` varchar(255),
  `Last_Name (NN)` varchar(255),
  `Email (NN)` varchar(255),
  `Phone_No (NN)` varchar(255),
  `Property (NN)` varchar(255),
  `Case_Number` integer
);

CREATE TABLE `Encounter_Form` (
  `Form_ID (primary_key)` integer,
  `Case_ID (foreign_key,NN)` integer,
  `Patient_ID (foreign_key,NN)` integer,
  `Illness_History` varchar(255),
  `Medical_History` varchar(255),
  `Medications` varchar(255),
  `Allergies` varchar(255),
  `Temprature` double,
  `Heart_Rate` double,
  `Respitory_Rate` double,
  `Blood_Pressure_High` double,
  `Blood_Pressure_Low` double,
  `O2` double,
  `Pain` varchar(255),
  `HEENT` varchar(255),
  `CV_Reading` varchar(255),
  `Chest_Reading` varchar(255),
  `ABD_Readings` varchar(255),
  `Extermitisis_Reading` varchar(255),
  `Skin_Test` varchar(255),
  `Neuro` varchar(255),
  `Other` varchar(255),
  `Diagnosis` varchar(255),
  `Treatment` varchar(255),
  `Dispensed_Medicine` varchar(255),
  `Proceure` varchar(255),
  `Follow_UP` varchar(255)
);

CREATE TABLE `Orders` (
  `Order_ID (primary_key)` integer,
  `Case_ID (foreign_key,NN,Unique)` integer,
  `Vendor_ID(foreign_key,NN)` integer,
  `Order_Detials (NN)` varchar(255),
  `Refills` integer
);

CREATE TABLE `Block_State` (
  `Case_ID (foreign_key,NN,Unique)` integer,
  `Reason` varchar(255)
);

CREATE TABLE `Vendor` (
  `Vendor_ID (primary_key)` integer,
  `Proffession_ID (foreign_key,NN)` integer,
  `Business Name (NN)` varchar(255),
  `Email (NN)` varchar(255),
  `Phone_Number (NN)` integer,
  `Fax_No (NN)` integer,
  `Business_Contact` varchar(255),
  `Address_ID (foreign_key, NN)` int
);

CREATE TABLE `Proffession` (
  `Proffession_ID (primary_key)` integer,
  `Proffession_Name (NN)` varchar(255)
);

CREATE TABLE `Logs` (
  `Log_ID (primary_key)` integer,
  `Type (NN)` varchar(255),
  `Reciepent_ID (foreign_key,NN)` integer,
  `Sender_ID (foreign_key,NN)` integer,
  `Action` varchar(255),
  `Sent_Date (NN)` date,
  `Bool_isSent(NN)` bool,
  `Sent_Tries` integer,
  `Confirmation_No` integer
);

CREATE TABLE `Shift` (
  `Shift_ID (primary_key)` integer,
  `Provider_ID (foreign_key)` integer,
  `Start_Date (NN)` date,
  `End_Date (NN)` date,
  `Bool_willRepeat (NN)` bool,
  `WeekDays` varchar(255),
  `Repeat_Till (NN)` varchar(255),
  `Region_ID (foreign_key,NN)` integer,
  `Bool_isAccepted (NN)` bool
);

ALTER TABLE `Role_Map_Rights` ADD FOREIGN KEY (`Role_ID (foreign_key,NN)`) REFERENCES `Role_Type` (`Role_ID (primary_key)`);

ALTER TABLE `Account_Map_Rights` ADD FOREIGN KEY (`Account_ID (foreign_key,NN)`) REFERENCES `Account_Type` (`Account_ID (primary_key)`);

ALTER TABLE `Account_Map_Rights` ADD FOREIGN KEY (`Rights_ID (foreign_key,NN)`) REFERENCES `Rights_Type` (`Rights_ID (primary_key)`);

ALTER TABLE `Role_Map_Rights` ADD FOREIGN KEY (`Rights_ID (foreign_key,NN)`) REFERENCES `Rights_Type` (`Rights_ID (primary_key)`);

ALTER TABLE `User` ADD FOREIGN KEY (`Account_ID (foreign_key,NN)`) REFERENCES `Account_Type` (`Account_ID (primary_key)`);

ALTER TABLE `User` ADD FOREIGN KEY (`Role_ID (foreign_key,NN)`) REFERENCES `Role_Type` (`Role_ID (primary_key)`);

ALTER TABLE `Shift` ADD FOREIGN KEY (`Provider_ID (foreign_key)`) REFERENCES `Provider` (`Provider_ID (primary_key)`);

ALTER TABLE `Shift` ADD FOREIGN KEY (`Region_ID (foreign_key,NN)`) REFERENCES `Region` (`Region_ID (primary_key)`);

ALTER TABLE `Provider` ADD FOREIGN KEY (`Address_ID (foreign_key, NN)`) REFERENCES `Address` (`Address_ID (primary key)`);

ALTER TABLE `Provider` ADD FOREIGN KEY (`User_ID (foreign_key,NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Provider_Medical` ADD FOREIGN KEY (`Provider_ID (foreign_key,Unique,NN)`) REFERENCES `Provider` (`Provider_ID (primary_key)`);

ALTER TABLE `Registered_User` ADD FOREIGN KEY (`User_ID (foreign_key, NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Admin` ADD FOREIGN KEY (`User_ID (foreign_key,NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Admin` ADD FOREIGN KEY (`Address_ID (foreign_key, NN)`) REFERENCES `Address` (`Address_ID (primary key)`);

ALTER TABLE `User_Map_Region` ADD FOREIGN KEY (`Region_ID (foreign_key,NN)`) REFERENCES `Region` (`Region_ID (primary_key)`);

ALTER TABLE `User_Map_Region` ADD FOREIGN KEY (`User_ID (foreign_key,NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Patient` ADD FOREIGN KEY (`User_ID (foreign_key,NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Request` ADD FOREIGN KEY (`Relation_ID (foreign_key, NN)`) REFERENCES `Relation` (`Relation_ID (primary_key)`);

ALTER TABLE `Request` ADD FOREIGN KEY (`Address_ID (foreign_key, NN)`) REFERENCES `Address` (`Address_ID (primary key)`);

ALTER TABLE `Request` ADD FOREIGN KEY (`Patient_ID (foreign_key,NN)`) REFERENCES `Patient` (`Patient_ID (primary_key)`);

ALTER TABLE `Request` ADD FOREIGN KEY (`State_ID (foreign_key, NN)`) REFERENCES `Case_States` (`State_ID (primary_key)`);

ALTER TABLE `Request` ADD FOREIGN KEY (`Provider_ID (foreign_key, NN)`) REFERENCES `Provider` (`Provider_ID (primary_key)`);

ALTER TABLE `Block_State` ADD FOREIGN KEY (`Case_ID (foreign_key,NN,Unique)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Documents` ADD FOREIGN KEY (`Case_ID (foreign_key,NN)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Documents` ADD FOREIGN KEY (`Patient_ID (foreign_key,NN)`) REFERENCES `Patient` (`Patient_ID (primary_key)`);

ALTER TABLE `Logs` ADD FOREIGN KEY (`Reciepent_ID (foreign_key,NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Logs` ADD FOREIGN KEY (`Sender_ID (foreign_key,NN)`) REFERENCES `User` (`User_ID (primary_key)`);

ALTER TABLE `Family_Friend` ADD FOREIGN KEY (`Case_ID (foreign_key,NN,Unique)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Concierge` ADD FOREIGN KEY (`Case_ID (foreign_key,NN,Unique)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Business` ADD FOREIGN KEY (`Case_ID (foreign_key,NN,Unique)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Request_by_Others` ADD FOREIGN KEY (`Case_ID (foreign_key,Unique,NN)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Request_by_Others` ADD FOREIGN KEY (`Patient_ID (foreign_key,NN)`) REFERENCES `Patient` (`Patient_ID (primary_key)`);

ALTER TABLE `Request_by_Others` ADD FOREIGN KEY (`Requestor_Patient_ID (foreign_key,NN)`) REFERENCES `Patient` (`Patient_ID (primary_key)`);

ALTER TABLE `Orders` ADD FOREIGN KEY (`Case_ID (foreign_key,NN,Unique)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Orders` ADD FOREIGN KEY (`Vendor_ID(foreign_key,NN)`) REFERENCES `Vendor` (`Vendor_ID (primary_key)`);

ALTER TABLE `Vendor` ADD FOREIGN KEY (`Proffession_ID (foreign_key,NN)`) REFERENCES `Proffession` (`Proffession_ID (primary_key)`);

ALTER TABLE `Vendor` ADD FOREIGN KEY (`Address_ID (foreign_key, NN)`) REFERENCES `Address` (`Address_ID (primary key)`);

ALTER TABLE `Notes` ADD FOREIGN KEY (`Case_ID (foreign_key,NN)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Encounter_Form` ADD FOREIGN KEY (`Case_ID (foreign_key,NN)`) REFERENCES `Request` (`Case_ID (primary_key)`);

ALTER TABLE `Encounter_Form` ADD FOREIGN KEY (`Patient_ID (foreign_key,NN)`) REFERENCES `Patient` (`Patient_ID (primary_key)`);
