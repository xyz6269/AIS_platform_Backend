# AIS Platform Backend

this project aims to build the backend of AIS club at ENSA el-Jadida members space 
platform, this project will be open sourced fully allowing all sort of collaborations
the application will be primarily built in spring boot for the backend

### Configuration:

make sure to run the following SQL insert statements to create the roles and authorities the application would need

```sql
INSERT INTO roles (id, role_name) VALUES (1, 'role_admin');
INSERT INTO roles (id, role_name) VALUES (2, 'role_user');
```

### Run Spring Boot application :

```
mvn spring-boot:run
```
