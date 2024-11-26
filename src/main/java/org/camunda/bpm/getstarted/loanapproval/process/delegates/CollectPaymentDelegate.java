package org.camunda.bpm.getstarted.loanapproval.process.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("collectPayment")
public class CollectPaymentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        makeReceipt();

        delegateExecution.setVariable("isOrderFulfilled", true);
    }

    private void makeReceipt() {
        //TODO: implement
    }
}
