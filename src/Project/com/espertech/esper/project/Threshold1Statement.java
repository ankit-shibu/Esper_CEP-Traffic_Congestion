package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold1Statement {

	private EPStatement statement;
	private int velT1 = 72;
	private int intT1 = 4598;
	private int tweetT1 = 67;
	public Threshold1Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {
		
		intT1 = a;
		velT1 = b;
		tweetT1 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT1+") and (A.intensity > "+intT1+") and (A.tweet_count > "+tweetT1+") and (A.timeofday = 1) and (A.typeofday = 1)," 
                + "               B as (B.velocity < "+velT1+") and (B.intensity > "+intT1+") and (B.tweet_count > "+tweetT1+") and (B.timeofday = 1) and (B.typeofday = 1),"
                + "               C as (C.velocity < "+velT1+") and (C.intensity > "+intT1+") and (C.tweet_count > "+tweetT1+") and (C.timeofday = 1) and (C.typeofday = 1)) ";
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
