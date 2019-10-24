# spring-data-redis 예제 연습

## issue history
- cannot find method 'value'
    - import문제. spring annotation으로 import를 해야하는데 lombok의 value를 import해서 발생한 문제
    
- Junit5 `@RunWith(SpringRunner.class)` 오류 
    - junit5에선 @RunWith -> @ExtendWith(SpringExtension.class) 으로 변경됨
    - [junit5 extendWith](https://www.baeldung.com/junit-5-runwith)
    - [SpringBoot Junit5로 테스트하기](https://java.ihoney.pe.kr/525)
        - 여기나온 `useJUnitPlatform` 은 별도로 등록 안해도 정상적으로 Junit5기반의 테스트 수행 됨   

- redis가 clustering 되어있으면 connectionFactory 만드는 방법도 변경해야함
    - return new LettuceConnectionFactory(new RedisClusterConfiguration(nodes));

- clustering을 하면 redirection이 발생하는데, 그 부분에서 에러가 난다.
    - `Connection refused: no further information: /127.0.0.1:7001`
    
### 19.10.24 
- profiles 관리문제
    `application-${active}.yml` 과 같이 이름을 관리해야한다.
     안그러면 springBoot에서 applicationProperty를 못찾아서 @Value annotation으로 선언한 인스턴스에 값 할당 안된다.
     > Could not resolve placeholder 'spring.redis.port' in value "${spring.redis.port}"
     
- profile 설정방법
    - testClass에 classLevel로 annotation추가 `@ActiveProfiles("a,b,c...")`
    - 또는, Run > Edit Configurations > VM Options > `-ea` 라고 되어있는 부분 지우고 
    `-Dspring.profiles.active=a` 와 같이 등록 후 run 하면 적용 됨
