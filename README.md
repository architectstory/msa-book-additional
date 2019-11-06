#mas-book additional

#설치환경
 - RabbitMQ https://www.rabbitmq.com/ 다운로드하여 로컬PC에 설치
   webpage: http://localhost:15672, id: guest, password: guest 설정
 - Gradle4.x 이상
 - openJDK1.8.x
 - Springboot 2.x
 - H2 Database
 - JPA
 
#마이크로서비스
 - 3개의 마이크로서비스 'Member(맴버관리)', 'Order(커피주문)', 'Status(상태확인)'가 존재한다. 
 - 'Member'는 회원정보만 관리(GET/POST/PUT/DELETE)한다.
 - 'Order'는 커피주문을 위한 서비스, 커피주문시(POST) 'userId'를 기준으로 'Member'서비스에 등록된 사용자가 있는지 확인한다.
    만약, 등록된 회원이면 '이름'을 가져온다. 그리고 주문내용은 
 - 'Status'는 주문확인 서비스이다.   
 - 이외 이름서비스를 위한 'Eureka', 서비스 게이트웨이를 용도의 'Zuul' 등의 서비스를 포함하고 있다.
 
#마이크로서비스 시나리오 
  1. 맴버를 등록한다.
  2. 커피를 주문한다. (맴버 서비스가 정상/장애 상황을 만들어 확인) 
  3. 주문 상태를 확인한다.

#마이크로서비스 시나리오 상세
  소스코드 폴더에 msa-book-additional-description.pdf 참고.
