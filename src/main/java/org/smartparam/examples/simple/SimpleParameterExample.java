package org.smartparam.examples.simple;

import static org.assertj.core.api.Assertions.assertThat;
import org.smartparam.engine.config.ParamEngineConfig;
import org.smartparam.engine.config.ParamEngineConfigBuilder;
import org.smartparam.engine.config.ParamEngineFactory;
import org.smartparam.engine.core.ParamEngine;
import org.smartparam.repository.fs.ClasspathParamRepository;
import org.testng.annotations.Test;

/**
 *
 * @author adam.dubiel
 */
@Test
public class SimpleParameterExample {

    public void shouldEvaluateDeadSimpleParameter() {
        // initialize ParamEngine with repository
        ClasspathParamRepository repository = new ClasspathParamRepository(
                "/org/smartparam/examples/simple/", ".*\\.param$"
        );

        ParamEngineConfig config = ParamEngineConfigBuilder.paramEngineConfig()
                .withParameterRepositories(repository)
                .build();
        ParamEngine paramEngine = ParamEngineFactory.paramEngine(config);

        // test if mappings work as expected
        assertThat(paramEngine.get("deadSimpleParam", "A").<String>get()).isEqualTo("outputA");
        assertThat(paramEngine.get("deadSimpleParam", "B").<String>get()).isEqualTo("outputB");
        
        // test default mapping value
        assertThat(paramEngine.get("deadSimpleParam", "Z").<String>get()).isEqualTo("outputWhatever");
    }

}
