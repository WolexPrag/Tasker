package com.wsc.tasker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.wsc.tasker.core.DateTeTime;



public class MainUnitTest {
    @Test
    public void testDateYear(){
        DateTeTime date1 = new DateTeTime(1999,5,15,10,30,30);
        DateTeTime date2 = new DateTeTime(2000,5,15,10,30,30);
        boolean answer = date1.isBefore(date2);
        assertTrue(answer);
    }
    @Test
    public void testDateMonth(){
        DateTeTime date1 = new DateTeTime(2000,1,15,10,30,30);
        DateTeTime date2 = new DateTeTime(2000,5,15,10,30,30);
        boolean answer = date1.isBefore(date2);
        assertTrue(answer);
    }@Test
    public void testDateDay(){
        DateTeTime date1 = new DateTeTime(2000,5,1,10,30,30);
        DateTeTime date2 = new DateTeTime(2000,5,15,10,30,30);
        boolean answer = date1.isBefore(date2);
        assertTrue(answer);
    }
    @Test
    public void testDateHour(){
        DateTeTime date1 = new DateTeTime(2000,5,15,1,30,30);
        DateTeTime date2 = new DateTeTime(2000,5,15,10,30,30);
        boolean answer = date1.isBefore(date2);
        assertTrue(answer);
    }
    @Test
    public void testDateMinute(){
        DateTeTime date1 = new DateTeTime(2000,5,15,10,1,30);
        DateTeTime date2 = new DateTeTime(2000,5,15,10,30,30);
        boolean answer = date1.isBefore(date2);
        assertTrue(answer);
    }
    @Test
    public void testDateSeconds(){
        DateTeTime date1 = new DateTeTime(2000,5,15,10,30,1);
        DateTeTime date2 = new DateTeTime(2000,5,15,10,30,30);
        boolean answer = date1.isBefore(date2);
       assertTrue(answer);
    }


}
