package com.maidservices.models;

import jakarta.persistence.*;
import jdk.jshell.Snippet;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingid;
    private int userid;
    private int maidid;
    private String bookingdate;
    private String bookingtime;
    private String bookinglocation;
    @Enumerated(EnumType.STRING)
    private Bookingstatus bookingstatus;

}
