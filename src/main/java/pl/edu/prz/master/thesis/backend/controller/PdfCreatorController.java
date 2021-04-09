package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/pdf", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Pdf Creator Controller")
public class PdfCreatorController {

}
