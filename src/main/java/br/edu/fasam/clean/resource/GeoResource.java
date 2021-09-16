package br.edu.fasam.clean.resource;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Operações relativas ao resource Address", tags = "address")
@RequestMapping(value = "/address", path = "/address")
public class GeoResource {
}
