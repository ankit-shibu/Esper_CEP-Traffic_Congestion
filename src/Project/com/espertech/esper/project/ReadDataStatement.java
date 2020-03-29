package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class ReadDataStatement {

	private EPStatement statement;

	public ReadDataStatement(EPCompiled compiled,Configuration configuration,EPRuntime runtime) {

		String stmt1 = "select * from CombinedEvent";
		try {
			compiled = EPCompilerProvider.getCompiler().compile(stmt1, new CompilerArguments(configuration));
		}catch(Exception e)
		{
			System.out.println("Error - not compiled @ DecreasingVelocityStatement");
		}
		try {
			statement = runtime.getDeploymentService().deploy(compiled).getStatements()[0];
		}catch(Exception e)
		{
			System.out.println("Error - not deployed @ DecreasingVelocityStatement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
