package com.theboyz.todo_list;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.theboyz.todo_list");

        noClasses()
            .that()
                .resideInAnyPackage("com.theboyz.todo_list.service..")
            .or()
                .resideInAnyPackage("com.theboyz.todo_list.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.theboyz.todo_list.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
