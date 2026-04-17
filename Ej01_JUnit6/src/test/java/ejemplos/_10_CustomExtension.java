package ejemplos;

import java.lang.reflect.Method;

import javax.swing.DefaultRowSorter;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestInstanceFactory;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestInstantiationException;

public class _10_CustomExtension implements
					InvocationInterceptor,
					ExecutionCondition,
					TestInstanceFactory,
					TestInstancePostProcessor,
					BeforeAllCallback,
					BeforeEachCallback,
					AfterEachCallback,
					AfterAllCallback					
{

    @Override
    public void interceptTestMethod(
    		Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext
    	) throws Throwable {
    	
    	System.out.println("Interceptando la llamada al test. Antes");
    	
    	try {
    		invocation.proceed();
    	} catch (Throwable t) {
    		//Hacer algo nuestro
    		throw t; 
    	}
    	
    	System.out.println("Interceptando la llamada al test. Despues"); 
    	
    }
	
	@Override
	public void afterAll(ExtensionContext context) throws Exception {		
		System.out.println("Extension afterAll: "+context.getTestClass());	
	}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		System.out.println("Extension beforeEach: "+context.getTestMethod());		
	}
	
	
	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		System.out.println("Extension afterEach: "+context.getTestMethod());		
	}


	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		System.out.println("Extension beforeAll: "+context.getTestClass());		
	}

	@Override
	public Object createTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext extensionContext)
			throws TestInstantiationException {
		System.out.println("Extension createTestInstance");	
		return new _10_Test_Extension();
	}

	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		System.out.println("Extension postProcessTestInstance");
		System.out.println(testInstance);
	}
	
	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
		System.out.println("Extension evaluateExecutionCondition");		
		return ConditionEvaluationResult.enabled("Decidiendo si hay que ejecutar el test o no...true");
	}


}
