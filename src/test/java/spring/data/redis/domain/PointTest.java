package spring.data.redis.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    private Point point;

    @BeforeEach
    void setUp() {
        point = Point.builder()
                .id("test")
                .amount(1000L)
                .refreshTime(LocalDateTime.of(2019, 10, 18, 0, 0))
                .build();
    }

    @DisplayName("Point 객체 생성")
    @Test
    public void newInstance() {
        //when, then
        assertThat(point).isNotNull();
    }

    @DisplayName("Point 정보 refresh_유지_갱신")
    @ParameterizedTest
    @CsvSource({"500, 17, 23, 59, 59, 1000, 18, 0, 0, 0"
            , "2000, 18, 0, 0, 1, 2000, 18, 0, 0, 1"})
    void refresh(ArgumentsAccessor args) {
        //when
        Long amount = args.getLong(0);
        LocalDateTime refreshTime = LocalDateTime.of(2019, 10, args.getInteger(1), args.getInteger(2), args.getInteger(3), args.getInteger(4));
        point.refresh(amount, refreshTime);

        //then
        Long expectedAmount = args.getLong(5);
        LocalDateTime expectedTime = LocalDateTime.of(2019, 10, args.getInteger(6), args.getInteger(7), args.getInteger(8), args.getInteger(9));
        assertThat(point.getAmount()).isEqualTo(expectedAmount);
        assertThat(point.getRefreshTime()).isEqualTo(expectedTime);
    }
}