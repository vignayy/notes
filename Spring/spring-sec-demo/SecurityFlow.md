Got it! Here's a rough flow of the **Spring Security Authentication and Authorization Process**. You can visualize or
sketch it as a sequence of steps that represent the flow.

### **Rough Flow Diagram (Steps)**

1. **User Sends Request**
    - A request is made to a protected resource (e.g., a page or an API endpoint).

2. **Intercepted by Spring Security Filter Chain**
    - Spring Security's filter chain intercepts the incoming request.

3. **Authentication Filter (e.g., `UsernamePasswordAuthenticationFilter`)**
    - The filter checks if the request contains authentication information (e.g., username and password).

    - **Decision**: If no authentication is provided, user is redirected to login.
    - **Action**: If authentication details are present, it moves to the next step.

4. **AuthenticationManager**
    - Spring Security's `AuthenticationManager` receives the credentials and processes them.
    - It delegates authentication to an `AuthenticationProvider` (e.g., `DaoAuthenticationProvider`).

5. **AuthenticationProvider**
    - The `AuthenticationProvider` uses a `UserDetailsService` to load the user details (username, password,
      authorities).

    - **Decision**: If the credentials match, it creates an authenticated `Authentication` token.

6. **Authentication Token**
    - If authentication is successful, an `Authentication` object (e.g., `UsernamePasswordAuthenticationToken`) is
      created with the user details and roles.

7. **Store Authentication in SecurityContextHolder**
    - The created `Authentication` token is stored in the `SecurityContextHolder`.
    - This token will be available throughout the request lifecycle.

8. **Authorization Filters Check Permissions**
    - Filters (e.g., `FilterSecurityInterceptor`) check the user's roles/permissions against the requested resource.

    - **Decision**: If the user is authorized, the request continues. If not, a 403 error (Forbidden) is returned.

9. **Response Sent Back to User**
    - If the user is authenticated and authorized, the requested resource is returned.
    - If not authenticated or authorized, an error response (like HTTP 401 or 403) is returned.

---

### **Visual Representation**

```
+------------------------+
| User Sends Request     |  
+------------------------+
          |
          v
+----------------------------+
| Spring Security Filter     | (Intercept request)
+----------------------------+
          |
          v
+------------------------------------+
| Authentication Filter             | (Check credentials)
+------------------------------------+
          |
          v
+-------------------------------+
| AuthenticationManager          | (Process credentials)
+-------------------------------+
          |
          v
+------------------------------+
| AuthenticationProvider        | (Validate credentials)
+------------------------------+
          |
          v
+------------------------------+
| Authentication Token Created  | (Token with user details)
+------------------------------+
          |
          v
+-----------------------------+
| SecurityContextHolder        | (Store token)
+-----------------------------+
          |
          v
+------------------------------+
| Authorization Filters         | (Check user roles/permissions)
+------------------------------+
          |
          v
+----------------------------+
| Response Sent to User      |  (Return resource or error)
+----------------------------+
```

This is the basic **Spring Security flow** in a nutshell. Each box represents a component involved in the process, and
arrows show the flow of data and actions.

I hope this helps you visualize the rough flow! Let me know if you need further details.


---


In the context of Spring Security, the terms **"Check credentials"**, **"Process credentials"**, and **"Validate
credentials"** refer to different parts of the authentication process, and they have specific meanings. Let's break them
down clearly:

### 1. **Check Credentials**:

- **Where**: This typically happens in the **Authentication Filter** (like `UsernamePasswordAuthenticationFilter`).
- **What it does**:
    - The filter looks at the incoming request to check whether it contains authentication information (e.g., username
      and password).
    - If the request contains authentication information (like a form login or basic authentication headers), it
      proceeds with the authentication flow.
    - If authentication credentials are missing or invalid (like missing username/password in the request), the filter
      may return an error or prompt for login.
- **Focus**: It's the initial step where Spring Security checks if credentials exist in the request and, if they do,
  extracts them for processing.

**Example**: In the case of form login, the filter checks if `username` and `password` parameters exist in the HTTP
request.

### 2. **Process Credentials**:

- **Where**: This occurs in the **AuthenticationManager** and is the **core part of Spring Security's authentication
  process**.
- **What it does**:
    - After the authentication filter extracts the credentials (username/password), the `AuthenticationManager` takes
      over.
    - The `AuthenticationManager` **delegates to an `AuthenticationProvider`** to process the credentials.
    - It passes the extracted credentials (e.g., username and password) to the `AuthenticationProvider`, which will then
      check if the credentials are valid.
    - This step may involve additional processing like **password encoding** or **interaction with external systems** (
      e.g., database lookups or LDAP servers).
- **Focus**: This is the stage where the extracted credentials are **sent for validation** (such as querying the
  database to check if the user exists and if their password matches the one stored).

**Example**: After extracting the username and password, the `AuthenticationManager` forwards this information to
an `AuthenticationProvider`, which might query a `UserDetailsService` to fetch the user's stored password and
authorities.

### 3. **Validate Credentials**:

- **Where**: This happens in the **AuthenticationProvider** (usually a `DaoAuthenticationProvider` or
  custom `AuthenticationProvider`).
- **What it does**:
    - The `AuthenticationProvider` validates the credentials (i.e., **checks if the provided username and password match
      the stored credentials**).
    - If the credentials are valid (the password matches what is stored for the user), the provider creates
      an `Authentication` object (e.g., `UsernamePasswordAuthenticationToken`) containing the user's details and
      authorities.
    - **If invalid**, the provider throws an exception (e.g., `BadCredentialsException`).
- **Focus**: This is the critical point where **actual validation of the credentials happens**. It checks the
  correctness of the username and password against the stored values (usually by interacting with a `UserDetailsService`
  or directly with the database).

**Example**: The `DaoAuthenticationProvider` will fetch the user's details from the database using `UserDetailsService`,
compare the stored password hash with the provided password, and decide if the user is authenticated.

---

### **Summary of Differences**:

| **Step**                 | **Where**                                                            | **What Happens**                                                            | **Focus**                                                      |
|--------------------------|----------------------------------------------------------------------|-----------------------------------------------------------------------------|----------------------------------------------------------------|
| **Check Credentials**    | Authentication Filter (e.g., `UsernamePasswordAuthenticationFilter`) | Checks if credentials (username/password) are provided in the request       | Initial step: Extract credentials from the request             |
| **Process Credentials**  | AuthenticationManager                                                | Passes credentials to `AuthenticationProvider` for processing               | Delegates to `AuthenticationProvider` for actual validation    |
| **Validate Credentials** | AuthenticationProvider (e.g., `DaoAuthenticationProvider`)           | Validates the credentials by checking with the data source (e.g., database) | Final validation of credentials, like checking stored password |

---

**Flow in Practice**:

1. **Check Credentials** (in `UsernamePasswordAuthenticationFilter`):
    - The filter checks if the username and password are part of the incoming request.

2. **Process Credentials** (in `AuthenticationManager`):
    - If credentials are found, they are passed to the `AuthenticationManager`, which delegates the responsibility to
      an `AuthenticationProvider`.

3. **Validate Credentials** (in `AuthenticationProvider`):
    - The `AuthenticationProvider` checks the provided credentials (e.g., by comparing the password with the stored one)
      and either validates or rejects them.

This progression ensures that Spring Security securely handles authentication from start to finish.

---
Links

https://docs.spring.io/spring-security/reference/servlet/architecture.html
https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#servlet-authentication-daoauthenticationprovider
---