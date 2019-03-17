package com.amonicks.adminiplhatke2019;

public class AddMatchResultsBeans {
    private String teamName1;
    private String teamName2;
    private String teamLogo1;
    private String teamLogo2;
    private String team1Score;
    private String team2Score;
    private String team1overs;
    private String team2overs;
    private String dateTime;
    private String results;

    public AddMatchResultsBeans() {
    }

    public AddMatchResultsBeans(String teamName1, String teamLogo1, String teamName2, String teamLogo2, String team1Score, String team2Score, String team1overs, String team2overs, String dateTime, String results) {
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamLogo1 = teamLogo1;
        this.teamLogo2 = teamLogo2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.team1overs = team1overs;
        this.team2overs = team2overs;
        this.dateTime = dateTime;
        this.results = results;
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

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    public String getTeam1overs() {
        return team1overs;
    }

    public void setTeam1overs(String team1overs) {
        this.team1overs = team1overs;
    }

    public String getTeam2overs() {
        return team2overs;
    }

    public void setTeam2overs(String team2overs) {
        this.team2overs = team2overs;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
