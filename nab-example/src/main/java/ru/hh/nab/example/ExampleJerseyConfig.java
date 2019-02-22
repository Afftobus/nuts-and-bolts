package ru.hh.nab.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.example.resource.ExampleResource;
import ru.hh.nab.example.resource.FrontResourse;
import ru.hh.nab.example.resource.RestResourse;

@Configuration
@Import({ExampleResource.class, FrontResourse.class, RestResourse.class, CORSFilter.class})
public class ExampleJerseyConfig {
}
