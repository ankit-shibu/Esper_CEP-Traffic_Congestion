package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold8Statement {

	private EPStatement statement;
	private int intT8 = 2474;
	private int velT8 = 83;
	private int tweetT8 = 79;
	public Threshold8Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {

		intT8 = a;
		velT8 = b;
		tweetT8 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT8+") and (A.intensity > "+intT8+") and (A.tweet_count > "+tweetT8+") and (A.timeofday = 4) and (A.typeofday = 0)," 
                + "               B as (B.velocity < "+velT8+") and (B.intensity > "+intT8+") and (B.tweet_count > "+tweetT8+") and (B.timeofday = 4) and (B.typeofday = 0),"
                + "               C as (C.velocity < "+velT8+") and (C.intensity > "+intT8+") and (C.tweet_count > "+tweetT8+") and (C.timeofday = 4) and (C.typeofday = 0)) ";
		try {
			compiled = EPCompilerProvider.getCompiler().compile(stmt1, new CompilerArguments(configuration));
		}catch(Exception e)
		{
			System.out.println(e);
		}
		try {
			statement = runtime.getDeploymentService().deploy(compiled).getStatements()[0];
		}catch(Exception e)
		{
			System.out.println("Error - not deployed @ Threshold8Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
