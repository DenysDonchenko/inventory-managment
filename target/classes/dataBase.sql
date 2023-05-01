CREATE TABLE User
(
    ID           UUID PRIMARY KEY,
    FirstName    VARCHAR(255) NOT NULL,
    LastName     VARCHAR(255) NOT NULL,
    Email        VARCHAR(255) NOT NULL UNIQUE,
    Password     VARCHAR(255) NOT NULL,
    OtherDetails VARCHAR(255)
);


CREATE TABLE Business
(
    ID           UUID PRIMARY KEY,
    Name         VARCHAR(255) NOT NULL,
    Address      VARCHAR(255) NOT NULL,
    Phone        VARCHAR(20)  NOT NULL,
    Email        VARCHAR(255) NOT NULL,
    Website      VARCHAR(255),
    OtherDetails VARCHAR(255),
    UserID       UUID         NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User (ID)
);

CREATE TABLE Product
(
    ID           UUID PRIMARY KEY,
    Name         VARCHAR(255)   NOT NULL,
    Description  VARCHAR(255),
    Type         VARCHAR(255)   NOT NULL,
    UnitPrice    DECIMAL(10, 2) NOT NULL,
    OtherDetails VARCHAR(255)
);

CREATE TABLE Inventory
(
    ID           UUID PRIMARY KEY,
    BusinessID   UUID     NOT NULL,
    ProductID    UUID     NOT NULL,
    Quantity     INT      NOT NULL,
    DateAdded    timestamp NOT NULL,
    OtherDetails VARCHAR(255),
    FOREIGN KEY (BusinessID) REFERENCES Business (ID),
    FOREIGN KEY (ProductID) REFERENCES Product (ID)
);


CREATE TABLE Sale
(
    ID           UUID PRIMARY KEY,
    BusinessID   UUID           NOT NULL,
    DateOfSale   timestamp       NOT NULL,
    TotalPrice   DECIMAL(10, 2) NOT NULL,
    OtherDetails VARCHAR(255),
    FOREIGN KEY (BusinessID) REFERENCES Business (ID)
);
