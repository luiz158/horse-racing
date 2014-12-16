package com.intenthq.horseracing.core;

import com.google.common.base.Preconditions;

/**
 * Horse name holder.
 */
public class Horse {
	
	private final String name;

	public Horse(String name) {
		this.name = Preconditions.checkNotNull(name);
	}

	public String getName() {
		return this.name;
	}

}
