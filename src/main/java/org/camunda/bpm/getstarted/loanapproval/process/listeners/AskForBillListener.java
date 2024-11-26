package org.camunda.bpm.getstarted.loanapproval.process.listeners;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("askForBill")
public class AskForBillListener implements TaskListener {
    private TaskService taskService;

    public AskForBillListener(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String taskId = delegateTask.getId();

        Map<String, Object> variables = new HashMap<>();
        variables.put("isCashPayment", true); //simulate user input

        taskService.complete(taskId, variables);
    }
}
