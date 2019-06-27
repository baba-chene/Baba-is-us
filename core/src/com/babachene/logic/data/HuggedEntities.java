package com.babachene.logic.data;

import java.util.LinkedList;

import com.babachene.logic.data.entities.Entity;

public class HuggedEntities {

	private LevelMap map;
	private LinkedList<Integer> xHugged;
	private LinkedList<Integer> yHugged;
	private LinkedList<Entity> entities;
	private boolean isWin;
	private int amount;
	private int amountEntity;


public HuggedEntities(LevelMap map) {
	this.map = map;
	entities = new LinkedList<Entity>();
	amount = 0;
	amountEntity = 0;
	xHugged = new LinkedList<Integer>();
	yHugged = new LinkedList<Integer>();
	isWin = false;
}


public void add(Entity entity)
{
	entities.add(entity);
	amountEntity ++;
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
	this.entities.clear();
	amountEntity =0;
}

public boolean isWin() {
	return isWin;
}

public void setSink(boolean isSink) {
	for (int i = amountEntity-1; i>-1;i--) {
		Entity entity = entities.get(i);
		entity.setSink(true);
		this.map.getMapCase(entity.getxPosition(),entity.getyPosition()).updateIsSink();

	}
}

public void setWin(boolean isWin) {
	this.isWin = isWin;
}

public int getSize() {
	return amount;
}
}