# Setup
---

## Tomcat
---
- reference: [How To Install Apache Tomcat 10 on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-10-on-ubuntu-20-04)
```bash
tomcat_version=$(curl -s 'https://api.github.com/repos/apache/tomcat/tags' | jq '.[] | select(.name | contains ("M") | not) | .name' | head -1 | sed -e 's/^"//' -e 's/"$//')
wget https://dlcdn.apache.org/tomcat/tomcat-10/v${tomcat_version}/bin/apache-tomcat-${tomcat_version}.tar.gz
sudo tar xzvf apache-tomcat-10*tar.gz -C /opt/tomcat --strip-components=1
```
NOTE: removing double quotes - `sed -e 's/^"//' -e 's/"$//'`

## MySQL
---
Install MySQL
```bash
sudo apt install mysql-server -y
sudo apt install mysql-client -y
```

install as `sudo`
```bash
sudo mysql
```
check validate_password variables
```sql
SHOW VARIABLES LIKE 'validate_password%';
```
```
+--------------------------------------+--------+
| Variable_name                        | Value  |
+--------------------------------------+--------+
| validate_password.check_user_name    | ON     |
| validate_password.dictionary_file    |        |
| validate_password.length             | 8      |
| validate_password.mixed_case_count   | 1      |
| validate_password.number_count       | 1      |
| validate_password.policy             | MEDIUM |
| validate_password.special_char_count | 1      |
+--------------------------------------+--------+
7 rows in set (0.24 sec)
```
set policy low
```sql
SET GLOBAL validate_password.policy=LOW;
```
check if affected
```sql
SHOW VARIABLES LIKE 'validate_password%';
```
```
+--------------------------------------+-------+
| Variable_name                        | Value |
+--------------------------------------+-------+
| validate_password.check_user_name    | ON    |
| validate_password.dictionary_file    |       |
| validate_password.length             | 8     |
| validate_password.mixed_case_count   | 1     |
| validate_password.number_count       | 1     |
| validate_password.policy             | LOW   |
| validate_password.special_char_count | 1     |
+--------------------------------------+-------+
7 rows in set (0.01 sec)
```
search plugins
```sql
select plugin_name, plugin_status from information_schema.plugins where plugin_name like 'validate%';
```
install a plugin
```sql
install plugin validate_password soname 'validate_password.so';
```
uninstall plugin
```sql
uninstall plugin validate_password;
```
list all plugins installed
```sql
select plugin_name, plugin_status from information_schema.plugins;
```
```
+---------------------------------+---------------+
| plugin_name                     | plugin_status |
+---------------------------------+---------------+
| binlog                          | ACTIVE        |
| mysql_native_password           | ACTIVE        |
| sha256_password                 | ACTIVE        |
| caching_sha2_password           | ACTIVE        |
```
set password to admin
```sql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password by 'admin_password';
Query OK, 0 rows affected (0.35 sec)
```
create nonadmin user
```sql
CREATE USER 'manager'@'localhost' IDENTIFIED WITH mysql_native_password BY 'manager_password';
```
if error
```sql
DROP USER 'manager'@'localhost';
```
create database
```sql
CREATE DATABASE employee;
```
list DBs
```sql
show databases;
```
```
+--------------------+
| Database           |
+--------------------+
| employee           |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
```
if error
```sql
DROP DATABASE employees;
```
```sql
USE employees;
```
```sql
CREATE TABLE credential (
    EmpId int NOT NULL PRIMARY KEY,
    PwdHash varchar(255) NOT NULL,
    ExpDate date NOT NULL
);
```
```sql
CREATE TABLE contact (
  EmpId int NOT NULL PRIMARY KEY,
	EmailId varchar(255) NOT NULL,
	PhoneNum varchar(255) NOT NULL,
	Address varchar(255) NOT NULL,
	State varchar(255) NOT NULL,
	Country varchar(255) NOT NULL
);
```
```sql
SHOW TABLES;
```
Grant Privileges
- manual: [GRANT](https://dev.mysql.com/doc/refman/5.7/en/grant.html)
```sql
GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES on employee.credential TO 'manager'@'localhost' WITH GRANT OPTION;
```
```sql
GRANT RELOAD on *.* TO 'manager'@'localhost' WITH GRANT OPTION;
```
NOTE: GLOBAL
```
PROCESS privileges is global privilege. 
we will try to grant it to user tech_user on author database
mysql>grant process on author.* to tech_user;
ERROR 1221 (HY000): Incorrect usage of DB GRANT and GLOBAL PRIVILEGES

here we see it failed with error 1221 (HY000).
to resolve this error we need reconsider this grant statements and its usage.
we must know global privileges can not be granted to individual database, instead it should be on all databases. i.e. use of *.* instead of author.*
so the statement should look like this
grant process on *.* to tech_user;
below are the some of the well know global privileges
FILE
PROCESS
REPLICATION CLIENT
REPLICATION SLAVE
SUPER
```
NOTE: TABLE LEVEL
```
I found out that the EXECUTE privilege could not be run with any table specified, since the "level" is not aiming at table

(About grant Execute)

Enable the user to execute stored routines. Levels: Global, database, routine.

MySql ref manual

I had to run EXECUTE separately with

GRANT EXECUTE ON tablename.* TO 'user123'@'%' IDENTIFIED BY 'pas$-word11';
```
As a final step following any updates to the user privileges, be sure to save the changes by issuing the FLUSH PRIVILEGES command from the mysql prompt.
```sql
FLUSH PRIVILEGES;
```
```bash
mysql -u manager -p
```
```sql
INSERT INTO credential VALUES ("1001", "ABCDEFGH", "2008-7-04");
INSERT INTO credential VALUES ("1002", "ABCDEFGI", "2008-7-05");
```

## JDBC
---
```bash
sudo apt install libmysql-java -y
```

```bash
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.29-1ubuntu20.04_all.deb
dpkg -i mysql-connector-java_8.0.29-1ubuntu20.04_all.deb
```

- download: [Download all versions of mysql-connector-java JAR files with all dependencies](https://jar-download.com/artifacts/mysql/mysql-connector-java)

## Java
---
```bash
javac jdbcMySql.java
java -cp .:mysql-connector-java-8.0.29.jar MysqlConn
```
