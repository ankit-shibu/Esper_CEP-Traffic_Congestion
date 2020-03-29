package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;

public class IncreasingIntensityStatement {

	private EPStatement statement;

	public IncreasingIntensityStatement(EPCompiled compiled1,Configuration configuration,EPRuntime runtime) {

		String stmt2 = "select * from IntensityEvent "
                + "match_recognize ( "
                + "       measures A as intensity1, B as intensity2, C as intensity3, D as intensity4 "
                + "       pattern (A B C D) " 
                + "       define "
                + "               A as (A.intensity < B.intensity)"
                + "               B as (B.intensity < C.intensity), "
                + "               C as (D.intensity < C.intensity), "
                + "               D as (D.intensity > (A.intensity * 1.5)+ ";

		try {
			compiled1 = EPCompilerProvider.getCompiler().compile(stmt2, new CompilerArguments(configuration));
		}catch(Exception e)
		{
			System.out.println("Error - not compiled");
		}
		try {
			statement = runtime.getDeploymentService().deploy(compiled1).getStatements()[0];
		}catch(Exception e)
		{
			System.out.println("Error - not deployed");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
