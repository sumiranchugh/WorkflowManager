package com.atlas.processes;

import com.atlas.common.types.Notification;
import com.atlas.processes.entity.Applicant;
import com.atlas.processes.repo.ApplicantRepository;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 */
@Controller
public class PreBimsController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ApplicantRepository applicantRepository;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/startSewadarRegistration", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String startSewadarRegistration(@RequestBody Map<String, String> data) {
        String id = null;
        Applicant applicant = new Applicant(data.get("name"), data.get("email"), data.get("phoneNumber"));
        applicantRepository.save(applicant);

        Map<String, Object> vars = Collections.<String, Object>singletonMap("applicant", applicant);
        try {
            identityService.setAuthenticatedUserId(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            id = runtimeService.startProcessInstanceByKey("PB", vars).getProcessInstanceId();
            return id;
        } finally {
            identityService.setAuthenticatedUserId(runtimeService.getVariable(id, "initiator").toString());

        }
    }


    @RequestMapping("/getNotifications")
    @ResponseBody
    public List<Notification> getNotificationsForUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Notification> notifications = new ArrayList<>();
        List<String> roles = userDetails.getAuthorities().stream().map((p) -> p.getAuthority()).collect(Collectors.toList());

        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroupIn(roles).list();
        tasks.addAll(taskService.createTaskQuery().taskCandidateUser(userDetails.getUsername()).list());
        tasks.forEach((p) -> {
            notifications.add(new Notification(p.getName(), p.getId(), p.getProcessInstanceId(), repositoryService.getProcessDefinition(p.getProcessDefinitionId()).getName()));
        });
        return notifications;
    }
}
