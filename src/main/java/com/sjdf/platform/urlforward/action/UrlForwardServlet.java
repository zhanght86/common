package com.sjdf.platform.urlforward.action;

import com.sjdf.platform.urlforward.service.UrlForwardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.IDN;

/**
 * Servlet implementation class UrlForwardServlet
 */
@WebServlet("/UrlForwardServlet")
/**
 *
 * @category 通过域名重定向到转发地址
 * @ClassName UrlForwardServlet
 * @author laberwu
 * @Created 2012 2012-10-8 下午1:30:58
 */
public class UrlForwardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UrlForwardServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String domain = request.getServerName();
        // 域名转换为 IDN 域名
        domain = IDN.toASCII(domain);

        // 从数据库中得到需要转发的url地址
        UrlForwardServiceImpl urlForwardService = new UrlForwardServiceImpl();
        String url = urlForwardService.findUrlByDomain(domain);
        // 解码，得到显示域名
        String showDomain = IDN.toUnicode(domain);
        if (url != null && !url.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("<TITLE>").append(showDomain).append("</TITLE>")
                    .append("<frameset rows='100%' border='0' frameborder='0' framespacing='0'>")
                    .append("<frame src='").append(url).append("' ").append("scrolling='auto'></frameset>");
            out.print(builder);
        } else {
            response.sendRedirect("urlforward.html");
        }
    }

}
