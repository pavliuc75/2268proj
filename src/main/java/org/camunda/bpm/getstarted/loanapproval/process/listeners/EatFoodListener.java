package org.camunda.bpm.getstarted.loanapproval.process.listeners;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("eatFood")
public class EatFoodListener implements TaskListener {
    private TaskService taskService;

    public EatFoodListener(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String taskId = delegateTask.getId();

        Map<String, Object> variables = new HashMap<>();
        variables.put("readyToMakeOrder", true); //simulate user input

        taskService.complete(taskId, variables);
    }
}
