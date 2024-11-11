-- Display Id, Title, Category Title, Price of the products which are Active (considering stock >0) 
SELECT p.ID,p.Name,c.name category,price
FROM product p 
JOIN catergory c
ON p.Category = c.ID
WHERE stock>0;

-- Display the list of products which don't have any images.
SELECT p.ID,p.Name
FROM product p
WHERE ID NOT IN(
SELECT p.ID
FROM product p 
JOIN image i
ON p.ID = i.Product);

-- Display all Id, Title and Parent Category Title for all the Categories listed, sorted by Parent Category Title and then Category Title. (If Category is top category then Parent Category Title column should display “Top Category” as value.)

SELECT c.ID,c.Name,coalesce(p.Name,'Top category') as Parent_category
FROM catergory c
LEFT JOIN catergory p 
ON c.parentID=p.ID
order by p.Name,c.name;

-- Display Id, Title, Parent Category Title of all the leaf Categories (categories which are not parent of any other category)
-- pls consider that we have two categories named est so mind the id of the category instead

SELECT c.ID,c.Name,coalesce(p.Name,'Top category') as Parent_category
FROM catergory c
LEFT JOIN catergory p 
ON c.parentID=p.ID
WHERE c.ID NOT IN (
SELECT ParentID 
FROM catergory 
where ParentID IS NOT NULL
)
order by p.Name,c.name;

-- Display Product Title, Price & Description which falls into particular category Title (i.e. “Mobile”)

SELECT ID,Name,Price,Description,category
FROM Product
WHERE category In (
SELECT ID category
FROM catergory
WHERE name='est'
);

-- Display the list of Products whose Quantity on hand (Inventory) is under 50 
-- here chekcing for 500 as our data does not have any product below 50 stock
SELECT ID,Name,stock
FROM product 
where Stock<500