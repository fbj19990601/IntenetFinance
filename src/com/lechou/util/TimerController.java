package com.lechou.util;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import com.lechou.project.ProjectMgr;
import com.lechou.project.ProjectVO;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class TimerController implements ServletContextListener {
	private java.util.Timer timer = null;

	public void contextInitialized(ServletContextEvent event) {
	
		/*	updateApproveStatus(event);
		 *  updateFundStartStatus(event);
		 * updateFundWillEndStatus(event); updateFundEndStatus(event);updateFundCompleteStatus(event);
		 */
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("更新审核状态定时器已关闭");
	}

	public void updateApproveStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();
				int approveresult = 0;
				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("未审核")) {
						if (projectvo.getApprovelastdate() <= 0) {
							approveresult = ProjectMgr.getInstance()
									.getApproveResult(projectvo);
							if (approveresult == 0) {
								ProjectMgr.getInstance().WhenApproveNoPass(
										projectvo);
							} else {

								ProjectMgr.getInstance().WhenApprovePass(
										projectvo);
							}
						}
					}
				}

			}
		};
		timer = new Timer();
		event.getServletContext().log("更新审核状态定时器已启动");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("已经添加计划任务");
	}

	public void updateFundStartStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("即将开始")) {
						if (projectvo.getFundWillStartLastDate() <= 0) {
							ProjectMgr.getInstance().WhenFundWillStart(
									projectvo);

						}
					}
				}

			}
		};
		timer = new Timer();
		event.getServletContext().log("更新即将开始众筹定时器已启动");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("已经添加计划任务");
	}

	public void updateFundWillEndStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("众筹中")) {
						if (projectvo.getFundlastdate() <= ProjectVO.FUNDWILLENDTDATE
								&& projectvo.getFundlastdate() > 0 && ProjectMgr.getInstance().getCompletedegree(
										projectvo.getPk_project()) < 10000) {
							ProjectMgr.getInstance().WhenFundWillEnd(projectvo);

						}
					}
				}

			}
		};
		timer = new Timer();
		event.getServletContext().log("更新众筹中定时器已启动");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("已经添加计划任务");
	}

	public void updateFundCompleteStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("众筹中")) {
						if (ProjectMgr.getInstance().getCompletedegree(
								projectvo.getPk_project()) >= 10000) {

							ProjectMgr.getInstance().WhenFundEndedWithSuccess(
									projectvo);
						}
					}
				}

			}
		};
		timer = new Timer();
		event.getServletContext().log("更新成功众筹定时器已启动");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("已经添加计划任务");
	}

	public void updateFundEndStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("即将结束")) {
						if (projectvo.getFundlastdate() <= 0) {
							if (ProjectMgr.getInstance().getCompletedegree(
									projectvo.getPk_project()) >= 10000) {
								ProjectMgr.getInstance()
										.WhenFundEndedWithSuccess(projectvo);
							} else {
								ProjectMgr.getInstance().WhenFundEndedWithFail(
										projectvo);
							}

						}
					}
				}

			}
		};
		timer = new Timer();
		event.getServletContext().log("更新即将结束众筹定时器已启动");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("已经添加计划任务");
	}

}