package com.espertech.esper.project;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;


public class Threshold6Statement {

	private EPStatement statement;
	private int intT6 = 3607;
	private int velT6 = 72;
	private int tweetT6 = 51;
	public Threshold6Statement(EPCompiled compiled,Configuration configuration,EPRuntime runtime,int a,int b,int c) {

		intT6 = a;
		velT6 = b;
		tweetT6 = c;
		String stmt1 = "select * from FeaturesEvent "
                + "match_recognize ( "
                + "       measures A.time as ce1, B as ce2, C as ce3"
                + "       pattern (A B C) " 
                + "       define "
                + "               A as (A.velocity < "+velT6+") and (A.intensity > "+intT6+") and (A.tweet_count > "+tweetT6+") and (A.timeofday = 3) and (A.typeofday = 0)," 
                + "               B as (B.velocity < "+velT6+") and (B.intensity > "+intT6+") and (B.tweet_count > "+tweetT6+") and (B.timeofday = 3) and (B.typeofday = 0),"
                + "               C as (C.velocity < "+velT6+") and (C.intensity > "+intT6+") and (C.tweet_count > "+tweetT6+") and (C.timeofday = 3) and (C.typeofday = 0)) ";
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
			System.out.println("Error - not deployed @ Threshold6Statement");
		}
	}

	public void addListener(UpdateListener listener) {
		statement.addListener(listener);
	}
}
