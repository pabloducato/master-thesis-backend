package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "User Controller")
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

}
