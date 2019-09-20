package com.abc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;   
import java.awt.Font;   
import java.awt.Graphics2D;   
import java.awt.image.BufferedImage;   
import java.util.Random;   

import javax.imageio.ImageIO;   
import javax.servlet.ServletException;   
import javax.servlet.ServletOutputStream;   
import javax.servlet.http.HttpServlet;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
import javax.servlet.http.HttpSession;

import net.javaguides.usermanagement.dao.UserDAO;   

/**
 * Servlet implementation class provecode
 */
@WebServlet("/provecode")
public class provecode extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = -5813134629255375160L;
    private int width = 65;   
    private int height = 20;   
    private int codeCount = 4;   
    private int xx = 0;   
    private int fontHeight;   
    private int codeY;   
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',   
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',   
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public provecode() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
        String strWidth = this.getInitParameter("width");   

        String strHeight = this.getInitParameter("height");   

        String strCodeCount = this.getInitParameter("codeCount");   


        try {   
            if (strWidth != null && strWidth.length() != 0) {   
                width = Integer.parseInt(strWidth);   
            }   
            if (strHeight != null && strHeight.length() != 0) {   
                height = Integer.parseInt(strHeight);   
            }   
            if (strCodeCount != null && strCodeCount.length() != 0) {   
                codeCount = Integer.parseInt(strCodeCount);   
            }   
        } catch (NumberFormatException e) {
         e.printStackTrace();
        }   

        xx = width / (codeCount + 1);   
        fontHeight = height - 2;   
        codeY = height - 4;   

  	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		   
		BufferedImage buffImg = new BufferedImage(width, height,   
		BufferedImage.TYPE_INT_RGB);   
	    Graphics2D gd = buffImg.createGraphics();   
		Random random = new Random();   
		gd.setColor(Color.WHITE);   
		gd.fillRect(0, 0, width, height);   

		        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);   

		        gd.setFont(font);   


		        gd.setColor(Color.BLACK);   
		        gd.drawRect(0, 0, width - 1, height - 1);

		        gd.setColor(Color.GRAY);   
		        for (int i = 0; i < 3; i++) {   
		            int x = random.nextInt(width);   
		            int y = random.nextInt(height);   
		            int xl = random.nextInt(12);   
		            int yl = random.nextInt(12);   
		            gd.drawLine(x, y, x + xl, y + yl);   
		        }
	
		        StringBuffer randomCode = new StringBuffer();   
		        int red = 0, green = 0, blue = 0;   

		        for (int i = 0; i < codeCount; i++) {   

		            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);   
	  
		            red = random.nextInt(255);   
		            green = random.nextInt(255);   
		            blue = random.nextInt(255);   

		            gd.setColor(new Color(red, green, blue));   
		            gd.drawString(strRand, (i*xx)+xx/2, codeY);   

		            randomCode.append(strRand);   
		        }   
 
		        HttpSession session = request.getSession();   
		        session.setAttribute("validateCode", randomCode.toString());   

		        response.setHeader("Pragma", "no-cache");   
		        response.setHeader("Cache-Control", "no-cache");   
		        response.setDateHeader("Expires", 0);   

		        response.setContentType("image/jpeg");   

		        ServletOutputStream sos = response.getOutputStream();   
		        ImageIO.write(buffImg, "jpeg", sos);   
		        sos.close();   
		    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
