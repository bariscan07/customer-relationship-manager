# customer-relationship-manager
A CRM project to practice Spring MVC, Hibernate and Spring AOP


### Setting up the database
First, execute the query below in your MySQL Workbench to create a user who will become the user of our datasource later on. You can check [this file](WebContent/WEB-INF/spring-mvc-crud-demo-servlet.xml) for the data source configuration. Bean name is `myDataSource`
```sql
CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';

# Following line is for MySql 8.0.4+ users. Exclude it if you are below that version.
ALTER USER 'springstudent'@'localhost' IDENTIFIED WITH mysql_native_password BY 'springstudent';
```

After that, execute the following query to create `customer` table
```sql
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
```

Finally execute this query to fill the `customer` table with initial data.
```sql
INSERT INTO `customer` VALUES 
	(1,'David','Adams','david@luv2code.com'),
	(2,'John','Doe','john@luv2code.com'),
	(3,'Ajay','Rao','ajay@luv2code.com'),
	(4,'Mary','Public','mary@luv2code.com'),
	(5,'Maxwell','Dixon','max@luv2code.com');
```
