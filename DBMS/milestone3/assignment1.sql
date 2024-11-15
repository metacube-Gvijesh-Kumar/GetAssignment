-- creating the databse storefront in which our tables will lie

CREATE DATABASE StoreFront;
  
USE StoreFront;

-- creating the Catergory table
CREATE TABLE Catergory (
    ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    Name varchar(255),
    Description varchar(600) DEFAULT '',
    ParentID int NULL,
	FOREIGN KEY (ParentID) REFERENCES Catergory(ID) ON DELETE CASCADE
);

-- creating the Product table
CREATE TABLE Product  (
    ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    Name varchar(255) NOT NULL,
    Description varchar(600) DEFAULT '',
    Stock INT UNSIGNED DEFAULT 0,
    Price INT UNSIGNED DEFAULT 0
);

CREATE TABLE Categorize(
	Product  int NOT NULL,
    Category int NOT NULL,
    PRIMARY KEY(Product,Category),
    FOREIGN KEY (Product) REFERENCES Product(ID) ON DELETE CASCADE,
    FOREIGN KEY (Category) REFERENCES Catergory(ID) ON DELETE CASCADE
);

-- creating the Image table
CREATE TABLE Image  (
    ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    Url varchar(512) NOT NULL,
    Product int NOT NULL,
    FOREIGN KEY (Product) REFERENCES Product(ID) ON DELETE CASCADE
);

-- creating user table
CREATE TABLE User (
    UserName varchar(100) NOT NULL UNIQUE PRIMARY KEY,
    Password Char(100) NOT NULL,
    Email varchar(250) NOT NULL,
    Role Enum('Shopper','Admin') NOT NULL DEFAULT 'Shopper'
);

-- creating Address table
CREATE TABLE Address (
   ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
   Street varchar(100) NOT NULL,
   City varchar(100) NOT NULL,
   PinCode int UNSIGNED NOT NULL,
   State varchar(100) NOT NULL,
   Country varchar(100) NOT NULL
);

-- creating shippingAddress table
Create Table ShippingAddress (
    User varchar(100) NOT NULL, 
    Address int NOT NULL, 
    FOREIGN KEY (Address) REFERENCES Address(ID) ON DELETE CASCADE,
    FOREIGN KEY (User) REFERENCES User(UserName) ON DELETE CASCADE
);

-- creating order table 
CREATE TABLE Orders (
    ID int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    User varchar(100) NOT NULL ,
    Date DATETIME(3) NOT NULL,
    FOREIGN KEY (User) REFERENCES User(UserName) ON DELETE CASCADE
);

-- creating order item table 
CREATE TABLE OrderItem (
    Orders int NOT NULL,
    Product int NOT NULL,
    Quantity int NOT NULL DEFAULT 1,
    Status Enum('on the way','shipped','canceled','returned') NOT NULL DEFAULT 'on the way',
    FOREIGN KEY (Orders) REFERENCES Orders(ID) ON DELETE CASCADE,
    FOREIGN KEY (Product) REFERENCES Product(ID) ON DELETE CASCADE,
    Primary key (Orders,Product)
);

-- dropping the product table alone is not possible as 
-- we will need to delete all the tables that refer any of the product table column in them as foreign field hence 