package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AnswerSheets;
import com.example.demo.model.Results;
import com.example.demo.model.Students;
import com.example.demo.repositories.ResultsRepository;
import com.example.demo.service.AnswerSheetsService;
import com.example.demo.service.ResultsService;
import com.example.demo.service.StudentsService;

@RestController
@RequestMapping("/Student")
public class Studentcontroller {
	@Autowired
	StudentsService service;

	@Autowired
	AnswerSheetsService ansservice;

	@Autowired
	ResultsService resservice;

	@Autowired
	ResultsRepository resrepo;

	@RequestMapping("/Test")
	public String test() {
		return "working";
	}

	@PutMapping("/EditProfile")
	public ResponseEntity<Students> update(Students obj) {
		service.Editprofile(obj, obj.getStudentid());
		return new ResponseEntity<Students>(obj, HttpStatus.OK);

	}

	@PostMapping("/addresponse")
	public ResponseEntity<AnswerSheets> addAnswer(AnswerSheets obj) {
		ansservice.addResponse(obj);
		return new ResponseEntity<AnswerSheets>(obj, HttpStatus.OK);
	}

	@GetMapping("/displayresults/{id}")
	public ResponseEntity<Results> showResult(@PathVariable(value = "id") Integer id) {
		Results results = resrepo.getById(id);
		if (results != null) {
			return new ResponseEntity<>(results, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}

	}
}
