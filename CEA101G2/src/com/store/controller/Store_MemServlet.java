
package com.store.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.store.model.Store_MemVO;
import com.store.model.Store_MemService;
import com.store.model.Store_MemVO;
@MultipartConfig
/**@WebServlet(name="Food_ListServlet" , urlPatterns = {"/CEA101G2/front-store-end/foodlist/select_page.jsp"})
 * Servlet implementation class Food_ListServlet
 */
//@WebServlet("/Food_ListServlet")
public class Store_MemServlet extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String store_no = req.getParameter("store_no");
				if (store_no == null || (store_no.trim()).length() == 0) {
					errorMsgs.add("�п�J���a�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
		
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-store-end/foodlist/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
				
			
				/***************************2.�}�l�d�߸��*****************************************/
				Store_MemService store_MemSvc = new Store_MemService();
				Store_MemVO store_MemVO = store_MemSvc.getOneStore_Mem(store_no);
				if (store_MemVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("store_MemVO", store_MemVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-store-end/store/listOneStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
			
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String store_no = req.getParameter("store_no");
				
				/***************************2.�}�l�d�߸��****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
				Store_MemService store_MemSvc = new Store_MemService();
				Store_MemVO store_MemVO = store_MemSvc.getOneStore_Mem(store_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("store_MemVO", store_MemVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-store-end/store/update_Store_Mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/listAllStore_Mem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String store_no = req.getParameter("store_no");
				String store_acct = req.getParameter("store_acct");
				String store_acctReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (store_acct == null || store_acct.trim().length() == 0) {
					errorMsgs.add("���a�b��: �ФŪť�");
				} else if(!store_acct.trim().matches(store_acctReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���a�b��: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String store_pwd = req.getParameter("store_pwd").trim();
				if (store_pwd == null || store_pwd.trim().length() == 0) {
					errorMsgs.add("���a�K�X�ФŪť�");
				}
				
				String store_name = req.getParameter("store_name").trim();
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.add("���a�W�ٽФŪť�");
				}
				
				
				
				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("�a�}�W�ٽФŪť�");
				}
				
				String open_dates = req.getParameter("open_dates").trim();
				if (open_dates == null || open_dates.trim().length() == 0) {
					errorMsgs.add("��~�ɶ��ФŪť�");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("Email�ФŪť�");
				}
				
				
				String s_category = req.getParameter("s_category").trim();
				if (s_category == null || s_category.trim().length() == 0) {
					errorMsgs.add("�����ФŪť�");
				}
				
				
				String store_info = req.getParameter("store_info").trim();
				if (store_info == null || store_info.trim().length() == 0) {
					errorMsgs.add("���a²���ФŪť�");
				}
				
				
				


				Integer upload_status = null;
				try {
					upload_status = new Integer(req.getParameter("upload_status").trim());
				} catch (NumberFormatException e) {
					upload_status = 0;
					errorMsgs.add("�W�[���A�ж�Ʀr.");
				}
				
				Integer s_permission = null;
				try {
					s_permission = new Integer(req.getParameter("s_permission").trim());
				} catch (NumberFormatException e) {
					s_permission = 0;
					errorMsgs.add("�v�����A�ж�Ʀr.");
				}
				
				Integer sum_grade = null;
				try {
					sum_grade = new Integer(req.getParameter("sum_grade").trim());
				} catch (NumberFormatException e) {
					sum_grade = 0;
					errorMsgs.add("�ֿn���|������Ʀr.");
				}
				
				Integer blocked = null;
				try {
					blocked = new Integer(req.getParameter("blocked").trim());
				} catch (NumberFormatException e) {
					blocked = 0;
					errorMsgs.add("�Q���|���ƽж�Ʀr.");
				}
				
				Double star_total = null;
				try {
					star_total = new Double(req.getParameter("star_total").trim());
				} catch (NumberFormatException e) {
					star_total = 0.0;
					errorMsgs.add("�ֿn���|������Ʀr.");
				}
				
				Integer star_times = null;
				try {
					star_times = new Integer(req.getParameter("star_times").trim());
				} catch (NumberFormatException e) {
					star_times = 0;
					errorMsgs.add("�ֿn�`�������ƽж�Ʀr.");
				}
				
				Integer table_limit = null;
				try {
					table_limit = new Integer(req.getParameter("table_limit").trim());
				} catch (NumberFormatException e) {
					table_limit = 0;
					errorMsgs.add("���W���ж�Ʀr.");
				}
				
				Store_MemVO store_MemVO = new Store_MemVO();
				store_MemVO.setStore_no(store_no);
				store_MemVO.setStore_acct(store_acct);
				store_MemVO.setStore_pwd(store_pwd);
				store_MemVO.setStore_name(store_name);
				store_MemVO.setAddr(addr);
				store_MemVO.setOpen_dates(open_dates);
				store_MemVO.setEmail(email);
				store_MemVO.setS_category(s_category);
				store_MemVO.setStore_info(store_info);
				store_MemVO.setUpload_status(upload_status);
				store_MemVO.setS_permission(s_permission);
				store_MemVO.setSum_grade(sum_grade);
				store_MemVO.setBlocked(blocked);
				store_MemVO.setStar_total(star_total);
				store_MemVO.setStar_times(star_times);
				store_MemVO.setTable_limit(table_limit);
//				store_MemVO.setRest_img(rest_img);	
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("store_MemVO", store_MemVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/update_Store_Mem_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
//				/***************************2.�}�l�ק���*****************************************/

				Store_MemService store_MemSvc = new Store_MemService();
				store_MemVO = store_MemSvc.update_Store_Mem_input(store_no,store_acct,store_pwd, store_name,addr, open_dates, email, s_category,store_info,upload_status,s_permission,sum_grade,blocked,star_total,star_times,table_limit);
				              
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("store_MemVO", store_MemVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-store-end/store/listOneStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/update_Store_Mem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
//        	System.out.println(req.getParameter("store_no"));
     
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			//(\u4e00-\u9fa5)�u�त��
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String store_acct = req.getParameter("store_acct");
				String store_noReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (store_acct == null || store_acct.trim().length() == 0) {
					errorMsgs.add("���a�b��: �ФŪť�");
				} else if(!store_acct.trim().matches(store_noReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���a�b��: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String store_pwd = req.getParameter("store_pwd").trim();
				if (store_pwd == null || store_pwd.trim().length() == 0) {
					errorMsgs.add("���a�K�X�ФŪť�");
				}
				
				String store_name = req.getParameter("store_name").trim();
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.add("���a�W�ٽФŪť�");
				}
				
				
				
				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("�a�}�W�ٽФŪť�");
				}
				
				String open_dates = req.getParameter("open_dates").trim();
				if (open_dates == null || open_dates.trim().length() == 0) {
					errorMsgs.add("��~�ɶ��ФŪť�");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("Email�ФŪť�");
				}
				
				
				String s_category = req.getParameter("s_category").trim();
				if (s_category == null || s_category.trim().length() == 0) {
					errorMsgs.add("�����ФŪť�");
				}
				
				
				String store_info = req.getParameter("store_info").trim();
				if (store_info == null || store_info.trim().length() == 0) {
					errorMsgs.add("���a²���ФŪť�");
				}
				
				
				Integer upload_status = null;
				try {
					upload_status = new Integer(req.getParameter("upload_status").trim());
				} catch (NumberFormatException e) {
					upload_status = 0;
					errorMsgs.add("�W�[���A�ж�Ʀr.");
				}
				
				Integer s_permission = null;
				try {
					s_permission = new Integer(req.getParameter("s_permission").trim());
				} catch (NumberFormatException e) {
					s_permission = 0;
					errorMsgs.add("�v�����A�ж�Ʀr.");
				}
				
				Integer sum_grade = null;
				try {
					sum_grade = new Integer(req.getParameter("sum_grade").trim());
				} catch (NumberFormatException e) {
					sum_grade = 0;
					errorMsgs.add("�ֿn���|������Ʀr.");
				}
				
				Integer blocked = null;
				try {
					blocked = new Integer(req.getParameter("blocked").trim());
				} catch (NumberFormatException e) {
					blocked = 0;
					errorMsgs.add("�Q���|���ƽж�Ʀr.");
				}
				
				Double star_total = null;
				try {
					star_total = new Double(req.getParameter("star_total").trim());
				} catch (NumberFormatException e) {
					star_total = 0.0;
					errorMsgs.add("�ֿn���|������Ʀr.");
				}
				
				Integer star_times = null;
				try {
					star_times = new Integer(req.getParameter("star_times").trim());
				} catch (NumberFormatException e) {
					star_times = 0;
					errorMsgs.add("�ֿn�`�������ƽж�Ʀr.");
				}
				
				Integer table_limit = null;
				try {
					table_limit = new Integer(req.getParameter("table_limit").trim());
				} catch (NumberFormatException e) {
					table_limit = 0;
					errorMsgs.add("���W���ж�Ʀr.");
				}
								
				
				byte rest_img2[] = null;
				try {					
					InputStream in =req.getPart("rest_img").getInputStream();
					rest_img2= new byte[in.available()];
					in.read(rest_img2);
					in.close();					
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("�W�ǹϤ�����");
					errorMsgs.add("�S���Ϥ�");
				}
				
				
				 
				
				Store_MemVO  store_MemVO  = new Store_MemVO();
				
				store_MemVO.setStore_acct(store_acct);
				store_MemVO.setStore_pwd(store_pwd);
				store_MemVO.setStore_name(store_name);
				store_MemVO.setAddr(addr);
				store_MemVO.setOpen_dates(open_dates);
				store_MemVO.setEmail(email);
				store_MemVO.setS_category(s_category);
				store_MemVO.setStore_info(store_info);
				store_MemVO.setUpload_status(upload_status);
				store_MemVO.setS_permission(s_permission);
				store_MemVO.setSum_grade(sum_grade);
				store_MemVO.setBlocked(blocked);
				store_MemVO.setStar_total(star_total);
				store_MemVO.setStar_times(star_times);
				store_MemVO.setTable_limit(table_limit);
				store_MemVO.setRest_img(rest_img2);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("store_MemVO", store_MemVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/addStore_Mem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				
				Store_MemService store_MemSvc = new Store_MemService();
				store_MemVO = store_MemSvc.addStore_Mem(store_acct,store_pwd, store_name,addr, open_dates, email, s_category,store_info,upload_status,s_permission,sum_grade,blocked,star_total,star_times,table_limit,rest_img2);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-store-end/store/listAllStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/addStore_Mem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				
				String store_no = req.getParameter("store_no");
				
				/***************************2.�}�l�R�����***************************************/
				Store_MemService store_MemSvc = new Store_MemService();
				store_MemSvc.deleteStore_Mem(store_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-store-end/store/listAllStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/listAllStore_Mem.jsp");
				failureView.forward(req, res);
				}
		}
			if("getOneImage".equals(action)) {
				res.setContentType("image/gif");
				ServletOutputStream out = res.getOutputStream();
			
			try {
			String store_no = req.getParameter("store_no");
			Store_MemService store_MemService = new Store_MemService();
			Store_MemVO store_MemVO = new Store_MemVO();
			store_MemVO = store_MemService.getOneStore_Mem(store_no);
			byte[] pic = store_MemVO.getRest_img();

			out.write(pic);

			
		}			
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		
		
		
		
		
		
		
			}
		}

	}
	
  }

