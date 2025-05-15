
FROM glassfish

LABEL maintainer="hppsrc"

ARG WAR_FILE_NAME=mpnotes_java.war

ARG WAR_FILE_PATH=dist/${WAR_FILE_NAME}

COPY ${WAR_FILE_PATH} ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/

EXPOSE 8080
EXPOSE 4848

CMD ["asadmin", "start-domain", "domain1"]
