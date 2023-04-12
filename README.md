# A simple OpenTelemetry demo
* JVM env example for jaeger:
  ```text
  -javaagent:D:/temp/opentelemetry-javaagent.jar
  -Dfile.encoding=UTF-8
  -Dotel.traces.exporter=jaeger
  -Dotel.service.name=service_1
  -Dserver.port=8081
  -Dotel.instrumentation.http.capture-headers.server.request=x-asc-token
  ```
* JVM env example for prometheus:
  ```text
  -javaagent:D:/temp/opentelemetry-javaagent.jar
  -Dfile.encoding=UTF-8
  -Dotel.metrics.exporter=prometheus
  -Dotel.exporter.prometheus.port=9494
  -Dotel.exporter.prometheus.host=0.0.0.0
  -Dotel.service.name=service_1
  -Dserver.port=8081
  -Dotel.instrumentation.http.capture-headers.server.request=x-asc-token
  ```
* Use [server-2](https://github.com/innocentliny/spring-opentelemetry-server-2) to simulate another service.