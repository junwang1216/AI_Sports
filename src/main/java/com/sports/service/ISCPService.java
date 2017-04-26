package com.sports.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2017/4/15.
 */
public interface ISCPService {

    public Integer saveSCPUser(Map<String, Object> scpUser);

    public Integer saveSCPUserTeam(List<Map<String, Object>> scpUserTeams, Integer scpUserId);

    public Integer saveSCPUserIdea(List<Map<String, Object>> scpUserIdeas, Integer scpUserId, HttpServletRequest request) throws Exception;

    public String uploadSCPIdea(MultipartHttpServletRequest multipartRequest) throws IOException;

}
