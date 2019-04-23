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
		event.getServletContext().log("�������״̬��ʱ���ѹر�");
	}

	public void updateApproveStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();
				int approveresult = 0;
				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("δ���")) {
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
		event.getServletContext().log("�������״̬��ʱ��������");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("�Ѿ���Ӽƻ�����");
	}

	public void updateFundStartStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("������ʼ")) {
						if (projectvo.getFundWillStartLastDate() <= 0) {
							ProjectMgr.getInstance().WhenFundWillStart(
									projectvo);

						}
					}
				}

			}
		};
		timer = new Timer();
		event.getServletContext().log("���¼�����ʼ�ڳﶨʱ��������");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("�Ѿ���Ӽƻ�����");
	}

	public void updateFundWillEndStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("�ڳ���")) {
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
		event.getServletContext().log("�����ڳ��ж�ʱ��������");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("�Ѿ���Ӽƻ�����");
	}

	public void updateFundCompleteStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("�ڳ���")) {
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
		event.getServletContext().log("���³ɹ��ڳﶨʱ��������");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("�Ѿ���Ӽƻ�����");
	}

	public void updateFundEndStatus(ServletContextEvent event) {

		TimerTask task = new TimerTask() {
			public void run() {
				List<ProjectVO> projects = ProjectMgr.getInstance()
						.getProjects();

				for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
					ProjectVO projectvo = it.next();
					if (projectvo.getStatus().trim().equals("��������")) {
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
		event.getServletContext().log("���¼��������ڳﶨʱ��������");
		timer.schedule(task, 0, 60000);
		event.getServletContext().log("�Ѿ���Ӽƻ�����");
	}

}