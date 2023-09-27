메트릭은 실시간 서버의 대략적인 값과 추세를 확인할 때 사용한다. 문제파악!

프로메테우스 : 메트릭을 수집하고 보관하는 DB역할

그라파나 : 메트릭을 보기 편하게 만들어주는 대시보드

---

#### 프로테메우스

실행파일 실행 후 [http://localhost:9090/](http://localhost:9090/) 접속

레이블 일치 연산자  
\= 제공된 문자열과 정확히 동일한 레이블 선택  
!= 제공된 문자열과 같지 않은 레이블 선택  
\=~ 제공된 문자열과 정규식 일치하는 레이블 선택  
!~ 제공된 문자열과 정규식 일치하지 않는 레이블 선택

---

#### 프로메테우스 범위벡터 활용  
프로메테우스의 메트릭은 게이지/카운터로 나뉜다.

increase() 를 사용하면 카운터 메트릭을 게이지처럼 그래프를 확인할 수 있다.지정한 시간 단위별로 증가를 확인할 수 있다.  
마지막에 \[시간\] 을 사용해서 범위 벡터를 선택해야 한다.

```
increase(http_server_requests_seconds_count{uri="/log"}[1m])
```

rate() : 초당 얼마나 증가하는지 확인 가능함

irate(): 초당 순간증가율 확인 가능, 특정 순간에 요청이 피크쳤을때 확인 가능

---

#### 그라파나 :

그라파나폴더/bin/grafana-server.exe 실행.

http://localhost:3000/ 으로 접속

그라파나 공유 대시보드 활용하기 

[https://grafana.com/grafana/dashboards/](https://grafana.com/grafana/dashboards/)

 [Dashboards | Grafana Labs

Thank you! Your message has been received!

grafana.com](https://grafana.com/grafana/dashboards/)

[https://grafana.com/grafana/dashboards/4701-jvm-micrometer/](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/)

 [JVM (Micrometer) | Grafana Labs

Thank you! Your message has been received!

grafana.com](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/)

[https://grafana.com/grafana/dashboards/11378-justai-system-monitor/](https://grafana.com/grafana/dashboards/11378-justai-system-monitor/)

 [Spring Boot 2.1 System Monitor | Grafana Labs

Thank you! Your message has been received!

grafana.com](https://grafana.com/grafana/dashboards/11378-justai-system-monitor/)
