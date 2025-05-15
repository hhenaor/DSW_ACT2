@echo off
setlocal

cd /d %~dp0
mkdir script_build 2>nul

if not exist script_build\apache-ant-1.10.15 (
    echo ===== Descargando Ant [1/7] ===== 
    if not exist script_build\ant.zip (
        curl -L -o script_build\ant.zip "https://dlcdn.apache.org//ant/binaries/apache-ant-1.10.15-bin.zip"
    )
    echo ===== Extrayendo Ant [2/7] ===== 
    powershell -Command "Expand-Archive -Path 'script_build\ant.zip' -DestinationPath 'script_build'"
) else (
    echo ===== Descargando Ant [1/7] ===== 
    echo ===== Extrayendo Ant [2/7] ===== 
    echo Pasos saltados...
)

if not exist script_build\glassfish7 (
    echo ===== Descargando GlassFish [3/7] ===== 
    if not exist script_build\glassfish.zip (
        curl -L -o script_build\glassfish.zip "https://mirrors.xmission.com/eclipse/ee4j/glassfish/glassfish-7.0.23.zip"
    )
    echo ===== Extrayendo GlassFish [4/7] ===== 
    powershell -Command "Expand-Archive -Path 'script_build\glassfish.zip' -DestinationPath 'script_build'"
) else (
    echo ===== Descargando GlassFish [3/7] ===== 
    echo ===== Extrayendo GlassFish [4/7] ===== 
    echo Pasos saltados...
)

echo ===== Compilando proyecto [5/7] ===== 
set ANT_DIR=script_build\apache-ant-1.10.15\bin
call "%ANT_DIR%\ant.bat"

echo ===== Preparando deploy [6/7] ===== 
set GF_DIR=script_build\glassfish7\glassfish
set WAR_FILE=dist\mpnotes_java.war
set DEPLOY_DIR=%GF_DIR%\domains\domain1\autodeploy
copy /Y "%WAR_FILE%" "%DEPLOY_DIR%\"

if exist "%WAR_FILE%" (
    copy /Y "%WAR_FILE%" "%GF_DIR%\domains\domain1\autodeploy\"
    echo ===== Ejecutando GlassFish [7/7] ===== 
    call "%GF_DIR%\bin\asadmin" start-domain
) else (
    echo ERROR: El archivo WAR no fue generado.
)

endlocal

pause