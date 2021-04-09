package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/receiving_institutions", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Receiving Institution Controller")
public class ReceivingInstitutionController {

}
