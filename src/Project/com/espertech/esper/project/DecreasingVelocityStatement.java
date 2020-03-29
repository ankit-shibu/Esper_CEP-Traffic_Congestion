package com.espertech.esper.project;

import Project.com.espertech.esper.common.client.configuration.Configuration;
import Project.com.espertech.esper.runtime.client.*;
import Project.com.espertech.esper.common.client.EPCompiled;
import Project.com.espertech.esper.compiler.client.CompilerArguments;
import Project.com.espertech.esper.compiler.client.EPCompilerProvider;


public class DecreasingVelocityStatement {

	private EPStatement statement;

	public DecreasingVelocityStatement(EPCompiled compiled,Configuration configuration,EPRuntime runtime) {

		String stmt1 = "select * from VelocityEvent "
                + "match_recognize ( "
                + "       measures A as vel1, B as vel2, C as vel3, D as vel4 "
                + "       pattern (A B C D) " 
                + "       define "
                + "               A as (A.velocity > B.velocity)"
                + "               B as (B.velocity > C.velocity), "
                + "               C as (D.velocity < C.velocity), "
                + "               D as (A.velocity > (D.velocity * 1.5)+ ";
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
