/*Data needed in order to run the selenuim tests*/

insert into solutionsetdependencytype (HJID, MINVERSION, NAME_) values ("3", "minversion3.0.0", "solutionset3");
insert into solutionsetdependencytype (HJID, MINVERSION, NAME_) values ("5", "minversion5.0.0", "solutionset5");
insert into solutionsetdependencytype (HJID, MINVERSION, NAME_) values ("7", "minversion7.0.0", "solutionset7");
insert into solutionsetdependencytype (HJID, MINVERSION, NAME_) values ("9", "minversion9.0.0", "solutionset9");

insert into solutionsetdependencylisttype (HJID, SOLUTIONSETDEPENDENCY_SOLUTI_0) values ("3", "3" );
insert into solutionsetdependencylisttype (HJID, SOLUTIONSETDEPENDENCY_SOLUTI_0) values ("5", "5" );
insert into solutionsetdependencylisttype (HJID, SOLUTIONSETDEPENDENCY_SOLUTI_0) values ("7", "7" );
insert into solutionsetdependencylisttype (HJID, SOLUTIONSETDEPENDENCY_SOLUTI_0) values ("9", "9" );

insert into solutionset (SOLUTIONSETID, AUTHORITYLEVEL, DESCRIPTION, NAME_, VERSION_, DEPENDENCIES_SOLUTIONSET_HJID) values ("1", "ROLE_DEFAULT", "Name: OSS Monitoring, Dependent on Performance Mgt", "OSS Monitoring", "test", "3");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("2", "Name: OSS Network Explorer, Dependent on nothing", "OSS Network Explorer", "test");
insert into solutionset (SOLUTIONSETID, AUTHORITYLEVEL, DESCRIPTION, NAME_, VERSION_) values ("3", "ROLE_DEFAULT", "Name: Performance Mgt, Dependent on nothing", "Performance Mgt", "test");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_, DEPENDENCIES_SOLUTIONSET_HJID) values ("4", "Name: OSS Common Explorer, Dependent on Admin. Log Management", "OSS Common Explorer", "test", "5");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_, DEPENDENCIES_SOLUTIONSET_HJID) values ("5", "Name: Admin. Log Management, Dependent on Performance Management", "Admin. Log Management", "test", "7");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_, DEPENDENCIES_SOLUTIONSET_HJID) values ("6", "Name: Element Management, Dependent on Performance Management", "Element Management", "test", "7");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_, DEPENDENCIES_SOLUTIONSET_HJID) values ("7", "Name: Performance Management, Dependent on Error Log Management", "Performance Management", "test", "9");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("8", "Name: Number Analysis Mgt., Dependent on nothing", "Number Analysis Mgt.", "test");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_, DEPENDENCIES_SOLUTIONSET_HJID) values ("9", "Name: Error Log Management, Dependent on Performance Mgt", "Error Log Management", "test", "3");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("10", "Name: Monitoring Management, Dependent on nothing", "Monitoring Management", "test");
insert into solutionset (SOLUTIONSETID, AUTHORITYLEVEL, DESCRIPTION, NAME_, VERSION_) values ("18", "ROLE_ADMIN", "Name: Hyperic  Management, Dependent on nothing", "Hyperic  Management", "test");


insert into product (PRODUCTID, NAME_, DESCRIPTION, VERSION_) values ("1", "AT&T", "This is product number 1", "1.0.1");
insert into product (PRODUCTID, NAME_, DESCRIPTION, VERSION_) values ("2", "T-Mobile", "This is product number 2", "1.7.1");


/* these will go in AT&T */
insert into solutionset (SOLUTIONSETID, AUTHORITYLEVEL, DESCRIPTION, NAME_, VERSION_) values ("11", "ROLE_DEFAULT","Name: Alarm Management, Dependent on nothing, part of AT&T", "Alarm Management", "Product");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("12", "Name: FMX Management, Dependent on nothing, part of AT&T", "FMX Management", "Product");

/* these will go in Product 2 */
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("14", "Name: IP Configuration Management, Dependent on nothing, part of Product 2", "IP Configuration Management", "Product");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("15", "Name: Element Network Management, Dependent on nothing, part of Product 2", "Element Network Management", "Product");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("16", "Name: Configuration Management, Dependent on nothing, part of Product 2", "Configuration Management", "Product");
insert into solutionset (SOLUTIONSETID, DESCRIPTION, NAME_, VERSION_) values ("17", "Name: CIF Management, Dependent on nothing, part of Product 2", "CIF Management", "Product");

/*  add solutionset to product. (HJID, SOLUTIONSET_ID, PRODUCT_ID)  */
insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("1", "11", "1");
insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("2", "12", "1");

insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("3", "14", "2");
insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("4", "15", "2");
insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("5", "16", "2");
insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("6", "17", "2");

insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("7", "3", "1");
/* put solutionset "7" in two products */ 
insert into  productsolutionsetassociation (HJID, SOLUTIONSET_PRODUCTSOLUTIONS_0, PRODUCTSOLUTIONSETASSOCIATIO_1) values ("8", "3", "2");





