-- 商品
CREATE TABLE goods (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(255) NOT NULL,
    price DECIMAL(10,2),
    description varchar(255),
    -- image TEXT,
    category varchar(255),
    number DECIMAL(10,2)
    unit varchar(255) NOT NULL,
    -- created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 供应商
CREATE TABLE supplier(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(255),
    address varchar(255),
    phone varchar(255),
    -- email TEXT,
    -- created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 进货单
CREATE TABLE purchase(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    supplier_id INTEGER,
    goods_id INTEGER,
    price DECIMAL(10,2),
    number DECIMAL(10,2),
    unit varchar(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 售货单
CREATE TABLE sale(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    goods_id INTEGER,
    price DECIMAL(10,2),
    number DECIMAL(10,2),
    unit varchar(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users(
    uid INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(255),
    pwd varchar(255),
    limits varchar(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);