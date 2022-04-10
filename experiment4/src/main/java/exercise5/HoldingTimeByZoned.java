package exercise5;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param 
 * @return 
 */

public class HoldingTimeByZoned {

    private static final Logger log = LoggerFactory.getLogger(HoldingTimeByZoned.class);
    private ZonedDateTime startingTime; //开始通话时间
    private ZonedDateTime endingTime;   //结束通话时间

    /**
     * @param startingTime
     * @param endingTime
     * @return 
     */

    public HoldingTimeByZoned(ZonedDateTime startingTime, ZonedDateTime endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * @param 
     * @return long
     */

    public long getHoldingTime() {
         long minute = 0;
         long between;
         long fee;

         ZoneId zoneId = startingTime.getZone();
         ZoneRules zoneRules = zoneId.getRules();

         Boolean isDstOfStart = zoneRules.isDaylightSavings(startingTime.toInstant());
         Boolean isDstOfEnd = zoneRules.isDaylightSavings(endingTime.toInstant());

         log.info("start:{},夏令时:{},end:{},夏令时:{}",
                 startingTime.toLocalDateTime(),
                 isDstOfStart,
                 endingTime.toLocalDateTime(),
                 isDstOfEnd
         );

         between = endingTime.toEpochSecond() - startingTime.toEpochSecond();

         if (between < 0)
             minute = between / 60; // 得到通话时长
         else
             minute = (between + 59) / 60; //不到一分钟，算一分钟

         log.info("between:{},minutes:{}", between, minute);

         if (minute > 1800 || minute < 0) {
             throw new RuntimeException("通话时间不正确");
         } else {
             if (minute > 0 && minute <= 20) {
                 fee = (long) (0.05 * minute);
                return fee;}
             else{
                 fee = (long) (2 + 0.1 * (minute - 20));
                 return fee;}

         }
     }

}
