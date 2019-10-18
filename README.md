# AQA8_UI

#### Алгоритм для подключения Allure

	1) обновить pom.xml
		- добавить dependency
		
		```
		  <dependency>
                <groupId>io.qameta.allure</groupId>
                 <artifactId>allure-testng</artifactId>
                  <version>2.12.0</version>
          </dependency>
        ```
		    
		- добавить plugins
		
		```
		 <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <configuration>
                            <source>1.8</source>
                            <target>1.8</target>
                        </configuration>
                    </plugin>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-surefire-plugin</artifactId>
                        <version>2.20</version>
                        <configuration>
                            <suiteXmlFiles>
                                <suiteXmlFile>TestNG.xml</suiteXmlFile>
                            </suiteXmlFiles>
                            <argLine>
                                -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                            </argLine>
                        </configuration>
                        <dependencies>
                            <dependency>
                                <groupId>org.aspectj</groupId>
                                <artifactId>aspectjweaver</artifactId>
                                <version>${aspectj.version}</version>
                            </dependency>
                        </dependencies>
                    </plugin>
                </plugins>
            </build>
         ```
		
		- добавить properties
		
		```
		 <properties>
                <aspectj.version>1.8.10</aspectj.version>
                <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
            </properties>
        ```
		
		- добавить TestNG.xml в корень проекта (что бы явно указать какие тесты и как запускать, не параллельно)
		```
		<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
        <suite name="Parallel test suite" >
            <test name="Issue">
                <classes>
                    <class name="JIRATest"/>
                </classes>
            </test>
        </suite>
		```

	2) обновить тест
		- добавить @Feature

	3) установить Allure CLI
	    ```
	    https://docs.qameta.io/allure/#_installing_a_commandline
	    ```

    4) запустить тесты 
        ```mvn clean test```

	5) выполнить в корне проекта консольную команду и получить отчет
		allure generate