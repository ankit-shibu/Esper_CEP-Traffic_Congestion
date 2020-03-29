package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold3Statement {

	private EPStatement statement;
	private int intT3 = 4536;
	private int velT3 = 59;
	private int tweetT3 = 66;
	public Threshold3Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {
		
		intT3 = a;
		velT3 = b;
		tweetT3 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT3+") and (A.intensity > "+intT3+") and (A.tweet_count > "+tweetT3+") and (A.timeofday = 2) and (A.typeofday = 1)," 
                + "               B as (B.velocity < "+velT3+") and (B.intensity > "+intT3+") and (B.tweet_count > "+tweetT3+") and (B.timeofday = 2) and (B.typeofday = 1),"
                + "               C as (C.velocity < "+velT3+") and (C.intensity > "+intT3+") and (C.tweet_count > "+tweetT3+") and (C.timeofday = 2) and (C.typeofday = 1)) ";
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
			System.out.println("Error - not deployed @ Threshold1Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
