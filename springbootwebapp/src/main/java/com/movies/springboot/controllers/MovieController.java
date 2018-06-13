package com.movies.springboot.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.movies.springboot.domain.Movies;
import com.movies.springboot.services.MovieService;

@Controller
public class MovieController {

	private Logger log = Logger.getLogger(MovieController.class);
	
	 @Autowired
    private MovieService movieService;


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("movies", movieService.listAllMovies());
        log.info("List of movies");
        return "movies";
    }

    @RequestMapping("movie/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("movie", movieService.getMovieById(id));
        log.info("edit movies");
        return "movieformedit";
    }

    @RequestMapping("movie/new")
    public String newMovie(Model model){
        model.addAttribute("movie", new Movies());
        log.info("new movies");
        return "movieform";
    }

    @RequestMapping(value = "movie", method = RequestMethod.POST)
    public String saveMovie(Movies movies){
    	movieService.saveMovie(movies);
    	log.info("save movies");
    	 return "redirect:/movies";
    }
    
    
    @RequestMapping(value = "movie/edit", method = RequestMethod.POST)
    public String editMovie(Movies movies){
    	movieService.editMovie(movies);
    	 return "redirect:/movies";
    }

    @RequestMapping("movie/delete/{id}")
    public String deleteMovie(@PathVariable Integer id){
    	log.info("delete movies");
    	movieService.deleteMovie(id);
        return "redirect:/movies";
    }

}
