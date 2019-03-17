package com.amonicks.adminiplhatke2019;

public class AddTeamNameBeans {
    private String teamLogo;
    private String teamName;

    public AddTeamNameBeans() {

    }

    public AddTeamNameBeans(String teamName,String teamLogo) {
        this.teamLogo = teamLogo;
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
