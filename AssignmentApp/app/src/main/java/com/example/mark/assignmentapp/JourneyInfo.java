package com.example.mark.assignmentapp;



public class JourneyInfo {

    public String journeyStart;
    public String journeyEnd;

    public JourneyInfo() {

    }

    public JourneyInfo(String journeyStart, String getJourneyEnd) {
        this.journeyStart = journeyStart;
        this.journeyEnd = getJourneyEnd;
    }

    public String getJourneyStart() {
        return journeyStart;
    }

    public String getJourneyEnd() {
        return journeyEnd;
    }

    public void setJourneyStart(String journeyStart) {
        this.journeyStart = journeyStart;
    }

    public void setJourneyEnd(String journeyEnd) {
        this.journeyEnd = journeyEnd;
    }
}
