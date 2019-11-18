package controller;

import java.io.IOException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MailService;
import bean.MessageView;
import bean.User;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userlogin");
		String msg = (String) session.getAttribute("msg");
		int id = 0;
		String folder = "";
		if (request.getParameter("idInbox") != null) {
			id = Integer.parseInt(request.getParameter("idInbox"));
			folder = "INBOX";
		}
		if (request.getParameter("idSent") != null) {
			id = Integer.parseInt(request.getParameter("idSent"));
			folder = "[Gmail]/Sent Mail";
		}
		if (msg != null) {
			request.setAttribute("msg", msg);
			session.removeAttribute("msg");
		}
		try {
			MailService mailService = new MailService();
			mailService.login("imap.gmail.com", userLogin);
			mailService.setFolder(folder);
			request.setAttribute("mailService", mailService);

			Message message = mailService.getMessage(id);

			String subject = "";
			String from = "";
			String to = "";
			String cc = "";
			String bcc = "";
			String content = "";
			Date date = null;
			if (message.getSubject() != null)
				subject = message.getSubject().toString();
			from = message.getFrom()[0].toString();
			InternetAddress[] toAddress = (InternetAddress[]) message.getRecipients(Message.RecipientType.TO);
			for (InternetAddress internetAddress : toAddress) {
				to += internetAddress.toString() + ", ";
			}
			if (message.getRecipients(Message.RecipientType.CC) != null) {
				InternetAddress[] ccAddress = (InternetAddress[]) message.getRecipients(Message.RecipientType.CC);
				for (InternetAddress i : ccAddress) {
					cc += i.toString() + ", ";
				}
			}
			if (message.getRecipients(Message.RecipientType.BCC) != null) {
				InternetAddress[] bccAddress = (InternetAddress[]) message.getRecipients(Message.RecipientType.BCC);
				for (InternetAddress i : bccAddress) {
					bcc += i.toString() + ", ";
				}
			}
			content = mailService.getText(message);
			date = message.getReceivedDate();
			MessageView obj = new MessageView(message.getMessageNumber(), subject, from, to, cc, bcc, content, date,
					true);
			request.setAttribute("msgRead", obj);
			request.setAttribute("content", message.getContentType());
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/inbox");
			e.printStackTrace();
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
