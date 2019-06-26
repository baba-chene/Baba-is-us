package com.babachene.logic.data;

import java.util.LinkedList;

import com.babachene.logic.data.entities.Entity;

public class HuggedEntities {

	private LinkedList<Integer> xHugged;
	private LinkedList<Integer> yHugged;
	private LinkedList<Entity> entities;
	private boolean isWin;
	private int amount;


public HuggedEntities() {
	entities = new LinkedList<Entity>();
	amount = 0;
	xHugged = new LinkedList<Integer>();
	yHugged = new LinkedList<Integer>();
	isWin = false;
}


public void add(Entity entity)
{
	entities.add(entity);
}

public LinkedList<Entity> getEntities(){
	return entities;
}
public void add(Integer x,Integer y)
{
	this.xHugged.add(x);
	this.yHugged.add(y);
	amount++;
}



public LinkedList<Integer> getxHugged() {
	return xHugged;
}

public LinkedList<Integer> getyHugged() {
	return yHugged;
}

public void clear()
{
	amount =0;
	this.xHugged.clear();
	this.yHugged.clear();
}

public boolean isWin() {
	return isWin;
}

public void setSink(boolean isSink) {
	for (int i =0; i< this.amount; i++) {
		
	}
}

public void setWin(boolean isWin) {
	this.isWin = isWin;
}

public int getSize() {
	return amount;
}
}