
## How To Run The Game
Follow the steps below to build, run, and test the game. Ensure you are in the same directory as your `pom.xml` file.

**Build the Game:**\
To build the project and make the Jar file, run the following command:\
`mvn clean compile`\
<br />
**Run the Game:**\
To execute the game, use the following command:\
`mvn exec:java`\
<br />
**Run Tests:**\
To execute the test suite, use this command:\
`mvn test`\
<br />
**Create Jar File:**\
To create the jar executable, use this command:\
`mvn clean package -DskipTests -Dmaven.javadoc.skip=true`\
*The jar file will be located inside the Artifacts folder*
\
<br />
**Run Jar File:**\
The jar file is located inside the Artifacts folder ,use the following command to execute it:\
`java -jar Artifacts/RabbitRun-1.0.jar`

