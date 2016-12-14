# ml-export-import
A java utility to load data page by page from a given collection of a given MarkLogic database to another MarkLogic database in the same server or another server.

#How to configure against your source and target server?
Update the src/main/resources/server.properties file to point to your server. 

#How to run the code?
After updating the property file mentioned above, run the org.sanju.ml.Main class.

#Where is the test case?
Check the TestMigration class to see an example.

#How to update the page size?
See the org.sanju.ml.Reader class PAGE_SIZE constant.