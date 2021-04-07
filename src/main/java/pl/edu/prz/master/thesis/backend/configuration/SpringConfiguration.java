package pl.edu.prz.master.thesis.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.edu.prz.master.thesis.backend.repository.BaseRepository;

@Configuration
@EnableJpaRepositories(basePackages = "pl.edu.prz.master.thesis.backend.repository", repositoryBaseClass = BaseRepository.class)
public class SpringConfiguration {

}
