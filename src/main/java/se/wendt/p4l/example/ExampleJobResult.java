package se.wendt.p4l.example;

import se.wendt.p4l.JobId;
import se.wendt.p4l.JobResult;

public class ExampleJobResult implements JobResult<String> {

	private static final long serialVersionUID = 1L;
	
	private final JobId jobId;
	private final String result;

	public ExampleJobResult(JobId jobId, String result) {
		this.jobId = jobId;
		this.result = result;
	}
	
	@Override
	public String toString() {
		return ExampleJobResult.class.getSimpleName() + "[" + jobId + "]";
	}

	@Override
	public JobId getJobId() {
		return jobId;
	}

	@Override
	public boolean wasSuccessful() {
		return true;
	}

	@Override
	public String getResult() {
		return result;
	}
	
}
