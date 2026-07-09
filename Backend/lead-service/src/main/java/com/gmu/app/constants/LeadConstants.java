package com.gmu.app.constants;

public final class LeadConstants {

    public static final String STATUS_ENQUIRY = "ENQUIRY";

    public static final String OPINION_INTERESTED = "Interested";
    public static final String OPINION_ENTRANCE_EXAM = "Entrance Exam";
    public static final String OPINION_NOT_DECIDED = "Not Decided";
    public static final String OPINION_NOT_INTERESTED = "Not interested";
    public static final String OPINION_NOT_RECEIVED = "Not Received";
    public static final String OPINION_WRONG_NUMBER = "Wrong number";
    public static final String OPINION_ALREADY_ADMITTED = "Already admitted";

    public static final String UNIVERSITY_ENGINEERING = "Engineering";
    public static final String UNIVERSITY_PHARMACY = "Pharmacy";
    public static final String UNIVERSITY_GMU = "GMU";

    public static final String ROLE_TELE_CALLER = "TELE-CALLER";
    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ACCOUNT_ACTIVE = "ACTIVE";
    public static final String ACCOUNT_INACTIVE = "INACTIVE";

    public static final String AUDIT_ADD = "add";
    public static final String AUDIT_EDIT = "edit";
    public static final String AUDIT_DELETE = "delete";

    public static final String DEFAULT_ACADEMIC_YEAR = "2025-26";
    public static final String DEFAULT_SOURCE = "Agency";

    private LeadConstants() {
        throw new IllegalStateException("Constants class — cannot be instantiated");
    }
}