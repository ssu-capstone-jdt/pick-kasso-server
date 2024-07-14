package com.pickkasso.domain.keyword.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodayKeyword {
    private static Date dateTime = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "keyword")
    private List<String> keyword;

    @Builder
    public TodayKeyword(String date, List<String> keyword) {
        this.date = date;
        this.keyword = keyword;
    }

    public static TodayKeyword createTodaykeyword(String date, List<String> keyword) {

        return TodayKeyword.builder().date(date).keyword(keyword).build();
    }

    public void addDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String reDate = simpleDateFormat.format(dateTime);
        this.date = reDate;
        addDateTime();
    }

    public Date addDateTime() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        // 문자열- Date 객체로 변환
        Date date = simpleDateFormat.parse(this.date);

        Calendar cal = Calendar.getInstance();
        // Date 객체, Calendar 객체에 설정
        cal.setTime(date);
        // 원하는 일 수를 더하기
        cal.add(Calendar.DAY_OF_MONTH, 1);

        // 결과 날짜를 포맷 형식에 맞게 변환
        String reDate = simpleDateFormat.format(cal.getTime());
        dateTime = simpleDateFormat.parse(reDate);

        return dateTime;
    }
}
