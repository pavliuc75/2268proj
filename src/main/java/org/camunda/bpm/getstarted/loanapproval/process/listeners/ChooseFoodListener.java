package org.camunda.bpm.getstarted.loanapproval.process.listeners;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("chooseFood")
public class ChooseFoodListener implements TaskListener {
    private TaskService taskService;

    public ChooseFoodListener(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String taskId = delegateTask.getId();

        Map<String, Object> variables = new HashMap<>();
        variables.put("readyToMakeOrder", true);

        taskService.complete(taskId, variables);
    }
}
