package org.eclipse.leshan.client.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.response.ExecuteResponse;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.util.NamedThreadFactory;

public class MyPicture extends BaseInstanceEnabler {

	private static final int STATE = 2;
	private static final int UPLOAD = 3;
	private static final int TARGET_OID = 1;
	private static final int FILE = 0;
	private final ScheduledExecutorService scheduler;

	public MyPicture() {
		this.scheduler = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Picture"));
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				//System.out.println("Picture...........client...execute");
			}
		}, 2, 2, TimeUnit.SECONDS);
	}

	@Override
	public synchronized ReadResponse read(int resourceId) {
		
	    int state =0;
		switch (resourceId) {
		case FILE:
			return ReadResponse.success(resourceId, "");
		case TARGET_OID:
			return ReadResponse.success(resourceId, "1.2.410.200073.1.1.1.2.100.1");
		case STATE:
			return ReadResponse.success(resourceId, state);
		case UPLOAD:
			return ReadResponse.success(resourceId, "");
		default:
			return super.read(resourceId);
		}
	}

	@Override
	public synchronized ExecuteResponse execute(int resourceId, String params) {
		switch (resourceId) {
		case UPLOAD:
			System.out.println("Picture re-upload request....");
			return ExecuteResponse.success();
		default:
			return super.execute(resourceId, params);
		}
	}

}
