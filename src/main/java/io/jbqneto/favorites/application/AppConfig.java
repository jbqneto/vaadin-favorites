package io.jbqneto.favorites.application;

import io.jbqneto.favorites.application.infrastructure.entity.CategoryEntity;
import io.jbqneto.favorites.application.infrastructure.repository.port.CategoryRepository;
import io.jbqneto.favorites.application.infrastructure.repository.port.FavoriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
@ComponentScan(basePackages = {"io.jbqneto.favorites.application"} )
@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner runner(CategoryRepository categoryRepository, FavoriteRepository favoriteRepository) {

        final var categoryTech = new CategoryEntity("Technology");

        final List<String> baseCategories = List.of(
                "Religion",
                "Politic",
                "Media",
                "Sport",
                "Business",
                "Finance",
                "Education",
                "Science",
                "Culture"
        );

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());

            if (categoryRepository.count() != 0)
                return;

            var savedCategory = categoryRepository.save(categoryTech);

            baseCategories.forEach((category) -> categoryRepository.save(new CategoryEntity(category)));

            var subCategory = new CategoryEntity("Software Development");
            subCategory.setCategory(savedCategory);

            categoryRepository.save(subCategory);

        };

    }

}
