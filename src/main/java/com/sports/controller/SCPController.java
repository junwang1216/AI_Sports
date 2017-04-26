package com.sports.controller;

import com.sports.common.annotation.NotProtected;
import com.sports.common.bean.ResponseBean;
import com.sports.common.bean.SCPInputView;
import com.sports.common.exception.MessageException;
import com.sports.common.util.CustomizedPropertyConfigurer;
import com.sports.common.util.JsonUtils;
import com.sports.service.ISCPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SCPController extends BaseController {

    @Autowired
    private ISCPService iscpService;

    @NotProtected
    @RequestMapping("/")
    public String renderSCPHome() {
        return "SCP/SCP_Index";
    }

    @NotProtected
    @RequestMapping("/scp")
    public String renderSCPDashboard() {
        return "SCP/SCP_Index";
    }

    @NotProtected
    @RequestMapping("/scp/v4")
    public String renderSCPV4() {
        return "SCP/SCP_Index";
    }

    @NotProtected
    @RequestMapping("/scp/v4/success")
    public String renderSCPV4Success() {
        return "SCP/SCP_Success";
    }

    @NotProtected
    @ResponseBody
    @RequestMapping(value = "/scp/submit", method = RequestMethod.POST)
    public ResponseBean submitSCPV4(SCPInputView scpInputView, HttpServletRequest request) {
        try {
            Integer scpUserId = iscpService.saveSCPUser(scpInputView.getScpUser());
            Map<String, Object> data = new HashMap<String, Object>();

            data.put("scpUserId", scpUserId);

            if (scpInputView.getScpUserIdeas() != null) {
                Integer scpUserIdeaId = iscpService.saveSCPUserIdea(scpInputView.getScpUserIdeas(), scpUserId, request);
                data.put("scpUserIdeaId", scpUserIdeaId);
            }

            if (scpInputView.getScpUserTeams() != null) {
                Integer scpUserTeamId = iscpService.saveSCPUserTeam(scpInputView.getScpUserTeams(), scpUserId);
                data.put("scpUserTeamId", scpUserTeamId);
            }

            return new ResponseBean(data);
        } catch (MessageException e) {
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(false);
        }
    }

    @NotProtected
//    @ResponseBody
    @RequestMapping("/scp/upload")
    public void uploadSCPV4(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException {
        ResponseBean responseBean = null;
        try {
            String filePath = iscpService.uploadSCPIdea(multipartRequest);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("error", "success");
            if (filePath.equals("mime")) {
                data.put("error", "Upload .png, .jpg or .gif format");
            }
            if (filePath.equals("size")) {
                data.put("error", "Upload max 2MB per image");
            }
            data.put("filePath", filePath);
            data.put("fileName", filePath.replace(CustomizedPropertyConfigurer.getPhotoPath().toString(), ""));
            responseBean = new ResponseBean(data);
        } catch (MessageException e) {
            e.printStackTrace();
            responseBean = new ResponseBean(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            responseBean = new ResponseBean(false);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JsonUtils.toJson(responseBean));
        writer.flush();
        writer.close();
    }
}
