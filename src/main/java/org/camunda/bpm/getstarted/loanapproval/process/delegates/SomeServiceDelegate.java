package org.camunda.bpm.getstarted.loanapproval.process.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

    @Component("someService")
    public class SomeServiceDelegate implements JavaDelegate {

        @Override
        public void execute(DelegateExecution delegateExecution) throws Exception {
            String pid = delegateExecution.getProcessInstanceId();

            System.out.println("SomeServiceDelegate executed" + pid);

            delegateExecution.setVariable("someVariable", false);
        }
    }
