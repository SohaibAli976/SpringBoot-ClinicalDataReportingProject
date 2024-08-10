package com.iqvia.clinicals.api.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iqvia.clinicals.api.model.ClinicalData;
import com.iqvia.clinicals.api.model.Patient;
import com.iqvia.clinicals.api.repository.ClinicalDataRepository;
import com.iqvia.clinicals.api.repository.PatientRepository;
import com.iqvia.clinicals.api.restcontrollers.dto.ClinicalDataRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalController {

	private ClinicalDataRepository repository;
	private PatientRepository patientRepository;

	@Autowired
	ClinicalController(ClinicalDataRepository repository, PatientRepository patientRepository) {
		this.repository = repository;
		this.patientRepository = patientRepository;
	}

	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest clinicalDataRequest) {
		Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
		ClinicalData data = new ClinicalData();
		data.setComponentName(clinicalDataRequest.getComponentName());
		data.setComponentValue(clinicalDataRequest.getComponentValue());
		data.setPatient(patient);
		return repository.save(data);
	}

	@RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
	public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,
			@PathVariable("componentName") String componentName) {
		return repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
	}

}
