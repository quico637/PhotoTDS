FROM openjdk:8-jre-alpine
ENV JAVA_HOME=/usr/lib/jvm/default-jvm
ENV PATH=$(JAVA_HOME)/bin/:$PATH
ENV JAVA_OPTIONS=-Djava.awt.headless=true
COPY . /usr/src/myapp 
WORKDIR /usr/src/myapp
EXPOSE 1099:1099
#CMD ["java", "-jar", "ServidorPersistenciaH2.jar"]
