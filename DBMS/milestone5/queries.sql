-- Given the id of a user, fetch all orders (Id, Order Date, Order Total) of that user which are in shipped state. Orders should be sorted by order date column in chronological order.
USE storeFront;

SELECT od.Id,date AS order_date,total AS order_total
FROM orders od
JOIN (
SELECT orders,SUM(p.price*oi.quantity) total
FROM orderitem oi
JOIN Product p
ON oi.product= p.id
WHERE oi.Status='shipped'
GROUP BY orders 
) AS op
ON od.ID = op.orders
WHERE od.Id NOT IN (
SELECT DISTINCT od.Id 
FROM orders od 
JOIN orderitem oi 
ON oi.orders=od.Id
WHERE oi.status not in ('shipped')
)  AND od.User = 'est'
ORDER BY order_date desc;

-- Insert five or more images of a product using batch insert technique.
INSERT INTO `Image` VALUES
(null,'http://productImage.com/',1);

-- Delete all those products which were not ordered by any Shopper in last 1 year. Return the number of products deleted.

SELECT ID 
FROM product
WHERE ID NOT IN (
SELECT oi.product
FROM orderitem oi
JOIN orders od
ON od.Id=oi.orders
WHERE od.Date>DATE_SUB(now(), INTERVAL 365 DAY)
);


-- Select and display the category title of all top parent categories sorted alphabetically and the count of their child categories.

SELECT p.ID,p.Name,count(p.Id) as childCount  
From catergory p
Join Catergory c
on c.parentId = p.Id
where p.ParentId Is null
group by p.Id
