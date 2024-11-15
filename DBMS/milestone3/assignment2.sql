-- Display the list of products (Id, Title, Count of Categories) which fall in more than one Category.
SELECT c.Product,p.Name,Count(c.Category) count
FROM categorize c
JOIN product p
ON p.ID=c.product
GROUP BY c.Product
HAVING count>=2;

-- Display Count of products as per below price range:
SELECT Range_in_RS,count(Id) count
FROM 
     (SELECT Id,
            CASE WHEN price BETWEEN 0 AND 50 THEN '0-50'
                WHEN price BETWEEN 51 AND 100 THEN '51-100'
                WHEN price > 100 THEN '>100'
            END AS Range_in_RS
       FROM product) rangeTable
GROUP By Range_in_RS;

-- Display the Categories along with number of products under each category.
-- will require recursive query working on it
-- WITH RECURSIVE category_hierarchy 
-- AS (
--     -- base query
-- 	
--     UNION ALL

--     -- recursive query
-- )
-- SELECT * FROM category_hierarchy;

