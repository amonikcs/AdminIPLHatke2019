package com.amonicks.adminiplhatke2019;

public class AddPlayerNameBeans {
    private String playerName;
    private String teamName;
    private String playerLogo;
    private String status;

    public AddPlayerNameBeans() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerLogo() {
        return playerLogo;
    }

    public void setPlayerLogo(String playerLogo) {
        this.playerLogo = playerLogo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddPlayerNameBeans(String playerName, String teamName, String playerLogo, String status) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.playerLogo = playerLogo;
        this.status = status;
    }
}
