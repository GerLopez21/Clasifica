/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grupo11.ProyectoEgg.Controlador;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Americo
 */
public class Controlador {/*
    public void error(ModelAndView model, Exception e) {
		model.addObject(Texts.ERROR, "Ocurrio un error inesperado mientras se ejecutaba la acción.");
		log.error("Error inesperado", e);
	}

	public void error(ModelAndView model, WebException e) {
		model.addObject(Texts.ERROR, e.getMessage());
	}

	public void error(ModelMap modelo, BindingResult resultado) {
		StringBuilder msg = new StringBuilder();
		for (ObjectError o : resultado.getAllErrors()) {
			msg.append(o.getDefaultMessage() + System.getProperty("line.separator"));
		}
		log.info("Error: " + msg.toString());
		modelo.addAttribute(Texts.ERROR, msg.toString());
	}

	public void error(ModelAndView model, String e) {
		model.addObject(Texts.ERROR, e);
		model.setViewName(formView);
	}

	public void error(Model model, String e) {
		model.addAttribute(Texts.ERROR, e);
	}*/

}
