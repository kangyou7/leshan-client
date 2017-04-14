package org.eclipse.leshan.client.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploader implements UploadOperation {

	private static final Logger LOG = LoggerFactory.getLogger(LeshanClientDemo.class);

	Request coapRequest;
	CoapEndpoint coapEndpoint;

	FileUploader(CoapEndpoint coapEndpoint) {
		this.coapRequest = new Request(Code.PUT);
		this.coapEndpoint = coapEndpoint;
	}

	@Override
	public void sendTargetLocation() {

		try {
			coapRequest.setDestination(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		coapRequest.setDestinationPort(5683);
		coapRequest.getOptions().setContentFormat(0);
		coapRequest.getOptions().addUriPath("rd");
		coapRequest.getOptions().addUriPath("1.2.410.200073.1.1.1.2.100.1");
		coapRequest.getOptions().addUriPath("22001");
		coapRequest.getOptions().addUriPath("0");
		coapRequest.getOptions().addUriPath("1");
		coapRequest.setPayload("1.1.1.2.200.3001");

		LOG.info("URI=" + coapRequest.getURI());
		LOG.info("PayLoad=" + coapRequest.getPayloadString());

		// send request
		coapEndpoint.sendRequest(coapRequest);

		// check response
		Response response = null;
		try {
			response = coapRequest.waitForResponse(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("===================================================");
		LOG.info("Target Location response");
		LOG.info("===================================================");
		LOG.info("Response Code =" + response.getCode() + " " + response.getCode().name());
		LOG.info("Target Location =" + response.getPayloadString());
		LOG.info("===================================================");
	}

	@Override
	public void sendUploadFile() {

		byte[] payload = "This is test".getBytes();

		try {
			coapRequest.setDestination(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		coapRequest.setDestinationPort(5683);
		coapRequest.getOptions().setContentFormat(0);
		coapRequest.getOptions().addUriPath("rd");
		coapRequest.getOptions().addUriPath("1.2.410.200073.1.1.1.2.100.1");
		coapRequest.getOptions().addUriPath("22001");
		coapRequest.getOptions().addUriPath("0");
		coapRequest.getOptions().addUriPath("0");
		coapRequest.setPayload(payload);

		LOG.info("URI=" + coapRequest.getURI());
		LOG.info("PayLoad=" + coapRequest.getPayloadString());

		coapEndpoint.sendRequest(coapRequest);

		// check response
		Response response = null;
		try {
			response = coapRequest.waitForResponse(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("===================================================");
		LOG.info("blockwise transfer response");
		LOG.info("===================================================");
		LOG.info("Response Code =" + response.getCode() + " " + response.getCode().name());
		LOG.info("===================================================");
	}

	@Override
	public void sendUploadState(int state) {

		try {
			coapRequest.setDestination(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		coapRequest.setDestinationPort(5683);
		coapRequest.getOptions().setContentFormat(0);
		coapRequest.getOptions().addUriPath("rd");
		coapRequest.getOptions().addUriPath("1.2.410.200073.1.1.1.2.100.1");
		coapRequest.getOptions().addUriPath("22001");
		coapRequest.getOptions().addUriPath("0");
		coapRequest.getOptions().addUriPath("2");
		coapRequest.setPayload(String.valueOf(state));

		LOG.info("URI=" + coapRequest.getURI());
		LOG.info("PayLoad=" + coapRequest.getPayloadString());

		coapEndpoint.sendRequest(coapRequest);

		// check response
		Response response = null;
		try {
			response = coapRequest.waitForResponse(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("===================================================");
		LOG.info("Upload state response");
		LOG.info("===================================================");
		LOG.info("Response Code =" + response.getCode() + " " + response.getCode().name());
		LOG.info("===================================================");

	}
}
