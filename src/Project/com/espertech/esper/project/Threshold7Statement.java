package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold7Statement {

	private EPStatement statement;
	private int intT7 = 2854;
	private int velT7 = 65;
	private int tweetT7 = 41;
	public Threshold7Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {

		intT7 = a;
		velT7 = b;
		tweetT7 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT7+") and (A.intensity > "+intT7+") and (A.tweet_count > "+tweetT7+") and (A.timeofday = 4) and (A.typeofday = 1)," 
                + "               B as (B.velocity < "+velT7+") and (B.intensity > "+intT7+") and (B.tweet_count > "+tweetT7+") and (B.timeofday = 4) and (B.typeofday = 1),"
                + "               C as (C.velocity < "+velT7+") and (C.intensity > "+intT7+") and (C.tweet_count > "+tweetT7+") and (C.timeofday = 4) and (C.typeofday = 1)) ";
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
			System.out.println("Error - not deployed @ Threshold7Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
