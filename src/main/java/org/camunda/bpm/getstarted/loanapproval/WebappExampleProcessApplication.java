/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableProcessApplication
public class WebappExampleProcessApplication {

  public static void main(String... args) {
    ConfigurableApplicationContext context = SpringApplication.run(WebappExampleProcessApplication.class, args);

    DecisionService decisionService = context.getBean(DecisionService.class);

    VariableMap variables = Variables.createVariables()
            .putValue("season", "Spring")
            .putValue("guestCount", 10);

    DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("dish", variables);
    String desiredDish = dishDecisionResult.getSingleEntry();

    System.out.println("Desired dish: " + desiredDish);

    VariableMap variables2 = Variables.createVariables()
            .putValue("age", 30)
            .putValue("waitingTime", 10);
    DmnDecisionTableResult dishDecisionResult2 = decisionService.evaluateDecisionTableByKey("Decision_0eigev3", variables2);

    String desiredDish2 = dishDecisionResult2.getSingleEntry();
    System.out.println("Desired dish: " + desiredDish2);

  }




}