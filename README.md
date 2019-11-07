## mas-book additional

   '자바 기반 마이크로서비스의 이해와 아키텍처 구축하기'의
   소스 코드(msa-book)를 보완

   1. 최근 버전 '라이브러리' 반영
   2. 'Kafka' -> 'RabbitMQ'
   3. 'eureka', 'zuul' -> 서브모듈로 통합
   4. 'Swagger-ui' 추가
   5. 소스코드 내 'Lombok',라이브러리 제거
   6. '비즈니스 시나리오' 보완 및 설명자료 추가 'msa-book-additional-description.pdf'

## 프로젝트 실행환경

 - RabbitMQ https://www.rabbitmq.com/ 다운로드하여 로컬PC에 설치
   web: http://localhost:15672, id: guest, password: guest
 - Intellij, STS, Eclipse
 - Gradle4.x 이상
 - openJDK1.8.x
 - Springboot 2.x
 - H2 Database
 - JPA
 
## 마이크로서비스

 - 3개의 마이크로서비스 'Member(맴버관리)', 'Order(커피주문)', 'Status(상태확인)'가 존재한다. 
 - 'Member'는 회원정보만 관리(GET/POST/PUT/DELETE)한다.
 - 'Order'는 커피주문을 위한 서비스, 커피주문시(POST) 'userId'를 기준으로 'Member'서비스에 등록된 사용자가 있는지 확인한다.
    만약, 등록된 회원이면 '이름'을 가져온다. 그리고 주문내용은 'Order' 서비스의 DB(H2)와 MQ(rabbit MQ)에 등록된다.
 - 'Status'는 주문확인 서비스이다.
   'Order'서비스에서 등록한 주문내역을 MQ로 부터 수신한다.
 - 외 마이크로서비스들의 이름을 관리하기 위한 이름서비스(Naming)역할을 하는'Eureka'와 분산된 서비스들의 
   진입점/라우팅 역할을 하는 'Zuul'서비스를 포함하고 있다.
 
## 프로젝트 구성의 이해를 돕기 위한 '비즈니스 시나리오'
 
  1. 'Member'서비스를 이용하여 맴버를 등록(POST)한다.
  2. 'Order'서비스를 이용하여 커피를 주문(POST)한다. 
     'Member'서비스가 정상일 때와 장애(Member서비스중지) 상황일 때를 가정하여
     'Order'서비스에 포함된'Feign'라이브러리의 기능동작 원리와
     'Fallback(대체실행)'기능을 확인한다.
  3. 'Status'서비스를 이용하여 주문상태 내역을 조회한다.
    

## 시나리오 실행을 위한 부가적인 설명
  
  - 소스코드 폴더에 포함된 파일 중'msa-book-additional-description.pdf' 참고.
  - 시나리오는 1~6번 까지 번호가 붙어 있고 개략적인 흐름을 도식화하였다.
  - 각 시나리오 별로 구분하여 상세설명을 추가하였다.
