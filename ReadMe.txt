1. Project is developed with selenium Java with Cucumber implemented and follow POM Way to design
2. Most of the compenent are defined under common component to increase the reuseability of the code
3. Cucumber is practising BDD and have Gherkin format which is more userfriendly
4. report wise are using cucumber report which can show clearly about which test is executed with tagging



pre-requisite
1. install Apache maven 
2. install java 21
3. cd to the directory  eg. cd "D:\Java-Selenium-with-POM"
4. Execute this command to run test mvn clean test -Dbrowser=chrome -Denv=qat -Dheadless=true 