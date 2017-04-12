package org.eclipse.leshan.client.demo;

public interface UploadOperation {
	
	public void sendTargetLocation();
	
	public void sendUploadFile();
	
	public void sendUploadState(int state);

}
