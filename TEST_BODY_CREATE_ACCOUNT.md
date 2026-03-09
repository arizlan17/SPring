# Create Bank Account - Test Body

## Endpoint
`POST /api/bankAccount`

## Valid Test Cases

### Test Case 1: Create Savings Account
**Request Body:**
```json
{
  "nic": "123456789V",
  "initialDeposit": 5000.0,
  "accountType": "SAVINGSACCOUNT",
  "owner": {
    "customerId": 1,
    "nicNumber": "123456789V",
    "customerName": "John Doe",
    "emailAddress": "john.doe@example.com",
    "phoneNumber": "0712345678"
  }
}
```

**Expected Response (201 Created):**
```json
{
  "accountNumber": 1001,
  "accountType": "Savings Account",
  "accountBalance": 5000.0,
  "nic": "123456789V",
  "customerName": "John Doe",
  "createdDate": "2026-03-06T12:30:00"
}
```

---

### Test Case 2: Create Current Account
**Request Body:**
```json
{
  "nic": "987654321X",
  "initialDeposit": 10000.0,
  "accountType": "CURRENTACCOUNT",
  "owner": {
    "customerId": 2,
    "nicNumber": "987654321X",
    "customerName": "Jane Smith",
    "emailAddress": "jane.smith@example.com",
    "phoneNumber": "0787654321"
  }
}
```

**Expected Response (201 Created):**
```json
{
  "accountNumber": 1002,
  "accountType": "Current Accounts",
  "accountBalance": 10000.0,
  "nic": "987654321X",
  "customerName": "Jane Smith",
  "createdDate": "2026-03-06T12:35:00"
}
```

---

### Test Case 3: Create Fixed Deposit Account
**Request Body:**
```json
{
  "nic": "555666777V",
  "initialDeposit": 50000.0,
  "accountType": "FIXEDDEPOSITE",
  "owner": {
    "customerId": 3,
    "nicNumber": "555666777V",
    "customerName": "Robert Johnson",
    "emailAddress": "robert.johnson@example.com",
    "phoneNumber": "0756789012"
  }
}
```

**Expected Response (201 Created):**
```json
{
  "accountNumber": 1003,
  "accountType": "Fixed Deposits",
  "accountBalance": 50000.0,
  "nic": "555666777V",
  "customerName": "Robert Johnson",
  "createdDate": "2026-03-06T12:40:00"
}
```

---

### Test Case 4: Create Investment Account
**Request Body:**
```json
{
  "nic": "888999000X",
  "initialDeposit": 25000.0,
  "accountType": "INVESETMENT",
  "owner": {
    "customerId": 4,
    "nicNumber": "888999000X",
    "customerName": "Emily Brown",
    "emailAddress": "emily.brown@example.com",
    "phoneNumber": "0745678901"
  }
}
```

**Expected Response (201 Created):**
```json
{
  "accountNumber": 1004,
  "accountType": "Investment",
  "accountBalance": 25000.0,
  "nic": "888999000X",
  "customerName": "Emily Brown",
  "createdDate": "2026-03-06T12:45:00"
}
```

---

## Invalid Test Cases (Should Return 400 Bad Request)

### Invalid Test Case 1: Missing NIC
**Request Body:**
```json
{
  "initialDeposit": 5000.0,
  "accountType": "SAVINGSACCOUNT",
  "owner": {
    "customerId": 1,
    "nicNumber": "123456789V",
    "customerName": "John Doe",
    "emailAddress": "john.doe@example.com",
    "phoneNumber": "0712345678"
  }
}
```

**Expected Response (400 Bad Request):**
```json
{
  "path": "/api/bankAccount",
  "details": {
    "nic": "Customer NIC is required"
  },
  "error": "Validation Failed",
  "message": "Input validation failed. See details below.",
  "timestamp": "2026-03-06T12:50:00",
  "status": 400
}
```

---

### Invalid Test Case 2: Missing Initial Deposit
**Request Body:**
```json
{
  "nic": "123456789V",
  "accountType": "SAVINGSACCOUNT",
  "owner": {
    "customerId": 1,
    "nicNumber": "123456789V",
    "customerName": "John Doe",
    "emailAddress": "john.doe@example.com",
    "phoneNumber": "0712345678"
  }
}
```

**Expected Response (400 Bad Request):**
```json
{
  "path": "/api/bankAccount",
  "details": {
    "initialDeposit": "Account balance is required"
  },
  "error": "Validation Failed",
  "message": "Input validation failed. See details below.",
  "timestamp": "2026-03-06T12:55:00",
  "status": 400
}
```

---

### Invalid Test Case 3: Missing Account Type
**Request Body:**
```json
{
  "nic": "123456789V",
  "initialDeposit": 5000.0,
  "owner": {
    "customerId": 1,
    "nicNumber": "123456789V",
    "customerName": "John Doe",
    "emailAddress": "john.doe@example.com",
    "phoneNumber": "0712345678"
  }
}
```

**Expected Response (400 Bad Request):**
```json
{
  "path": "/api/bankAccount",
  "details": {
    "accountType": "Account type is required"
  },
  "error": "Validation Failed",
  "message": "Input validation failed. See details below.",
  "timestamp": "2026-03-06T13:00:00",
  "status": 400
}
```

---

### Invalid Test Case 4: Missing Owner
**Request Body:**
```json
{
  "nic": "123456789V",
  "initialDeposit": 5000.0,
  "accountType": "SAVINGSACCOUNT"
}
```

**Expected Response (400 Bad Request):**
```json
{
  "path": "/api/bankAccount",
  "details": {
    "owner": "Account Owner is required"
  },
  "error": "Validation Failed",
  "message": "Input validation failed. See details below.",
  "timestamp": "2026-03-06T13:05:00",
  "status": 400
}
```

---

### Invalid Test Case 5: Invalid NIC Format
**Request Body:**
```json
{
  "nic": "12345",
  "initialDeposit": 5000.0,
  "accountType": "SAVINGSACCOUNT",
  "owner": {
    "customerId": 1,
    "nicNumber": "12345",
    "customerName": "John Doe",
    "emailAddress": "john.doe@example.com",
    "phoneNumber": "0712345678"
  }
}
```

**Expected Response (400 Bad Request):**
```json
{
  "path": "/api/bankAccount",
  "details": {
    "nic": "Invalid NIC format. Must be 9 digits + V/X or 12 digits."
  },
  "error": "Validation Failed",
  "message": "Input validation failed. See details below.",
  "timestamp": "2026-03-06T13:10:00",
  "status": 400
}
```

---

### Invalid Test Case 6: Negative Initial Deposit
**Request Body:**
```json
{
  "nic": "123456789V",
  "initialDeposit": -5000.0,
  "accountType": "SAVINGSACCOUNT",
  "owner": {
    "customerId": 1,
    "nicNumber": "123456789V",
    "customerName": "John Doe",
    "emailAddress": "john.doe@example.com",
    "phoneNumber": "0712345678"
  }
}
```

**Expected Response (400 Bad Request):**
```json
{
  "path": "/api/bankAccount",
  "details": {
    "initialDeposit": "Initial deposit must be greater than 0"
  },
  "error": "Validation Failed",
  "message": "Input validation failed. See details below.",
  "timestamp": "2026-03-06T13:15:00",
  "status": 400
}
```

---

## Using with Postman/cURL

### cURL Example:
```bash
curl -X POST http://localhost:8080/api/bankAccount \
  -H "Content-Type: application/json" \
  -d '{
    "nic": "123456789V",
    "initialDeposit": 5000.0,
    "accountType": "SAVINGSACCOUNT",
    "owner": {
      "customerId": 1,
      "nicNumber": "123456789V",
      "customerName": "John Doe",
      "emailAddress": "john.doe@example.com",
      "phoneNumber": "0712345678"
    }
  }'
```

### Postman:
1. Set Method: **POST**
2. Set URL: `http://localhost:8080/api/bankAccount`
3. Go to **Headers** tab, add:
   - Key: `Content-Type`
   - Value: `application/json`
4. Go to **Body** tab
5. Select **raw**
6. Select **JSON** from dropdown
7. Paste one of the request bodies above
8. Click **Send**

---

## Account Types (Valid Values)
- `SAVINGSACCOUNT` - Savings Account
- `CURRENTACCOUNT` - Current Accounts
- `FIXEDDEPOSITE` - Fixed Deposits
- `INVESETMENT` - Investment

---

## NIC Format Rules
- Valid format: 9 digits followed by V or X (e.g., `123456789V`, `987654321X`)
- Valid format: 12 digits (e.g., `123456789012`)
- Invalid: Less than 9 digits or wrong suffix
- Invalid: Negative or zero initial deposit

---

## Notes
- Ensure customer with given customerId exists in the database before creating account
- Initial deposit must be greater than 0
- NIC number should match between the request and the owner object
- All fields are required as marked with `@NotNull` or `@NotBlank`

