package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/students", produces = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@AllArgsConstructor
@Api(tags = "Student Controller")
public class StudentController {

}
