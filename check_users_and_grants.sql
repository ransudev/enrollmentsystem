-- Script to check MySQL users and their grants
-- This script should be run by a user with administrative privileges (like root)

-- Show all users
SELECT User, Host FROM mysql.user;

-- Show grants for a specific user (replace 'username' and 'hostname' with actual values)
-- SHOW GRANTS FOR 'username'@'hostname';

-- Show grants for all users (this will generate the commands to check each user)
SELECT CONCAT('SHOW GRANTS FOR ''', User, '''@''', Host, ''';') AS GrantCommand
FROM mysql.user
WHERE User LIKE '%stud%' OR User LIKE '%teacher%' OR User LIKE '%admin%'
ORDER BY User;

-- To check grants for all users, you can use this approach:
-- SELECT User, Host, 
--        (SELECT GROUP_CONCAT(priv SEPARATOR ', ') 
--         FROM (SELECT 'SELECT' as priv UNION SELECT 'INSERT' UNION SELECT 'UPDATE' UNION SELECT 'DELETE') p
--         WHERE EXISTS (SELECT 1 FROM mysql.tables_priv tp 
--                       WHERE tp.User = u.User AND tp.Host = u.Host)) AS Permissions
-- FROM mysql.user u
-- WHERE User LIKE '%stud%' OR User LIKE '%teacher%' OR User LIKE '%admin%';

-- Alternative way to check user privileges
SELECT User, Host, Select_priv, Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv
FROM mysql.user
WHERE User LIKE '%stud%' OR User LIKE '%teacher%' OR User LIKE '%admin%'
ORDER BY User;

-- Check database-specific privileges
SELECT User, Host, Db, Select_priv, Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv
FROM mysql.db
WHERE User LIKE '%stud%' OR User LIKE '%teacher%' OR User LIKE '%admin%'
ORDER BY User, Db;

-- Check table-specific privileges
SELECT User, Host, Db, Table_name, Table_priv
FROM mysql.tables_priv
WHERE User LIKE '%stud%' OR User LIKE '%teacher%' OR User LIKE '%admin%'
ORDER BY User, Db, Table_name;

-- To check specific database access
-- Replace 'your_database_name' with the actual database name
-- SELECT User, Host FROM mysql.db WHERE Db = 'your_database_name';

-- Check current connections
SHOW PROCESSLIST;

-- Check current user
SELECT USER();

-- Check current database
SELECT DATABASE();