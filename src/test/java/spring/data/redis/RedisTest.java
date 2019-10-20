package spring.data.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.data.redis.domain.Point;
import spring.data.redis.domain.PointRedisRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private PointRedisRepository pointRedisRepository;

    private String id;
    private Point point;

    @BeforeEach
    void setUp() {
        id = "user";
        LocalDateTime refreshTime = LocalDateTime.of(2019, 10, 20, 11, 0);
        point = Point.builder()
                .id(id)
                .amount(1000L)
                .refreshTime(refreshTime)
                .build();

        pointRedisRepository.save(point);
    }

    @AfterEach
    public void tearDown() throws Exception {
        pointRedisRepository.deleteAll();
    }

    @DisplayName("등록, 확인")
    @Test
    public void register() {
        //then
        Point actual = pointRedisRepository.findById(id).get();
        assertThat(actual.getAmount()).isEqualTo(point.getAmount());
        assertThat(actual.getRefreshTime()).isEqualTo(point.getRefreshTime());
    }

    @DisplayName("수정 확인")
    @Test
    void refresh() {
        //when
        Point expected = pointRedisRepository.findById(id).get();
        expected.refresh(2000L, LocalDateTime.of(2019, 10, 20, 12, 0));
        pointRedisRepository.save(expected);

        //then
        Point actual = pointRedisRepository.findById(id).get();
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getAmount()).isEqualTo(expected.getAmount());
        assertThat(actual.getRefreshTime()).isEqualTo(expected.getRefreshTime());
    }
}
