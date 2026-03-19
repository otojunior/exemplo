/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * 
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@ActiveProfiles("test")
@Import(TestContainersConfig.class)
public @interface TestContainersIntegrationTest { }
