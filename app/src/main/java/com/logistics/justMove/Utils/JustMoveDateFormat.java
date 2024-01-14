package com.logistics.justMove.Utils;


import com.logistics.justMove.Models.JustMoveTime;
import com.logistics.justMove.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JustMoveDateFormat {

    Calendar calendar;
   List<JustMoveTime> days;

    LocalDateTime today;

   public JustMoveDateFormat(){
        today = LocalDateTime.now();
        calendar = Calendar.getInstance();
        days = new ArrayList<JustMoveTime>();

    }

    public List<JustMoveTime> GetJustMoveDates(){

        int weekDay = today.getDayOfWeek().getValue();
        int currMonth = today.getMonth().getValue();
        int currDay = today.getDayOfMonth();

        String todayValue = currDay +"/"+currMonth;
        int todayBackground =  R.color.lugg_white_dark;

         List<Integer> monthDayRes = this.GetMonthDay(currDay,currMonth);
         currMonth = monthDayRes.get(0);
         currDay = monthDayRes.get(1);

        String tomorrowValue = currMonth +"/"+currDay;
        int tomorrowBackground =  R.color.lugg_white_dark;

        days.add(new JustMoveTime("Today",todayValue,todayBackground));
        days.add(new JustMoveTime("Tomorrow",tomorrowValue,tomorrowBackground));
        weekDay = weekDay+1;
        for(int i=0; i<30; i++){
            monthDayRes = this.GetMonthDay(currDay,currMonth);
            currMonth = monthDayRes.get(0);
            currDay = monthDayRes.get(1);
            weekDay = weekDay+1;
            if(weekDay > 7){
                weekDay = weekDay - 7;
            }

            String textDay = this.GetWeekDayInText(weekDay);
            String textMonth = this.GetMonthInText(currMonth);
            String label = textDay +", "+ textMonth+ " " +currDay;
            String value = currMonth + "/"+currDay;
            int background = R.color.lugg_white_dark;
            days.add(new JustMoveTime(label,value,background));
        }

        return  days;

    }

    private String GetWeekDayInText(int day){

        switch (day){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return String.valueOf(day) +" cannot be resolved to a day";

        }
    }

    private String GetMonthInText(int month){
        switch (month){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            default:
                return "Dec";
        }
    }
    private  List<Integer> GetMonthDay(int day, int month) {
        List <Integer> monthDay = new ArrayList<>();
        switch (month){
            case 2:
                if(day > 28){
                    monthDay.add(3);
                    monthDay.add(1);
                }
                else {
                    monthDay.add(month);
                    monthDay.add(day + 1);
                }
                return monthDay;
            case 4:
            case 6:
            case 9:
            case 11:
                if(day > 29){
                    monthDay.add(month + 1);
                    monthDay.add(1);
                }
                else {
                    monthDay.add(month);
                    monthDay.add(day + 1);
                }
                return monthDay;
            case 12:
                if(day > 30){
                    monthDay.add(1);
                    monthDay.add(1);
                }
                else {
                    monthDay.add(month);
                    monthDay.add(day + 1);
                }
                return monthDay;
            default:
                if(day > 30){
                    monthDay.add(month + 1);
                    monthDay.add(1);
                }
                else {
                    monthDay.add(month);
                    monthDay.add(day + 1);
                }
                return monthDay;
        }
    }
}
