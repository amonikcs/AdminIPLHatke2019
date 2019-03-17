package com.amonicks.adminiplhatke2019;

public class AddTimeTableBeans {
    private String teamName1;
    private String teamName2;
    private String teamLogo1;
    private String teamLogo2;
    private String datetime;
    private String result;

    public AddTimeTableBeans() {
    }

    public AddTimeTableBeans(String teamName1, String teamLogo1, String teamName2, String teamLogo2, String datetime, String result) {
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamLogo1 = teamLogo1;
        this.teamLogo2 = teamLogo2;
        this.datetime = datetime;
        this.result = result;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public String getTeamLogo1() {
        return teamLogo1;
    }

    public void setTeamLogo1(String teamLogo1) {
        this.teamLogo1 = teamLogo1;
    }

    public String getTeamLogo2() {
        return teamLogo2;
    }

    public void setTeamLogo2(String teamLogo2) {
        this.teamLogo2 = teamLogo2;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
