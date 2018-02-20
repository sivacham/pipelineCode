package com.cicd.pipeline.model;

import java.io.Serializable;

public class PipelineModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String branch;
	private String scmUrl;
	private String serverPort;
	private String developmentServer;
	private String stagingServer;
	private String productionServer;
	private String GitCredentials;
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getScmUrl() {
		return scmUrl;
	}
	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getDevelopmentServer() {
		return developmentServer;
	}
	public void setDevelopmentServer(String developmentServer) {
		this.developmentServer = developmentServer;
	}
	public String getStagingServer() {
		return stagingServer;
	}
	public void setStagingServer(String stagingServer) {
		this.stagingServer = stagingServer;
	}
	public String getProductionServer() {
		return productionServer;
	}
	public void setProductionServer(String productionServer) {
		this.productionServer = productionServer;
	}
	public String getGitCredentials() {
		return GitCredentials;
	}
	public void setGitCredentials(String gitCredentials) {
		GitCredentials = gitCredentials;
	}
}
