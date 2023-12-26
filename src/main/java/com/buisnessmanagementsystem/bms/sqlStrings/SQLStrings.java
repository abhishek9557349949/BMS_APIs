package com.buisnessmanagementsystem.bms.sqlStrings;

public class SQLStrings {

    public static final String loginString = "SELECT CLIENT_NAME, MAIL_ID, FAILED_LOGIN_ATTEMPTS, ACCOUNT_STATUS, BUSINESS_NAME, ROLE, APPROVED_BY_OWNER " +
            "FROM hix_bms_data.bms_login_data " +
            "WHERE USER_NAME = ? AND PASSWORD_HASHCODE = ?";

    public static final String incorrectLogin = "UPDATE hix_bms_data.bms_login_data SET FAILED_LOGIN_ATTEMPTS = FAILED_LOGIN_ATTEMPTS "
            + "+ 1 WHERE USER_NAME = ?;";

    public static final String signupLogin = "INSERT INTO hix_bms_data.bms_login_data (PASSWORD_HASHCODE, USER_NAME, MAIL_ID, "
            + "CLIENT_NAME, FAILED_LOGIN_ATTEMPTS, ACCOUNT_STATUS, BUSINESS_NAME, ROLE, APPROVED_BY_OWNER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'N');";

    public static final String checkValidUser = "SELECT USER_NAME FROM hix_bms_data.bms_login_data where USER_NAME = ?;";

    public static final String getPendingRequest = "SELECT CLIENT_NAME, MAIL_ID, BUSINESS_NAME, CASE WHEN ROLE = 'BMS_OWNER' THEN 'Owner' WHEN ROLE = 'BMS_MANAGER'"
            + " THEN 'Manager' WHEN ROLE = 'BMS_ACCOUNTANT' THEN 'Accountant' WHEN ROLE = 'BMS_EXECUTIVE' THEN 'Executive' ELSE ROLE END AS ROLE FROM hix_bms_data."
            + "bms_login_data WHERE BUSINESS_NAME = ? and APPROVED_BY_OWNER = 'N' and ROLE != 'BMS_OWNER' and ACCOUNT_STATUS = 'Active';";

    public static final String approvePendingRequest = "UPDATE hix_bms_data.bms_login_data SET APPROVED_BY_OWNER = 'Y' WHERE CLIENT_NAME = ?;";

    public static final String getProductDetails = "SELECT PRODUCT_NAME, UNIT_PRICE, STOCK_QUANTITY, EXPIRATION_DATE, DISCOUNT, CASE WHEN STOCK_QUANTITY < 55 THEN 'Buy Now'"
            + " WHEN STOCK_QUANTITY <= 75 THEN 'Order Soon' ELSE 'Sufficient Amount' END AS order_status FROM hix_bms_data.bms_products;";

    public static final String getAllWorkerData = "SELECT USER_NAME, CLIENT_NAME, MAIL_ID, BUSINESS_NAME, CASE WHEN ROLE = 'BMS_OWNER' THEN 'Owner' WHEN ROLE = 'BMS_MANAGER' THEN 'Manager' WHEN ROLE = 'BMS_ACCOUNTANT' THEN "
            + "'Accountant' WHEN ROLE = 'BMS_EXICUTIVE' THEN 'Executive' ELSE ROLE END AS ROLE FROM hix_bms_data.bms_login_data where ROLE != 'BMS_OWNER' and ACCOUNT_STATUS = 'Active';";

    public static final String revokeAccess = "UPDATE hix_bms_data.bms_login_data SET ACCOUNT_STATUS = 'Disabled' WHERE USER_NAME = ?;";

    public static final String getBillsList = "SELECT b.BILL_ID, CONCAT(c.FIRST_NAME, ' ', c.LAST_NAME) AS CUSTOMER_NAME, c.PHONE_NUMBER, b.BILL_DATE, b.TOTAL_AMOUNT, b.DISCOUNT, CONCAT(c.ADDRESS, ' ', c.CITY) AS"
            + " CUSTOMER_ADDRESS FROM bms_bills b JOIN bms_customers c ON b.CUSTOMER_ID = c.CUSTOMER_ID;";

    public static final String getSpecificBillInfo = "SELECT b.BILL_ID, b.BILL_DATE, b.TOTAL_AMOUNT, b.DISCOUNT, CONCAT(c.FIRST_NAME, ' ', c.LAST_NAME) AS CUSTOMER_NAME, c.EMAIL, c.PHONE_NUMBER, c.ADDRESS, p.PRODUCT_NAME,"
            + " p.UNIT_PRICE AS PRODUCT_UNIT_PRICE, bi.QUANTITY, CASE WHEN b.DISCOUNT IS NOT NULL THEN ((bi.UNIT_PRICE * bi.QUANTITY) - (b.DISCOUNT / 100 * (bi.UNIT_PRICE * bi.QUANTITY))) + ((bi.UNIT_PRICE * bi.QUANTITY) * 0.05) "
            + "ELSE (bi.UNIT_PRICE * bi.QUANTITY) + (bi.UNIT_PRICE * bi.QUANTITY * 0.05) END AS TOTAL_PRICE FROM bms_bills b JOIN bms_customers c ON b.CUSTOMER_ID = c.CUSTOMER_ID JOIN bms_bill_items bi ON b.BILL_ID = bi.BILL_ID JOIN "
            + "bms_products p ON bi.PRODUCT_ID = p.PRODUCT_ID WHERE b.BILL_ID = ?;";

    public static final String gstDetails = "SELECT bi.BILL_ID, b.BILL_DATE, p.PRODUCT_NAME, bi.QUANTITY, p.UNIT_PRICE, ROUND(CASE WHEN p.DISCOUNT IS NOT NULL THEN ((p.UNIT_PRICE * bi.QUANTITY) - (p.DISCOUNT / 100 * (p.UNIT_PRICE * "
            + "bi.QUANTITY))) + ((p.UNIT_PRICE * bi.QUANTITY) * 0.05) ELSE (p.UNIT_PRICE * bi.QUANTITY) + (p.UNIT_PRICE * bi.QUANTITY * 0.05) END, 2) AS TOTAL_PRICE, ROUND((CASE WHEN p.DISCOUNT IS NOT NULL THEN ((p.UNIT_PRICE * "
            + "bi.QUANTITY) - (p.DISCOUNT / 100 * (p.UNIT_PRICE * bi.QUANTITY))) + ((p.UNIT_PRICE * bi.QUANTITY) * 0.05) ELSE (p.UNIT_PRICE * bi.QUANTITY) + (p.UNIT_PRICE * bi.QUANTITY * 0.05) END) * 0.05, 2) AS GST FROM bms_bill_items"
            + " bi JOIN bms_products p ON bi.PRODUCT_ID = p.PRODUCT_ID JOIN bms_bills b ON bi.BILL_ID = b.BILL_ID ORDER BY b.BILL_DATE ASC;";


}
