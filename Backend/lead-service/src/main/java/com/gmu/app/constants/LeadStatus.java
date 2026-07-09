package com.gmu.app.constants;

public class LeadStatus {

    // Status Constants
    public static final String HOT = "HOT";
    public static final String COLD = "COLD";
    public static final String ENQUIRY = "ENQUIRY";
    public static final String ACTIVE = "ACTIVE";
    public static final String INACTIVE = "INACTIVE";

    // User Group Constants
    public static final String TELE_CALLER = "TELE-CALLER";
    public static final String ADMIN = "ADMIN";

    // Private constructor to prevent instantiation
    private LeadStatus() {
        throw new IllegalStateException("Constants class");
    }
}