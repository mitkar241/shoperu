JC = javac
CLASSNAME = JdbcMySql
LIBLOC = lib
MAINLOC = src/main/java
CLASSLOC = src/main/classes
CLASSPREF = src.main.classes

default:
	echo "Makefile for JAVA"

compile:
	$(JC) -d . $(MAINLOC)/*.java

run:
	java -cp .:$(LIBLOC)/* $(CLASSPREF).$(CLASSNAME)

# RM is a predefined macro in make (RM = rm -f)
clean:
	$(RM) $(CLASSLOC)/*.class
