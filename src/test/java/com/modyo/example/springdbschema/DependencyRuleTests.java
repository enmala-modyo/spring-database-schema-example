package com.modyo.example.springdbschema;

import com.modyo.example.springdbschema.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class DependencyRuleTests {

  private static final String rootPackageName = "com.modyo.example.springdbschema";
  private static final String domainPackage = "domain";
  private static final String applicationPackage = "application";
  private static final String adaptersPackage = "adapters";

  @Test
  void validateRegistrationContextArchitecture() {
    HexagonalArchitecture.boundedContext(rootPackageName)
        .withDomainLayer(domainPackage)
        .withAdaptersLayer(adaptersPackage)
        .incoming("web")
        .outgoing("persistence")
        .outgoing("restclient")
        .and()
        .withApplicationLayer(applicationPackage)
        .services("service")
        .incomingPorts("port.in")
        .outgoingPorts("port.out")
        .and()
        .withConfiguration("configuration")
        .check(new ClassFileImporter()
            .importPackages(rootPackageName));
  }

}
