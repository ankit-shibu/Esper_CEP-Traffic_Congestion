package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold5Statement {

	private EPStatement statement;
	private int intT5 = 4161;
	private int velT5 = 59;
	private int tweetT5 = 72;
	public Threshold5Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {

		intT5 = a;
		velT5 = b;
		tweetT5 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT5+") and (A.intensity > "+intT5+") and (A.tweet_count > "+tweetT5+") and (A.timeofday = 3) and (A.typeofday = 1)," 
                + "               B as (B.velocity < "+velT5+") and (B.intensity > "+intT5+") and (B.tweet_count > "+tweetT5+") and (B.timeofday = 3) and (B.typeofday = 1),"
                + "               C as (C.velocity < "+velT5+") and (C.intensity > "+intT5+") and (C.tweet_count > "+tweetT5+") and (C.timeofday = 3) and (C.typeofday = 1)) ";
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
			System.out.println("Error - not deployed @ Threshold5Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
