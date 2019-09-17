package net.tou.mantri.onlineshopping.exception;

/*import java.io.PrintWriter;
import java.io.StringWriter;*/

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The Page is not contructed!");
		mv.addObject("errorDescription", "The Page you are looking for is not available now!");
		mv.addObject("title", "404");
		return mv;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not available!");
		mv.addObject("errorDescription", "The Product you are looking for is not available now!");
		mv.addObject("title", "Produtc Not Available");
		return mv;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e) {

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact Your administrator");
		
		
		/*only for debugging purpose
		
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		
		e.printStackTrace(pw);
		
		mv.addObject("errorDescription", sw.toString());*/
		
		mv.addObject("errorDescription", e.toString());
		mv.addObject("title", "Error!");
		return mv;
	}
}
