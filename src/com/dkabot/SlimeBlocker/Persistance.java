package com.dkabot.SlimeBlocker;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;

@Entity()
@Table(name = "SlimeBlocker")
public class Persistance {

    @Id
    private int id;
    @NotEmpty
    private String chunk;
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}
}