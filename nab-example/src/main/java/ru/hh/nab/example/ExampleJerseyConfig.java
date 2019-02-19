package ru.hh.nab.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ExampleResource.class, FrontResourse.class, RestResourse.class})
public class ExampleJerseyConfig {
}
