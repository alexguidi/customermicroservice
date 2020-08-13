sonar:
	chmod +x mvnw && ./mvnw -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.login=${SONAR_TOKEN} -Dsonar.coverage.jacoco.xmlReportPaths=${project.build.directory}/site/jacoco/jacoco.xml 
