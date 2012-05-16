package se.wendt.p4l.example;

import java.util.Date;

import se.wendt.p4l.ExecutionContext;
import se.wendt.p4l.Job;
import se.wendt.p4l.JobId;
import se.wendt.p4l.impl.JobIdLongImpl;

public class ExampleJob implements Job {

	private static final long serialVersionUID = 1L;
	
	private JobId jobId = new JobIdLongImpl(System.currentTimeMillis());

	@Override
	public JobId getJobId() {
		return jobId;
	}
	
	@Override
	public String toString() {
		return ExampleJob.class.getSimpleName() + "[" + jobId + "]";
	}

	@Override
	public void execute(ExecutionContext executionContext) {
		String format = "This is %s executing in %s, on %s";
		String result = String.format(format, jobId, executionContext.getExecutingWorker(), new Date());
		System.out.println(result);
		executionContext.publishJobResult(new ExampleJobResult(jobId, result));
	}

}
