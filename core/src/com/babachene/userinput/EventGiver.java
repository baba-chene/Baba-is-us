package com.babachene.userinput;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.babachene.cliserv.InputEvent;

/**
 * This object stores event from user input in a queue. Use <code>
 * pollEvent()</code> to get the first event of the queue. The type
 * of the event is {@link InputEvent}.
 * <p>
 * To link this class with the low-level input processing, construct
 * a new LevelInputProcessor with it: <br> <code>new LevelInputProcessor(eventGiver)</code>
 * <br>And manage the created InputProcessor.
 * @author jeremy
 *
 */
public class EventGiver {
	
	public static final int CAPACITY = 256;
	
	private Queue<InputEvent> queue;
	
	
	
	
	public EventGiver() {
		queue = new ArrayBlockingQueue<InputEvent>(CAPACITY);
	}
	
	/**
	 * Return the number of InputEvents that are yet to
	 * be polled.
	 * @return The size of this buffer.
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * The capacity of this Buffer. Any additional InputEvent
	 * are dropped if the queue is full.
	 * @return
	 */
	public final int capacity() {
		return CAPACITY;
	}
	
	protected boolean addEvent(InputEvent e) {
		return queue.offer(e);
	}
	
	/**
	 * Poll an Event. It is removed from this EventGiver and
	 * returned.
	 * @return The first InputEvent of this queue, or <code>null</code>
	 * if it's empty.
	 */
	public InputEvent pollEvent() {
		return queue.poll();
	}
	
	/**
	 * Empty the buffer of this EventGiver, making it
	 * empty. This method reallocate a new queue so don't
	 * use it too often.
	 */
	public void emptyBuffer() {
		queue = new ArrayBlockingQueue<InputEvent>(CAPACITY);
	}

}
