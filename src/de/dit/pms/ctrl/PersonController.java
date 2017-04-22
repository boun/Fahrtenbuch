package de.dit.pms.ctrl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.dit.pms.model.Person;
import de.dit.pms.service.PersonService;

@Controller
public class PersonController {
	private static Logger log = Logger.getLogger(PersonController.class);
	@Autowired
	private PersonService personDao = null; //warum gleich null?

    @RequestMapping(value="/EditPerson.html", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required=false) Integer id) {
    	log.debug("EditPerson.html");
    	ModelAndView mv = new ModelAndView();
    	if (id == null) {
    		mv.addObject(new Person());
    	} else { 
    		mv.addObject(personDao.findById(id));
    	}
    	mv.setViewName("edit-person");
    	return mv;
    }
    
    /**
     * <p>Saves a person.</p>
     * 
     * <p>Expected HTTP POST and request '/SavePerson.html'.</p>
     * @return 
     */
    @RequestMapping(value="/SavePerson.html", method=RequestMethod.POST)
    public String save(Person person, Model model) {
        personDao.save(person);
        return "redirect:AllePersonen.html";
    }

    /**
     * <p>Deletes a person.</p>
     * 
     * <p>Expected HTTP POST and request '/LoeschePerson.html'.</p>
     */
    @RequestMapping(value="/LoeschePerson.html", method=RequestMethod.GET)
    public String delete(Person person) {
        personDao.delete(person);
        return "redirect:AllePersonen.html";
    }

    /**
     * <p>Searches for all persons and returns them in a 
     * <code>Collection</code>.</p>
     * 
     * <p>Expected HTTP GET and request '/AllePersonen.html'.</p>
     */
    @RequestMapping(value="/AllePersonen.html", method=RequestMethod.GET)
    public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Vereinsmitglieder");
		mv.addObject("message", "Alle Mitglieder des Vereins");
		mv.addObject("personen", personDao.findAll());
		mv.setViewName("list-personen");
		return mv;
    }

}