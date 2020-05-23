package com.b2w.starwars.model;

public class PlanetaResultApi {
	
	private String   name;
    private String[] films;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getFilms() {
        return films;
    }
    public void setFilms(String[] films) {
        this.films = films;
    }
    public int getContadorFilmes() {
        return films == null ? 0 : films.length;
    }
}
