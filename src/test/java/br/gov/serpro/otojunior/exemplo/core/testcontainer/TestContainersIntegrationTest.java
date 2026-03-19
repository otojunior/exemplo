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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@ActiveProfiles("test")
@Import(TestContainersConfig.class)
@Transactional
@Rollback
public @interface TestContainersIntegrationTest { }
