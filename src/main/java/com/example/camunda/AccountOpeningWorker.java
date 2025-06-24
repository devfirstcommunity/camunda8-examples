package com.example.camunda;

import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class AccountOpeningWorker {

    @JobWorker(type = "validate-data")
    public void validateData(final JobClient client, final ActivatedJob job) {
        System.out.println("Validating customer data...");
        client.newCompleteCommand(job.getKey()).send().join();
    }

    @JobWorker(type = "perform-kyc")
    public void performKYC(final JobClient client, final ActivatedJob job) {
        System.out.println("Calling KYC system...");
        client.newCompleteCommand(job.getKey()).send().join();
    }

    @JobWorker(type = "create-account")
    public void createAccount(final JobClient client, final ActivatedJob job) {
        System.out.println("Creating account in core system...");
        client.newCompleteCommand(job.getKey()).send().join();
    }

    @JobWorker(type = "notify-customer")
    public void notifyCustomer(final JobClient client, final ActivatedJob job) {
        System.out.println("Sending account notification...");
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
