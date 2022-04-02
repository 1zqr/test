package exercise5;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class HoldingTimeByZonedTest {
    @Test
    @Order(1)
    @DisplayName("开始通话和结束通话时间均在标准时间内,且通话为整数分钟")
    void testBetweenStandardTime() {

        LocalDateTime startingTime = LocalDateTime.of(2022,2,13,12,00,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2022, 2,13,12,20,0);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(20);
    }

    @Test
    @Order(2)
    @DisplayName("开始通话和结束通话时间均在标准时间内,且通话不为整数分钟")
    void testBetweenStandardTime_with_seconds() {

        LocalDateTime startingTime = LocalDateTime.of(2022,2,13,12,00,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2022, 2,13,12,20,30);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(21);
    }

    @Test
    @Order(3)
    @DisplayName("开始通话和结束通话时间均在夏令时内,且通话为整数分钟")
    void testBetweenDaylightTime() {

        LocalDateTime startingTime = LocalDateTime.of(2022,3,13,3,00,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2022, 3,13,3,20,0);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(20);
    }

    @Test
    @Order(4)
    @DisplayName("开始通话在标准时间，结束通话时间在夏令时内,且通话为整数分钟")
    void test_standard_to_DaylightTime() {

        LocalDateTime startingTime = LocalDateTime.of(2022,3,13,1,00,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2022, 3,13,3,30,0);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(90);
    }
}