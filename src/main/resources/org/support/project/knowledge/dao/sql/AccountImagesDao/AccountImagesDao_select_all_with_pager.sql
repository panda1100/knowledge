SELECT * FROM ACCOUNT_IMAGES
WHERE DELETE_FLAG = 0
ORDER BY INSERT_DATETIME %s
LIMIT ? OFFSET ?;
