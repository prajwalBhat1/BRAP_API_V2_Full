package com.brap.schedule.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;

public class NetClientPost {

	public static void main(String[] args) {
		/*
		 * String actionType= "create"; switch(actionType){ case "create": int
		 * responseCodeNewJob = new NetClientPost().createJob("NewSuccess");
		 * System.out.println("The response code is: "+responseCodeNewJob); break; case
		 * "build": HttpResponse responseCodeBuildJob = new
		 * NetClientPost().buildJob("NewSuccess");
		 * System.out.println("The response code is: "+responseCodeBuildJob); break; }
		 */

		NetClientPost netClientPost = new NetClientPost();
		netClientPost.testClient();

	}

	public int createJob(String jobName) {
		int responseCode = 00;
		try {

			URL url = new URL("http://localhost:8080?name=" + "tst99");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");
			// the config file could be found in your ~/.jenkins/jobs/JOBNAME

			// folder if you have already configured a job through jenkins UI

			// If you dont' have , I have kept a sample config file content at the end of
			// this post.

			// please have a look and modify accordingly

			String input = "<?xml version='1.0' encoding='UTF-8'?>\r\n"
					+ "<maven2-moduleset plugin='maven-plugin@1.505'>\r\n" + "  <actions/>\r\n"
					+ "  <description>Test Job</description>\r\n" + "  <keepDependencies>false</keepDependencies>\r\n"
					+ "  <properties/>\r\n" + "  <scm class='hudson.scm.NullSCM'/>\r\n"
					+ "  <assignedNode>TestSlave</assignedNode>\r\n" + "  <canRoam>false</canRoam>\r\n"
					+ "  <disabled>true</disabled>\r\n"
					+ "  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>\r\n"
					+ "  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>\r\n"
					+ "  <triggers class='vector'/>\r\n" + "  <concurrentBuild>false</concurrentBuild>\r\n"
					+ "  <aggregatorStyleBuild>true</aggregatorStyleBuild>\r\n"
					+ "  <incrementalBuild>false</incrementalBuild>\r\n" + "  <perModuleEmail>true</perModuleEmail>\r\n"
					+ "  <ignoreUpstremChanges>false</ignoreUpstremChanges>\r\n"
					+ "  <archivingDisabled>false</archivingDisabled>\r\n"
					+ "  <resolveDependencies>false</resolveDependencies>\r\n"
					+ "  <processPlugins>false</processPlugins>\r\n"
					+ "  <mavenValidationLevel>-1</mavenValidationLevel>\r\n" + "  <runHeadless>false</runHeadless>\r\n"
					+ "  <disableTriggerDownstreamProjects>false</disableTriggerDownstreamProjects>\r\n"
					+ "  <settings class='jenkins.mvn.DefaultSettingsProvider'/>\r\n"
					+ "  <globalSettings class='jenkins.mvn.DefaultGlobalSettingsProvider'/>\r\n" + "  <reporters/>\r\n"
					+ "  <publishers/>\r\n" + "  <buildWrappers/>\r\n" + "  <prebuilders/>\r\n"
					+ "  <postbuilders/>\r\n" + "  <runPostStepsIfResult>\r\n" + "    <name>FAILURE</name>\r\n"
					+ "    <ordinal>2</ordinal>\r\n" + "    <color>RED</color>\r\n" + "  </runPostStepsIfResult>\r\n"
					+ "</maven2-moduleset>";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			responseCode = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseCode;
	}

	public HttpResponse buildJob(String jobName) {
		HttpResponse responseCode = null;
		URL url;
		try {

			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet("http://YOURJENKINSURL/job/" + jobName + "/build");
			responseCode = client.execute(get);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseCode;
	}

	public void testClient() {
		try {
			JenkinsServer jenkins = new JenkinsServer(new URI("http://localhost:8080/"), "admin",
					"8887694b02fe4610be2b38a5c3059de6");
			Map<String, Job> jobsMap = jenkins.getJobs();
			// filename is filepath string
			BufferedReader br = new BufferedReader(
					new FileReader(new File("C:\\Users\\prajwbhat\\Desktop\\New folder\\config.xml")));
			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
			// jenkins.createJob("test088", sb.toString(), true);
			jenkins.getJob("test088").build();
			System.out.println(jobsMap.get("test99"));
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
