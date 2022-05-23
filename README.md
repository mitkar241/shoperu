# Shoperu
---
- online shopping website
- based on Java stack completely
- Document profusely
- Future plan is to integrate Spring
- Design should be based on MVC

```
javac -d src/main/classes/ src/main/java/jdbcMySql.java
java -cp .:mysql-connector-java-8.0.29.jar src.main.classes.jdbcmysql.JdbcMySql
```

```
bin (Binaries)
doc (Documents)
inf (Information)
lib (Libraries)
res (Resources)
src (Source)
tst (Test)
```

```
src
|- com.enterprise_name
| |- project_name
| | |- Main.java (the main class)
| | |- model.(here all model classes)
| | |- view.(here all view classes, JFrame, Jdialog,etc)
| | |  |- resources.(here all the files and images used in the views *note)
| | |- controller.(here all controller classes)
| |- lib/(here all the external libraries - dont forget add to build path)

*note if you need some resource file (xml, config file, etc) create a package .resources. in the specific place where do you need (model, controller, view)
```
