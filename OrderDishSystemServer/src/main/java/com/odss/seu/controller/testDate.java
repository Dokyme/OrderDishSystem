package com.odss.seu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

class tDate{
    interface Summary{}
    @JsonView(Summary.class)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

@RestController
@RequestMapping(value = "/tt")
public class testDate {

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(tDate.Summary.class)
    public tDate rtnDate(@RequestBody tDate date){
        return date;
    }
}
