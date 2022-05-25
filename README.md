# Shoperu
---
- online shopping website
- based on Java stack completely
- Document profusely
- Future plan is to integrate Spring
- Design should be based on MVC

## Commands
---
```bash
javac -d . src/main/java/*.java
java -cp .:lib/* src.main.classes.JdbcMySql
```

## Resource
---
- [Understanding Java projects structures and how it all works](https://manparvesh.com/post/2017-08-15-understand-java-project-structure/)
- [Java Read XML – Java DOM Parser Example](https://howtodoinjava.com/java/xml/read-xml-dom-parser-example/)

## Potential Repo Structures
---
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
