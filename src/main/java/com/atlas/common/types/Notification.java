package com.atlas.common.types;

import org.activiti.engine.task.DelegationState;

/**
 * Created by Sumiran Chugh on 1/30/2016.
 */
public class Notification {

    String taskName;

    String taskId;

    String processName;

    String processId;

    DelegationState delegationState;

    public Notification(String taskName, String taskId, String processName, String processId, DelegationState delegationState) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.processName = processName;
        this.processId = processId;
        this.delegationState = delegationState;
    }

    public DelegationState getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(DelegationState delegationState) {
        this.delegationState = delegationState;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
