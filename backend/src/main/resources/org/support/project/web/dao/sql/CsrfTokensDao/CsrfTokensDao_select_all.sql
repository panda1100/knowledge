SELECT * FROM CSRF_TOKENS
WHERE DELETE_FLAG = 0
ORDER BY INSERT_DATETIME %s;
