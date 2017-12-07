create schema library;

CREATE TABLE MEMBERS (
    MemberID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT,
    Fname VARCHAR(20),
    Minit CHAR(1),
    Lname VARCHAR(20),
    Address VARCHAR(50),
    PhoneNumber VARCHAR(10),
    Username VARCHAR(15) UNIQUE,
    Password VARCHAR(15),
    Fines DECIMAL(5, 2) DEFAULT 0,
    IsActive BOOLEAN NOT NULL,
    MemberLevel INT(1),
    PRIMARY KEY (MemberID)
);

INSERT INTO MEMBERS (Fname, Username, Password, IsActive, MemberLevel)
VALUES ("Administrator","admin", "admin", true, 2);

CREATE TABLE BOOKS (
    ISBN VARCHAR(13) NOT NULL,
    Title VARCHAR(100),
    Year VARCHAR(4),
    PRIMARY KEY (ISBN)
);

CREATE TABLE COPIES (
    ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    ISBN VARCHAR(13) NOT NULL,
    CheckedOut BOOLEAN,
    OnHold BOOLEAN,
    DateOut DATE,
    CheckedOutMemberID INT(4) UNSIGNED,
    OnHoldMemberID INT(4) UNSIGNED,
    RenewCount INT(1),
    IsLost BOOLEAN,
    PRIMARY KEY (ID),
    FOREIGN KEY (ISBN)
        REFERENCES BOOKS (ISBN),
    FOREIGN KEY (CheckedOutMemberID)
        REFERENCES MEMBERS (MemberID),
    FOREIGN KEY (OnHoldMemberID)
        REFERENCES MEMBERS (MemberID)
);

CREATE TABLE AUTHORS (
    ISBN VARCHAR(13) NOT NULL,
    AName VARCHAR(50) NOT NULL,
    FOREIGN KEY (ISBN)
        REFERENCES BOOKS (ISBN),
    PRIMARY KEY (ISBN , AName)
);

CREATE TABLE KEYWORDS (
    ISBN VARCHAR(13) NOT NULL,
    Keyword VARCHAR(200),
    FOREIGN KEY (ISBN)
        REFERENCES BOOKS (ISBN),
    PRIMARY KEY (ISBN , Keyword)
);
	