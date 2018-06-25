**************************
*******HOW TO RUN APP*****
**************************
normally ->>mvn spring-boot:run
in debuMode ->>> >mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


How to run this app:
1. In your computer must be installed maven ->https://maven.apache.org/install.html
2. In your computer must be installed IDE i recommend -> https://www.jetbrains.com/idea/download/#section=windows
3. Open project in IDE
4. You must install lombok plugin in IDE, this steps mus be done:
	Go to File > Settings > Plugins
   Click on Browse repositories...
   Search for Lombok Plugin
   Click on Install plugin
   Restart IntelliJ IDEA
 link with images how to do it ->https://stackoverflow.com/questions/41161076/adding-lombok-plugin-to-intellij-project?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
5. Create db in mysql with name -> reservation_tool
6. change db login name and password in this file-> application.properties faile
spring.datasource.username=YOUR_NAME_MUST_BE_HERE
spring.datasource.password=YOUR_PASSWORD_MUST_BE_HERE
7. run this command in Terminal-> mvn clean install
8. run this command in Terminal-> mvn spring-boot:run
9. open browser and insert this url -> http://localhost:8080
