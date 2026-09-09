package pe.edu.upeu.bomerp;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(BomerpBackendApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }
}
