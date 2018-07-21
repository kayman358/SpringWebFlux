/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.webflux.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Ibrahim.Arowolo
 */
@Data
public class EventModel {
    private final long id;
    private final Date date;

    public EventModel(long id, Date date) {
        this.id = id;
        this.date = date;
    }

   
    
}
