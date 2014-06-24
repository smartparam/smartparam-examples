package org.smartparam.examples.simple.range;

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
public class SimpleParameterWithRangeMatcherExample {
    
    public void shouldEvaluateDeadSimpleParameterWithRangeMatcher() {
        // initialize ParamEngine with repository
        ClasspathParamRepository repository = new ClasspathParamRepository(
                "/org/smartparam/examples/simple/range/", ".*\\.param$"
        );

        ParamEngineConfig config = ParamEngineConfigBuilder.paramEngineConfig()
                .withParameterRepositories(repository)
                .build();
        ParamEngine paramEngine = ParamEngineFactory.paramEngine(config);

        // test if mappings work as expected
        assertThat(paramEngine.get("deadSimpleParamWithRange", "A", 1).<String>get()).isEqualTo("outputA");
        assertThat(paramEngine.get("deadSimpleParamWithRange", "B", 6).<String>get()).isEqualTo("outputB");
        
        // test default mapping value
        assertThat(paramEngine.get("deadSimpleParamWithRange", "Z", 123).<String>get()).isEqualTo("outputWhatever");
        
        // check that there is no A input with 6 number defined
        assertThat(paramEngine.get("deadSimpleParamWithRange", "A", 6).isEmpty()).isTrue();
    }
    
}
