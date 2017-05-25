package sspku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import core.ajaxResult.AjaxResult;
import core.controller.BaseController;
import sspku.dao.Job;
import sspku.service.impl.JobService;

@Controller
@RequestMapping("/jobInfo")
public class JobController extends BaseController {

	public JobController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private JobService jobService;

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	/**
	 * 
	 * @param num
	 *            一次展示的岗位数目（默认10）
	 * @param workType
	 *            展示的工作类型（默认是全职工作即编号为2）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getLatestJob", method = RequestMethod.POST)
	public AjaxResult getLatestJob(@RequestParam(value = "num", required = false, defaultValue = "10") Integer num,
			@RequestParam(value = "workType", required = false, defaultValue = "2") Integer workType,
			@RequestParam(value = "city", required = false, defaultValue = "北京") String city) {
		List<Job> jobs = jobService.listLatestJob(num, city, workType);
		return AjaxResult.getOK(jobs);
	}

}
