package com.movies.springboot.services;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.movies.springboot.domain.Movies;
import com.movies.springboot.util.Util;

@Service
public class MovieServiceImpl implements MovieService {
	private Logger log = Logger.getLogger(MovieServiceImpl.class);
	
	@Autowired
	private Util util;

	@Override
	public List<Movies> listAllMovies() {
		final String methodName = "listAllMovies()----->";
		List<Movies> prodList = null;
		try {
			prodList = util.readJson();
		} catch (JsonParseException e) {
			log.error(methodName+"JsonParseException: " , e);
		} catch (JsonMappingException e) {
			log.error(methodName+"JsonMappingException: " , e);
		} catch (IOException e) {
			log.error(methodName+"IOException: " , e);
		}catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}
		return prodList;
	}

	@Override
	public Movies getMovieById(String id) {
		final String methodName = "getMovieById()----->";
		List<Movies> prodList = null;
		Movies mr = null;
		try {
			prodList = util.readJson();
		    mr = prodList.get(Integer.parseInt(id));
		} catch (JsonParseException e) {
			log.error(methodName+"JsonParseException: " , e);
		} catch (JsonMappingException e) {
			log.error(methodName+"JsonMappingException: " , e);
		} catch (IOException e) {
			log.error(methodName+"IOException: " , e);
		}catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}
		
		return mr;
	}

	@Override
	public void saveMovie(Movies movie) {
		final String methodName = "saveMovie()----->";
		try {
			util.createJson(movie);
		} catch (JsonParseException e) {
			log.error(methodName+"JsonParseException: " , e);
		} catch (JsonMappingException e) {
			log.error(methodName+"JsonMappingException: " , e);
		} catch (IOException e) {
			log.error("IOException: " , e);
		}catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}
	}

	@Override
	public void deleteMovie(Integer id) {
		final String methodName = "deleteMovie()----->";
		try {
			util.deleteJson(id);
		} catch (JsonParseException e) {
			log.error(methodName+"JsonParseException: " , e);
		} catch (JsonMappingException e) {
			log.error(methodName+"JsonMappingException: " , e);
		} catch (IOException e) {
			log.error(methodName+"IOException: " , e);
		}catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}
	}

	@Override
	public void editMovie(Movies movie) {
		final String methodName = "editMovie()----->";
		try {
			util.concatJson(movie);
		} catch (JsonParseException e) {
			log.error(methodName+"JsonParseException: " , e);
		} catch (JsonMappingException e) {
			log.error(methodName+"JsonMappingException: " , e);
		} catch (IOException e) {
			log.error(methodName+"IOException: " , e);
		}catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}
	}
}
