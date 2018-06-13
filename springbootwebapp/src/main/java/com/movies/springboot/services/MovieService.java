package com.movies.springboot.services;


import java.util.List;

import com.movies.springboot.domain.Movies;

public interface MovieService {
    List<Movies> listAllMovies();

    Movies getMovieById(String id);

    void saveMovie(Movies movie);
    
    void editMovie(Movies movie);

    void deleteMovie(Integer id);
}
