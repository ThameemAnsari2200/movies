package com.movies.springboot.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.springboot.domain.Movies;

@Component
public class Util {
	
	private Logger log = Logger.getLogger(Util.class);
	
	private final String JSON_FILE = "src/main/resources/data/movies.json";

	public List<Movies> readJson() throws JsonParseException, JsonMappingException, IOException {
		final String methodName = "readJson()----->";
		List<Movies> prodList = null;
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream input = new FileInputStream(JSON_FILE);
		prodList = objectMapper.readValue(input, new TypeReference<List<Movies>>() {
		});
		} catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}
		return prodList;
	}

	public void createJson(Movies movie) throws JsonParseException, JsonMappingException, IOException {
		final String methodName = "createJson()----->";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Movies> prodList = readJson();
			Movies mr = new Movies();
			mr.setDirector(movie.getDirector());
			mr.setReleaseDate(movie.getReleaseDate());
			mr.setType(movie.getType());
			mr.setTitle(movie.getTitle());
			prodList.add(mr);
			objectMapper.writeValue(new FileOutputStream(JSON_FILE), prodList);
		} catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}

	}

	public void concatJson(Movies movie) throws JsonParseException, JsonMappingException, IOException {
		final String methodName = "concatJson()----->";
		try {
			List<Movies> prodList = readJson();
			ObjectMapper objectMapper = new ObjectMapper();
			for (Iterator<Movies> iter = prodList.listIterator(); iter.hasNext();) {
				Movies mov = iter.next();
				if (mov.getTitle().equalsIgnoreCase(movie.getTitle())
						|| mov.getDirector().equalsIgnoreCase(movie.getDirector())
						|| mov.getReleaseDate().equalsIgnoreCase(movie.getReleaseDate())
						|| mov.getType().equalsIgnoreCase(movie.getType())) {
					iter.remove();
				}
			}
			prodList.add(movie);
			objectMapper.writeValue(new FileOutputStream(JSON_FILE), prodList);
		} catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}

	}

	public void deleteJson(Integer id) throws JsonParseException, JsonMappingException, IOException {
		final String methodName = "deleteJson()----->";
		try {
			List<Movies> prodList = readJson();
			ObjectMapper objectMapper = new ObjectMapper();
			prodList.remove(id.intValue());
			objectMapper.writeValue(new FileOutputStream(JSON_FILE), prodList);
		} catch (Exception e) {
			log.error(methodName+"Exception: " , e);
		}

	}
}
