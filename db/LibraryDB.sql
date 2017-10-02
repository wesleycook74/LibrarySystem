create schema library;

create table MEMBERS (
	Fname VARCHAR(20),
    Minit CHAR(1),
    Lname VARCHAR(20),
    MemberID INT(4) NOT NULL,
    Address VARCHAR(50),
    PhoneNumber INT(10),
    Username VARCHAR(15),
    Password VARCHAR(15),
    PRIMARY KEY (MemberID)
);

create table ASSOCIATES (
	  MemberID INT(4) NOT NULL,
    Type char (1) NOT NULL,  #associate or manager a or m
	  PRIMARY KEY (MemberID),
    FOREIGN KEY (MemberID) REFERENCES MEMBERS (MemberID)
);

create table BOOKS (
    ISBN VARCHAR(13) NOT NULL,
    Title VARCHAR(100),
    Year VARCHAR(4),
    PRIMARY KEY (ISBN)
);

create table INVENTORY (
    ID INT(10) NOT NULL AUTO_INCREMENT,
    ISBN VARCHAR(13) NOT NULL,
    checkedout BINARY,
    PRIMARY KEY (ID),
    FOREIGN KEY (ISBN) REFERENCES BOOKS (ISBN)
);

create table AUTHORS (
    ISBN VARCHAR(13) NOT NULL,
    AName VARCHAR(50) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES BOOKS (ISBN),
    PRIMARY KEY (ISBN, AName)
);

create table CHECKOUT_LOG (
    BookID INT(10) NOT NULL,
	  MemberID INT(4) NOT NULL,
    Date_out DATE NOT NULL,
    Date_in DATE,
    FOREIGN KEY (BookID) REFERENCES INVENTORY (ID),
	  FOREIGN KEY (MemberID) REFERENCES MEMBERS (MemberID),
    PRIMARY KEY (BookID, MemberID, Date_out)
);

create table KEYWORDS (
    ISBN VARCHAR(13) NOT NULL,
    keyword VARCHAR(200),
    FOREIGN KEY (ISBN) REFERENCES BOOKS (ISBN),
    PRIMARY KEY (ISBN, keyword)
);
	