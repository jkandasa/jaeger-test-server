MC_PID=`ps -ef | grep "org.redhat.qe.jaeger.StartServer" | grep -v grep | awk '{ print $2 }'`
if [ ! -z "$MC_PID" ]
then
  kill -15 ${MC_PID}
  echo 'Termination issued for JTS server!'
else
  echo 'JTS server is not running!'
fi
