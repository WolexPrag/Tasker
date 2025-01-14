package com.wsc.tasker;

import java.util.Date;

public class Task {
    protected String _name;
    protected String _description;
    protected Date _created;


    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getDescription() {
        return _description;
    }

    public Date getDateCreated() {
        return _created;
    }
}
