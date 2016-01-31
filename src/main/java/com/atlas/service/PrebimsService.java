package com.atlas.service;

import com.atlas.processes.entity.Applicant;
import com.atlas.processes.repo.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 * @copyright atlas
 */
@Component
public class PrebimsService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public void submitApplication(Applicant applicant){

        applicantRepository.save(applicant);
    }
}
