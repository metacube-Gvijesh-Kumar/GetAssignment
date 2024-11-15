-- Identify the columns require indexing in order, product, category tables and create indexes.

-- order index(orderId) and user

CREATE INDEX order_id
ON orders (ID);

CREATE INDEX order_user
ON orders (user);

-- product index(Id)

CREATE INDEX product_id
ON product(ID);

-- category index(Id) and parentid

CREATE INDEX category_id
ON category(ID);

CREATE INDEX category_pid
ON orders (parentID);
