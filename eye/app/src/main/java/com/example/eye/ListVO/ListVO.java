package com.example.eye.ListVO;

import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YooJongHyeok on 2017-08-07.
 */

public class ListVO {
    private String name;
    private Integer LeftDate, RightDate, LeftPeriod, RightPeriod;
    public View.OnClickListener onClickListener;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLeftDate() {
        return LeftDate;
    }

    public void setLeftDate(Integer date) {
        LeftDate = date;
    }

    public Integer getRightDate() {
        return RightDate;
    }

    public void setRightDate(Integer date) {
        RightDate = date;
    }

    public Integer getLeftPeriod() {
        return LeftPeriod;
    }

    public void setLeftPeriod(Integer period) {
        LeftPeriod = period;
    }

    public Integer getRightPerid() {
        return RightPeriod;
    }

    public void setRightPeriod(Integer period) {
        RightPeriod = period;
    }

    public String Left() {
        long a;
        Date date = new Date(System.currentTimeMillis());
        String today = sdf.format(date);
        a = doDiffOfDate(today, Integer.toString(LeftDate));
        return "L: " + a + "일째, 앞으로 " + (LeftPeriod - a) + "일";

        //return "left";
    }

    public String Right() {
        long a;
        Date date = new Date(System.currentTimeMillis());
        String today = sdf.format(date);
        a = doDiffOfDate(today, Integer.toString(RightDate));
        return "R: " + a + "일째, 앞으로 " + (RightPeriod - a) + "일";
    }

    public long doDiffOfDate(String start, String end) {
        try{
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);

            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            long diffDay = (startDate.getTime() - endDate.getTime()) / (24*60*60*1000);
            System.out.println(diffDay+"일");
            return diffDay;
        }catch(ParseException e){
            e.printStackTrace();
            return -1;
        }
    }
}