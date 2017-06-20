# Get user current location
USER_LOCATION=$PWD
ACTUAL_LOCATION=`dirname $0`

# Change the location to where exactly script is located
cd ${ACTUAL_LOCATION}


#Java Heap settings
HEAP_MIN=-Xms4m
HEAP_MAX=-Xmx64m

JAVA_VERSION="1.7"

#configuration file location
CONF_LOG_FILE=../conf/logback.xml

if [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    _java="$JAVA_HOME/bin/java"
elif type -p java; then
    _java=java
else
    echo "java is not installed on this machine"
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "java version: $version"
    if [[ "$version" > "$JAVA_VERSION" ]]; then
        MC_PID=`ps -ef | grep "org.redhat.qe.jaeger.StartServer" | grep -v grep | awk '{ print $2 }'`
        if [ ! -z "$MC_PID" ]
        then
          echo "JTS server is already running on pid[${MC_PID}]"
        else
          $_java ${HEAP_MIN} ${HEAP_MAX} -Dlogback.configurationFile=${CONF_LOG_FILE} -cp "../lib/*" org.redhat.qe.jaeger.StartServer >> ../logs/jts.log 2>&1 &
          echo 'Start issued for JTS'
        fi
    else
      echo "JTS server required java version $JAVA_VERSION or later"
    fi
fi

# back to user location
cd ${USER_LOCATION}
