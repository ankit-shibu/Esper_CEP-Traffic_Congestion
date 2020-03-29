package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold4Statement {

	private EPStatement statement;
	private int velT4 = 70;
	private int intT4 = 3465;
	private int tweetT4 = 55;
	public Threshold4Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {

		//String stmt2 = "select * from FeaturesEvent";
		intT4 = a;
		velT4 = b;
		tweetT4 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT4+") and (A.intensity > "+intT4+") and (A.tweet_count > "+tweetT4+") and (A.timeofday = 2) and (A.typeofday = 0)," 
                + "               B as (B.velocity < "+velT4+") and (B.intensity > "+intT4+") and (B.tweet_count > "+tweetT4+") and (B.timeofday = 2) and (B.typeofday = 0),"
                + "               C as (C.velocity < "+velT4+") and (C.intensity > "+intT4+") and (C.tweet_count > "+tweetT4+") and (C.timeofday = 2) and (C.typeofday = 0)) ";
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
			System.out.println("Error - not deployed @ Threshold4Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
