package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Team implements Cloneable, Serializable {
  private static final long serialVersionUID = 1L;

  private int teamId;
  private String teamName;
  private String teamInfo;
  private String teamArea;
  private int teamAgeId;
  private int teamTypeId;
  private int teamLevelId;
  private int teamSportsId;
  private String teamEmblemPhoto;
  private boolean teamUniform;
  private String teamUniformPhoto;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date teamCreateDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date teamInfoModifieDate;

  private Member member;
  private int memberNo;
  private TeamAges teamAges;
  private TeamLevel teamLevel;
  private TeamMember teamMember;
  private TeamType teamType;
  private TeamTypeSports teamTypeSports;
  private MatchApply matchApply;
  private TopLocation topLocation;
  private MiddleLocation middleLocation;
  private Match match;
  
  @Override
  public String toString() {
    return "Team [teamId=" + teamId + ", teamName=" + teamName + ", teamInfo=" + teamInfo
        + ", teamArea=" + teamArea + ", teamAgeId=" + teamAgeId + ", teamTypeId=" + teamTypeId
        + ", teamLevelId=" + teamLevelId + ", teamSportsId=" + teamSportsId + ", teamEmblemPhoto="
        + teamEmblemPhoto + ", teamUniform=" + teamUniform + ", teamUniformPhoto="
        + teamUniformPhoto + ", teamCreateDate=" + teamCreateDate + ", teamInfoModifieDate="
        + teamInfoModifieDate + ", member=" + member + ", memberNo=" + memberNo + ", teamAges="
        + teamAges + ", teamLevel=" + teamLevel + ", teamMember=" + teamMember + ", teamType="
        + teamType + ", teamTypeSports=" + teamTypeSports + ", matchApply=" + matchApply
        + ", topLocation=" + topLocation + ", middleLocation=" + middleLocation + ", match=" + match
        + "]";
  }
  public int getTeamId() {
    return teamId;
  }
  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }
  public String getTeamName() {
    return teamName;
  }
  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }
  public String getTeamInfo() {
    return teamInfo;
  }
  public void setTeamInfo(String teamInfo) {
    this.teamInfo = teamInfo;
  }
  public String getTeamArea() {
    return teamArea;
  }
  public void setTeamArea(String teamArea) {
    this.teamArea = teamArea;
  }
  public int getTeamAgeId() {
    return teamAgeId;
  }
  public void setTeamAgeId(int teamAgeId) {
    this.teamAgeId = teamAgeId;
  }
  public int getTeamTypeId() {
    return teamTypeId;
  }
  public void setTeamTypeId(int teamTypeId) {
    this.teamTypeId = teamTypeId;
  }
  public int getTeamLevelId() {
    return teamLevelId;
  }
  public void setTeamLevelId(int teamLevelId) {
    this.teamLevelId = teamLevelId;
  }
  public int getTeamSportsId() {
    return teamSportsId;
  }
  public void setTeamSportsId(int teamSportsId) {
    this.teamSportsId = teamSportsId;
  }
  public String getTeamEmblemPhoto() {
    return teamEmblemPhoto;
  }
  public void setTeamEmblemPhoto(String teamEmblemPhoto) {
    this.teamEmblemPhoto = teamEmblemPhoto;
  }
  public boolean isTeamUniform() {
    return teamUniform;
  }
  public void setTeamUniform(boolean teamUniform) {
    this.teamUniform = teamUniform;
  }
  public String getTeamUniformPhoto() {
    return teamUniformPhoto;
  }
  public void setTeamUniformPhoto(String teamUniformPhoto) {
    this.teamUniformPhoto = teamUniformPhoto;
  }
  public Date getTeamCreateDate() {
    return teamCreateDate;
  }
  public void setTeamCreateDate(Date teamCreateDate) {
    this.teamCreateDate = teamCreateDate;
  }
  public Date getTeamInfoModifieDate() {
    return teamInfoModifieDate;
  }
  public void setTeamInfoModifieDate(Date teamInfoModifieDate) {
    this.teamInfoModifieDate = teamInfoModifieDate;
  }
  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  public int getMemberNo() {
    return memberNo;
  }
  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }
  public TeamAges getTeamAges() {
    return teamAges;
  }
  public void setTeamAges(TeamAges teamAges) {
    this.teamAges = teamAges;
  }
  public TeamLevel getTeamLevel() {
    return teamLevel;
  }
  public void setTeamLevel(TeamLevel teamLevel) {
    this.teamLevel = teamLevel;
  }
  public TeamMember getTeamMember() {
    return teamMember;
  }
  public void setTeamMember(TeamMember teamMember) {
    this.teamMember = teamMember;
  }
  public TeamType getTeamType() {
    return teamType;
  }
  public void setTeamType(TeamType teamType) {
    this.teamType = teamType;
  }
  public TeamTypeSports getTeamTypeSports() {
    return teamTypeSports;
  }
  public void setTeamTypeSports(TeamTypeSports teamTypeSports) {
    this.teamTypeSports = teamTypeSports;
  }
  public MatchApply getMatchApply() {
    return matchApply;
  }
  public void setMatchApply(MatchApply matchApply) {
    this.matchApply = matchApply;
  }
  public TopLocation getTopLocation() {
    return topLocation;
  }
  public void setTopLocation(TopLocation topLocation) {
    this.topLocation = topLocation;
  }
  public MiddleLocation getMiddleLocation() {
    return middleLocation;
  }
  public void setMiddleLocation(MiddleLocation middleLocation) {
    this.middleLocation = middleLocation;
  }
  public Match getMatch() {
    return match;
  }
  public void setMatch(Match match) {
    this.match = match;
  }

  
  
 
  
  

}
