package com.sports.common.bean;

import java.util.List;
import java.util.Map;

public class SCPInputView {

	private Map<String, Object> scpUser;
	private List<Map<String, Object>> scpUserTeams;
	private List<Map<String, Object>> scpUserIdeas;
	private List<Map<String, Object>> scpIdeaImages;

	public Map<String, Object>  getScpUser() {
		return scpUser;
	}
	public void setScpUser(Map<String, Object>  scpUser) {
		this.scpUser = scpUser;
	}

    public List<Map<String, Object>>  getScpUserIdeas() {
        return scpUserIdeas;
    }
    public void setScpUserIdeas(List<Map<String, Object>>  scpUserIdeas) {
        this.scpUserIdeas = scpUserIdeas;
    }

    public List<Map<String, Object>>  getScpUserTeams() {
        return scpUserTeams;
    }
    public void setScpUserTeams(List<Map<String, Object>>  scpUserTeams) {
        this.scpUserTeams = scpUserTeams;
    }

    public List<Map<String, Object>>  getScpIdeaImages() {
        return scpIdeaImages;
    }
    public void setScpIdeaImages(List<Map<String, Object>>  scpIdeaImages) {
        this.scpIdeaImages = scpIdeaImages;
    }
}
