package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold2Statement {
	private EPStatement statement;
	private int velT2 = 77;
	private int intT2 = 4687;
	private int tweetT2 = 61;
	public Threshold2Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {
		
		intT2 = a;
		velT2 = b;
		tweetT2 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT2+") and (A.intensity > "+intT2+") and (A.tweet_count > "+tweetT2+") and (A.timeofday = 1) and (A.typeofday = 0)," 
                + "               B as (B.velocity < "+velT2+") and (B.intensity > "+intT2+") and (B.tweet_count > "+tweetT2+") and (B.timeofday = 1) and (B.typeofday = 0),"
                + "               C as (C.velocity < "+velT2+") and (C.intensity > "+intT2+") and (C.tweet_count > "+tweetT2+") and (C.timeofday = 1) and (C.typeofday = 0)) ";
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
			System.out.println("Error - not deployed @ Threshold2Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
