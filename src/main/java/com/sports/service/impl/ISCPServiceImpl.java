package com.sports.service.impl;

import com.sports.common.po.SCPUser;
import com.sports.common.po.SCPUserIdea;
import com.sports.common.po.SCPUserTeam;
import com.sports.common.util.CustomizedPropertyConfigurer;
import com.sports.common.util.FileUtil;
import com.sports.common.util.StrUtil;
import com.sports.dao.IBaseDao;
import com.sports.service.ISCPService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ISCPServiceImpl extends BaseService implements ISCPService {

    @Autowired
    private IBaseDao baseDao;

    /***
     * save scp user
     * @param scpUser
     * @return
     */
    @Override
    public Integer saveSCPUser(Map<String, Object> scpUser) {

        SCPUser scpUser1 = new SCPUser();
        scpUser1.setName(StrUtil.objToStr(scpUser.get("name")));
        scpUser1.setGender(StrUtil.objToStr(scpUser.get("gender")));
        scpUser1.setDivision(StrUtil.objToStr(scpUser.get("division")));
        scpUser1.setEntity(StrUtil.objToStr(scpUser.get("entity")));
        scpUser1.setNationality(StrUtil.objToStr(scpUser.get("nationality")));

        baseDao.save(scpUser1, null);
        return scpUser1.getId();
    }

    /***
     * save scp user team
     * @param scpUserTeams
     * @param scpUserId
     * @return
     */
    @Override
    public Integer saveSCPUserTeam(List<Map<String, Object>> scpUserTeams, Integer scpUserId) {

        int teamId = 0;

        for(Map<String, Object> map : scpUserTeams) {
            SCPUserTeam scpUserTeam = new SCPUserTeam();
            scpUserTeam.setUser_id(scpUserId);
            scpUserTeam.setTeam_id(StrUtil.objToInt(map.get("team_id")));
            scpUserTeam.setUser_team(StrUtil.objToStr(map.get("user_team")));

            baseDao.save(scpUserTeam, null);
            teamId = scpUserTeam.getId();
        }

        return teamId;
    }

    private void moveFiles(String paths, String childPath, String basePath) throws Exception {
        try {
            String sourcePath = CustomizedPropertyConfigurer.getPhotoPath().toString();
            String targetPath = sourcePath.concat(childPath).concat("/");
            File file = new File(basePath + targetPath);

            if(!file.exists()){
                file.mkdirs();
            }

            String[] arr = StrUtil.split(paths, ",");
            for (int i = 0; i < arr.length; i++) {

                String path = arr[i];
                File sourceFile = new File(basePath + path);

                if (sourceFile.isFile()) {
                    FileUtils.copyFileToDirectory(sourceFile, file, true);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * save scp user idea
     * @param scpUserIdeas
     * @param scpUserId
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Integer saveSCPUserIdea(List<Map<String, Object>> scpUserIdeas, Integer scpUserId, HttpServletRequest request) throws Exception {

        int ideaId = 0;
        String flag = "u";

        for(Map<String, Object> map : scpUserIdeas) {
            SCPUserIdea scpUserIdea = new SCPUserIdea();
            int ideaTmpId = StrUtil.objToInt(map.get("idea_id"));
            String userTmpIdea = StrUtil.objToStr(map.get("user_idea"));
            String userIdea = userTmpIdea;

            scpUserIdea.setUser_id(scpUserId);
            scpUserIdea.setIdea_id(ideaTmpId);

            flag = flag + scpUserId;
            String sourcePath = CustomizedPropertyConfigurer.getPhotoPath().toString();
            if (userIdea.contains(sourcePath)) {
                moveFiles(userTmpIdea, flag, request.getSession().getServletContext().getRealPath(""));

                String targetPath = sourcePath.concat(flag).concat("/");
                userIdea = userTmpIdea.replaceAll(sourcePath, targetPath);
            }
            scpUserIdea.setUser_idea(userIdea);

            baseDao.save(scpUserIdea, null);
            ideaId = scpUserIdea.getId();
            flag = "u";
        }

        return ideaId;
    }

    @Override
    public String uploadSCPIdea(MultipartHttpServletRequest multipartRequest) throws IOException {
        String str = null;

        List<MultipartFile> multipartFiles = FileUtil.getMultipartFiles(multipartRequest);
        if(multipartFiles.size() > 0){ //保存
            str = FileUtil.saveFile(multipartFiles.get(0), multipartRequest).toString();
        }
        return str;
    }

}
