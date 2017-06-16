# jaeger-test-server
This server used to test Jaeger server

#### Docker image
```bash
docker run -d -p 7000:7000 jkandasa/jaeger-test-server
```

#### Execute test
##### Ping to test server:
GET https://localhost:7000/rest/ping
Response: 200 OK
```json
{
  "hostname" : "jaeger-test-server-1-d1sj0",
  "timestamp" : 1497617332076
}
```
##### Run a test on Jaeger server
POST http://localhost:7000/rest/test/simple
```json
{
  "serviceName":"fromdocker",
  "config":{
    "serverHost": "localhost",
    "serverPort": 80,
    "agentHost": "localhost"
 }
}
```
**Note:** Here `serverHost` and `agentHost` are the Jaeger server/agent hostname or IP address.

Response: 200 OK
```json
{
  "count" : {
    "passed" : 2,
    "failed" : 0,
    "skipped" : 0,
    "configurationFailures" : 0,
    "configurationSkips" : 0
  },
  "configurationFailures" : [ ],
  "configurationSkips" : [ ],
  "passed" : [ {
    "method" : "singleSpanTest",
    "class" : "org.redhat.qe.jaeger.tests.SimpleSpanTest",
    "startMillis" : 1497616538331,
    "endMillis" : 1497616538845,
    "success" : true,
    "parameters" : [ ],
    "status" : 1
  }, {
    "method" : "spanWithChildTest",
    "class" : "org.redhat.qe.jaeger.tests.SimpleSpanTest",
    "startMillis" : 1497616538846,
    "endMillis" : 1497616539800,
    "success" : true,
    "parameters" : [ ],
    "status" : 1
  } ],
  "falied" : [ ],
  "skipped" : [ ]
}
```
