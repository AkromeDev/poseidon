CREATE TABLE bidlist (

    PRIMARY KEY (bidlistid)

    bidlistid tinyint(4) NOT NULL AUTO_INCREMENT,
    account VARCHAR(30) NOT NULL,
    type VARCHAR(30) NOT NULL,
    bid_quantity DOUBLE,
    ask_quantity DOUBLE,
    bid DOUBLE,
    ask DOUBLE,
    benchmark VARCHAR(125),
    bid_list_date TIMESTAMP,
    commentary VARCHAR(125),
    security VARCHAR(125),
    status VARCHAR(10),
    trader VARCHAR(125),
    book VARCHAR(125),
    creation_name VARCHAR(125),
    creation_date TIMESTAMP ,
    revision_name VARCHAR(125),
    revision_date TIMESTAMP,
    deal_name VARCHAR(125),
    deal_type VARCHAR(125),
    source_list_id VARCHAR(125),
    side VARCHAR(125),
)

CREATE TABLE curvepoint (

    PRIMARY KEY (id)

    id tinyint(4) NOT NULL AUTO_INCREMENT,
    curveId Integer NOT NULL,
    asOfDate TIMESTAMP,
    term DOUBLE NOT NULL,
    value DOUBLE NOT NULL,
    creationDate TIMESTAMP,
)

CREATE TABLE rating (

    PRIMARY KEY (id)

    id tinyint(4) NOT NULL AUTO_INCREMENT,
    moodysRating VARCHAR(125),
    sandPRating VARCHAR(125),
    fitchRating VARCHAR(125),
    orderNumber Integer,
)

CREATE TABLE rule (

    PRIMARY KEY (id)

    id tinyint(4) NOT NULL AUTO_INCREMENT,
    name VARCHAR(125) NOT NULL,
    description VARCHAR(125),
    json VARCHAR(125),
    template VARCHAR(125),
    sqlStr VARCHAR(125),
    sqlPart VARCHAR(125),
)

CREATE TABLE trade (

    PRIMARY KEY (tradeId)

    tradeId tinyint(4) NOT NULL AUTO_INCREMENT,
    account VARCHAR(125) NOT NULL,
    type VARCHAR(125) NOT NULL,
    buyQuantity DOUBLE,
    sellQuantity DOUBLE,
    buyPrice DOUBLE,
    sellPrice DOUBLE,
    benchmark VARCHAR(125),
    tradeDate TIMESTAMP,
    security VARCHAR(125),
    status VARCHAR(125),
    trader VARCHAR(125),
    book VARCHAR(125),
    creationName VARCHAR(125),
    creationDate TIMESTAMP,
    revisionName VARCHAR(125),
    revisionDate TIMESTAMP,
    dealName VARCHAR(125),
    dealType VARCHAR(125),
    sourceListId VARCHAR(125),
    side VARCHAR(125),
)

CREATE TABLE user (

    PRIMARY KEY (id)

    id tinyint(4) NOT NULL AUTO_INCREMENT,
    String username VARCHAR(125) NOT NULL,
    String password VARCHAR(125) NOT NULL,
    String fullname VARCHAR(125) NOT NULL,
    String role VARCHAR(125) NOT NULL,
)