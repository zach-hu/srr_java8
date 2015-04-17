package com.tsa.puridiom.timer;

public class TestJob extends ScheduledJob {

	public void execute()
	{
		if(ScheduleManager.getInstance().isAllStoped())
		{
			ScheduleManager.getInstance().stopMe();
		}
	}

}
