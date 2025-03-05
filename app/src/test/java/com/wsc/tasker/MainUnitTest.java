package com.wsc.tasker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.wsc.tasker.core.DateTeTime;
import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;



public class MainUnitTest {
    @Test
    public void testDate(){
        DateTeTime date1 = DateTeTime.now();
        DateTeTime date2 = DateTeTime.now();
        boolean answer = date1.isAfter(date2);
        assertFalse(answer);

    }
    @Test
    public void testTask(){
        Notifier.Subscriber sub;

    }
}
