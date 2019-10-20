spring-data-redis 예제 연습


# 문제
- cannot find method 'value'
    - import문제. spring annotation으로 import를 해야하는데 lombok의 value를 import 했어
    
- `@RunWith(SpringRunner.class)`
    - junit5에선 @RunWith -> @ExtendWith(SpringExtension.class) 으로 변경됨
    - [junit5 extendWith](https://www.baeldung.com/junit-5-runwith)
    
- [SpringBoot Junit5로 테스트하기](https://java.ihoney.pe.kr/525)
    - 여기나온 `useJUnitPlatform` 은 별도로 등록 안해도 정상적으로 Junit5기반의 테스트 수행 됨    
